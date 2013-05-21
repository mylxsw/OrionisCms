package name.orionis.cms.extensions.blogroll.service;

import name.orionis.cms.extensions.blogroll.model.BlogrollCategory;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = {BlogrollCategory.class })
public interface BlogrollCategoryService {
	/**
	 * Update category
	 * @param cate
	 * @return
	 */
	public BlogrollCategory updateBlogrollCategory(BlogrollCategory cate);
}
