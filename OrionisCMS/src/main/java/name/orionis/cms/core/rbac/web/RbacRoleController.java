package name.orionis.cms.core.rbac.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import name.orionis.cms.core.base.BaseController;
import name.orionis.cms.core.exception.ActionFailedException;
import name.orionis.cms.core.rbac.form.RoleForm;
import name.orionis.cms.core.rbac.model.RbacRole;
import name.orionis.cms.core.rbac.service.RbacRoleService;
import name.orionis.helper.reflection.annotation.Remark;
/**
 * Rbac Role Controller
 * @author orionis
 * @2013-5-17
 * Site : http://blog.orionis.name
 *
 */
@Controller
@RequestMapping("/rbac/role")
@Remark(value="RBAC Role Controller", group="rbac")
public class RbacRoleController extends BaseController {

	@Resource
	private RbacRoleService roleService;
	
	/**
	 * Role List
	 * @param req
	 * @param model
	 * @return
	 */
	@Remark(value="Role List",group="rbac_role")
	@RequestMapping("list")
	public String list(HttpServletRequest req, Model model){
		model.addAttribute("roles", roleService.findAllRbacRoles());
		return view("list");
	}
	
	/**
	 * Role Add
	 * @param roleForm
	 * @param result
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@Remark(value="Add Role",group="rbac_role")
	@RequestMapping("add")
	public String add(
			@Valid @ModelAttribute("roleForm") RoleForm roleForm,
			BindingResult result,
			HttpServletRequest req, HttpServletResponse resp, Model model){
		if(HTTP_GET.equals(req.getMethod())){
			return view("add");
		}
		
		// Check Form Information
		if(result.hasErrors() || !roleForm.validate()){
			return errors(result, roleForm, resp);
		}
		// Add
		try{
			roleService.saveRbacRole(roleForm.toEntity());
		}catch(Exception e){
			return failed(resp);
		}
		
		return success(resp);
	}
	/**
	 * Role Edit
	 * @param roleForm
	 * @param result
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@Remark(value="Modify Role", group="rbac_role")
	@RequestMapping("edit")
	public String edit(
			@Valid @ModelAttribute("roleForm") RoleForm roleForm,
			BindingResult result,
			HttpServletRequest req, HttpServletResponse resp,
			Model model){
		
		// Role id
		long id = 0;
		try{
			id = Long.parseLong(req.getParameter("id"));
		}catch(Exception e){
			throw new ActionFailedException("Invalid Id!");
		}
		if(HTTP_GET.equals(req.getMethod())){
			// fill back role information
			model.addAttribute("role", roleService.findRbacRole(id));
			model.addAttribute("role_id", id);
			return view("edit");
		}
		// Check
		if(result.hasErrors() || !roleForm.validate()){
			return errors(result, roleForm, resp);
		}
		// Save Modify
		RbacRole rbacRole = roleForm.toEntity();
		rbacRole.setId(id);
		try{
			roleService.updateRbacRole(rbacRole);
		} catch(Exception e){
			return failed(resp);
		}
		
		return success(resp);
	}
	/**
	 * Role Delete
	 * @param id
	 * @param req
	 * @param resp
	 * @return
	 */
	@Remark(value="Delete Role",group="rbac_role")
	@RequestMapping("delete")
	public String role_delete(@RequestParam("id") long id, HttpServletRequest req, HttpServletResponse resp){
		roleService.deleteRbacRole(roleService.findRbacRole(id));
		return success(resp);
	}
	
	@Override
	protected String _viewBase() {
		return "rbac/role/";
	}

}
