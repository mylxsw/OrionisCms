package name.orionis.cms.extensions.content.service;


import name.orionis.cms.extensions.content.form.NewsForm;
import name.orionis.cms.extensions.content.model.Category;
import name.orionis.cms.extensions.content.model.News;


public class NewsServiceImpl implements NewsService {

	@Override
	public void addNews(NewsForm newsForm) {
		News news = newsForm.toEntity();
		news.setCategory(Category.findCategory(newsForm.getCategory()));
		
		news.persist();
	}
	
}
