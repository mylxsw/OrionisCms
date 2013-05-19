package name.orionis.cms.core.rbac.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections.map.LinkedMap;
import org.directwebremoting.annotations.DataTransferObject;
import org.directwebremoting.annotations.RemoteProperty;
/**
 * Navigation Items
 * @author code.404
 *
 */
@DataTransferObject
public class NavItem implements Serializable {
	private static final long serialVersionUID = 1L;
	@RemoteProperty
	private long id;
	@RemoteProperty
	private String linkName;
	@RemoteProperty
	private String linkUrl;
	@RemoteProperty
	private String description;
	@RemoteProperty
	private boolean folder = false;
	@RemoteProperty
	private List<NavItem> subItems = new ArrayList<NavItem>();
	@RemoteProperty
	private boolean isMenuSep = false;
	@RemoteProperty
	private boolean isValid = true;
	
	@SuppressWarnings("unchecked")
	public static Map<Long, String> toMap(NavItem root, String prefix, int offset){
		Map<Long, String> result = new LinkedMap();
		if(root.getId() > 0 ){
			result.put(root.getId(), prefix.substring(0, prefix.length() - offset * 2) + root.getLinkName());
		}
		if(root.getSubItems().size() > 0 ){
			for(NavItem v: root.getSubItems()){
				Map<Long, String> map = toMap(v, prefix + prefix, offset);
				result.putAll(map);
			}
		}
		return result;
	}
	
	public boolean isValid() {
		return isValid;
	}
	public NavItem setValid(boolean isValid) {
		this.isValid = isValid;
		return this;
	}
	/**
	 * Get Navigation id
	 * @return
	 */
	public long getId() {
		return id;
	}
	/**
	 * Set Navigation Id
	 * @param id
	 * @return
	 */
	public NavItem setId(long id) {
		this.id = id;
		return this;
	}
	/**
	 * Navigation Item Name
	 * @return
	 */
	public String getLinkName() {
		return linkName;
	}
	/**
	 * Set Navigation Item Name
	 * @param linkName
	 * @return
	 */
	public NavItem setLinkName(String linkName) {
		this.linkName = linkName;
		return this;
	}
	/**
	 * Navigation Item link
	 * @return
	 */
	public String getLinkUrl() {
		return linkUrl;
	}
	/**
	 * Set link
	 * @param linkUrl
	 * @return
	 */
	public NavItem setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
		return this;
	}
	/**
	 * Navigation description
	 * @return
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * set navigation description
	 * @param description
	 * @return
	 */
	public NavItem setDescription(String description) {
		this.description = description;
		return this;
	}
	/**
	 * Whether this is a folder
	 * If this is a folder, you can not click it and it can have subitems.
	 * default is false
	 * @return
	 */
	public boolean isFolder() {
		return folder;
	}
	/**
	 * set this item as a folder
	 * @param folder
	 * @return
	 */
	public NavItem setFolder(boolean folder) {
		this.folder = folder;
		return this;
	}
	/**
	 * get all subitems
	 * @return
	 */
	public List<NavItem> getSubItems() {
		return subItems;
	}
	/**
	 * add a sub item
	 * @param subItem
	 * @return
	 */
	public NavItem add(NavItem subItem) {
		this.subItems.add(subItem);
		return this;
	}
	public NavItem setSubItems(List<NavItem> sub){
		this.subItems = sub;
		return this;
	}
	/**
	 * separator
	 * @return
	 */
	public boolean isMenuSep() {
		return isMenuSep;
	}
	/**
	 * separator
	 * @param isMenuSep
	 * @return
	 */
	public NavItem setMenuSep(boolean isMenuSep) {
		this.isMenuSep = isMenuSep;
		return this;
	}
}
