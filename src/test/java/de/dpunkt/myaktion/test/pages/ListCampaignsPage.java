package de.dpunkt.myaktion.test.pages;

import static org.junit.Assert.assertEquals;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

import static org.jboss.arquillian.graphene.Graphene.guardHttp;

import org.jboss.arquillian.graphene.page.Location;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import de.dpunkt.myaktion.test.Config;

@Location(Config.TEST_WEB_ARCHIVE + "/listCampaigns.jsf")
public class ListCampaignsPage extends AbstractPage{
	
	@FindBy(xpath="//tbody/tr[last()]/td[2]")
	private WebElement lastCampaignName;
	
	@FindBy(xpath = "//tbody/tr[last()]/td[7]/a")
	private WebElement lastEditFormLink;
	
	@FindBy(xpath = "//tbody/tr[last()]/td[4]")
	private WebElement lastDonationAmountSoFar;

	
	public void assertOnPage() {
		assertTitle("listCampaigns.my_campaigns");
	}
	
	public void assertCampaignName(String campaignName) {
		assertEquals(campaignName, lastCampaignName.getText());
	}
	
	public void doAddCampaign() {
		WebElement addCampaignButton = getButtonByLabel("listCampaigns.add_campaign");
		guardHttp(addCampaignButton).click();
	}
	
	public void clickCampaignUrl() {
		guardHttp(lastEditFormLink).click();
	}
	
	public void assertAmountDonatedSoFar(double expectedAmount) {
		double lastDonatedAmountSoFarNumber = getAmount(lastDonationAmountSoFar.getText());
		assertEquals(expectedAmount, lastDonatedAmountSoFarNumber, 0.004);
	}
	
	public Double getAmountDonatedSoFar() {
		return getAmount(lastDonationAmountSoFar.getText());
	}
	
	private Double getAmount(String amount) {
		String filteredAmount = amount.trim().replace("EUR", "").trim();
		NumberFormat numberFormat = NumberFormat.getNumberInstance(Locale.GERMANY);
		try {
			Number number = numberFormat.parse(filteredAmount);
			return number.doubleValue();
		} catch (ParseException e) {
			e.printStackTrace();
			return null;
		}		
	}


}
