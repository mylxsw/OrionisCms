package name.orionis.cms.extensions.blogroll.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import name.orionis.cms.extensions.blogroll.model.BlogRoll;
import name.orionis.cms.extensions.blogroll.model.BlogrollCategory;


public class BlogrollServiceImpl implements BlogrollService {

	@Override
	public Map<Long, String> findAllBlogRollsMap() {
		List<BlogrollCategory> categorys = BlogrollCategory.findAllBlogrollCategorys();
		Map<Long, String> result = new HashMap<Long, String>();
		for(BlogrollCategory bc : categorys){
			result.put(bc.getId(), bc.getCategoryName());
		}
		return result;
	}
	@Override
	public BlogRoll updateBlogRoll(BlogRoll blogroll){
		BlogRoll b = BlogRoll.findBlogRoll(blogroll.getId());
		b.setLinkName(blogroll.getLinkName());
		b.setLinkUrl(blogroll.getLinkUrl());
		b.setLinkType(blogroll.getLinkType());
		b.setIntroduce(blogroll.getIntroduce());
		b.setListOrder(blogroll.getListOrder());
		b.setLogo(blogroll.getLogo());
		
		return b.merge();
	}
}
