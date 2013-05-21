// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package name.orionis.cms.extensions.blogroll.service;

import java.util.List;
import name.orionis.cms.extensions.blogroll.model.BlogRoll;
import name.orionis.cms.extensions.blogroll.service.BlogrollServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect BlogrollServiceImpl_Roo_Service {
    
    declare @type: BlogrollServiceImpl: @Service;
    
    declare @type: BlogrollServiceImpl: @Transactional;
    
    public long BlogrollServiceImpl.countAllBlogRolls() {
        return BlogRoll.countBlogRolls();
    }
    
    public void BlogrollServiceImpl.deleteBlogRoll(BlogRoll blogRoll) {
        blogRoll.remove();
    }
    
    public BlogRoll BlogrollServiceImpl.findBlogRoll(Long id) {
        return BlogRoll.findBlogRoll(id);
    }
    
    public List<BlogRoll> BlogrollServiceImpl.findAllBlogRolls() {
        return BlogRoll.findAllBlogRolls();
    }
    
    public List<BlogRoll> BlogrollServiceImpl.findBlogRollEntries(int firstResult, int maxResults) {
        return BlogRoll.findBlogRollEntries(firstResult, maxResults);
    }
    
    public void BlogrollServiceImpl.saveBlogRoll(BlogRoll blogRoll) {
        blogRoll.persist();
    }
    
}
