package name.orionis.cms.core.rbac.authentication;

public class UserInfo {
	private String userId;
	private short roleId;
	private String username;
	private String token;
	private boolean godMode = false;
	
	
	public UserInfo(String userId, short roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}
	public UserInfo(){
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public short getRoleId() {
		return roleId;
	}
	public void setRoleId(short roleId) {
		this.roleId = roleId;
	}

	public UserInfo setGodMode(boolean b) {
		this.godMode = b;
		return this;
	}
	public boolean isGodMode(){
		return godMode;
	}
	public String getUsername() {
		if(username == null){
			username = userId;
		}
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
