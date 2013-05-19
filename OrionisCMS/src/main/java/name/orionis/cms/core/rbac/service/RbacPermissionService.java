package name.orionis.cms.core.rbac.service;

import java.util.List;

import name.orionis.cms.core.rbac.model.RbacPermission;

import org.springframework.context.ApplicationContext;
import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { name.orionis.cms.core.rbac.model.RbacPermission.class })
public interface RbacPermissionService {

	/**
	 * Get Permissions of role by role_id
	 * @param id
	 * @return
	 */
	List<RbacPermission> findRbacPermissionsByRbacRoleId(long id);

	/**
	 * Update All Permissions
	 * Just for developer
	 * @param applicationContext
	 */
	void updatePermissions(ApplicationContext applicationContext);
}
