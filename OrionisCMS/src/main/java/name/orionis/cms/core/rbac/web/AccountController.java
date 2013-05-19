package name.orionis.cms.core.rbac.web;

import java.util.Date;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import name.orionis.cms.core.base.BaseController;
import name.orionis.cms.core.exception.ActionFailedException;
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
@Remark(value="Account Controller", group="account")
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
	@Remark(value="User Login", group="account")
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
			return ajax("Username or password not correct!", STATUS_FAILED, resp);
		}
		
		// God Mode
		if(configHelper.isAllowGodMode()){
			if(configHelper.getGodUser().equals(
					Encrypt.encryptPassword(password, username)) 
					&& configHelper.getGodPassword().equals(Encrypt.encryptPassword(password, username))){
				session.setAttribute(ACCOUNT_INFO, new UserInfo().setGodMode(true));
				return ajax("God Mode Successfully!", STATUS_SUCCESS, resp);
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
		return ajax("Login Successfully", STATUS_SUCCESS, resp);
	}
	
	/**
	 * Safe logout
	 * @param session
	 * @param resp
	 * @return
	 */
	@Remark(value="Logout", group="account")
	@RequestMapping("logout")
	public String logout(HttpSession session, HttpServletResponse resp){
		session.invalidate();
		return ajax("Logout Successfully!", STATUS_SUCCESS, resp);
	}
	
	@Override
	protected String _viewBase() {
		return "account/";
	}

}
