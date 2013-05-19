package name.orionis.cms.core.rbac.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import name.orionis.cms.core.base.BaseController;
import name.orionis.cms.core.exception.ActionFailedException;
import name.orionis.cms.core.rbac.authentication.SecurityHelper;
import name.orionis.cms.core.rbac.form.PermissionForm;
import name.orionis.cms.core.rbac.model.RbacPermission;
import name.orionis.cms.core.rbac.service.RbacPermissionService;
import name.orionis.cms.core.rbac.service.RbacRoleService;
import name.orionis.cms.utils.Constant;
import name.orionis.helper.reflection.ControllerClassInfo;
import name.orionis.helper.reflection.ControllerReflectionUtil;
import name.orionis.helper.reflection.annotation.Remark;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Rbac Permission Controller
 * @author orionis
 * @2013-5-18
 * Site : http://blog.orionis.name
 *
 */
@Controller
@RequestMapping("/rbac/permission")
@Remark(value="Rbac Permission Controller", group="rbac")
public class RbacPermissionController extends BaseController {

	@Resource
	private SecurityHelper securityHelper;
	@Resource
	private RbacPermissionService permissionService;
	
	/**
	 * Permission Update
	 * This option is just for developer
	 * @param resp
	 * @return
	 */
	@Remark(value="Update Permission",group="rbac_permission")
	@RequestMapping("update")
	public String update(HttpServletResponse resp){
		permissionService.updatePermissions(getApplicationContext());
		
		// Reload all permissions
		securityHelper.reloadRolePermissionCache();
		return success(resp);
	}
	/**
	 * Grant permission to role
	 * @param id
	 * @param permissionForm
	 * @param result
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@Remark(value="Grant permission to role",group="rbac_permission")
	@RequestMapping("add")
	public String add(
			@RequestParam(value="id", required=false) long id,
			@Valid @ModelAttribute("permissionForm") PermissionForm permissionForm,
			BindingResult result,
			HttpServletRequest req, 
			HttpServletResponse resp, Model model){
		
		if(HTTP_GET.equals(req.getMethod())){
			// Check if received an role id
			if(id == 0){
				throw new ActionFailedException("info.action.invalididsuply");
			}
			
			// Get all controller and method by reflection tools
			List<ControllerClassInfo> controllers = ControllerReflectionUtil
					.getControllers(getApplicationContext(),Constant.SECURITY_BASE_PACKAGE, true);
			model.addAttribute("tree",controllers);
			
			// Get all permissions of current role
			List<RbacPermission> permission_set = permissionService.findRbacPermissionsByRbacRoleId(id);
			Map<String, RbacPermission> permissions = new HashMap<String, RbacPermission>();
			for(RbacPermission rp: permission_set){
				permissions.put(rp.getController() + "." + rp.getMethod(), rp);
			}
			model.addAttribute("permissions", permissions);
			return view("add");
		}
		
		// Check errors
		if(result.hasErrors() || !permissionForm.validate()){
			return errors(result, permissionForm, resp);
		}
		
		permissionService.saveRbacPermission(permissionForm.toEntity());
	
		// Reload Cached Role Permissions
		securityHelper.reloadRolePermissionCache();
		
		return success(resp);
	}
	
	/**
	 * Role Permission delete
	 * @param id
	 * @param resp
	 * @return
	 */
	@Remark(value="Role-Permission delete",group="rbac_permission")
	@RequestMapping("delete")
	public String delete( @RequestParam(value="id",required=false) long id,
			HttpServletResponse resp){
		
		permissionService.deleteRbacPermission(RbacPermission.findRbacPermission(id));
		
		securityHelper.reloadRolePermissionCache();
		return success(resp);
	}
	
	@Override
	protected String _viewBase() {
		return null;
	}

}
