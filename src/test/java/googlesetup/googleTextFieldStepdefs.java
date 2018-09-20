package googlesetup;

import cucumber.annotation.en.And;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import cucumber.runtime.PendingException;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runner.Runner;
import org.junit.runner.notification.RunNotifier;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public class googleTextFieldStepdefs extends Runner {

    private StringPlaceHolderClass sphObject = new StringPlaceHolderClass();
    private CommonFactoryClass cfcObject = new CommonFactoryClass();
    private OpenChrome ocObject = new OpenChrome();

    @BeforeTest
    public void testSetup() {
        ocObject.testSetup();
    }

    @AfterTest
    public void testTearDown() throws IOException {
        ocObject.testTearDown();
    }

    @Given("^I open the google homepage$")
    public void iOpenTheGoogleHomepage() {
        ocObject.driver.navigate().to(sphObject.sourceUrl);
        WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
        Assert.assertTrue(textFieldSrc.isEnabled());
    }

    @And("^I find the text search area$")
    public void iFindTheTextSearchArea() throws Throwable {
        WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
        Assert.assertTrue(textFieldSrc.isDisplayed());
        textFieldSrc.click();
    }

    @When("^I add \"([^\"]*)\" into the text area$")
    public void iAddIntoTheTextArea(String arg0) throws Throwable {
        WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
        textFieldSrc.sendKeys(arg0);
        Assert.assertTrue(textFieldSrc.isEnabled());
    }

    @Then("^I assert \"([^\"]*)\" for proper rendering$")
    public void iAssertForProperRendering(String arg0) throws Throwable {
        WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
        Assert.assertEquals(textFieldSrc.getAttribute("value"), arg0);
        Assert.assertNotNull(textFieldSrc.getAttribute("value"));
        ocObject.driver.close();
    }

    public Description getDescription() {
        return null;
    }

    public void run(RunNotifier runNotifier) {

    }

}
