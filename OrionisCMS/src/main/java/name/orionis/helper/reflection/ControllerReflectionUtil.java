package name.orionis.helper.reflection;
import java.lang.annotation.Annotation;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import name.orionis.helper.reflection.annotation.Remark;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * Controller Information Reflection Tools
 * @author code.404
 *
 */
@SuppressWarnings("rawtypes")
public class ControllerReflectionUtil {

	/**
	 * Get All Controller Class From Spring Context
	 * @param ctx 
	 * @param packagePrefix
	 * @param retrievalMethods
	 * @return
	 */
	public static List<ControllerClassInfo> getControllers(
			ApplicationContext ctx,
			String packagePrefix, 
			boolean retrievalMethods ){
		
		// Add  Multiple packagePrefix Support
		String[] packagePrefix_split = packagePrefix.split("&&");
		
		List<ControllerClassInfo> results = new ArrayList<ControllerClassInfo>();
		
		// Get All Controller in Spring Context With @Contrller Annotation
		Map<String, Object> beansWithAnnotation = ctx.getBeansWithAnnotation(Controller.class);
		Set<Entry<String, Object>> entrySet = beansWithAnnotation.entrySet();
		Iterator<Entry<String, Object>> iterator = entrySet.iterator();
		
		while(iterator.hasNext()){
			Entry<String, Object> next = iterator.next();
			
			// Get all controller`s class name, Something important is that 
			// ,Most of the class has been rewrote by CGLib
			// So we need exclude $$ part
			String simpleName = next.getValue().getClass().getName();
			int indexOf = simpleName.indexOf("$$");
			
			// get the base package name
			boolean flag = false;
			for(int i = 0; i < packagePrefix_split.length; i ++){
				if(simpleName.indexOf(packagePrefix_split[i].trim()) != 0){
					continue;
				}
				flag = true;
				break;
			}
			if(!flag){
				continue;
			}
			
			
			// get the really controller class name
			String className = indexOf == -1 ? simpleName : simpleName.substring(0, indexOf );
			
			try {
				Class<?> loadClass = ctx.getClass().getClassLoader().loadClass(className);
				ControllerClassInfo info = new ControllerClassInfo();
				info.setClassName(className);
				info.setClazz(loadClass);
				Remark remark = loadClass.getAnnotation(Remark.class);
				if(remark != null){
					info.setRemark(remark.value());
					info.setGroup(remark.group());
				}
				// get all of the method in the class
				if(retrievalMethods){
					List<ActionMethodInfo> actionMethods = getActionMethods(loadClass, true);
					info.setMethods(actionMethods);
				}
				
				results.add(info);
			} catch (ClassNotFoundException e) {}
		}
		
		Collections.sort(results);
		return results;
		
	}
	/**
	 * Get all of the web access method in specified controller
	 * @param clazz
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public static List<ActionMethodInfo> getActionMethods(
			Class clazz, boolean onlyAnnotated){
		// make sure @controller annotated
		Annotation isController = clazz.getAnnotation(Controller.class);
		if(isController == null){
			return null;
		}
		// get the @RequestMapping annotation all Controller class
		RequestMapping requestMappingForClass = (RequestMapping) 
				clazz.getAnnotation(RequestMapping.class);

		List<ActionMethodInfo> results = new ArrayList<ActionMethodInfo>();
		// get all the public method in controller
		Method[] methods = clazz.getDeclaredMethods();
		for(int i = 0; i < methods.length; i ++){
			RequestMapping annotation = methods[i]
					.getAnnotation(RequestMapping.class);
			if(onlyAnnotated){// only supported @RequestMapping annotation
				if(annotation == null){
					continue;
				}
			}else{
				// if the method is start with get* or set* or _*, 
				// we ignore it as private method
				if(methods[i].getName().startsWith("get") 
						|| methods[i].getName().startsWith("set")
						|| methods[i].getName().startsWith("_")){
					continue;
				}
			}
			
			// fill datas
			ActionMethodInfo info = new ActionMethodInfo();
			info.setBelong(clazz.getName());
			info.setMethodName(methods[i].getName());
			
			// get method access url
			
			if(annotation != null){// access based on @RequestMapping
				// annotation mapping address
				String[] value = annotation.value();
				
				// process multiple mapping url
				if(requestMappingForClass != null ){
					String[] mapping = requestMappingForClass.value();
					String [] url = 
							new String [mapping.length * annotation.value().length];
					
					int cur = 0;
					for(int x = 0; x < mapping.length ; x ++){// the controller maps
						String prefix = mapping[x] 
								+ (mapping[x].endsWith("/") ? "" : "/");
						for(int y = 0; y < value.length; y ++){// the method maps
							url[cur] = prefix + (value[y].indexOf("/") == 0 ? 
									value[y].substring(1) : value[y]);
							cur ++;
						}
					}
					info.setUrl(url);
				}else{
					info.setUrl(value);
				}
				
			}else{// Based on convention
				String simpleClassName = clazz.getSimpleName();
				String controller = simpleClassName
						.substring(0, simpleClassName.indexOf("Controller"));
				
				info.setUrl(new String [] {"/" + controller.toLowerCase() 
						+ "/" + methods[i].getName()});
			}
			
			// get method comment and groups
			Remark remark = methods[i].getAnnotation(Remark.class);
			if(remark != null){
				info.setRemark(remark.value());
				info.setGroup(remark.group());
			}
			
			
			results.add(info);
		}
		Collections.sort(results);
		return results;
	}
}
