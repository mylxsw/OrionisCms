package name.orionis.cms.core.rbac.authentication;
/**
 * 
 * Config Helper
 * @author code.404
 *
 */
public class ConfigHelper {
	private String godUser;
	private String godPassword;
	private boolean allowGodMode;
	private boolean devMode;
	
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
}
