package name.orionis.cms.core.rbac.web;

import java.util.List;
import java.util.Map;

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
import name.orionis.cms.core.rbac.dto.NavItem;
import name.orionis.cms.core.rbac.form.MenuForm;
import name.orionis.cms.core.rbac.model.RbacMenu;
import name.orionis.cms.core.rbac.service.RbacMenuService;
import name.orionis.cms.core.rbac.service.RbacRoleService;
import name.orionis.cms.utils.Constant;
import name.orionis.helper.reflection.ControllerClassInfo;
import name.orionis.helper.reflection.ControllerReflectionUtil;
import name.orionis.helper.reflection.annotation.Remark;

/**
 * Rbac Menu Controller
 * @author orionis
 * @2013-5-18
 * Site : http://blog.orionis.name
 *
 */
@Controller
@RequestMapping("/rbac/menus")
@Remark(value="RBAC菜单管理", group="rbac")
public class RbacMenuController extends BaseController {

	@Resource
	private RbacMenuService menuService;
	@Resource private RbacRoleService roleService;
	
	/**
	 * Menu list
	 * @param id
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@Remark(value="菜单管理",group="rbac_menus")
	@RequestMapping("list")
	public String list( 
			@RequestParam("id") long id, 
			HttpServletRequest req, 
			HttpServletResponse resp, 
			Model model){
		NavItem navItems = menuService.listMenusTree(id);
		model.addAttribute("menus", navItems);
		
		model.addAttribute("role_id", id);
		model.addAttribute("role", roleService.findRbacRole(id));
		return view("list");
	}
	/**
	 * Menu add
	 * @param id
	 * @param menuForm
	 * @param result
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@Remark(value="添加菜单",group="rbac_menus")
	@RequestMapping("add")
	public String add(
			@Valid @ModelAttribute("menuForm") MenuForm menuForm,
			BindingResult result,
			HttpServletRequest req, HttpServletResponse resp, Model model){
		
		if(HTTP_GET.equals(req.getMethod())){
			long id = Long.parseLong(req.getParameter("id"));
			NavItem menusTree = menuService.listMenusTree(id);
			Map<Long, String> menus = NavItem.toMap(menusTree, "&nbsp;&nbsp;", "&nbsp;&nbsp;".length());
			menus.put(0L, "Root");
			model.addAttribute("menus",menus );
			
			model.addAttribute("role_id", id);
			return view("add");
		}
		
		// Check errors
		if(result.hasErrors() || !menuForm.validate()){
			return errors(result, menuForm, resp);
		}
		
		menuService.saveRbacMenu(menuForm.toEntity());
		
		return success(resp);
	}
	/**
	 * Menu Select
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@Remark(value="选择菜单回调",group="rbac_menus")
	@RequestMapping("select")
	public String select(HttpServletRequest req, HttpServletResponse resp ,  Model model){
		List<ControllerClassInfo> controllers = 
				ControllerReflectionUtil.getControllers(getApplicationContext(),
						Constant.SECURITY_BASE_PACKAGE, true);
		model.addAttribute("tree",controllers);
		return view("select");
	}
	/**
	 * Modify Menu
	 * @param menuForm
	 * @param result
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@Remark(value="修改菜单", group="rbac_menus")
	@RequestMapping("modify")
	public String modify(
			@Valid @ModelAttribute("menuForm") MenuForm menuForm,
			BindingResult result,
			HttpServletRequest req, 
			HttpServletResponse resp,Model model){
		long id, role_id;
		try{
			id = Long.parseLong(req.getParameter("id"));
			role_id = Long.parseLong(req.getParameter("roldId"));
		}catch(Exception e){
			throw new ActionFailedException("Invalid ID");
		}
		 
		if(HTTP_GET.equals(req.getMethod())){
			// get role `s menu tree
			NavItem menusTree = menuService.listMenusTree(role_id);
			Map<Long, String> menus = NavItem.toMap(menusTree, "&nbsp;&nbsp;", "&nbsp;&nbsp;".length());
			menus.put(0L, "Root");
			model.addAttribute("menus",menus );
			
			// get current menu you want to modify
			RbacMenu menu = RbacMenu.findRbacMenu(id);
			model.addAttribute("menu", menu);
			model.addAttribute("role_id", role_id);
			return view("modify");
		}
		// here , id attribute is menu id
		
		// Check errors
		if(result.hasErrors() || !menuForm.validate()){
			return errors(result, menuForm, resp);
		}
		RbacMenu rbacMenu = menuForm.toEntity();
		rbacMenu.setId(id);
		
		try{
			menuService.updateRbacMenu(rbacMenu);
		}catch(Exception e){
			return failed(resp);
		}
		
		return success(resp);
	}
	
	/**
	 * Menu delete
	 * @param id
	 * @param req
	 * @param resp
	 * @return
	 */
	@Remark(value="删除菜单",group="rbac_menus")
	@RequestMapping("delete")
	public String delete(@RequestParam("id") long id, HttpServletRequest req, HttpServletResponse resp){
		menuService.deleteRbacMenuCascade(RbacMenu.findRbacMenu(id));
		return success(resp);
	}
	
	
	@Override
	protected String _viewBase() {
		return "rbac/menu/";
	}
}
