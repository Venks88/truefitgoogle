package googlesetupFireFox;

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
import java.net.HttpURLConnection;
import java.net.URL;

public class googleTextFieldSearchNegativeSteps_BDD {

    private StringPlaceHolderClass sphObject = new StringPlaceHolderClass();
    private TestGoogleHomePageOpeningFirefox ocObject = new TestGoogleHomePageOpeningFirefox();

    @BeforeTest
    public void testSetup() {
        ocObject.testSetup();
    }

    @AfterTest
    public void testTearDown() throws IOException {
        ocObject.testTearDown();
    }

    @Given("^I open the google homepage to search - negative test$")
    public void iOpenTheGoogleHomepageToSearchNegativeTest() throws Throwable {
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

    @And("^I find the text search area for typing inputs - negative test$")
    public void iFindTheTextSearchAreaForTypingInputsNegativeTest() throws Throwable {
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

    @When("^I add \"([^\"]*)\" into the text area for searching - negative test$")
    public void iAddIntoTheTextAreaForSearchingNegativeTest(String arg0) throws Throwable {
        try {
            WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
            textFieldSrc.sendKeys(arg0);
            Assert.assertTrue(textFieldSrc.isEnabled());
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        } catch (ElementNotSelectableException e){
            e.printStackTrace();
        }
    }

    @Then("^I assert \"([^\"]*)\" for proper rendering after typing - negative test$")
    public void iAssertForProperRenderingAfterTypingNegativeTest(String arg0) throws Throwable {
        try {
            WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
            Assert.assertEquals(textFieldSrc.getAttribute("value"), arg0);
            Assert.assertNotNull(textFieldSrc.getAttribute("value"));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        } catch (ElementNotSelectableException e){
            e.printStackTrace();
        }
    }

    @Then("^I search for \"([^\"]*)\" using the search button - negative test$")
    public void iSearchForUsingTheSearchButtonNegativeTest(String arg0) throws Throwable {
        try {
            WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
            Assert.assertTrue(textFieldSrc.isDisplayed(), "Text field is NOT displayed");
            Assert.assertTrue(textFieldSrc.isEnabled(), "Text field is NOT enabled and usable");
            textFieldSrc.click();
            textFieldSrc.sendKeys(arg0);
            WebElement searchButton = ocObject.driver.findElementByName(sphObject.googleSearchButton);
            searchButton.submit();
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        } catch (ElementNotSelectableException e){
            e.printStackTrace();
        }
    }

    @Then("^I assert the returned JSON content for \"([^\"]*)\" - negative test$")
    public void iAssertTheReturnedJSONContentForNegativeTest(String arg0) throws Throwable {
        try {
            URL url = new URL(sphObject.searchUrl + arg0 + sphObject.searchUrlClientPsy);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            Assert.assertEquals(connection.getResponseCode(), 400);
            Assert.assertEquals(connection.getResponseMessage(), "Bad Request");
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
