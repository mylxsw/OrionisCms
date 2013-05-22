package name.orionis.cms.extensions.advertisement.service;

import name.orionis.cms.extensions.advertisement.model.Advertisement;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { name.orionis.cms.extensions.advertisement.model.Advertisement.class })
public interface AdvertisementService {
	/**
	 * Update Advertisement
	 * @param advertisement
	 * @return
	 */
	public Advertisement updateAdvertisement(Advertisement advertisement);
}
