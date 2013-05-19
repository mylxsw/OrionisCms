package name.orionis.cms.core.rbac.authentication;


import java.util.List;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * 
 * Config Helper
 * @author code.404
 *
 */
public class ConfigHelper implements ApplicationContextAware {
	private String godUser;
	private String godPassword;
	private boolean allowGodMode;
	private boolean devMode;
	private List<String> publicAccess;// Public access page
	private List<String> anonymousOnlyAccess;// Anonymous Only Access
	private static ApplicationContext ctx;

	public static ConfigHelper getInstance(){
		return ctx.getBean(ConfigHelper.class);
	}
	
	public ConfigHelper(){
	}
	public String getGodUser() {
		return godUser;
	}
	public void setGodUser(String godUser) {
		this.godUser = godUser;
	}
	public String getGodPassword() {
		return godPassword;
	}
	public void setGodPassword(String godPassword) {
		this.godPassword = godPassword;
	}
	public boolean isAllowGodMode() {
		return allowGodMode;
	}
	public void setAllowGodMode(boolean allowGodMode) {
		this.allowGodMode = allowGodMode;
	}
	public boolean isDevMode() {
		return devMode;
	}
	public void setDevMode(boolean devMode) {
		this.devMode = devMode;
	}
	public List<String> getPublicAccess() {
		return publicAccess;
	}

	public void setPublicAccess(List<String> publicAccess) {
		this.publicAccess = publicAccess;
	}

	public List<String> getAnonymousOnlyAccess() {
		return anonymousOnlyAccess;
	}

	public void setAnonymousOnlyAccess(List<String> anonymousOnlyAccess) {
		this.anonymousOnlyAccess = anonymousOnlyAccess;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ctx = applicationContext;
	}
}
