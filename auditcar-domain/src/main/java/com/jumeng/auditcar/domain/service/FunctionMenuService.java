package com.jumeng.auditcar.domain.service;


import com.jumeng.auditcar.domain.FunctionMenu;
import com.jumeng.auditcar.domain.form.param.FunctionMenuParam;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.Map;

public interface FunctionMenuService {
    FunctionMenu findFunctionMenuById(String functionMenuId);

    List<FunctionMenu> findFunctionMenuListByPidAndFtypeAndFto(String pid, String ftype, String fto);

    List<Map<String, Object>> findFunctionMenuTreeByPidAndFtypeAndFto(String pid, String ftype, String fto);

    void deleteFunctionMenu(String functionMenuId);

    FunctionMenu saveFunctionMenu(FunctionMenu functionMenu);

    FunctionMenu updateFunctionMenu(FunctionMenu functionMenu);

    Page<FunctionMenu> findFunctionMenuPage(FunctionMenuParam functionMenuParam);
}
