package name.orionis.cms.extensions.announce.service;

import name.orionis.cms.extensions.announce.model.Announce;

import org.springframework.roo.addon.layers.service.RooService;

@RooService(domainTypes = { name.orionis.cms.extensions.announce.model.Announce.class })
public interface AnnounceService {
	
	/**
	 * Update Announce
	 * @param ann
	 * @return
	 */
	public Announce updateAnnounce(Announce ann);
}
