package de.dpunkt.myaktion.services;

import java.util.List;

import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.model.Donation;
import de.dpunkt.myaktion.util.DonationSoFarConstraintAnnotation;

public interface DonationService {
	
	List<Donation> getDonationList(Long campaignId);
	
	void addDonation(Long campaignId, @DonationSoFarConstraintAnnotation Donation donation);
	
	Campaign getDonationCampaign(Long campaignId);

}
