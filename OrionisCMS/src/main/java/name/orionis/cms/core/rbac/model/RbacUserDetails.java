package name.orionis.cms.core.rbac.model;

import java.util.Collection;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class RbacUserDetails implements UserDetails {

	private RbacUser user;
	public static final long ADMINISTRATOR_ID = 1;
	
	
	public RbacUserDetails(RbacUser user){
		this.user = user;
	}
	
	public RbacUserDetails(){
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		
		Collection<RbacPermission> permissions = CollectionUtils.EMPTY_COLLECTION;
		RbacRole role = user.getRbacRole();
		if(role.getId() == ADMINISTRATOR_ID){
			permissions = RbacPermission.findAllRbacPermissions();
		}else{
			permissions = role.getRbacPermissions();
		}
		
		Collection<GrantedAuthority> authorities = CollectionUtils.EMPTY_COLLECTION;
		for(RbacPermission rp : permissions){
		}
		return null;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
	}

	@Override
	public boolean isAccountNonExpired() {
		return user.getStatus() != Status.EXPIRED;
	}

	@Override
	public boolean isAccountNonLocked() {
		return user.getStatus() != Status.LOCKED;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return user.getStatus() == Status.ENABLED;
	}

	public RbacUser getUser() {
		return user;
	}

	public void setUser(RbacUser user) {
		this.user = user;
	}

}
