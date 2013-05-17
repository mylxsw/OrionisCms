package name.orionis.cms.core.base;

/**
 * Form Object Super Class
 * @author orionis
 * @2013-5-17
 * Site : http://blog.orionis.name
 *
 * @param <T> The entity you want to convert
 */
public abstract class Form<T> {
	protected String errorMessages = "";
	
	/**
	 * add extra validation Logic
	 * @return
	 */
	public boolean validate(){
		return true;
	}
	
	/**
	 * Convert to Entity
	 * @return
	 */
	public abstract T toEntity();
	/**
	 * Get error Messages
	 * @return
	 */
	public String errorMessages(){
		return errorMessages;
	}
}
