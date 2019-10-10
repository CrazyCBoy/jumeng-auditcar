package com.jumeng.auditcar.web.security;

import com.jumeng.auditcar.domain.Admin;
import com.jumeng.auditcar.domain.AdminRole;
import com.jumeng.auditcar.domain.service.AdminRoleService;
import com.jumeng.auditcar.domain.service.AdminService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Service实现类 - 管理权限认证
 */

@Service
public class TokenAdminDetailsService implements UserDetailsService {

	@Resource
	private AdminService adminService;
	@Resource
	private AdminRoleService adminRoleService;

	@Value("${setting.isLoginFailureLock}")
	private Boolean isLoginFailureLock;
	@Value("${setting.loginFailureLockTime}")
	private Integer loginFailureLockTime;
	
	public JWTUserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		Admin admin = null;
		Set<String> authoritySet = new HashSet<>();
		try {
            admin = adminService.findAdminByUsername(username);
		} catch (Exception e) {
			throw new UsernameNotFoundException("用户[" + username + "]不存在!");
		}
		if (admin == null) {
			throw new UsernameNotFoundException("用户[" + username + "]不存在!");
		}
		if(StringUtils.isEmpty(admin.getRoleIdStore())) {
			throw new UsernameNotFoundException("用户[" + username + "]权限异常!");
		}
		for (String roleId : admin.getRoleIdList()) {
			AdminRole adminRole = adminRoleService.findAdminRoleById(roleId);
			if (adminRole != null && adminRole.getAuthorityList().size() > 0) {
				authoritySet.addAll(adminRole.getAuthorityList());
			}
		}
		if (authoritySet.size() <= 0) {
			throw new UsernameNotFoundException("用户[" + username + "]权限异常!");
		}

		// 解除管理员账户锁定
		if (admin.getIsAccountLocked()) {
			if (isLoginFailureLock) {
				if (loginFailureLockTime != 0) {
					Date lockedDate = admin.getLockedDate() == null ? new Date() : admin.getLockedDate();
					Date nonLockedTime = DateUtils.addMinutes(lockedDate, loginFailureLockTime);
					Date now = new Date();
					if (now.after(nonLockedTime)) {
						admin.setLoginFailureCount(0);
						admin.setIsAccountLocked(false);
						admin.setLockedDate(null);
						try {
							adminService.saveAdmin(admin);
						} catch (Exception e) {
							throw new UsernameNotFoundException("用户[" + username + "]登录失败!");
						}
					}
				}
			} else {
				admin.setLoginFailureCount(0);
				admin.setIsAccountLocked(false);
				admin.setLockedDate(null);
				try {
					adminService.saveAdmin(admin);
				} catch (Exception e) {
					throw new UsernameNotFoundException("用户[" + username + "]登录失败!");
				}
			}
		}

		return new JWTUserDetails(admin.getId(), admin.getUsername(), admin.getPassword(), null,
				admin.getIsAccountEnabled(), true, true, admin.getIsAccountLocked(), getGrantedAuthorities(authoritySet));
	}

	private Collection<? extends GrantedAuthority> getGrantedAuthorities(Set<String> authoritySet) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		for (String authority : authoritySet) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authority));
		}
		return grantedAuthorities;
	}

}