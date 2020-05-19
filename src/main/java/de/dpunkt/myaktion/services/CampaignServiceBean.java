package de.dpunkt.myaktion.services;

import java.util.LinkedList;
import java.util.List;

import javax.enterprise.context.RequestScoped;

import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.util.TestQualifier.MyService;

@RequestScoped
@MyService
public class CampaignServiceBean implements CampaignService{

	@Override
	public List<Campaign> getAllCampaigns() {
		System.out.println("Called CampaignServiceBean");
		return new LinkedList<Campaign>();
	}
	
	

}
