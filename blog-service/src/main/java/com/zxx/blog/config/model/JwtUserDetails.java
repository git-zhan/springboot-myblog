package com.zxx.blog.config.model;

import com.zxx.blog.entity.security.User;

import lombok.Data;
import lombok.NoArgsConstructor;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@NoArgsConstructor
@Data
public class JwtUserDetails implements UserDetails {
	private User user;
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<? extends GrantedAuthority> authList;
	
	
	
	public JwtUserDetails(User user) {
		this.user = user;
	}
	
	@Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authList;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }

	public void setAuthList(List<? extends GrantedAuthority> authList) {
		this.authList = authList;
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}
}
