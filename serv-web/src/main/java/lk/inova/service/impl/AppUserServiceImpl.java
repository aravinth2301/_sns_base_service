package lk.inova.service.impl;

import java.util.Arrays;
import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class AppUserServiceImpl implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String arg0) throws UsernameNotFoundException {
		
		UserDetails userDetails = new UserDetails() {
			
			/**
			 * 
			 */
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isEnabled() {
				
				return true;
			}
			
			@Override
			public boolean isCredentialsNonExpired() {
		
				return true;
			}
			
			@Override
			public boolean isAccountNonLocked() {
			
				return true;
			}
			
			@Override
			public boolean isAccountNonExpired() {
				
				return true;
			}
			
			@Override
			public String getUsername() {
				
				return "admin";
			}
			
			@Override
			public String getPassword() {
				
				return "admin";
			}
			
			@Override
			public Collection<? extends GrantedAuthority> getAuthorities() {		
				return Arrays.asList(new GrantedAuthority() {

					@Override
					public String getAuthority() {
						
						return "USER";
					}
					
				});
			}
		};
		
		return userDetails;
	}

}
