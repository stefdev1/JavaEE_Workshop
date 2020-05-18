package de.dpunkt.myaktion.controller;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import de.dpunkt.myaktion.data.CampaignListProducer;
import de.dpunkt.myaktion.data.CampaignProducer;
import de.dpunkt.myaktion.model.Campaign;

@SessionScoped
@Named
public class EditCampaignController implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5676225633974777903L;

	@Inject
	private CampaignListProducer campaignListProducer;
	
	@Inject
	private CampaignProducer campaignProducer;
	
	public String doSave() {
		if(campaignProducer.isAddMode()) {
			campaignListProducer.getCampaigns().add(campaignProducer.getSelectedCampaign());
		}
		System.out.println("Save");
		return Pages.LIST_CAMPAIGNS;
	}
	
	public String doCancel() {
		System.out.println("Cancel");
		return Pages.LIST_CAMPAIGNS;
	}
	
}
