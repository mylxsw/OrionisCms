package name.orionis.cms.extensions.advertisement.service;

import name.orionis.cms.extensions.advertisement.model.Advertisement;


public class AdvertisementServiceImpl implements AdvertisementService {
	@Override
	public Advertisement updateAdvertisement(Advertisement advertisement){
		
		Advertisement adv = Advertisement.findAdvertisement(advertisement.getId());
		adv.setTitle(advertisement.getTitle());
		adv.setContent(advertisement.getContent());
		adv.setUpdator(advertisement.getUpdator());
		
		return adv.merge();
	}
}
