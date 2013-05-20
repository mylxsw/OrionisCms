package name.orionis.cms.core.rbac.service;

import name.orionis.cms.core.rbac.dto.NavItem;
import name.orionis.cms.core.rbac.model.RbacMenu;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { name.orionis.cms.core.rbac.model.RbacMenu.class })
public interface RbacMenuService {

	/**
	 * Get Menu Tree
	 * @param id
	 * @return
	 */
	NavItem listMenusTree(long id);

	/**
	 * Delete Menus cascade
	 * @param findRbacMenu
	 */
	void deleteRbacMenuCascade(RbacMenu findRbacMenu);
	/**
	 * Update Menu
	 * @param menu
	 * @return
	 */
	public RbacMenu updateRbacMenu(RbacMenu menu);
}
