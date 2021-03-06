package de.dpunkt.myaktion.model;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Donation {
	
	@GeneratedValue
	@Id
	private Long id;
	
	@ManyToOne
	private Campaign campaign;
	
	@Embedded
	private Account account;
	
	@NotNull(message = "{donation.amount.notNull}")
	@DecimalMin(value = "1.00", message = "{donation.amount.decimalMin}")
	private Double amount;
	
	@NotNull
	@Size(min = 4, max = 40, message = "{donation.donarName.size}")
	private String donarName;
	
	@NotNull
	private Boolean receiptRequested;
	
	@NotNull
	private Status status;
	
	public enum Status {
		TRANSFERRED, IN_PROCESS;
	}
	
	public Donation() {
		this.account = new Account();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Campaign getCampaign() {
		return campaign;
	}

	public void setCampaign(Campaign campaign) {
		this.campaign = campaign;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDonarName() {
		return donarName;
	}

	public void setDonarName(String donarName) {
		this.donarName = donarName;
	}

	public Boolean getReceiptRequested() {
		return receiptRequested;
	}

	public void setReceiptRequested(Boolean receiptRequested) {
		this.receiptRequested = receiptRequested;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

}
