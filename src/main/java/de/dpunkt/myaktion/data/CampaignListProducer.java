package de.dpunkt.myaktion.data;

import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.event.Observes;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;

import de.dpunkt.myaktion.model.Account;
import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.model.Donation;
import de.dpunkt.myaktion.model.Donation.Status;
import de.dpunkt.myaktion.services.CampaignService;
import de.dpunkt.myaktion.util.Events.Added;
import de.dpunkt.myaktion.util.Events.Deleted;
import de.dpunkt.myaktion.util.Events.Updated;

@RequestScoped
public class CampaignListProducer{
	
	private List<Campaign> campaigns;
	
	@Inject 
	private CampaignService campaignService;
	
	@PostConstruct
	public void init() {
		campaigns = campaignService.getAllCampaigns();
	}
	
	@Produces
	@Named
	public List<Campaign> getCampaigns() {
		return campaigns;
	}
	
	public void onCampaignAdded(@Observes @Added Campaign campaign) {
		campaignService.addCampaign(campaign);
		init();
	}
	
	public void onCampaignDeleted(@Observes @Deleted Campaign campaign) {
		campaignService.deleteCampaign(campaign);
		init();
	}
	
	public void onCampaignUpdate(@Observes @Updated Campaign campaign) {
		campaignService.updateCampaign(campaign);
		init();
	}
}
