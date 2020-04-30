package de.dpunkt.myaktion.controller;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import de.dpunkt.myaktion.model.Donation;

@SessionScoped
@Named
public class DonateMoneyController implements Serializable{

	private String textColor = "000000";
	private String bgColor = "ffffff";
	private String title = "Geld-Spende"; 
	private Long campaignId;
	private Donation donation;
	
	public DonateMoneyController() {
		this.setDonation(new Donation());
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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
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
		final FacesContext facesContext = FacesContext.getCurrentInstance();
		final ResourceBundle resourceBundle = facesContext.getApplication().getResourceBundle(facesContext, "msg");
		final String msg = resourceBundle.getString("donateMoney.thank_you");
		facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, msg, null));
		this.donation = new Donation();
		return Pages.DONATE_MONEY;
	}

}
