package de.dpunkt.myaktion.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import de.dpunkt.myaktion.data.CampaignProducer;

@SessionScoped
@Named
public class EditDonationFormController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 686003802508479033L;
	private String textColor = "000000";
	private String bgColor = "ffffff";
	
	@Inject
	private CampaignProducer campaignProducer;
	
	@Inject
	HttpServletRequest req;
	
	public String doOk() {
		return Pages.LIST_CAMPAIGNS;
	}
	
	public String getUrl() {
		return getAppUrl() + "/" + Pages.DONATE_MONEY + ".jsf?bgColor=" + bgColor + "&textColor=" + textColor + "&campaignId=" + campaignProducer.getSelectedCampaign().getId(); 
	}
	
	private String getAppUrl() {
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		return scheme + "://" + serverName + ":" + serverPort + contextPath; 
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
	


}
