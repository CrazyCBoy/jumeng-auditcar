package com.jumeng.auditcar.domain.dao;


import com.jumeng.auditcar.domain.FunctionMenu;
import com.jumeng.auditcar.domain.reposiotry.BaseRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FunctionMenuDao extends BaseRepository<FunctionMenu, String> {

   @Query(value = "select * from function_menu where if(:pid != '', pid = :pid, pid is null) and if(:ftype != '', ftype = :ftype, 1 = 1) and if(:fto != '', fto = :fto, 1 = 1)", nativeQuery = true)
   List<FunctionMenu> findFunctionMenuByPidAndAndFtypeAndAndFto(@Param("pid") String pid, @Param("ftype") String ftype, @Param("fto") String fto);

   @Query(value = "select * from function_menu where if(:fullPath !='',full_path like concat(:fullPath,',%'), pid is null) order by level asc", nativeQuery = true)
   List<FunctionMenu> getChildrenFunctionMenuListByFullPath(@Param("fullPath") String fullPath);
}
