package de.dpunkt.myaktion.controller;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import de.dpunkt.myaktion.data.CampaignProducer;
import de.dpunkt.myaktion.model.FormConfig;

@SessionScoped
@Named
public class EditDonationFormController implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 686003802508479033L;
	
	private FormConfig formConfig;
	
	@Inject
	private CampaignProducer campaignProducer;
	
	public EditDonationFormController() {
		this.formConfig = new FormConfig("ffffff", "000000", "Spende Geld");
	}
	
	public String doOk() {
		return Pages.LIST_CAMPAIGNS;
	}
	
	public String getUrl() {
		return getAppUrl() + "/" + Pages.DONATE_MONEY + ".jsf?bgColor=" + formConfig.getBgColor() + "&textColor=" + formConfig.getTextColor() + "&campaignId=" + campaignProducer.getSelectedCampaign().getId() + "&title="+formConfig.getTitle(); 
	}
	
	private String getAppUrl() {
		HttpServletRequest req= (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
		String scheme = req.getScheme();
		String serverName = req.getServerName();
		int serverPort = req.getServerPort();
		String contextPath = req.getContextPath();
		return scheme + "://" + serverName + ":" + serverPort + contextPath; 
	}

	public FormConfig getFormConfig() {
		return formConfig;
	}

	public void setFormConfig(FormConfig formConfig) {
		this.formConfig = formConfig;
	}
	


}
