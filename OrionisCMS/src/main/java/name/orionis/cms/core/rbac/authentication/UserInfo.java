package name.orionis.cms.core.rbac.authentication;

import org.apache.commons.codec.digest.DigestUtils;

import name.orionis.cms.core.rbac.web.AccountController;
/**
 * Stored Current User information
 * @author orionis
 * @2013-5-16
 * Site : http://blog.orionis.name
 *
 */
public class UserInfo {
	private Long userId;
	private Long roleId;
	private String username;
	/**
	 * Token , for Security use
	 */
	private String token;
	/**
	 * God Mode ,for super administrator
	 * God Mode will get all priviledge
	 */
	private boolean godMode = false;

	/**
	 * Get Current User info
	 * @return
	 */
	public static UserInfo getCurrentUser(){
		return (UserInfo) SessionHelper.getSession().getAttribute(AccountController.ACCOUNT_INFO);
	}
	
	public UserInfo(Long userId, Long roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}
	public UserInfo(){
	}
	public Long getUserId() {
		return userId;
	}
	public UserInfo setUserId(Long userId) {
		this.userId = userId;
		return this;
	}
	public Long getRoleId() {
		return roleId;
	}
	public UserInfo setRoleId(Long roleId) {
		this.roleId = roleId;
		return this;
	}

	public UserInfo setGodMode(boolean b) {
		this.godMode = b;
		return this;
	}
	public boolean isGodMode(){
		return godMode;
	}
	public String getUsername() {
		return username;
	}
	public UserInfo setUsername(String username) {
		this.username = username;
		return this;
	}
	public String getToken() {
		// if not set token ,then initialize it 
		if(token == null){
			token = DigestUtils.sha256Hex(getUsername() + "{" + System.currentTimeMillis() + "}");
		}
		return token;
	}
	public UserInfo setToken(String token) {
		this.token = token;
		return this;
	}
	
}
