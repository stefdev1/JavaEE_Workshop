package de.dpunkt.myaktion.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.inject.Named;

import de.dpunkt.myaktion.data.CampaignListProducer;
import de.dpunkt.myaktion.data.CampaignProducer;
import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.util.Events.Added;
import de.dpunkt.myaktion.util.Events.Updated;

@SessionScoped
@Named
public class EditCampaignController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5676225633974777903L;

	@Inject @Added
	private Event<Campaign> campaignAddEvent;
	
	@Inject @Updated
	private Event<Campaign> campaignUpdateEvent;
	
	@Inject
	private CampaignProducer campaignProducer;
	
	public String doSave() {
		if(campaignProducer.isAddMode()) {
			campaignAddEvent.fire(campaignProducer.getSelectedCampaign());
		}
		else {
			campaignUpdateEvent.fire(campaignProducer.getSelectedCampaign());
		}
		System.out.println("Save");
		return Pages.LIST_CAMPAIGNS;
	}
	
	public String doCancel() {
		System.out.println("Cancel");
		return Pages.LIST_CAMPAIGNS;
	}
	
}
