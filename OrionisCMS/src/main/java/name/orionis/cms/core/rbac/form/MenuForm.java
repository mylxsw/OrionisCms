package name.orionis.cms.core.rbac.form;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Value;

import name.orionis.cms.core.base.Form;
import name.orionis.cms.core.rbac.model.RbacMenu;
import name.orionis.cms.core.rbac.model.RbacRole;
import name.orionis.cms.core.rbac.model.Status;

/**
 * Rbac Menu Form
 * @author orionis
 * @2013-5-18
 * Site : http://blog.orionis.name
 *
 */
public class MenuForm extends Form<RbacMenu> {

	@NotNull(message="菜单名必须!")
    @Size(max = 40, message="菜单名称不能多余40个字符")
    private String menuName;

    @Size(max = 100, message="菜单地址必须!")
    private String url;

    @Value("0")
    private Long parentId;
    
    @Min(value=0, message="角色id不能为空!")
    private Long roldId;
    
	@Override
	public RbacMenu toEntity() {
		RbacMenu menu = new RbacMenu();
		menu.setMenuName(menuName);
		menu.setUrl(url);
		menu.setParentId(parentId);
		menu.setRbacRole(RbacRole.findRbacRole(roldId));
		menu.setStatus(Status.ENABLED);
		
		return menu;
	}

	public String getMenuName() {
		return menuName;
	}

	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getParentId() {
		return parentId;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getRoldId() {
		return roldId;
	}

	public void setRoldId(Long roldId) {
		this.roldId = roldId;
	}

}
