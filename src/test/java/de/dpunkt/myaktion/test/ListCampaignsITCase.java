package de.dpunkt.myaktion.test;

import static org.junit.Assert.assertEquals;

import org.jboss.arquillian.drone.api.annotation.Drone;
import org.jboss.arquillian.graphene.Graphene;
import org.jboss.arquillian.graphene.page.InitialPage;
import org.jboss.arquillian.graphene.page.Page;
import org.jboss.arquillian.junit.Arquillian;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebDriver;

import de.dpunkt.myaktion.model.Donation;
import de.dpunkt.myaktion.test.pages.DonateMoneyPage;
import de.dpunkt.myaktion.test.pages.EditDonationFormPage;
import de.dpunkt.myaktion.test.pages.ListCampaignsPage;

@RunWith(Arquillian.class)
public class ListCampaignsITCase extends AbstractITCase{
	
	@Drone
	WebDriver browser;
	
	@Page
	ListCampaignsPage listCampaignsPage;	
	@Page
	private EditDonationFormPage editDonationFormPage;	
	@Page
	private DonateMoneyPage donateMoneyPage;
	

	@Rule
	public ExpectedException expectWrongAssumtion = ExpectedException.none();
	
	@Test
	public void testAddDonationToCampaign(@InitialPage ListCampaignsPage listCampaignsPage) {
		SetupDatabase.addCampaign(DataFactory.createTestCampaign());
		listCampaignsPage.assertOnPage();
		Double amountDonatedSoFarStart = listCampaignsPage.getAmountDonatedSoFar();
		Donation donation = DataFactory.createDonation();
		SetupDatabase.addDonation(donation);
		listCampaignsPage = Graphene.goTo(ListCampaignsPage.class);
		
		assertEquals(amountDonatedSoFarStart + donation.getAmount(), listCampaignsPage.getAmountDonatedSoFar(), 0.01);
		
		SetupDatabase.addDonation(donation);
		listCampaignsPage = Graphene.goTo(ListCampaignsPage.class);
		assertEquals(2 * donation.getAmount(), listCampaignsPage.getAmountDonatedSoFar(), 0.01);


	
	}
	

}
