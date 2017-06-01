/**
 * 
 */
package com.adserver.campaign.domain;

import java.util.Collection;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

/**
 * @author Athi
 *
 */
public class AdRepositoryImpl implements AdRepository {

	private ConcurrentHashMap<String, Ad> campaign = new ConcurrentHashMap<String, Ad>();
	Logger logger = Logger.getLogger("AdRepositoryImpl");
	
	@Override
	public Collection<Ad> listAllAdCampaigns() {
		if (campaign.size() == 0) {
			//create some test ad's
			logger.info("Adding some sample Ad's");
			campaign.putIfAbsent("Comcast", new Ad(1, "Comcast", 180, "Comcast Ad Content1"));
			campaign.putIfAbsent("Verizon", new Ad(1, "Verizon", 150, "Verizon Ad Content1"));
			campaign.putIfAbsent("Time Warner", new Ad(1, "Time Warner", 120, "Time WarnerAd Content1"));
		}
		return campaign.values();
	}
	
	@Override
	public Ad getAdByPartner(String partner) {
		return campaign.get(partner);
	}

	@Override
	public boolean saveAd(Ad ad) {
		logger.info("Save Ad : " + ad.toString());
		ad.setId(campaign.size() + 1);
		campaign.putIfAbsent(ad.getPartner(), ad);
		return true;
	}

	@Override
	public boolean updateAd(String partner, Ad ad) {
		logger.info("Update Ad : " + ad.toString());
		Ad currentAd = getAdByPartner(partner);
		//update currentAd with the new ad passed along 
		currentAd.setContent(ad.getContent());
		currentAd.setDuration(ad.getDuration());
		currentAd.setCreationTime(ad.getCreationTime());
		campaign.put(partner, ad);
		return true;
	}	
	
	@Override
	public boolean deleteAd(String partner, Ad ad) {
		logger.info("Delete Ad : " + ad.toString());
		return campaign.remove(ad.getPartner()) != null;
	}	
	
	@Override
	public boolean deleteAllAdCampaigns() {
		logger.info("Delete All Ad campaigns : ");
		campaign.clear();
		return true;
	}
}
