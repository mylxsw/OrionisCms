package name.orionis.cms.core.rbac.authentication;
import java.util.ArrayList;
import java.util.List;

import name.orionis.cms.core.rbac.web.AccountController;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Security AOP
 * @author orionis
 * @2013-5-16
 * Site : http://blog.orionis.name
 *
 */
@Aspect
public class SecurityAspect {
	
	/**
	 * 1. Anonymous, If visit public page, access, otherwise, deny
	 * 2. Non Anonymous, If in god mode, permit all, otherwise check permissions
	 * 3. Check user permissions
	 * @param joinPoint
	 */
	@Before("hasAnnotation() && controller() && publicMethod()")
	public void authentication(JoinPoint joinPoint) {
		// Develop Mode
		if(configHelper.isDevMode()){
			return ;
		}
		// Current invoke Controller-Method
		String invokeClass = joinPoint.getTarget().getClass().getName();
		String invokeMethod = joinPoint.getSignature().getName();
		
		// Current permission name
		String invokePermissionName = SecurityHelper.parsePermissionName(
				invokeClass, invokeMethod);
		
		// Public page, open it
		if(containsElement(invokePermissionName, configHelper.getPublicAccess())){
			return ;
		}
		
		// Get current user
		UserInfo userinfo = (UserInfo) sessionHelper.get(AccountController.ACCOUNT_INFO);
		// Anonymous, Check if the current page are anonymous only
		if (userinfo == null) {
			if(containsElement(invokePermissionName, configHelper.getAnonymousOnlyAccess())){
				return ;
			}
			throw new PermissionDenyException("Access Denied!", "account/login");
		}
		
		// God Mode, Permit all
		if(userinfo.isGodMode()){
			log.info("God Mode:" 
					+ SessionHelper.getHttpServletRequest().getRequestURI());
			return ;
		}
		
		// If invoke anonymous only method, forbidden
		if(containsElement(invokePermissionName, configHelper.getAnonymousOnlyAccess())){
			throw new PermissionDenyException(
					"Access Denied!");
		}
		
		

		// Role Permissions List
		List<String> role_permissions =new ArrayList<String>();
		try{
			role_permissions =  securityHelper
					.getPermissionsByRoleId(userinfo.getRoleId());
		}catch(Exception e){}

		if (!role_permissions.contains(invokePermissionName)) {
			log.info("Access Intercept:  " + invokeClass + " : " + invokeMethod);
			throw new PermissionDenyException("Access Denied!");
		}
	}
	
	public SecurityAspect() {
		log.info("Instance SecurityAspect");
	}

	@Pointcut("execution(public * name.orionis..*.*(..))")
	public void publicMethod() {
	}
	@Pointcut("@annotation(org.springframework.web.bind.annotation.RequestMapping)")
	public void hasAnnotation(){}

	@Pointcut("within(@org.springframework.stereotype.Controller *)")
	public void controller() {
	}
	/**
	 * Check whether str in list, support regex
	 * @param key
	 * @param list
	 * @return
	 */
	private boolean containsElement(String key, List<String> list){
		for(String l: list){
			if(key.matches(l)){
				return true;
			}
		}
		return false;
	}
	

	private final Logger log = LoggerFactory.getLogger(getClass());

	private SecurityHelper securityHelper = SecurityHelper.getInstance();
	private SessionHelper sessionHelper = SessionHelper.getInstance();
	private ConfigHelper configHelper = ConfigHelper.getInstance();

}
