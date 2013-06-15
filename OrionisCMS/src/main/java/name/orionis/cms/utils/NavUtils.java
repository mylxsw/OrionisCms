package name.orionis.cms.utils;

import name.orionis.cms.core.rbac.dto.NavItem;
/**
 * Navigation Utils
 * @author orionis
 * @2013-5-19
 * Site : http://blog.orionis.name
 *
 */
public class NavUtils {
	/**
	 * Parse root, generate navigation structure
	 * @param root
	 * @return
	 */
	public static String genNav(NavItem items){
		
		if(items == null){
			return "";
		}
		
		StringBuffer buffer = new StringBuffer();
		buffer.append("<ul>");
		for(NavItem item : items.getSubItems()){
			if(item.isMenuSep()){
				buffer.append("<li class=\"menusep\" ><span class=\"menuline\"></span></li>");
				continue;
			}
			buffer.append("<li><a href=\"" + item.getLinkUrl() + "\" class=\"parse\" folder=\"" + (item.isFolder() ? "true" : "false") + "\">");
			buffer.append(item.getLinkName() + "</a>");
			if(item.isFolder()){
				buffer.append(genNav(item));
			}else{
				buffer.append("</li>");
			}
		}
		buffer.append("</ul>");
		return buffer.toString();
	}
	/**
	 * Navigation Preview
	 * @param items
	 * @return
	 */
	public static String genNavManager(NavItem items){
		StringBuffer buffer = new StringBuffer();
		buffer.append("<ul>");
		for(NavItem item : items.getSubItems()){
			if(item.isMenuSep()){
				buffer.append("<li class=\"menusep\" ><span class=\"menuline\"></span></li>");
				continue;
			}
			buffer.append("<li><span "
					+ (item.isValid() ? "" : "status='invalid'")
					+ "><a href=\"" + item.getLinkUrl() + "\"  folder=\"" + (item.isFolder() ? "true" : "false") + "\">");
			buffer.append(item.getLinkName() + "</a><span class='control' mid='" + item.getId() + "'><a href='edit' class='edit'>编辑</a>  |  <a href='del' class='del '>删除</a></span></span>");
			if(item.isFolder()){
				buffer.append(genNavManager(item));
			}else{
				buffer.append("</li>");
			}
		}
		buffer.append("</ul>");
		return buffer.toString();
	}
}
