package name.orionis.cms.core.rbac.authentication;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * Session Helper
 * @author code.404
 *
 */
@Component
public class SessionHelper {

	public static HttpServletRequest getHttpServletRequest(){
		return ((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
	}
	
	public static HttpSession getSession(){
		return getHttpServletRequest().getSession();
	}
	
	public void put(String key , Object value){
		getSession().setAttribute(key, value);
	}
	public Object get(String key){
		return getSession().getAttribute(key);
	}
	public Object get(String key ,Object defaultValue){
		Object object = getSession().getAttribute(key);
		if(object == null){
			return defaultValue;
		}
		return object;
	}
}
