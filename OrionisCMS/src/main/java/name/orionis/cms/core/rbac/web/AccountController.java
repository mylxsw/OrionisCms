package name.orionis.cms.core.rbac.web;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import name.orionis.cms.core.base.BaseController;
import name.orionis.cms.core.rbac.authentication.ConfigHelper;
import name.orionis.cms.core.rbac.authentication.UserInfo;
import name.orionis.cms.core.rbac.model.RbacUser;
import name.orionis.cms.core.rbac.service.RbacService;
import name.orionis.cms.utils.Encrypt;
import name.orionis.helper.reflection.annotation.Remark;

/**
 * Account Controller
 * @author orionis
 * @2013-5-17
 * Site : http://blog.orionis.name
 *
 */
@Controller
@Remark(value="账户控制", group="account")
@RequestMapping("account")
public class AccountController extends BaseController {
	public static final String ACCOUNT_INFO = "userinfo";
	@Resource
	private ConfigHelper configHelper;
	@Resource
	private RbacService rbacService;
	
	/**
	 * User Login Form And Check
	 * @param username
	 * @param password
	 * @param req
	 * @param resp
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping("login")
	@Remark(value="用户登录", group="account")
	public String login(@RequestParam(value="username", required=false) String username, 
			@RequestParam(value="password", required=false) String password, 
			HttpServletRequest req,
			HttpServletResponse resp,
			HttpSession session, Model model){

		// Render Form View
		if(req.getMethod() == HTTP_GET){
			return view("login");
		}
		
		// Username and password must be not blank 
		if(StringUtils.isBlank(username) || StringUtils.isBlank(password)){
			return ajax("用户名或密码错误!", STATUS_FAILED, resp);
		}
		
		// God Mode
		if(configHelper.isAllowGodMode()){
			if(configHelper.getGodUser().equals(
					DigestUtils.sha256Hex(username)) 
					&& configHelper.getGodPassword().equals(Encrypt.encryptPassword(password, username))){
				session.setAttribute(ACCOUNT_INFO, new UserInfo().setGodMode(true).setUserId(0L).setUsername(username).setRoleId(0L));
				return ajax("以超级管理员身份登录成功!", STATUS_SUCCESS, resp);
			}
		}
		
		// Create Login User Information
		RbacUser _user = new RbacUser();
		_user.setUserName(username);
		_user.setPassword(Encrypt.encryptPassword(password, username));
		_user.setLastLoginIP(req.getRemoteAddr());
		_user.setLastLoginTime(new Date(System.currentTimeMillis()));
		
		// Check User and update user status
		RbacUser user = null;
		try{
			user = rbacService.userLogin(_user);
		} catch( Exception e){
			return ajax(e.getMessage(), STATUS_FAILED, resp);
		}
		
		session.setAttribute(ACCOUNT_INFO, new UserInfo(user.getId(), 
				user.getRbacRole().getId()).setUsername(user.getUserName()));
		return ajax("登录成功!", STATUS_SUCCESS, resp);
	}
	
	
	@Remark(value="修改个人信息", group="account")
	@RequestMapping("personalinfo")
	public String personalinfo(HttpSession session, HttpServletRequest req, HttpServletResponse resp, Model model){
		UserInfo user = (UserInfo) session.getAttribute(AccountController.ACCOUNT_INFO);
		if(req.getMethod().equals(HTTP_GET)){
			model.addAttribute("user", rbacService.findRbacUser(user.getUserId()));
			return view("personalinfo");
		}
		String email = (String) req.getParameter("email");
		
		RbacUser rbacUser = rbacService.findRbacUser(user.getUserId());
		rbacUser.setEmail(email);
		
		rbacService.updateRbacUser(rbacUser);
		return success(resp);
	}
	
	@Remark(value="修改密码", group="account")
	@RequestMapping("changePassword")
	public String changePassword(HttpSession session, HttpServletRequest req, HttpServletResponse resp, Model model){
		if(req.getMethod().equals(HTTP_GET)){
			return view("change_password");
		}
		String password = req.getParameter("password").trim();
		if("".equals(password)){
			return failed(resp);
		}
		
		UserInfo user = (UserInfo) session.getAttribute(AccountController.ACCOUNT_INFO);
		RbacUser rbacUser = rbacService.findRbacUser(user.getUserId());
		rbacUser.setPassword(Encrypt.encryptPassword(password, rbacUser.getUserName()));
		rbacService.updateRbacUser(rbacUser);
		return success(resp);
		
	}
	/**
	 * Safe logout
	 * @param session
	 * @param resp
	 * @return
	 */
	@Remark(value="退出系统", group="account")
	@RequestMapping("logout")
	public String logout(HttpSession session, HttpServletResponse resp){
		session.invalidate();
		return ajax("您已经安全退出系统!", STATUS_SUCCESS, resp);
	}
	
	@Override
	protected String _viewBase() {
		return "account/";
	}

}
