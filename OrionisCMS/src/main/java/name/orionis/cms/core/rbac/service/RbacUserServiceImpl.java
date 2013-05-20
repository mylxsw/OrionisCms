package name.orionis.cms.core.rbac.service;

import name.orionis.cms.core.exception.ActionFailedException;
import name.orionis.cms.core.rbac.model.RbacUser;


public class RbacUserServiceImpl implements RbacUserService {
	@Override
	public RbacUser updateRbacUser(RbacUser user){
		RbacUser rbacUser = RbacUser.findRbacUser(user.getId());
		if(!user.getPassword().equals("")){
			rbacUser.setPassword(user.getPassword());
		}
		rbacUser.setRealName(user.getRealName());
		rbacUser.setEmail(user.getEmail());
		rbacUser.setStatus(user.getStatus());
		rbacUser.setRbacRole(user.getRbacRole());
		if(!rbacUser.getUserName().equals(user.getUserName())){
			// check if the new username has been used, if used , then ignore new username.
			// otherwise, throw an new exception , set the new username.
			try{
				RbacUser singleResult = RbacUser.findRbacUsersByUserNameEquals(user.getUserName()).getSingleResult();
				if(singleResult != null	){
					throw new ActionFailedException();
				}
			}catch (Exception e){
				rbacUser.setUserName(user.getUserName());
			}
		}
		rbacUser.setUserName(user.getUserName());
		
		return rbacUser.merge();
	}
}
