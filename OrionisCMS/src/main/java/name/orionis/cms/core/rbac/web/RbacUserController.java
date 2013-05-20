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
import name.orionis.cms.core.exception.ActionFailedException;
import name.orionis.cms.core.rbac.form.UserForm;
import name.orionis.cms.core.rbac.model.RbacRole;
import name.orionis.cms.core.rbac.model.RbacUser;
import name.orionis.cms.core.rbac.service.RbacUserService;
import name.orionis.cms.utils.Configuration;
import name.orionis.cms.utils.Constant;
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
				.findRbacUserEntries(cfg.getInt(Constant.CMS_PAGE_SIZE) * (page -1), cfg.getInt(Constant.CMS_PAGE_SIZE)));
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
			return view("add");
		}
		
		// Check Form Information
		if(result.hasErrors() || !userForm.validate()){
			return errors(result, userForm, resp);
		}
		try{
			userService.saveRbacUser(userForm.toEntity());
		}catch(Exception e){
			return ajax(Constant.MESSAGE_ACTION_FAILED, STATUS_FAILED, resp);
		}
		
		
		return success(resp);
	}
	@Remark(value="User Edit", group="rbac_user")
	@RequestMapping("edit")
	public String edit(
			@Valid @ModelAttribute("userForm") UserForm userForm,
			BindingResult result,
			HttpServletRequest req, HttpServletResponse resp,
			Model model){
		
		// user id
		long id = 0;
		try{
			id = Long.parseLong(req.getParameter("id"));
		}catch(Exception e){
			throw new ActionFailedException("Invalid Id!");
		}
		if(HTTP_GET.equals(req.getMethod())){
			// Role list
			List<RbacRole> roles = RbacRole.findAllRbacRoles();
			Map<Long, String> role_list = new HashMap<Long, String>();
			for(RbacRole r : roles){
				role_list.put(r.getId(), r.getRoleName());
			}
			model.addAttribute("roles", role_list);
			
			// fill back user information
			model.addAttribute("user", userService.findRbacUser(id));
			model.addAttribute("user_id", id);
			return view("edit");
		}
		// Check , here,we need not check form`s validate method,
		// because we are updating user information
		if(result.hasErrors()){
			return errors(result, userForm, resp);
		}
		// Save Modify
		RbacUser rbacUser = userForm.toEntity();
		String pwd = req.getParameter("password");
		
		// if password equals "--------", indicate that password not change
		if(pwd.equals("--------")){
			rbacUser.setPassword("");
		}
		rbacUser.setId(id);
		
		try{
			userService.updateRbacUser(rbacUser);
		} catch(Exception e){
			return failed(resp);
		}
		
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
	public String delete(@RequestParam("id") long id, HttpServletResponse resp){
		userService.deleteRbacUser(RbacUser.findRbacUser(id));
		return success(resp);
	}
	
	@Override
	protected String _viewBase() {
		return "rbac/user/";
	}
	
}
