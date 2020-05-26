package de.dpunkt.myaktion.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;

import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.model.Donation;
import de.dpunkt.myaktion.util.DonationSoFarConstraintAnnotation;

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
	public void addDonation(Long campaignId, @DonationSoFarConstraintAnnotation Donation donation) {
		System.out.println("Find campaign for donation");

		Campaign managedCampaign = entityManager.find(Campaign.class, campaignId);
		System.out.println("Found campaign, name is " + managedCampaign);
		donation.setCampaign(managedCampaign);
		System.out.println("Before persist donation");
		entityManager.persist(donation);		
	}

	@Override
	public Campaign getDonationCampaign(Long campaignId) {
		return entityManager.find(Campaign.class, campaignId);
	}

}
