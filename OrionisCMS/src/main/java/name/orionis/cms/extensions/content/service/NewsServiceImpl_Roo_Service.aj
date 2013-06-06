// WARNING: DO NOT EDIT THIS FILE. THIS FILE IS MANAGED BY SPRING ROO.
// You may push code into the target .java compilation unit if you wish to edit any member(s).

package name.orionis.cms.extensions.content.service;

import java.util.List;
import name.orionis.cms.extensions.content.model.News;
import name.orionis.cms.extensions.content.service.NewsServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

privileged aspect NewsServiceImpl_Roo_Service {
    
    declare @type: NewsServiceImpl: @Service;
    
    declare @type: NewsServiceImpl: @Transactional;
    
    public long NewsServiceImpl.countAllNews() {
        return News.countNews();
    }
    
    public void NewsServiceImpl.deleteNews(News news) {
        news.remove();
    }
    
    public News NewsServiceImpl.findNews(Long id) {
        return News.findNews(id);
    }
    
    public List<News> NewsServiceImpl.findAllNews() {
        return News.findAllNews();
    }
    
    public List<News> NewsServiceImpl.findNewsEntries(int firstResult, int maxResults) {
        return News.findNewsEntries(firstResult, maxResults);
    }
    
    public void NewsServiceImpl.saveNews(News news) {
        news.persist();
    }
    
    public News NewsServiceImpl.updateNews(News news) {
        return news.merge();
    }
    
}