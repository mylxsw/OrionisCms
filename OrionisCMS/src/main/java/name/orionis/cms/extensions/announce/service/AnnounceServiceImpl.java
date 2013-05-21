package name.orionis.cms.extensions.announce.service;

import name.orionis.cms.extensions.announce.model.Announce;


public class AnnounceServiceImpl implements AnnounceService {
	@Override
	public Announce updateAnnounce(Announce ann){
		Announce announce = Announce.findAnnounce(ann.getId());
		announce.setTitle(ann.getTitle());
		announce.setContent(ann.getContent());
		announce.setStartTime(ann.getStartTime());
		announce.setEndTime(ann.getEndTime());
		announce.setPublishTime(ann.getPublishTime());
		announce.setUpdator(ann.getUpdator());
		
		return announce.merge();
	}
}
