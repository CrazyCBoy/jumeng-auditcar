package com.jumeng.auditcar.web.security;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Data
public class JWTUserDetails implements UserDetails {

	private static final long serialVersionUID = 1L;
	
	private String userId;
    private String password;
    private String avatar;
    private final String username;
    private final Collection<? extends GrantedAuthority> authorities;
    private final boolean accountNonExpired;
    private final boolean accountNonLocked;
    private final boolean credentialsNonExpired;
    private final boolean enabled;


    public JWTUserDetails(String userId, String username, String password, String avatar, Collection<? extends GrantedAuthority> authorities) {
        this(userId, username, password, avatar, true, true, true, true, authorities);
    }

    public JWTUserDetails(String userId, String username, String password, String avatar, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        if (username != null && !"".equals(username) && password != null) {
            this.userId = userId;
            this.username = username;
            this.password = password;
            this.avatar = avatar;
            this.enabled = enabled;
            this.accountNonExpired = accountNonExpired;
            this.credentialsNonExpired = credentialsNonExpired;
            this.accountNonLocked = !accountNonLocked;
            this.authorities = authorities;
        } else {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }
    }

    public List<String> getUserRoleList(){
    	List<String> roleList = new ArrayList<>();
    	if(authorities != null) {
    		for (GrantedAuthority grantedAuthority : authorities) {
    			roleList.add(grantedAuthority.getAuthority());
    		}
    	}
    	return roleList;
    }
}
