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
import name.orionis.cms.core.rbac.form.RoleForm;
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
@Remark(value="Rbac Role Controller", group="rbac")
public class RbacRoleController extends BaseController {

	@Resource
	private RbacRoleService roleService;
	
	/**
	 * Role List
	 * @param req
	 * @param model
	 * @return
	 */
	@Remark(value="Role list",group="rbac_role")
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
	@Remark(value="Role Add",group="rbac_role")
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
		
		roleService.saveRbacRole(roleForm.toEntity());
		
		return success(resp);
	}
	
	/**
	 * Role Delete
	 * @param id
	 * @param req
	 * @param resp
	 * @return
	 */
	@Remark(value="Role delete",group="rbac_role")
	@RequestMapping("delete")
	public String role_delete(@RequestParam("id") long id, HttpServletRequest req, HttpServletResponse resp){
		roleService.deleteRbacRole(roleService.findRbacRole(id));
		return success(resp);
	}
	
	@Override
	protected String _viewBase() {
		return null;
	}

}
