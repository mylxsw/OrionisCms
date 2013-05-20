package name.orionis.cms.extensions.blogroll.form;

import javax.validation.constraints.Size;

import name.orionis.cms.core.base.Form;
import name.orionis.cms.extensions.blogroll.model.BlogrollCategory;
/**
 * Blogroll Category Form
 * @author orionis
 * @2013-5-20
 * Site : http://blog.orionis.name
 *
 */
public class BlogrollCategoryForm extends Form<BlogrollCategory> {

    @Size(min = 1, max = 50)
    private String categoryName;
    private int listOrder;
	
	@Override
	public BlogrollCategory toEntity() {
		BlogrollCategory cate = new BlogrollCategory();
		cate.setCategoryName(categoryName);
		cate.setListOrder(listOrder);
		return cate;
	}

	public String getCategoryName() {
		return categoryName;
	}

	public int getListOrder() {
		return listOrder;
	}

	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}

	public void setListOrder(int listOrder) {
		this.listOrder = listOrder;
	}
}
