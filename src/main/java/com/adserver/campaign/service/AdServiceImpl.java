/**
 * 
 */
package com.adserver.campaign.service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.logging.Logger;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.springframework.beans.factory.annotation.Autowired;

import com.adserver.campaign.domain.Ad;
import com.adserver.campaign.domain.AdRepository;

/**
 * @author User
 *
 */
public class AdServiceImpl implements AdService {

	Logger logger = Logger.getLogger("AdServiceImpl");
	
	@Autowired
	AdRepository adRepository;
	
	@Override
	public Collection<Ad> listAllAdCampaigns() {
		return adRepository.listAllAdCampaigns();
	}

	@Override
	public Ad getAdByPartner(String partner) {
		return adRepository.getAdByPartner(partner);
	}

	@Override
	public boolean isAnAdActiveForPartner(String partner) {
		Ad currentAd = getAdByPartner(partner);
		LocalDateTime currentDate = LocalDateTime.now();
		LocalDateTime creationDate = currentAd.getCreationTime();
		
		Duration duration = Duration.between(creationDate, currentDate);
		if (duration.getSeconds() > currentAd.getDuration())  
			return false;
		else
			return true;
	}
	
	@Override
	public Status saveAd(String partner, Ad ad) {		
        if (isAnAdActiveForPartner(partner)) {
            logger.info("An active Ad  for partner " + ad.getPartner() + " already exists.");
            return Status.CONFLICT;
        }
        
		if (adRepository.saveAd(ad))
			return Status.OK;
		else
			return Status.INTERNAL_SERVER_ERROR;
	}	
	
	@Override
	public Status updateAd(String partner, Ad ad) {
        Ad currentAd = getAdByPartner(partner);
        
        if (currentAd==null) {
        	logger.info("Ad for partner " + partner + " not found");
            return Status.NOT_FOUND;
        }
        
        //TODO
		if (adRepository.updateAd(partner, ad))
			return Status.OK;
		else
			return Status.INTERNAL_SERVER_ERROR;        
	}
	
	@Override
	public Status deleteAd(String partner, Ad ad) {
        Ad currentAd = getAdByPartner(partner);
        if (currentAd == null) {
        	logger.info("Unable to delete. Ad for partner " + partner + " not found");
            return Status.NOT_FOUND;
        }
        
        //TODO
		if (adRepository.deleteAd(partner, ad))
			return Status.NO_CONTENT;
		else
			return Status.INTERNAL_SERVER_ERROR;  
	}
	
	@Override
	public Status deleteAllCampaigns() {
		 //TODO
		if (adRepository.deleteAllAdCampaigns())
			return Status.NO_CONTENT;
		else
			return Status.INTERNAL_SERVER_ERROR;  
	}
}
