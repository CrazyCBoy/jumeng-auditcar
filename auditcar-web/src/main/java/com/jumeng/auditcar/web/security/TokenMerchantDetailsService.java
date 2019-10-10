package com.jumeng.auditcar.web.security;

import com.jumeng.auditcar.domain.Merchant;
import com.jumeng.auditcar.domain.MerchantRole;
import com.jumeng.auditcar.domain.service.MerchantRoleService;
import com.jumeng.auditcar.domain.service.MerchantService;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Service实现类 - 管理权限认证
 */

@Service
public class TokenMerchantDetailsService implements UserDetailsService {

	@Autowired
	private MerchantService merchantService;
	@Autowired
	private MerchantRoleService merchantRoleService;
	@Value("${setting.isLoginFailureLock}")
	private Boolean isLoginFailureLock;
	@Value("${setting.loginFailureLockTime}")
	private Integer loginFailureLockTime;
	
	public JWTUserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
		Merchant merchant = null;
		Set<String> authoritySet = new HashSet<>();
		try {
			merchant = merchantService.findMerchantByMobile(username);
			if (merchant == null) {
				throw new UsernameNotFoundException("用户[" + username + "]不存在!");
			}
			if(StringUtils.isEmpty(merchant.getRoleIdStore())) {
				throw new UsernameNotFoundException("用户[" + username + "]权限异常!");
			}
			for (String roleId : merchant.getRoleIdList()) {
				MerchantRole merchantRole = merchantRoleService.findMerchantRoleById(roleId);
				if (merchantRole != null && merchantRole.getAuthorityList().size() > 0) {
					authoritySet.addAll(merchantRole.getAuthorityList());
				}
			}
			if (authoritySet.size() <= 0) {
				throw new UsernameNotFoundException("用户[" + username + "]权限异常!");
			}
		} catch (Exception e) {
			throw new UsernameNotFoundException("用户[" + username + "]不存在!");
		}
		
		// 解除商户账户锁定
		if (merchant.getIsAccountLocked()) {
			if (isLoginFailureLock) {
				if (loginFailureLockTime != 0) {
					Date lockedDate = merchant.getLockedDate() == null ? new Date() : merchant.getLockedDate();
					Date nonLockedTime = DateUtils.addMinutes(lockedDate, loginFailureLockTime);
					Date now = new Date();
					if (now.after(nonLockedTime)) {
						merchant.setLoginFailureCount(0);
						merchant.setIsAccountLocked(false);
						merchant.setLockedDate(null);
						try {
							merchantService.saveMerchant(merchant);
						} catch (Exception e) {
							throw new UsernameNotFoundException("用户[" + username + "]登录失败!");
						}
					}
				}
			} else {
				merchant.setLoginFailureCount(0);
				merchant.setIsAccountLocked(false);
				merchant.setLockedDate(null);
				try {
					merchantService.saveMerchant(merchant);
				} catch (Exception e) {
					throw new UsernameNotFoundException("用户[" + username + "]登录失败!");
				}
			}
		}
		
		return new JWTUserDetails(merchant.getId(), merchant.getName(), merchant.getPassword(), merchant.getLogo(),
				merchant.getIsAccountEnabled(), true, true, merchant.getIsAccountLocked(), getGrantedAuthorities(authoritySet));
	}

	private Collection<? extends GrantedAuthority> getGrantedAuthorities(Set<String> authoritySet) {
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		for (String authority : authoritySet) {
			grantedAuthorities.add(new SimpleGrantedAuthority(authority));
		}
		return grantedAuthorities;
	}

}