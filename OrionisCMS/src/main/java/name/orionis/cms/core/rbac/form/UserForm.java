package name.orionis.cms.core.rbac.form;


import java.util.Date;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;

import name.orionis.cms.core.base.Form;
import name.orionis.cms.core.rbac.model.RbacRole;
import name.orionis.cms.core.rbac.model.RbacUser;
import name.orionis.cms.core.rbac.model.Status;
import name.orionis.cms.utils.Encrypt;

/**
 * User Form
 * @author orionis
 * @2013-5-17
 * Site : http://blog.orionis.name
 *
 */
public class UserForm extends Form<RbacUser> {
	@NotNull
    @Size(min = 3, max = 50, message="ui.msg.usernameFormatError")
    private String userName;

    @Size(min = 6, max = 30, message="ui.msg.passwordmustin630")
    private String password;

    private String email;

    @Size(max = 50, message="ui.msg.realnamemustlt50")
    private String realName;
    
    private long roleId;
    
	@Override
	public RbacUser toEntity() {
		RbacUser user = new RbacUser();
		user.setUserName(userName);
		user.setPassword(Encrypt.encryptPassword(password, userName));
		user.setEmail(email);
		user.setCreateData(new Date(System.currentTimeMillis()));
		user.setRealName(realName);
		user.setStatus(Status.ENABLED);
		user.setRbacRole(RbacRole.findRbacRole(roleId));
		
		return user;
	}
	
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}


	public long getRoleId() {
		return roleId;
	}


	public void setRoleId(long roleId) {
		this.roleId = roleId;
	}
	
}
