package name.orionis.cms.extensions.content.service;

import name.orionis.cms.extensions.content.model.Category;


public class CategoryServiceImpl implements CategoryService {
	
	@Override
	public Category updateCategory(Category category){
		Category c = Category.findCategory(category.getId());
		c.setCate_name(category.getCate_name());
		c.setDescription(category.getDescription());
		c.setList_order(category.getList_order());
		c.setParent_id(category.getParent_id());
		c.setSethtml(category.getSethtml());
		c.setUrl(category.getUrl());
		c.setSettings(category.getSettings());
		c.setStyle(category.getStyle());
		
		return c.merge();
	}
}
