package name.orionis.cms.core.rbac.service;

import name.orionis.cms.core.exception.ActionFailedException;
import name.orionis.cms.core.rbac.model.RbacUser;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { name.orionis.cms.core.rbac.model.RbacUser.class })
public interface RbacService {
	/**
	 * User Login
	 * @param user
	 * @return
	 */
	public RbacUser userLogin(RbacUser user) throws ActionFailedException;
}
