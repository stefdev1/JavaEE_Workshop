package de.dpunkt.myaktion.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import de.dpunkt.myaktion.data.CampaignProducer;
import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.model.Donation;
import de.dpunkt.myaktion.services.DonationService;
import de.dpunkt.myaktion.util.Events.Deleted;

@SessionScoped
@Named
public class ListCampaignsController implements Serializable{
	
	private static final long serialVersionUID = 3996530407285491907L;
	
	@Inject
	CampaignProducer campaignProducer;
	
	@Inject
	private DonationService donationService;
	
	@Inject @Deleted
	private Event<Campaign> campaignDeleteEvent;
	
	private Campaign campaignToDelete;

	public String doAddCampaign() {
		campaignProducer.prepareAddCampaign();
		return Pages.EDIT_CAMPAIGN;
	}
	
	public String doEditCampaign(Campaign campaign) {
		campaignProducer.prepareEditCampaign(campaign);
		return Pages.EDIT_CAMPAIGN;
	}
	
	public String doEditDonationForm(Campaign campaign) {
		campaignProducer.setSelectedCampaign(campaign);
		return Pages.EDIT_DONATION_FORM;
	}
	
	public String doListDonations(Campaign campaign) {
		final List<Donation> donations = donationService.getDonationList(campaign.getId());
		campaign.setDonations(donations);
		campaignProducer.setSelectedCampaign(campaign);
		if(campaign.getCreatedAt() == null)  {
			System.out.println("Campaign created is null ");

		}
		else {
			System.out.println("Campaign created: " + campaign.getCreatedAt().toString());
		}
		return Pages.LIST_DONATIONS;
	}
	
	public void doDeleteCampaign(Campaign campaign) {
		this.campaignToDelete = campaign;
		System.out.println("Camapign earmarkedfor deletion!");
	}
	
	public void commitDeleteCamapign() {
		campaignDeleteEvent.fire(campaignToDelete);
	}
	

}
