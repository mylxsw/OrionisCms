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
			root.add(new NavItem().setLinkName("主面板").setLinkUrl("menu.my_dashboard"));
			root.add(new NavItem().setLinkName("设置").setLinkUrl("menu.setting"));
			root.add(new NavItem().setLinkName("模块").setLinkUrl("menu.module"));
			root.add(new NavItem().setLinkName("内容").setLinkUrl("menu.content"));
			root.add(new NavItem().setLinkName("用户").setLinkUrl("menu.user"));
			root.add(new NavItem().setLinkName("界面").setLinkUrl("menu.ui"));
			root.add(new NavItem().setLinkName("扩展").setLinkUrl("menu.extension"));
			root.add(new NavItem().setLinkName("应用").setLinkUrl("menu.app"));
		}
		if(position.equalsIgnoreCase("menu.my_dashboard")){ // Dashboard
			root.add(new NavItem().setLinkName("个人信息").setFolder(true)
					.add(new NavItem().setLinkName("修改个人信息").setLinkUrl("account/change_personal"))
					.add(new NavItem().setLinkName("修改密码").setLinkUrl("account/change_password")));
		}else if(position.equalsIgnoreCase("menu.setting")){ // Settings
			root.add(new NavItem().setLinkName("相关设置").setFolder(true)
					.add(new NavItem().setLinkName("站点设置").setLinkUrl("account/change_personal"))
					.add(new NavItem().setLinkName("基本设置").setLinkUrl("account/change_password"))
					.add(new NavItem().setLinkName("安全设置").setLinkUrl(""))
					.add(new NavItem().setLinkName("邮箱设置").setLinkUrl("")));
			root.add(new NavItem().setLinkName("管理员设置").setFolder(true)
					.add(new NavItem().setLinkName("管理员管理").setLinkUrl("rbac/user/list"))
					.add(new NavItem().setLinkName("角色管理").setLinkUrl("rbac/role/list")));
		}else if(position.equalsIgnoreCase("menu.module")){ // Modules
			root.add(new NavItem().setLinkName("模块管理").setFolder(true)
					.add(new NavItem().setLinkName("广告").setLinkUrl("advertisement/list"))
					.add(new NavItem().setLinkName("公告").setLinkUrl("announce/list"))
					.add(new NavItem().setLinkName("消息").setLinkUrl("message/list"))
					.add(new NavItem().setLinkName("短信").setLinkUrl("sms/list"))
					.add(new NavItem().setLinkName("评论").setLinkUrl("remark/list"))
					.add(new NavItem().setLinkName("友情链接").setLinkUrl("blogroll/list")) // Blogroll
					.add(new NavItem().setLinkName("投票").setLinkUrl("vote/list"))
					.add(new NavItem().setLinkName("搜索").setLinkUrl("search/list")));
		}else if(position.equalsIgnoreCase("menu.content")){ // Context
			root.add(new NavItem().setLinkName("内容管理").setFolder(true)
					.add(new NavItem().setLinkName("文章").setLinkUrl("content/list"))
					.add(new NavItem().setLinkName("附件").setLinkUrl("attachment/list"))
					.add(new NavItem().setLinkName("专题").setLinkUrl("subject/list"))
					.add(new NavItem().setLinkName("碎片").setLinkUrl("fragment/list"))
					.add(new NavItem().setLinkName("收集").setLinkUrl("collection/list"))
					.add(new NavItem().setLinkName("评论管理").setLinkUrl("remark/list")));
			root.add(new NavItem().setLinkName("缓存管理").setFolder(true)
					.add(new NavItem().setLinkName("刷新栏目缓存").setLinkUrl("account/change_personal"))
					.add(new NavItem().setLinkName("刷新首页缓存").setLinkUrl("account/change_password"))
					.add(new NavItem().setLinkName("刷新URL缓存").setLinkUrl(""))
					.add(new NavItem().setLinkName("同步刷新").setLinkUrl("")));
			root.add(new NavItem().setLinkName("内容设置").setFolder(true)
					.add(new NavItem().setLinkName("分类管理").setLinkUrl("account/change_personal"))
					.add(new NavItem().setLinkName("模块管理").setLinkUrl("account/change_password")));
		}else if(position.equalsIgnoreCase("menu.user")){ // User
			root.add(new NavItem().setLinkName("用户管理").setFolder(true)
					.add(new NavItem().setLinkName("用户审核").setLinkUrl("account/change_personal"))
					.add(new NavItem().setLinkName("用户模型管理").setLinkUrl("account/change_password"))
					.add(new NavItem().setLinkName("用户组管理").setLinkUrl(""))
					.add(new NavItem().setLinkName("模型管理").setLinkUrl("")));
		}else if(position.equalsIgnoreCase("menu.ui")){ // UI
			root.add(new NavItem().setLinkName("模板管理").setFolder(true)
					.add(new NavItem().setLinkName("模板样式").setLinkUrl("account/change_personal"))
					.add(new NavItem().setLinkName("标签库").setLinkUrl("account/change_password")));
		}else if(position.equalsIgnoreCase("menu.extension")){
			
		}else if(position.equalsIgnoreCase("menu.app")){
			
		}
		
		return root;
	}

}
