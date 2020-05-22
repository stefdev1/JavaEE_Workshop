package de.dpunkt.myaktion.controller;

import java.io.Serializable;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

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
	
	public String doDonation() {
		getDonation().setStatus(Status.IN_PROCESS);
		donationService.addDonation(getCampaignId(), getDonation());
		logger.log(Level.INFO, "log.donateMoney.thank_you", new Object[] {getDonation().getDonarName(), getDonation().getAmount()});
		final ResourceBundle resourceBundle = facesContext.getApplication().getResourceBundle(facesContext, "msg");
		final String msg = resourceBundle.getString("donateMoney.thank_you");
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
		init();
		return Pages.DONATE_MONEY;
	}

}
