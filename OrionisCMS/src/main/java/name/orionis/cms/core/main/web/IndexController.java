package name.orionis.cms.core.main.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import name.orionis.cms.core.base.BaseController;
import name.orionis.cms.core.rbac.authentication.UserInfo;
import name.orionis.cms.core.rbac.service.RbacMenuService;
import name.orionis.cms.core.rbac.web.AccountController;
import name.orionis.helper.reflection.annotation.Remark;

/**
 * The Cms System main controller
 * @author orionis
 * @2013-5-14
 * Site : http://blog.orionis.name
 *
 */
@Controller
@Remark(value="Cms Admin Main Page")
public class IndexController extends BaseController {
	
	@Resource
	private RbacMenuService menuService;
	
	/**
	 * Admin Home Page
	 * @param model
	 * @return
	 */
	@Remark(value="Admin Main Page" , group="main")
	@RequestMapping("/index")
	public String index(Model model, HttpSession session){
		UserInfo user = (UserInfo) session.getAttribute(AccountController.ACCOUNT_INFO);
		model.addAttribute("menu_trees", menuService.listMenusTree(user.getRoleId()));
		return view("index");
	}
	
	
	@Override
	protected String _viewBase() {
		return "main/";
	}

}
