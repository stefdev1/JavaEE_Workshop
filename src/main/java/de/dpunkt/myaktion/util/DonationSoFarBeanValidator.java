package de.dpunkt.myaktion.util;

import java.math.BigDecimal;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import de.dpunkt.myaktion.data.CampaignProducer;
import de.dpunkt.myaktion.model.Donation;

public class DonationSoFarBeanValidator implements ConstraintValidator<DonationSoFarConstraintAnnotation, Donation>{

	@Inject
	CampaignProducer campaignProducer;
	
	@Override
	public void initialize(DonationSoFarConstraintAnnotation constraintAnnotation) {
		// TODO Auto-generated method stub
		System.out.println("initialize DonationSoFarBeanValidator...");

		
	}

	public boolean isValidDouble(Double value, ConstraintValidatorContext context) {

		boolean isValid = false;
		System.out.println("IsValid DonationSoFarBeanValidator start...");
		System.out.println(" donation is "+ value);
		if(campaignProducer.getSelectedCampaign() != null) {
			System.out.println("Campaign exisits");
			if(campaignProducer.getSelectedCampaign().getDonationMinimum() != null) {
				System.out.println("Campaign getDonationMinimum is =" + campaignProducer.getSelectedCampaign().getDonationMinimum());
				if(value.doubleValue() >= campaignProducer.getSelectedCampaign().getDonationMinimum()) {
					System.out.println("Donation Amount is higher than Donation Minimum. IsValid");
					isValid = true;
				}
				else {
					System.out.println("Donation Amount is lower than Donation Minimum. Not Valid");
					context = context.buildConstraintViolationWithTemplate("Donation amount is lower than the donation mimmum " + campaignProducer.getSelectedCampaign().getDonationMinimum() + " EUR.").addConstraintViolation();
					context.disableDefaultConstraintViolation();
					isValid = false;
				}
			}
			else {
				System.out.println("Campaign getDonationMinimum is null");
				isValid = false;
			}

		}
		else {
			System.out.println("Campaign is null");
			isValid = false;
		}
		//if(value.)
		
		return isValid;
	}
	
	public boolean isValid(Long value, ConstraintValidatorContext context) {
		
		return isValidDouble(value.doubleValue(), context);
	}
	
	public boolean isValid(Donation value, ConstraintValidatorContext context) {
		boolean isValid = false;
		System.out.println("IsValid DonationSoFarBeanValidator start...");
		System.out.println("For " + value.getDonarName() + " donation is "+ value.getAmount());
		if(value.getCampaign() != null) {
			System.out.println("Campaign exisits");
			if(value.getCampaign().getDonationMinimum() != null) {
				System.out.println("Campaign getDonationMinimum is =" + value.getCampaign().getDonationMinimum());
				if(value.getAmount() >= value.getCampaign().getDonationMinimum()) {
					System.out.println("Donation Amount is higher than Donation Minimum. IsValid");
					isValid = true;
				}
				else {
					System.out.println("Donation Amount is lower than Donation Minimum. Not Valid");
					isValid = false;
				}
			}
			else {
				System.out.println("Campaign getDonationMinimum is null");
				isValid = false;
			}

		}
		else {
			System.out.println("Campaign is null");
			isValid = false;
		}
		//if(value.)
		
		return isValid;
	}

}
