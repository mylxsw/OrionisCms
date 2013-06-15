package name.orionis.cms.extensions.blogroll.form;

import java.util.Date;

import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


import name.orionis.cms.core.base.Form;
import name.orionis.cms.core.rbac.authentication.SessionHelper;
import name.orionis.cms.extensions.blogroll.model.BlogRoll;
import name.orionis.cms.extensions.blogroll.model.BlogrollCategory;
/**
 * Blogroll Form
 * @author code.404
 * @2013-5-21
 * Site : http://blog.orionis.name
 *
 */
public class BlogrollForm extends Form<BlogRoll> {

    @Size(min = 1, max = 100, message="链接名称必须为1-100个字符长度")
    private String linkName;

    @Size(max = 100,message="链接地址长度不能大于100字符")
    private String linkUrl;

    @Size(max = 100)
    private String logo;

    @Size(max = 200, message="简介信息不能大于200字符")
    private String introduce;

    private int listOrder;

    private int linkType;
    
    @NotNull
    private long categoryId;

	@Override
	public BlogRoll toEntity() {
		BlogRoll blogRoll = new BlogRoll();
		blogRoll.setLinkName(linkName);
		blogRoll.setLinkUrl(linkUrl);
		blogRoll.setIntroduce(introduce);
		blogRoll.setListOrder(listOrder);
		blogRoll.setCreateTime(new Date());
		blogRoll.setBlogRollCategory(BlogrollCategory.findBlogrollCategory(categoryId));
		blogRoll.setLinkType(linkType);
		// Exchange Image name
		HttpSession session = SessionHelper.getSession();
		if(logo != null){
			String real_path = (String) session.getAttribute(logo);
			session.removeAttribute(logo);
			logo = real_path;
			blogRoll.setLogo(logo);
		}
		
		return blogRoll;
	}

	public String getLinkName() {
		return linkName;
	}

	public void setLinkName(String linkName) {
		this.linkName = linkName;
	}

	public String getLinkUrl() {
		return linkUrl;
	}

	public void setLinkUrl(String linkUrl) {
		this.linkUrl = linkUrl;
	}

	public String getLogo() {
		return logo;
	}

	public void setLogo(String logo) {
		this.logo = logo;
	}

	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public int getListOrder() {
		return listOrder;
	}

	public void setListOrder(int listOrder) {
		this.listOrder = listOrder;
	}

	public int getLinkType() {
		return linkType;
	}

	public void setLinkType(int linkType) {
		this.linkType = linkType;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

}
