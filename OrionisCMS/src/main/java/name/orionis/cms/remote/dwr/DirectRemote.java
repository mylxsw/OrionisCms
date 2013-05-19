package name.orionis.cms.remote.dwr;

import java.util.Locale;

import name.orionis.cms.core.rbac.dto.NavItem;

import org.directwebremoting.annotations.RemoteMethod;
import org.directwebremoting.annotations.RemoteProxy;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
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
public class DirectRemote implements ApplicationContextAware {
	private ApplicationContext ctx;
	
	@RemoteMethod
	public NavItem getNavigation(String position){
		NavItem root = new NavItem();
		if(position.equalsIgnoreCase("top")){
			root.add(new NavItem().setLinkName(msg("menu.my_dashboard")).setLinkUrl("menu.my_dashboard"));
			root.add(new NavItem().setLinkName(msg("menu.setting")).setLinkUrl("menu.setting"));
			root.add(new NavItem().setLinkName(msg("menu.module")).setLinkUrl("menu.module"));
			root.add(new NavItem().setLinkName(msg("menu.content")).setLinkUrl("menu.content"));
			root.add(new NavItem().setLinkName(msg("menu.user")).setLinkUrl("menu.user"));
			root.add(new NavItem().setLinkName(msg("menu.ui")).setLinkUrl("menu.ui"));
			root.add(new NavItem().setLinkName(msg("menu.extension")).setLinkUrl("menu.extension"));
			root.add(new NavItem().setLinkName(msg("menu.app")).setLinkUrl("menu.app"));
		}
		if(position.equalsIgnoreCase("menu.my_dashboard")){
			root.add(new NavItem().setLinkName(msg("menu.personal.information")).setFolder(true)
					.add(new NavItem().setLinkName(msg("menu.personal.information.change")).setLinkUrl("account/change_personal"))
					.add(new NavItem().setLinkName(msg("menu.personal.password.change")).setLinkUrl("account/change_password")));
		}else if(position.equalsIgnoreCase("menu.setting")){
			root.add(new NavItem().setLinkName(msg("menu.setting.relatesetting")).setFolder(true)
					.add(new NavItem().setLinkName(msg("menu.setting.site")).setLinkUrl("account/change_personal"))
					.add(new NavItem().setLinkName(msg("menu.setting.base")).setLinkUrl("account/change_password"))
					.add(new NavItem().setLinkName(msg("menu.setting.security")).setLinkUrl(""))
					.add(new NavItem().setLinkName(msg("menu.setting.email")).setLinkUrl("")));
			root.add(new NavItem().setLinkName(msg("menu.admin.setting")).setFolder(true)
					.add(new NavItem().setLinkName(msg("menu.admin.adminmanage")).setLinkUrl("account/change_personal"))
					.add(new NavItem().setLinkName(msg("menu.admin.rolemanage")).setLinkUrl("rbac/role/list")));
		}else if(position.equalsIgnoreCase("menu.module")){
			root.add(new NavItem().setLinkName(msg("menu.module.modulemanage")).setFolder(true)
					.add(new NavItem().setLinkName(msg("menu.module.advertisement")).setLinkUrl("account/change_personal"))
					.add(new NavItem().setLinkName(msg("menu.module.announce")).setLinkUrl("account/change_password"))
					.add(new NavItem().setLinkName(msg("menu.module.message")).setLinkUrl(""))
					.add(new NavItem().setLinkName(msg("menu.module.sms")).setLinkUrl(""))
					.add(new NavItem().setLinkName(msg("menu.module.remark")).setLinkUrl(""))
					.add(new NavItem().setLinkName(msg("menu.module.friendlink")).setLinkUrl(""))
					.add(new NavItem().setLinkName(msg("menu.module.vote")).setLinkUrl(""))
					.add(new NavItem().setLinkName(msg("menu.module.search")).setLinkUrl("")));
		}else if(position.equalsIgnoreCase("menu.content")){
			root.add(new NavItem().setLinkName(msg("menu.content.content_pub_manage")).setFolder(true)
					.add(new NavItem().setLinkName(msg("menu.content.attachment_manage")).setLinkUrl("account/change_personal"))
					.add(new NavItem().setLinkName(msg("menu.content.subject")).setLinkUrl("account/change_password"))
					.add(new NavItem().setLinkName(msg("menu.content.fragment_manage")).setLinkUrl(""))
					.add(new NavItem().setLinkName(msg("menu.content.collection")).setLinkUrl(""))
					.add(new NavItem().setLinkName(msg("menu.content.remark_manage")).setLinkUrl("")));
			root.add(new NavItem().setLinkName(msg("menu.content.pub.pub_manage")).setFolder(true)
					.add(new NavItem().setLinkName(msg("menu.content.pub.refresh.category")).setLinkUrl("account/change_personal"))
					.add(new NavItem().setLinkName(msg("menu.content.pub.refresh.index")).setLinkUrl("account/change_password"))
					.add(new NavItem().setLinkName(msg("menu.content.pub.refresh.url")).setLinkUrl(""))
					.add(new NavItem().setLinkName(msg("menu.content.pub.refresh.synchronization")).setLinkUrl("")));
			root.add(new NavItem().setLinkName(msg("menu.content.relation.content_relation_setting")).setFolder(true)
					.add(new NavItem().setLinkName(msg("menu.content.relation.category_manage")).setLinkUrl("account/change_personal"))
					.add(new NavItem().setLinkName(msg("menu.content.relation.modle_manage")).setLinkUrl("account/change_password")));
		}else if(position.equalsIgnoreCase("menu.user")){
			root.add(new NavItem().setLinkName(msg("menu.user.usermanage")).setFolder(true)
					.add(new NavItem().setLinkName(msg("menu.user.audituser")).setLinkUrl("account/change_personal"))
					.add(new NavItem().setLinkName(msg("menu.user.usermodule.setting")).setLinkUrl("account/change_password"))
					.add(new NavItem().setLinkName(msg("menu.user.group.manage")).setLinkUrl(""))
					.add(new NavItem().setLinkName(msg("menu.user.model.manage")).setLinkUrl("")));
		}else if(position.equalsIgnoreCase("menu.ui")){
			root.add(new NavItem().setLinkName(msg("menu.ui.template_manage")).setFolder(true)
					.add(new NavItem().setLinkName(msg("menu.ui.template.style")).setLinkUrl("account/change_personal"))
					.add(new NavItem().setLinkName(msg("menu.ui.taglib")).setLinkUrl("account/change_password")));
		}else if(position.equalsIgnoreCase("menu.extension")){
			
		}else if(position.equalsIgnoreCase("menu.app")){
			
		}
		
		return root;
	}

	@Override
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		ctx = applicationContext;
	}
	private String msg(String key){
		return ctx.getMessage(key, new Object[]{},  Locale.getDefault());
	}
}
