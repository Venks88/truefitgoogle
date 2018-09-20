package googlesetup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestGoogleSearchButton extends TestListenerAdapter{
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

    @Override
    public void onTestFailure(ITestResult result) {
        cfcObject.screenShotMechanismOnFailure(result);
    }

    public void textSearchSetup(String inputKey){
        ocObject.driver.navigate().to(sphObject.sourceUrl);
        WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
        Assert.assertTrue(textFieldSrc.isDisplayed(), "Text field is displayed");
        Assert.assertTrue(textFieldSrc.isEnabled(), "Text field is not enabled and usable");
        textFieldSrc.click();
        textFieldSrc.sendKeys(inputKey);
    }

    @Test
    public void test_LookForSearchButton(){
        ocObject.driver.navigate().to(sphObject.sourceUrl);
        WebElement searchButton = ocObject.driver.findElementByName(sphObject.googleSearchButton);
        Assert.assertFalse(searchButton.isDisplayed(), "The search button is not displayed");
        Assert.assertTrue(searchButton.isEnabled(), "The search button is not enabled");
        Assert.assertEquals(searchButton.getTagName(), "input");
        Assert.assertEquals(searchButton.getAttribute("defaultValue"), "Google Search");
        Assert.assertEquals(searchButton.getAttribute("type"), "submit");
    }

    @Test
    public void test_LookForLuckSearchButton(){
        ocObject.driver.navigate().to(sphObject.sourceUrl);
        WebElement searchButton = ocObject.driver.findElementByName(sphObject.googleSearchLuckyButton);
        Assert.assertEquals(searchButton.getTagName(), "input");
        Assert.assertEquals(searchButton.getAttribute("defaultValue"), "I'm Feeling Lucky");
        Assert.assertEquals(searchButton.getAttribute("type"), "submit");
        Assert.assertFalse(searchButton.isDisplayed(), "The search button is not displayed");
        Assert.assertTrue(searchButton.isEnabled(), "The search button is not enabled");
    }

    //https://www.google.com/search?source=hp&q=Hello&btnK=Google+Search&oq=Hello
    @Test
    public void test_SearchButtonWithText() throws InterruptedException {
        textSearchSetup("Hello");
        WebElement searchButton = ocObject.driver.findElementByName(sphObject.googleSearchButton);
        searchButton.submit();
        WebDriverWait wait = new WebDriverWait(ocObject.driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(sphObject.googleTextFieldname)));
        WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
        Assert.assertTrue(textFieldSrc.isDisplayed(), "Text field is available on the redirected page");
        Assert.assertTrue(textFieldSrc.isEnabled(), "Text field is NOT enabled");
        Assert.assertEquals(textFieldSrc.getAttribute("value"), "Hello");
        Assert.assertEquals(textFieldSrc.getAttribute("title"), "Search");
        Assert.assertEquals(textFieldSrc.getAttribute("type"), "text");
        Assert.assertEquals(textFieldSrc.getTagName(), "input");
    }

    @Test
    public void test_SearchButtonWithNoText(){

    }

    @Test
    public void test_SearchButtonWithNumbers(){

    }

    @Test
    public void test_SearchButtonWithSpecialChars(){

    }

    @Test
    public void test_SearchButtonWithTextAndRedirect(){

    }

    @Test
    public void test_SearchButtonWithTextJSONResponse(){

    }

    @Test
    public void test_SearchButtonWithEmptyTextJSONResponse(){

    }

}
