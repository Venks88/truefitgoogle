package googlesetupChrome;

import commonfactory.CommonFactoryClass;
import commonfactory.StringPlaceHolderClass;
import cucumber.annotation.en.And;
import cucumber.annotation.en.Given;
import cucumber.annotation.en.Then;
import cucumber.annotation.en.When;
import org.openqa.selenium.ElementNotSelectableException;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;

import java.io.IOException;

public class googleTextFieldSteps_BDD {

    private StringPlaceHolderClass sphObject = new StringPlaceHolderClass();
    private TestGoogleHomePageOpeningChrome ocObject = new TestGoogleHomePageOpeningChrome();

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
        try {
            ocObject.driver.navigate().to(sphObject.sourceUrl);
            WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
            Assert.assertTrue(textFieldSrc.isEnabled());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        } catch (ElementNotSelectableException e){
            e.printStackTrace();
        }
    }

    @And("^I find the text search area$")
    public void iFindTheTextSearchArea() throws Throwable {
        try {
            WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
            Assert.assertTrue(textFieldSrc.isDisplayed());
            textFieldSrc.click();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        } catch (ElementNotSelectableException e){
            e.printStackTrace();
        }
    }

    @When("^I add \"([^\"]*)\" into the text area$")
    public void iAddIntoTheTextArea(String arg0) throws Throwable {
        try {
            WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
            textFieldSrc.sendKeys(arg0);
            Assert.assertTrue(textFieldSrc.isEnabled());
        }catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        } catch (ElementNotSelectableException e){
            e.printStackTrace();
        }
    }

    @Then("^I assert \"([^\"]*)\" for proper rendering$")
    public void iAssertForProperRendering(String arg0) throws Throwable {
        try {
            WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
            Assert.assertEquals(textFieldSrc.getAttribute("value"), arg0);
            Assert.assertNotNull(textFieldSrc.getAttribute("value"));
            ocObject.driver.close();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        } catch (ElementNotSelectableException e){
            e.printStackTrace();
        }
    }
}
