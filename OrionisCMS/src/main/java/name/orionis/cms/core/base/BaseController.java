package name.orionis.cms.core.base;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import name.orionis.cms.utils.Constant;
import name.orionis.cms.utils.JsonConverter;
import name.orionis.cms.utils.MessageBuilder;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
/**
 * Base Controller
 * @author code.404
 * @2013-5-13
 * Site : http://blog.orionis.name
 * 
 * every controllers need to extends this controller
 *
 */
public abstract class BaseController implements ApplicationContextAware {
	
	private ApplicationContext applicationContext;
	protected final Logger log = LoggerFactory.getLogger(getClass());
	final protected static String STATUS_SUCCESS = "1";
	final protected static String STATUS_FAILED = "0";
	final protected static String STATUS_ERROR = "-1";
	
	final protected static String HTTP_GET = "GET";
	final protected static String HTTP_POST = "POST";
	
	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}
	/**
	 * Get Spring Application Context
	 * @return
	 */
	protected ApplicationContext getApplicationContext(){
		return applicationContext;
	}
	
	
	/**
	 * Return View Folder, need suffix with "/"
	 * @return
	 */
	abstract protected String _viewBase();
	
	/**
	 * Get current view name
	 * @param viewName
	 * @return
	 */
	protected String view(String viewName){
		return _viewBase() + viewName;
	}
	
	/**
	 * Return an ajax message
	 * @param map
	 * @param resp
	 * @return
	 */
	protected String ajax(Map<String, String> map, HttpServletResponse resp){
		String json = JsonConverter.mapToJson(map);
		resp.setCharacterEncoding("UTF-8");
		try {
			resp.getWriter().print(json);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Return an ajax message
	 * @param info
	 * @param status
	 * @param resp
	 * @return
	 */
	protected String ajax(String info, String status , HttpServletResponse resp){
		Map<String, String> map = new HashMap<String, String>();
		map.put("info", info);
		map.put("status", status);
		
		return ajax(map, resp);
	}
	/**
	 * Return an ajax message
	 * @param data
	 * @param info
	 * @param status
	 * @param resp
	 * @return
	 */
	protected String ajax(Map<String ,String> data, String info, 
			String status, HttpServletResponse resp){
		Map<String,String> map = new HashMap<String, String>();
		map.putAll(data);
		map.put("info", info);
		map.put("status", status);
		
		return ajax(map, resp);
	}
	
	/**
	 * Send an redirect
	 * @param url
	 * @return
	 */
	protected String redirect(String url){
		return "redirect:" + url;
	}
	
	/**
	 * Send an redirect with flash data
	 * @param url
	 * @param flashData
	 * @param redirectAttr
	 * @return
	 */
	protected String redirect(String url, Map<String, Object> flashData, 
			RedirectAttributes redirectAttr){
		for(Map.Entry<String, Object> m : flashData.entrySet()){
			redirectAttr.addFlashAttribute(m.getKey(), m.getValue());
		}
		return "redirect:" + url;
	}
	/**
	 * Return Form Check error Messages
	 * @param result
	 * @param form
	 * @param resp
	 * @return
	 */
	protected String errors(BindingResult result, Form<?> form, 
			HttpServletResponse resp){
		Map<String,String> map = new HashMap<String,String>();
		map.put("errors", MessageBuilder.init().
				addMessage(result.getFieldErrors()).
				addMessage(form.errorMessages()).
				build());
		return ajax(map, 
				Constant.MESSAGE_ACTION_FORM_WRONG, STATUS_FAILED, resp);
	}
	/**
	 * Return Action Successfully Message
	 * @param resp
	 * @return
	 */
	protected String success(HttpServletResponse resp){
		return ajax(Constant.MESSAGE_ACTION_SUCCESS, STATUS_SUCCESS, resp);
	}
	/**
	 * Return Action failed Message
	 * @param resp
	 * @return
	 */
	protected String failed(HttpServletResponse resp){
		return ajax(Constant.MESSAGE_ACTION_FAILED, STATUS_FAILED, resp);
	}
}
