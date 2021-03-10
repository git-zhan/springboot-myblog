package com.zxx.blog.config.login;

import com.zxx.blog.entity.security.User;

import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
public class UserDetail extends User implements UserDetails {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<? extends GrantedAuthority> authList;
	
	
	
	public UserDetail(User user,List<? extends GrantedAuthority> authList) {
		this.setUserName(user.getUserName());
		this.setPassword(user.getPassword());
		this.setAuthList(authList);
	}
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authList;
    }

    @Override
    public String getUsername() {
        return getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }

	public void setAuthList(List<? extends GrantedAuthority> authList) {
		this.authList = authList;
	}
}
