package googlesetup;

import commonfactory.CommonFactoryClass;
import commonfactory.StringPlaceHolderClass;
import cucumber.annotation.en.And;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class googleTextFieldSearchSteps_BDD {
    private StringPlaceHolderClass sphObject = new StringPlaceHolderClass();
    private CommonFactoryClass cfcObject = new CommonFactoryClass();
    private TestGoogleHomePageOpening ocObject = new TestGoogleHomePageOpening();

    @BeforeTest
    public void testSetup() {
        ocObject.testSetup();
    }

    @AfterTest
    public void testTearDown() throws IOException {
        ocObject.testTearDown();
    }

    @Given("^I open the google homepage to search$")
    public void iOpenTheGoogleHomepageToSearch() throws Throwable {
        ocObject.driver.navigate().to(sphObject.sourceUrl);
        WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
        Assert.assertTrue(textFieldSrc.isEnabled());
    }

    @And("^I find the text search area for typing inputs$")
    public void iFindTheTextSearchAreaForTypingInputs() throws Throwable {
        WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
        Assert.assertTrue(textFieldSrc.isDisplayed());
        textFieldSrc.click();
    }

    @When("^I add \"([^\"]*)\" into the text area for searching$")
    public void iAddIntoTheTextAreaForSearching(String arg0) throws Throwable {
        WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
        textFieldSrc.sendKeys(arg0);
        Assert.assertTrue(textFieldSrc.isEnabled());
    }

    @Then("^I assert \"([^\"]*)\" for proper rendering after typing$")
    public void iAssertForProperRenderingAfterTyping(String arg0) throws Throwable {
        WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
        Assert.assertEquals(textFieldSrc.getAttribute("value"), arg0);
        Assert.assertNotNull(textFieldSrc.getAttribute("value"));
    }

    @Then("^I search for \"([^\"]*)\" using the search button$")
    public void iSearchForUsingTheSearchButton(String arg0) throws Throwable {
        WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
        Assert.assertTrue(textFieldSrc.isDisplayed(), "Text field is displayed");
        Assert.assertTrue(textFieldSrc.isEnabled(), "Text field is not enabled and usable");
        textFieldSrc.click();
        textFieldSrc.sendKeys(arg0);
        WebElement searchButton = ocObject.driver.findElementByName(sphObject.googleSearchButton);
        searchButton.submit();
    }

    @Then("^I assert the returned JSON content for \"([^\"]*)\"$")
    public void iAssertTheReturnedJSONContentFor(String arg0) throws Throwable {
        URL url = new URL(sphObject.searchUrl+arg0+sphObject.searchUrlClientPsy);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Assert.assertEquals(connection.getResponseCode(),200);
        Assert.assertEquals(connection.getResponseMessage(),"OK");
        ocObject.driver.close();
    }
}