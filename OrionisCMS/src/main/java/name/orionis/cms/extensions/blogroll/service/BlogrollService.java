package name.orionis.cms.extensions.blogroll.service;

import java.util.Map;

import name.orionis.cms.extensions.blogroll.model.BlogRoll;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { name.orionis.cms.extensions.blogroll.model.BlogRoll.class })
public interface BlogrollService {
	/**
	 * Find all blogroll cateogry in a map
	 * @return
	 */
	Map<Long, String> findAllBlogRollsMap();
	/**
	 * Update Blogroll
	 * @param blogroll
	 * @return
	 */
	public BlogRoll updateBlogRoll(BlogRoll blogroll);
}
