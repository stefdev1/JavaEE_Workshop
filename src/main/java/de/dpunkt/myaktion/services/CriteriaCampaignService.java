package de.dpunkt.myaktion.services;

import java.util.List;

import javax.ejb.Stateless;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import de.dpunkt.myaktion.model.Campaign;
import de.dpunkt.myaktion.model.Donation;

@Stateless
@Alternative
public class CriteriaCampaignService implements CampaignService{

	@Inject
	EntityManager entityManager;
	
	@Override
	public List<Campaign> getAllCampaigns() {
		System.out.println("Use CriteriaCampaignService getAllCampaigns");
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Campaign> criteria = cb.createQuery(Campaign.class);
		Root<Campaign> campaignRoot = criteria.from(Campaign.class);
		criteria.select(campaignRoot).orderBy(cb.asc(campaignRoot.get("name")));
		TypedQuery<Campaign> query = entityManager.createQuery(criteria);
		List<Campaign> campaigns = query.getResultList();
		
		campaigns.forEach(campaign -> campaign.setAmountDonatedSoFar(getAmountDonatedSoFar(campaign)));
		return campaigns;
	}

	@Override
	public void addCampaign(Campaign campaign) {
		entityManager.persist(campaign);	
		
	}

	@Override
	public void deleteCampaign(Campaign campaign) {
		Campaign managedCampaign = entityManager.find(Campaign.class, campaign.getId());
		entityManager.remove(managedCampaign);
	}

	@Override
	public void updateCampaign(Campaign campaign) {
		// TODO Auto-generated method stub
		
	}
	
	private Double getAmountDonatedSoFar(Campaign campaign) {
		System.out.println("Use CriteriaCampaignService getAmountDonatedSoFar");

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		
		//SELECT SUM(d.amount) FROM Donation d WHERE d.campaign = :campaign"
		CriteriaQuery<Double> criteria = cb.createQuery(Double.class);
		Root<Donation> donationRoot = criteria.from(Donation.class);
		criteria.multiselect(cb.sum(donationRoot.get("amount")));
		ParameterExpression<Campaign> paramterCampaign = cb.parameter(Campaign.class);
		
		criteria.where(cb.equal(donationRoot.get("campaign"), paramterCampaign));
		
		
		TypedQuery<Double> query = entityManager.createQuery(criteria);
		query.setParameter(paramterCampaign, campaign);
		Double amountDonatedSoFar = query.getSingleResult();
		if(amountDonatedSoFar == null) {
			amountDonatedSoFar = 0d;
		}
		return amountDonatedSoFar;
	}


}
