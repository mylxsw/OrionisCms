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
import name.orionis.cms.core.rbac.dto.NavItem;
import name.orionis.cms.core.rbac.form.MenuForm;
import name.orionis.cms.core.rbac.model.RbacMenu;
import name.orionis.cms.core.rbac.service.RbacMenuService;
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
@Remark(value="Rbac Menu Controller", group="rbac")
public class RbacMenuController extends BaseController {

	@Resource
	private RbacMenuService menuService;
	
	/**
	 * Menu list
	 * @param id
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@Remark(value="Menu list",group="rbac_menus")
	@RequestMapping("list")
	public String list( 
			@RequestParam("id") long id, 
			HttpServletRequest req, 
			HttpServletResponse resp, 
			Model model){
		NavItem navItems = menuService.listMenusTree(id);
		model.addAttribute("menus", navItems);
		
		model.addAttribute("role_id", id);
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
	@Remark(value="Menu add",group="rbac_menus")
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
	@Remark(value="Menu select",group="rbac_menus")
	@RequestMapping("select")
	public String select(HttpServletRequest req, HttpServletResponse resp ,  Model model){
		List<ControllerClassInfo> controllers = 
				ControllerReflectionUtil.getControllers(getApplicationContext(),
						Constant.SECURITY_BASE_PACKAGE, true);
		model.addAttribute("tree",controllers);
		return view("select");
	}
	
	@Remark(value="Menu modify", group="rbac_menus")
	@RequestMapping("modify")
	public String modify(
			@Valid @ModelAttribute("menuForm") MenuForm menuForm,
			BindingResult result,
			HttpServletRequest req, 
			HttpServletResponse resp,Model model){
		
		long id = Long.parseLong(req.getParameter("id"));
		
		if(HTTP_GET.equals(req.getMethod())){
			// here, id attribute is role id
			RbacMenu menu = RbacMenu.findRbacMenu(id);
			model.addAttribute("menu", menu);
			return view("modify");
		}
		// here , id attribute is menu id
		
		// Check errors
		if(result.hasErrors() || !menuForm.validate()){
			return errors(result, menuForm, resp);
		}
		
		RbacMenu rbacMenu = RbacMenu.findRbacMenu(id);
		
		rbacMenu.setMenuName(menuForm.getMenuName());
		rbacMenu.setParentId(menuForm.getParentId());
		rbacMenu.setUrl(menuForm.getUrl());
		
		menuService.updateRbacMenu(rbacMenu);
		
		return success(resp);
	}
	
	/**
	 * Menu delete
	 * @param id
	 * @param req
	 * @param resp
	 * @return
	 */
	@Remark(value="Menu delete",group="rbac_menus")
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
