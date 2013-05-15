package name.orionis.cms.core.rbac.service;


import name.orionis.cms.core.rbac.model.RbacUser;
import name.orionis.cms.core.rbac.model.RbacUserDetails;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CmsUserDetailsService implements UserDetailsService {

	@SuppressWarnings("deprecation")
	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		if(StringUtils.isBlank(username)){
			throw new UsernameNotFoundException("No Such User", username);
		}
		
		RbacUser user = RbacUser.findRbacUsersByUserNameEquals(username).getSingleResult();
		if(user == null){
			throw new UsernameNotFoundException("No Such User", username);
		}
		return new RbacUserDetails(user);
	}

}
