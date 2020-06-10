package de.dpunkt.myaktion.controller;

import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.ValidationException;

import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.model.Donation;
import de.dpunkt.myaktion.model.Donation.Status;
import de.dpunkt.myaktion.services.DonationService;

@SessionScoped
@Named
public class DonateMoneyController implements Serializable{
	
	@Inject
	private DonationService donationService;

	@Inject
	private FacesContext facesContext;
	
	@Inject
	private Logger logger;
	
	private String textColor = "000000";
	private String bgColor = "ffffff";
	private Long campaignId;
	private Donation donation;
	
	
	@PostConstruct
	public void init() {
		this.donation = new Donation();
	}

	public String getTextColor() {
		return textColor;
	}

	public void setTextColor(String textColor) {
		this.textColor = textColor;
	}

	public String getBgColor() {
		return bgColor;
	}

	public void setBgColor(String bgColor) {
		this.bgColor = bgColor;
	}

	public Long getCampaignId() {
		return campaignId;
	}

	public void setCampaignId(Long campaignId) {
		this.campaignId = campaignId;
	}

	public Donation getDonation() {
		return donation;
	}

	public void setDonation(Donation donation) {
		this.donation = donation;
	}
	
	public void isMinimumDonationAmount(FacesContext facesContext, UIComponent uiComponent, Object obj) throws ValidationException {
		double enteredAmount = (Double) obj;
		Campaign campaign = donationService.loadCampaign(campaignId);
		
		if(enteredAmount < campaign.getDonationMinimum()) {
			String msg = "Du Geizhals, die Spende muss mindestens " + campaign.getDonationMinimum().toString() +  " Euro betragen.";
			throw new ValidatorException(new FacesMessage(msg));
		}
		
	}
	
	public String doDonation() {
		final ResourceBundle resourceBundle = facesContext.getApplication().getResourceBundle(facesContext, "msg");
		try {
			getDonation().setStatus(Status.IN_PROCESS);
			donationService.addDonation(getCampaignId(), getDonation());
			logger.log(Level.INFO, "log.donateMoney.thank_you", new Object[] {getDonation().getDonarName(), getDonation().getAmount()});
			final String msg = resourceBundle.getString("donateMoney.thank_you");
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
			init();
		}
		catch (Exception exception) {
			String message = resourceBundle.getString("donateMoney.donation_amount_low");
			message = String.format(message, new Object[] {exception.getMessage()});
			facesContext.addMessage("donationForm:donationAmount", new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null));
		}

		return Pages.DONATE_MONEY;
	}

}
