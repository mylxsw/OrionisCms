package name.orionis.cms.core.rbac.service;

import java.util.ArrayList;
import java.util.List;

import name.orionis.cms.core.rbac.dto.NavItem;
import name.orionis.cms.core.rbac.model.RbacMenu;
import name.orionis.cms.core.rbac.model.RbacRole;
import name.orionis.cms.core.rbac.model.Status;

public class RbacMenuServiceImpl implements RbacMenuService {
	@Override
	public void deleteRbacMenuCascade(RbacMenu menu){
		// Search for all submenus
		List<Long> ids = _subMenuIds(menu.getId());
		
		// delete all submenus
		for(Long id : ids){
			RbacMenu.findRbacMenu(id).remove();
		}
		
		// delete current menu item
		menu.remove();
	}
	private List<Long> _subMenuIds(Long id) {
		List<RbacMenu> list = RbacMenu.findRbacMenusByParentIdEquals(id).getResultList();
		List<Long> result = new ArrayList<Long>();

		for(RbacMenu m : list){
			result.add(m.getId());
			List<Long> _subMenuIds = _subMenuIds(m.getId());
			if(_subMenuIds.size() > 0){
				result.addAll(_subMenuIds);
			}
		}
		return result;
	}
	@Override
	public NavItem listMenusTree(long id){
		return _navTree(RbacRole.findRbacRole(id), 0L);
	}
	/**
	 * Generate Menu Tree
	 * @param role
	 * @param topLevel
	 * @return
	 */
	private NavItem _navTree(RbacRole role, long topLevel){
		NavItem root = new NavItem();
		List<RbacMenu> topMenus = RbacMenu.findRbacMenusByRbacRoleAndParentIdEquals(
				role, topLevel).getResultList();
		
		for(RbacMenu m : topMenus){
			NavItem _navTree = _navTree(role, m.getId());
			NavItem newItem = new NavItem()
				.setId(m.getId())
				.setLinkName(m.getMenuName())
				.setValid(m.getStatus() == Status.ENABLED).setLinkUrl(m.getUrl());
			
			if(_navTree.getSubItems().size() > 0){
				newItem.setFolder(true);
			}
			newItem.setSubItems(_navTree.getSubItems());
			root.add(newItem);
		}
		
		return root;
	}
}
