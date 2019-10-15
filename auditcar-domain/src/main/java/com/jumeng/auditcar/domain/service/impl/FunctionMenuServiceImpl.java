package com.jumeng.auditcar.domain.service.impl;


import com.jumeng.auditcar.domain.FunctionMenu;
import com.jumeng.auditcar.domain.dao.FunctionMenuDao;
import com.jumeng.auditcar.domain.form.param.FunctionMenuParam;
import com.jumeng.auditcar.domain.service.FunctionMenuService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName FunctionMenuServiceImpl
 * @Description TODO
 * @Author 46330
 * @Date 2019/9/11 10:56
 * @Version 1.0
 **/
@Service
public class FunctionMenuServiceImpl implements FunctionMenuService {

    @Autowired
    private FunctionMenuDao functionMenuDao;

    @Override
    public FunctionMenu saveFunctionMenu(FunctionMenu functionMenu) {
        functionMenu = functionMenuDao.save(functionMenu);
        functionMenu.setFullPath(functionMenu.getId());
        functionMenu.setFullName(functionMenu.getName());
        FunctionMenu baseFunctionMenu = functionMenuDao.findById(functionMenu.getId()).orElse(null);
        if(baseFunctionMenu != null){
            fillFunctionMenu(baseFunctionMenu);
        }
        functionMenuDao.save(baseFunctionMenu);
        return functionMenu;
    }

    @Override
    public FunctionMenu updateFunctionMenu(FunctionMenu functionMenu) {
        fillFunctionMenu(functionMenu);
        functionMenuDao.save(functionMenu);
        List<FunctionMenu> childrenFunctionMenuList = functionMenuDao.getChildrenFunctionMenuListByFullPath(functionMenu.getFullPath());
        if (childrenFunctionMenuList != null) {
            for (int i = 0; i < childrenFunctionMenuList.size(); i ++) {
                FunctionMenu childrenCategory = childrenFunctionMenuList.get(i);
                fillFunctionMenu(childrenCategory);
                functionMenuDao.save(childrenCategory);
                if(i % 20 == 0) {
                    functionMenuDao.flush();
                }
            }
        }
        return functionMenu;
    }

    @Override
    public FunctionMenu findFunctionMenuById(String functionMenuId) {
        return functionMenuDao.findById(functionMenuId).orElse(null);
    }

    @Override
    public List<FunctionMenu> findFunctionMenuListByPidAndFtypeAndFto(String pid, String ftype, String fto) {
        return functionMenuDao.findFunctionMenuByPidAndAndFtypeAndAndFto(pid, ftype, fto);
    }

    @Override
    public List<Map<String, Object>> findFunctionMenuTreeByPidAndFtypeAndFto(String pid, String ftype, String fto) {
        List<FunctionMenu> list = findFunctionMenuListByPidAndFtypeAndFto(pid, ftype, fto);
        List<Map<String, Object>> resultList = new ArrayList<>();
        if(list != null){
            for (FunctionMenu functionMenu : list) {
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("id", functionMenu.getId());
                map.put("pid", functionMenu.getPid());
                map.put("name", functionMenu.getName());
                map.put("role", functionMenu.getRole());
                map.put("level", functionMenu.getLevel());
                List<Map<String, Object>> childrenMapList = new ArrayList<>();
                List<FunctionMenu> childrenList = findFunctionMenuListByPidAndFtypeAndFto(functionMenu.getId(), ftype, fto);
                for (FunctionMenu childrenFunctionMenu : childrenList) {
                    Map<String, Object> childrenMap = new HashMap<String, Object>();
                    childrenMap.put("id", childrenFunctionMenu.getId());
                    childrenMap.put("pid", functionMenu.getId());
                    childrenMap.put("name", childrenFunctionMenu.getName());
                    childrenMap.put("role", childrenFunctionMenu.getRole());
                    childrenMap.put("level", childrenFunctionMenu.getLevel());
                    List<Map<String, Object>> grandsonMapList = new ArrayList<>();
                    List<FunctionMenu> grandsonList = findFunctionMenuListByPidAndFtypeAndFto(childrenFunctionMenu.getId(), ftype, fto);
                    for (FunctionMenu grandsonFunctionMenu : grandsonList) {
                        Map<String, Object> grandsonMap = new HashMap<String, Object>();
                        grandsonMap.put("id", grandsonFunctionMenu.getId());
                        grandsonMap.put("pid", childrenFunctionMenu.getId());
                        grandsonMap.put("name", grandsonFunctionMenu.getName());
                        grandsonMap.put("role", grandsonFunctionMenu.getRole());
                        grandsonMap.put("level", grandsonFunctionMenu.getLevel());
                        grandsonMapList.add(grandsonMap);
                    }
                    childrenMap.put("children", grandsonMapList);
                    childrenMapList.add(childrenMap);
                }
                map.put("children", childrenMapList);
                resultList.add(map);
            }
        }
        return resultList;
    }

    @Override
    public void deleteFunctionMenu(String functionMenuId) {
        functionMenuDao.findById(functionMenuId).ifPresent(functionMenuDao::delete);
    }

    @Override
    public Page<FunctionMenu> findFunctionMenuPage(FunctionMenuParam functionMenuParam) {
        return functionMenuDao.findAll(functionMenuParam);
    }

    private void fillFunctionMenu(FunctionMenu functionMenu) {
        FunctionMenu parent = null;
        if (StringUtils.isNotBlank(functionMenu.getPid())) {
            parent = functionMenuDao.findById(functionMenu.getPid()).orElse(null);
        }
        if (parent == null) {
            functionMenu.setFullPath(functionMenu.getId());
            functionMenu.setFullName(functionMenu.getName());
        } else {
            functionMenu.setFullPath(parent.getFullPath() + FunctionMenu.PATH_SEPARATOR + functionMenu.getId());
            functionMenu.setFullName(parent.getFullName() + FunctionMenu.NAME_SEPARATOR + functionMenu.getName());
        }
        functionMenu.setLevel(functionMenu.getFullPath().split(FunctionMenu.PATH_SEPARATOR).length - 1);
    }
}
