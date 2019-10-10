package com.jumeng.auditcar.domain.service;

import com.jumeng.auditcar.domain.MerchantRole;
import com.jumeng.auditcar.domain.form.param.MerchantRoleParam;
import com.jumeng.auditcar.domain.form.param.nopage.MerchantRoleNoPageParam;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MerchantRoleService {
    MerchantRole findMerchantRoleById(String merchantRoleId);

    MerchantRole findMerchantRoleByName(String name);

    void deleteMerchantRole(String merchantRoleId);

    MerchantRole saveMerchantRole(MerchantRole merchantRole);

    MerchantRole updateMerchantRole(MerchantRole merchantRole);

    Page<MerchantRole> findMerchantRolePage(MerchantRoleParam merchantRoleParam);

    List<MerchantRole> findMerchantRoleList(MerchantRoleNoPageParam merchantRoleNoPageParam);
}
