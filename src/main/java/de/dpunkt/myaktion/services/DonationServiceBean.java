package de.dpunkt.myaktion.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.mail.MessagingException;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import de.dpunkt.myaktion.BusinessException.DontionAmountValidationException;
import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.model.Donation;
import de.dpunkt.myaktion.util.EmailService;

@Stateless
public class DonationServiceBean implements DonationService{
	
	@Inject
	EntityManager entityManager;
	
	@Inject
	EmailService emailService;

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
		checkTargetAmountReached(managedCampaign);
		
	}
	
	public Campaign loadCampaign(Long campaignId) {
		return entityManager.find(Campaign.class, campaignId);
	}
	
	private void checkTargetAmountReached (Campaign campaign) {
		TypedQuery<Double> query = entityManager.createNamedQuery(Campaign.getAmountDonatedSoFar, Double.class);
		query.setParameter("campaign", campaign);
		Double result = query.getSingleResult();
		if(result == null) {
			result = 0d;
		}
		
		if(result >= campaign.getTargetAmount()) {
			String content = "Campaign " + campaign.getName() + " hat das Spendenziel von " + campaign.getTargetAmount() + "Euro erreicht.";
			try {
				emailService.sendEmail("stefdev@mailo.com", "stefan.timpen@gmail.com", "Spendenziel erreicht", content);
			} catch (MessagingException e) {
				e.printStackTrace();
			}
		}
	}

}
