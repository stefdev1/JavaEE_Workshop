package de.dpunkt.myaktion.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.model.Donation;

@Stateless
public class DonationServiceBean implements DonationService{
	
	@Inject
	EntityManager entityManager;

	@Override
	public List<Donation> getDonationList(Long campaignId) {
		Campaign managedCampaign = entityManager.find(Campaign.class, campaignId);
		TypedQuery<Donation> donationQuery = entityManager.createNamedQuery(Campaign.getDonations, Donation.class);
		donationQuery.setParameter("campaign", managedCampaign);
		List<Donation> donations= donationQuery.getResultList();
		donations.size();
		return donations;
	}

	@Override
	public void addDonation(Long campaignId, Donation donation) {
		Campaign managedCampaign = entityManager.find(Campaign.class, campaignId);
		donation.setCampaign(managedCampaign);
		entityManager.persist(donation);		
	}

}
