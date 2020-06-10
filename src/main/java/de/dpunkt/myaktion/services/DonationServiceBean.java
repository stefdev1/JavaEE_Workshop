package de.dpunkt.myaktion.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import de.dpunkt.myaktion.BusinessException.DontionAmountValidationException;
import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.model.Donation;

@Stateless
public class DonationServiceBean implements DonationService{
	
	@Inject
	EntityManager entityManager;

	@Override
	public List<Donation> getDonationList(Long campaignId) {
		Campaign managedCampaign = entityManager.find(Campaign.class, campaignId);
		List<Donation> donations= managedCampaign.getDonations();
		donations.size();
		return donations;
	}

	@Override
	public void addDonation(Long campaignId, Donation donation) throws Exception {
		Campaign managedCampaign = entityManager.find(Campaign.class, campaignId);
		if(donation.getAmount() < managedCampaign.getDonationMinimum()) {
			throw new DontionAmountValidationException(managedCampaign.getDonationMinimum().toString());
		}
		donation.setCampaign(managedCampaign);
		entityManager.persist(donation);		
	}
	
	public Campaign loadCampaign(Long campaignId) {
		return entityManager.find(Campaign.class, campaignId);
	}

}
