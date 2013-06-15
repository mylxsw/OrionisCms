package name.orionis.cms.extensions.content.service;


import name.orionis.cms.extensions.content.form.NewsForm;
import name.orionis.cms.extensions.content.model.Category;
import name.orionis.cms.extensions.content.model.News;


public class NewsServiceImpl implements NewsService {

	@Override
	public void addArtilce(NewsForm newsForm) {
		News news = newsForm.toEntity();
		news.setCategory(Category.findCategory(newsForm.getCategory()));
		
		news.persist();
	}
	
	@Override
	public News updateNewsById(NewsForm newsForm, long id){
		News entity = newsForm.toEntity();
		
		News newEntity = News.findNews(id);
		newEntity.setCategory(Category.findCategory(newsForm.getCategory()));
		newEntity.setContent(entity.getContent());
		newEntity.setTitle(entity.getTitle());
		newEntity.setDescription(entity.getDescription());
		newEntity.setList_order(entity.getList_order());
		newEntity.setUsername(entity.getUsername());
		
		return newEntity.merge();
	}
	
}
