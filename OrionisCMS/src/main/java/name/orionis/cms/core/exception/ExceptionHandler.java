package name.orionis.cms.core.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
		System.out.println("hello");
		return new ModelAndView("errors/exceptions");
	}

}
