package name.orionis.cms.extensions.content.service;

import name.orionis.cms.extensions.content.model.Category;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { name.orionis.cms.extensions.content.model.Category.class })
public interface CategoryService {
	/**
	 * Update Category
	 * @param category
	 * @return
	 */
	public Category updateCategory(Category category);
}
