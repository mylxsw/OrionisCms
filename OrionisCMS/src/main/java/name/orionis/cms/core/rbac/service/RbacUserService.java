package name.orionis.cms.core.rbac.service;

import name.orionis.cms.core.rbac.model.RbacUser;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { name.orionis.cms.core.rbac.model.RbacUser.class })
public interface RbacUserService {
	/**
	 * Update User
	 * @param user
	 * @return
	 */
	public RbacUser updateRbacUser(RbacUser user);
}
