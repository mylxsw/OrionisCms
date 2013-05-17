package name.orionis.cms.core.rbac.web;

import java.util.HashMap;
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
import name.orionis.cms.core.rbac.form.UserForm;
import name.orionis.cms.core.rbac.model.RbacRole;
import name.orionis.cms.core.rbac.model.RbacUser;
import name.orionis.cms.core.rbac.service.RbacUserService;
import name.orionis.cms.utils.Configuration;
import name.orionis.cms.utils.Constant;
import name.orionis.cms.utils.MessageBuilder;
import name.orionis.helper.reflection.annotation.Remark;

/**
 * Rbac User Controller
 * @author orionis
 * @2013-5-17
 * Site : http://blog.orionis.name
 *
 */
@Controller
@RequestMapping("/rbac/user")
@Remark(value="Rbac User Controller", group="rbac")
public class RbacUserController extends BaseController {
	
	@Resource
	private RbacUserService userService;
	@Resource
	private Configuration cfg;
	
	/**
	 * User List
	 * @param model
	 * @return
	 */
	@Remark(value="User List", group="rbac_user")
	@RequestMapping("list")
	public String list(@RequestParam(value="page", defaultValue="1") int page, 
			Model model){
		// Current User Count
		model.addAttribute("total", RbacUser.countRbacUsers());
		// Current page user list
		model.addAttribute("users", userService
				.findRbacUserEntries(page, cfg.getInt(Constant.CMS_PAGE_SIZE)));
		return view("list");
	}
	
	/**
	 * User Add
	 * @param userForm
	 * @param result
	 * @param req
	 * @param resp
	 * @param model
	 * @return
	 */
	@Remark(value="User Add",group="rbac_user")
	@RequestMapping("add")
	public String add(@Valid @ModelAttribute("userForm") UserForm userForm, 
			BindingResult result, 
			HttpServletRequest req, 
			HttpServletResponse resp, Model model){
		if(HTTP_GET.equals(req.getMethod())){
			List<RbacRole> roles = RbacRole.findAllRbacRoles();
			Map<Long, String> role_list = new HashMap<Long, String>();
			for(RbacRole r : roles){
				role_list.put(r.getId(), r.getRoleName());
			}
			model.addAttribute("roles", role_list);
			return view("user_add");
		}
		
		// Check Form Information
		if(result.hasErrors() || !userForm.validate()){
			return errors(result, userForm, resp);
		}
		
		userService.saveRbacUser(userForm.toEntity());
		
		return success(resp);
	}
	
	/**
	 * User delete
	 * @param id
	 * @param resp
	 * @return
	 */
	@Remark(value="User delete",group="rbac_user")
	@RequestMapping("delete")
	public String user_delete(@RequestParam("id") long id, HttpServletResponse resp){
		userService.deleteRbacUser(RbacUser.findRbacUser(id));
		return success(resp);
	}
	
	@Override
	protected String _viewBase() {
		return null;
	}
	
}
