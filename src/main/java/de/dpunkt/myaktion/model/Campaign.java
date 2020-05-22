package de.dpunkt.myaktion.model;

import java.util.List;

import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Transient;


@Entity
@NamedQueries({@NamedQuery(name = Campaign.findAll, query = "SELECT c FROM Campaign c ORDER BY c.name"),
			   @NamedQuery(name = Campaign.getAmountDonatedSoFar, query = "SELECT SUM(d.amount) FROM Donation d WHERE d.campaign = :campaign"),
			   @NamedQuery(name = Campaign.getDonations, query = "SELECT d FROM Donation d WHERE d.campaign = :campaign")
	})

public class Campaign {
	
	public static final String findAll = "Campaign.findAll";
	public static final String getAmountDonatedSoFar = "Campaign.getAmountDonatedSoFar";
	public static final String getDonations = "Campaign.getDonations";
	
	@GeneratedValue
	@Id
	private Long id;

	@OneToMany(mappedBy = "campaign")
	private List<Donation> donations;

	@Embedded
	@AttributeOverrides({@AttributeOverride(name = "name", column = @Column(name = "accountName"))})
	private Account account;

	private String name;
	private Double targetAmount;
	private Double donationMinimum;
	
	@Transient
	private Double amountDonatedSoFar;
		
	public Campaign() {
		setAccount(new Account());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getTargetAmount() {
		return targetAmount;
	}

	public void setTargetAmount(Double targetAmount) {
		this.targetAmount = targetAmount;
	}

	public Double getDonationMinimum() {
		return donationMinimum;
	}

	public void setDonationMinimum(Double donationMinimum) {
		this.donationMinimum = donationMinimum;
	}

	public Double getAmountDonatedSoFar() {
		return amountDonatedSoFar;
	}

	public void setAmountDonatedSoFar(Double amountDonatedSoFar) {
		this.amountDonatedSoFar = amountDonatedSoFar;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Donation> getDonations() {
		return donations;
	}

	public void setDonations(List<Donation> donations) {
		this.donations = donations;
	}
	
	

}
