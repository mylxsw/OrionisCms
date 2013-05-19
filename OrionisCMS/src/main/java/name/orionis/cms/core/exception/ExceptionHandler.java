package name.orionis.cms.core.exception;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import name.orionis.cms.core.rbac.authentication.PermissionDenyException;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;
/**
 * Exception Handler
 * @author orionis
 * @2013-5-18
 * Site : http://blog.orionis.name
 * 
 */
public class ExceptionHandler implements HandlerExceptionResolver {


	@Override
	public ModelAndView resolveException(HttpServletRequest request,
			HttpServletResponse response, Object handler, Exception ex) {
		response.setStatus(HttpServletResponse.SC_OK);
		
		ex.printStackTrace();
		Map<String, Object> model = new HashMap<String, Object>();
		
		if(ex instanceof PermissionDenyException){
			PermissionDenyException pde = (PermissionDenyException) ex;
			if(pde.getRedirect() != null){
				model.put("redirect", pde.getRedirect());
			}
		}
		model.put("exception",ex.getMessage());
		model.put("e", ex);
		return new ModelAndView("errors/exceptions", model);
	}

}
