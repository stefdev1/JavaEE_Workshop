package de.dpunkt.myaktion.data;

import java.io.Serializable;
import java.util.Random;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import de.dpunkt.myaktion.model.Campaign;

@SessionScoped
@Named
public class CampaignProducer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3973054940915608814L;

	private enum Mode {
		EDIT, ADD
	}
	
	private Campaign campaign;
	private Mode mode;
	
	public Campaign getSelectedCampaign() {
		return campaign;
	}
	
	public void setSelectedCampaign(Campaign campaign) {
		this.campaign = campaign;
	}
	
	public boolean isAddMode() {
		return mode == Mode.ADD;
	}
	
	public void prepareAddCampaign() {
		this.campaign = new Campaign();
		Random randomNumber = new Random();		
		campaign.setId(randomNumber.nextLong());
		this.mode = Mode.ADD;
	}
	
	public void prepareEditCampaign(Campaign campaign) {
		this.campaign = campaign;
		this.mode = Mode.EDIT;
	}

}
