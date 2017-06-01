/**
 * 
 */
package com.adserver.campaign.domain;

import java.util.Collection;

/**
 * @author Athi
 *
 */
public interface AdRepository {
	Collection<Ad> listAllAdCampaigns();
	Ad getAdByPartner(String partner);	
	boolean saveAd(Ad ad);
	boolean updateAd(String partner, Ad ad);
	boolean deleteAd(String partner, Ad ad);
	boolean deleteAllAdCampaigns();
}
