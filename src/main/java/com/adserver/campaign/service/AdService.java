/**
 * 
 */
package com.adserver.campaign.service;

import java.util.Collection;

import javax.ws.rs.core.Response.Status;

import com.adserver.campaign.domain.Ad;

/**
 * @author Athi
 *
 */
public interface AdService {
    Collection<Ad> listAllAdCampaigns();

    Ad getAdByPartner(String partner);

    Status saveAd(String partner, Ad ad);
    
    Status updateAd(String partner, Ad ad);
    
    Status deleteAd(String partner, Ad ad);
    
    Status deleteAllCampaigns();

	boolean isAnAdActiveForPartner(String partner);

}
