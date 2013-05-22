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
			root.add(new NavItem().setLinkName("Dashboard").setLinkUrl("menu.my_dashboard"));
			root.add(new NavItem().setLinkName("Setting").setLinkUrl("menu.setting"));
			root.add(new NavItem().setLinkName("Module").setLinkUrl("menu.module"));
			root.add(new NavItem().setLinkName("Content").setLinkUrl("menu.content"));
			root.add(new NavItem().setLinkName("User").setLinkUrl("menu.user"));
			root.add(new NavItem().setLinkName("UI").setLinkUrl("menu.ui"));
			root.add(new NavItem().setLinkName("Extension").setLinkUrl("menu.extension"));
			root.add(new NavItem().setLinkName("App").setLinkUrl("menu.app"));
		}
		if(position.equalsIgnoreCase("menu.my_dashboard")){ // Dashboard
			root.add(new NavItem().setLinkName("Personal Information").setFolder(true)
					.add(new NavItem().setLinkName("Modify Personal Info").setLinkUrl("account/change_personal"))
					.add(new NavItem().setLinkName("Change Password").setLinkUrl("account/change_password")));
		}else if(position.equalsIgnoreCase("menu.setting")){ // Settings
			root.add(new NavItem().setLinkName("Related Settings").setFolder(true)
					.add(new NavItem().setLinkName("Site Setting").setLinkUrl("account/change_personal"))
					.add(new NavItem().setLinkName("Basic Setting").setLinkUrl("account/change_password"))
					.add(new NavItem().setLinkName("Security Setting").setLinkUrl(""))
					.add(new NavItem().setLinkName("Email Setting").setLinkUrl("")));
			root.add(new NavItem().setLinkName("Admin Settings").setFolder(true)
					.add(new NavItem().setLinkName("Admin Management").setLinkUrl("rbac/user/list"))
					.add(new NavItem().setLinkName("Role Management").setLinkUrl("rbac/role/list")));
		}else if(position.equalsIgnoreCase("menu.module")){ // Modules
			root.add(new NavItem().setLinkName("Modules Management").setFolder(true)
					.add(new NavItem().setLinkName("Advertisement").setLinkUrl("advertisement/list"))
					.add(new NavItem().setLinkName("Announce").setLinkUrl("announce/list"))
					.add(new NavItem().setLinkName("Message").setLinkUrl("message/list"))
					.add(new NavItem().setLinkName("SMS").setLinkUrl("sms/list"))
					.add(new NavItem().setLinkName("Remark").setLinkUrl("remark/list"))
					.add(new NavItem().setLinkName("Blogroll").setLinkUrl("blogroll/list")) // Blogroll
					.add(new NavItem().setLinkName("Vote").setLinkUrl("vote/list"))
					.add(new NavItem().setLinkName("Search").setLinkUrl("search/list")));
		}else if(position.equalsIgnoreCase("menu.content")){ // Context
			root.add(new NavItem().setLinkName("Content Management").setFolder(true)
					.add(new NavItem().setLinkName("Attachment").setLinkUrl("attachment/list"))
					.add(new NavItem().setLinkName("Subject").setLinkUrl("subject/list"))
					.add(new NavItem().setLinkName("Fragment").setLinkUrl("fragment/list"))
					.add(new NavItem().setLinkName("Collection").setLinkUrl("collection/list"))
					.add(new NavItem().setLinkName("Remark Management").setLinkUrl("remark/list")));
			root.add(new NavItem().setLinkName("Cached Management").setFolder(true)
					.add(new NavItem().setLinkName("Category Refresh").setLinkUrl("account/change_personal"))
					.add(new NavItem().setLinkName("Index Page Refresh").setLinkUrl("account/change_password"))
					.add(new NavItem().setLinkName("Url Refresh").setLinkUrl(""))
					.add(new NavItem().setLinkName("Synchronization Refresh").setLinkUrl("")));
			root.add(new NavItem().setLinkName("Content Settings").setFolder(true)
					.add(new NavItem().setLinkName("Category Management").setLinkUrl("account/change_personal"))
					.add(new NavItem().setLinkName("Module Management").setLinkUrl("account/change_password")));
		}else if(position.equalsIgnoreCase("menu.user")){ // User
			root.add(new NavItem().setLinkName("User Management").setFolder(true)
					.add(new NavItem().setLinkName("User Audit").setLinkUrl("account/change_personal"))
					.add(new NavItem().setLinkName("User Module Setting").setLinkUrl("account/change_password"))
					.add(new NavItem().setLinkName("Group Management").setLinkUrl(""))
					.add(new NavItem().setLinkName("Model Management").setLinkUrl("")));
		}else if(position.equalsIgnoreCase("menu.ui")){ // UI
			root.add(new NavItem().setLinkName("Template Management").setFolder(true)
					.add(new NavItem().setLinkName("Template Style").setLinkUrl("account/change_personal"))
					.add(new NavItem().setLinkName("Taglib").setLinkUrl("account/change_password")));
		}else if(position.equalsIgnoreCase("menu.extension")){
			
		}else if(position.equalsIgnoreCase("menu.app")){
			
		}
		
		return root;
	}

}
