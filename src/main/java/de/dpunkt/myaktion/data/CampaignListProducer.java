package de.dpunkt.myaktion.data;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;

import de.dpunkt.myaktion.model.Account;
import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.model.Donation;
import de.dpunkt.myaktion.model.Donation.Status;

@SessionScoped
public class CampaignListProducer implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6181792934119987061L;
	private List<Campaign> campaigns;
	
	@PostConstruct
	public void init() {
		campaigns = createMockCampaigns();
	}
	
	@Produces
	@Named
	public List<Campaign> getCampaigns() {
		return campaigns;
	}
	
	public List<Campaign> createMockCampaigns() {
		Donation donation1= new Donation();
		donation1.setDonarName("Heinz Schmidt");
		donation1.setAmount(20d);
		donation1.setReceiptRequested(true);
		donation1.setStatus(Status.TRANSFERRED);
		donation1.setAccount(new Account(donation1.getDonarName(), "XXX Bank", "DE444876543210000123456"));
		
		Donation donation2 =  new Donation();
		donation2.setDonarName("Karl Meier");
		donation2.setAmount(30d);
		donation2.setReceiptRequested(false);
		donation2.setStatus(Status.IN_PROCESS);
		donation2.setAccount(new Account(donation2.getDonarName(), "YYY Bank", "DE44864275310000654321"));
		
		List<Donation> spenden = new LinkedList<Donation>();
		spenden.add(donation1);
		spenden.add(donation2);
		
		Campaign campaign1 = new Campaign();
		campaign1.setName("Trikots für A-Jugend");
		campaign1.setTargetAmount(1000d);
		campaign1.setAmountDonatedSoFar(258d);
		campaign1.setDonationMinimum(20d);
		campaign1.setId(1L);
		campaign1.setAccount(new Account("Max Mustermann", "ABC Bank", "DE44123456780100200300"));
		campaign1.setDonations(spenden);
		
		Campaign campaign2 = new Campaign();
		campaign2.setName("Rollstuhl für Maria");
		campaign2.setTargetAmount(1000d);
		campaign2.setAmountDonatedSoFar(742d);
		campaign2.setDonationMinimum(25d);
		campaign2.setId(2L);
		campaign2.setAccount(campaign1.getAccount());
		campaign2.setDonations(spenden);
				
		List<Campaign> ret = new LinkedList<Campaign>();
		ret.add(campaign1);
		ret.add(campaign2);
		return ret;
	}

}
