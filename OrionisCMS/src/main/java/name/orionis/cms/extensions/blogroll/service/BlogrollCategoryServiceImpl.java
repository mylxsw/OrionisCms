package name.orionis.cms.extensions.blogroll.service;

import name.orionis.cms.extensions.blogroll.model.BlogrollCategory;


public class BlogrollCategoryServiceImpl implements BlogrollCategoryService {
	@Override
	public BlogrollCategory updateBlogrollCategory(BlogrollCategory cate){
		BlogrollCategory category = BlogrollCategory.findBlogrollCategory(cate.getId());
		category.setCategoryName(cate.getCategoryName());
		category.setListOrder(cate.getListOrder());
		return category.merge();
	}
}
