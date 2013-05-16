package name.orionis.cms.core.rbac.authentication;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import name.orionis.cms.core.rbac.model.RbacPermission;
import name.orionis.cms.core.rbac.model.RbacRole;
import name.orionis.helper.cache.Cached;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * Security Helper
 * @author code.404
 *
 */
@Component
public class SecurityHelper {
	final public static String ROLE_PERMISSION_LIST = "name.orionis.cms.core.rbac.authentication.role_permission_list";
	
	protected final Logger log = LoggerFactory.getLogger(getClass());
	/**
	 * Reload Role Permissions
	 */
	@Cached(value=ROLE_PERMISSION_LIST, reload=true)
	public void reloadRolePermissionCache(){
		log.info("Reload Role Permission Cache!");
	}
	
	/**
	 * Get all roles permissions
	 * @return
	 */
	@Cached(value=ROLE_PERMISSION_LIST)
	public Map<Long, List<String>> getAllRolePermissions(){
		Map<Long, List<String>> rplist = new HashMap<Long, List<String>>();
		
		// initialize role-permissions
		List<RbacRole> rolelist = RbacRole.findAllRbacRoles();
		for(RbacRole r : rolelist){
			List<String> list = new ArrayList<String>();
			List<RbacPermission> permissionsAvailable = RbacPermission.findRbacPermissionsByRbacRole(r).getResultList();
			for(RbacPermission p: permissionsAvailable){
				list.add(parsePermissionName(p.getController(),p.getMethod() ));
			}
			if(!list.isEmpty())
				rplist.put(r.getId(), list);
		}
		return rplist;
	}
	
	/**
	 * get role`s permissions
	 * @param role_id
	 * @return
	 */
	public List<String> getPermissionsByRoleId(Long role_id){
		return getAllRolePermissions().get(role_id);
	}
	
	/**
	 * get Permission name by controller and method
	 * @param controller
	 * @param method
	 * @return
	 */
	public static String parsePermissionName(String controller, String method){
		return controller + "." + method;
	}
}
