package name.orionis.cms.extensions.content.service;

import name.orionis.cms.extensions.content.form.NewsForm;
import name.orionis.cms.extensions.content.model.News;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { name.orionis.cms.extensions.content.model.News.class })
public interface NewsService {

	void addArtilce(NewsForm newsForm);
	
	News updateNewsById(NewsForm newsForm, long id);
}
