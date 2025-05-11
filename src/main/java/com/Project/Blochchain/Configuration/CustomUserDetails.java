package com.Project.Blochchain.Configuration;
import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


public class CustomUserDetails implements UserDetails {

	private final String username;
	
	private final String password;
	
	private final String marsId;
	
	private final Collection<? extends GrantedAuthority> authorites;
	
	public CustomUserDetails(String _username, String _password ,String _marsId, Collection<? extends GrantedAuthority> _authorites){
		this.username=_username;
		this.password=_password;
		this.marsId=_marsId;
		this.authorites=_authorites;
	}
	
	public String getMarsId() {
		return marsId;
	}
	
	@Override
	public String getUsername() {
		return username;
	}
	
	@Override
	public String getPassword() {
		return password;
	}

	@Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorites;
	}

}
