package name.orionis.cms.core.rbac.service;

import name.orionis.cms.core.exception.ActionFailedException;
import name.orionis.cms.core.rbac.model.RbacUser;
import name.orionis.cms.core.rbac.model.Status;

public class RbacServiceImpl implements RbacService {
	@Override
	public RbacUser userLogin(RbacUser user){
		RbacUser loginUser = RbacUser.findRbacUsersByUserNameEqualsAndPasswordEquals(
				user.getUserName(), user.getPassword()).getSingleResult();
		
		// Check user account status
		if(loginUser == null){
			throw new ActionFailedException("Username or password not correct!");
		}
		System.out.println(Status.ENABLED);
		if(loginUser.getStatus() != Status.ENABLED){
			throw new ActionFailedException("User Maybe Locked or Disabled!");
		}
		
		// Update user login information
		loginUser.setLastLoginIP(user.getLastLoginIP());
		loginUser.setLastLoginTime(user.getLastLoginTime());
		loginUser.merge();
		
		return loginUser;
	}
}
