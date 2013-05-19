package name.orionis.cms.core.rbac.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.context.ApplicationContext;

import name.orionis.cms.core.rbac.model.RbacPermission;
import name.orionis.cms.core.rbac.model.RbacRole;
import name.orionis.cms.utils.Constant;
import name.orionis.helper.reflection.ActionMethodInfo;
import name.orionis.helper.reflection.ControllerClassInfo;
import name.orionis.helper.reflection.ControllerReflectionUtil;


public class RbacPermissionServiceImpl implements RbacPermissionService {

	@Override
	public List<RbacPermission> findRbacPermissionsByRbacRoleId(long id) {
		return RbacPermission.findRbacPermissionsByRbacRole(RbacRole.findRbacRole(id)).getResultList();
	}
	@Override
	public void updatePermissions(ApplicationContext applicationContext){
		// Get latest permission information
		// key: classname.methodName 
		// value:method information
		List<ControllerClassInfo> controllers = ControllerReflectionUtil.getControllers(applicationContext, Constant.SECURITY_BASE_PACKAGE, true);
		Map<String, ActionMethodInfo> maps = new HashMap<String, ActionMethodInfo>();
		for(ControllerClassInfo c: controllers){
			List<ActionMethodInfo> methods = c.getMethods();
			for(ActionMethodInfo a : methods){
				maps.put(c.getClassName() + "." + a.getMethodName(), a);
			}
		}
		
		// Update Permission Information in database
		List<RbacPermission> all = RbacPermission.findAllRbacPermissions();
		for(RbacPermission p: all){
			String pname = p.getController() + "." + p.getMethod();
			if(maps.containsKey(pname)){
				ActionMethodInfo info = maps.get(pname);
				p.setPermissionName(info.getRemark());
				p.setUrl(info.getUrl()[0]);
				
				p.merge();
			}else{// Permission has removed
				p.remove();
			}
		}
	}
}
