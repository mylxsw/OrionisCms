package name.orionis.cms.remote.dwr;


import java.util.List;

import javax.annotation.Resource;

import name.orionis.cms.core.rbac.authentication.UserInfo;
import name.orionis.cms.core.rbac.dto.NavItem;
import name.orionis.cms.core.rbac.service.RbacMenuService;
import name.orionis.cms.core.rbac.web.AccountController;

import org.directwebremoting.WebContextFactory;
import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.stereotype.Component;

/**
 * Dwr Access Service
 * @author code.404
 * @2013-5-14
 * Site : http://blog.orionis.name
 *
 */
@Component
@RemoteProxy
public class DirectRemote {
	@Resource
	private RbacMenuService menuService;
	
	/**
	 * Navigations
	 * @param position
	 * @return
	 */
	@RemoteMethod
	public NavItem getNavigation(String position){
		
		UserInfo user = (UserInfo) WebContextFactory.get().getSession()
				.getAttribute(AccountController.ACCOUNT_INFO);
		if(user.isGodMode()){
			return _godNavs(position);
		}
		/******************************************************************
		 *				Normal User Dynamic Menus
		 ******************************************************************/
		NavItem tree = menuService.listMenusTree(user.getRoleId());
		
		// Get Top Menus
		if(position.equalsIgnoreCase("top")){
			NavItem root = new NavItem();
			List<NavItem> subItems = tree.getSubItems();
			for(NavItem n : subItems){
				root.add(new NavItem().setLinkName(n.getLinkName())
						.setLinkUrl(n.getLinkUrl()));
			}
			return root;
		}
		
		// Get Sub menus
		List<NavItem> subItems = tree.getSubItems();
		for(NavItem n: subItems){
			if(n.getLinkUrl().equals(position)){
				if(n.getSubItems().size() > 0){
					n.setFolder(true);
				}
				return n;
			}
		}
		return null;
	}
	
	
	/******************************************************************
	 * 				Super User Fixed Menus
	 ******************************************************************/
	private NavItem _godNavs(String position) {
		NavItem root = new NavItem();
		if(position.equalsIgnoreCase("top")){
			root.add(new NavItem().setLinkName("DashBoard").setLinkUrl("menu.my_dashboard"));
			root.add(new NavItem().setLinkName("Settings").setLinkUrl("menu.setting"));
			root.add(new NavItem().setLinkName("Modules").setLinkUrl("menu.module"));
			root.add(new NavItem().setLinkName("Content").setLinkUrl("menu.content"));
			root.add(new NavItem().setLinkName("Extensions").setLinkUrl("menu.extension"));
		}
		if(position.equalsIgnoreCase("menu.my_dashboard")){ // Dashboard
			root.add(new NavItem().setLinkName("Personal Information").setFolder(true)
					.add(new NavItem().setLinkName("Modify Personal Info").setLinkUrl("account/change_personal"))
					.add(new NavItem().setLinkName("Change Password").setLinkUrl("account/change_password")));
		}else if(position.equalsIgnoreCase("menu.setting")){ // Settings
			root.add(new NavItem().setLinkName("Administrators Settings").setFolder(true)
					.add(new NavItem().setLinkName("Administrator Management").setLinkUrl("rbac/user/list"))
					.add(new NavItem().setLinkName("Role Management").setLinkUrl("rbac/role/list")));
		}else if(position.equalsIgnoreCase("menu.module")){ // Modules
			root.add(new NavItem().setLinkName("Modules").setFolder(true)
					.add(new NavItem().setLinkName("Advertisement").setLinkUrl("advertisement/list"))
					.add(new NavItem().setLinkName("Announce").setLinkUrl("announce/list"))
					.add(new NavItem().setLinkName("Message").setLinkUrl("message/list"))
					.add(new NavItem().setLinkName("Blogroll").setLinkUrl("blogroll/list")) // Blogroll
					);
		}else if(position.equalsIgnoreCase("menu.content")){ // Context
			root.add(new NavItem().setLinkName("Content").setFolder(true)
					.add(new NavItem().setLinkName("Aritcle").setLinkUrl("news/list"))
					.add(new NavItem().setLinkName("Remark").setLinkUrl("remark/list")));
			root.add(new NavItem().setLinkName("Content Settings").setFolder(true)
					.add(new NavItem().setLinkName("Category").setLinkUrl("category/list")));
		}else if(position.equalsIgnoreCase("menu.extension")){
			
		}
		
		return root;
	}

}
