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
	public News updateNews(NewsForm newsForm, long id){
		News entity = newsForm.toEntity();
		entity.setId(id);
		entity.setCategory(Category.findCategory(newsForm.getCategory()));
		return entity.merge();
	}
	
}
