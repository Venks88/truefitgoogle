package googlesetupChrome;

import commonfactory.CommonFactoryClass;
import commonfactory.StringPlaceHolderClass;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.*;

public class TestEmptyTextBoxSearch extends TestListenerAdapter{
    private StringPlaceHolderClass sphObject = new StringPlaceHolderClass();
    private CommonFactoryClass cfcObject = new CommonFactoryClass();
    private TestGoogleHomePageOpeningChrome ocObject = new TestGoogleHomePageOpeningChrome();

    @BeforeTest
    public void testSetup() {
        ocObject.testSetup();
    }

    @AfterTest
    public void testTearDown() throws IOException {
        ocObject.testTearDown();
    }

    @Test
    public void test_emptyTextBoxSearch_typing(){
        try {
            ocObject.driver.navigate().to(sphObject.sourceUrl);
            WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
            textFieldSrc.click();
            textFieldSrc.sendKeys("");
            WebElement searchButton = ocObject.driver.findElementByName(sphObject.googleSearchButton);
            WebElement luckySearchButton = ocObject.driver.findElementByName(sphObject.googleSearchLuckyButton);
            searchButton.submit();
            luckySearchButton.submit();
            Assert.assertFalse(searchButton.isDisplayed(), "Empty string searches work, when they should not be yeilding a new page");
            Assert.assertFalse(!searchButton.isEnabled(), "Empty string keeps the button in disabled state");
            Assert.assertFalse(luckySearchButton.isDisplayed(), "Empty string searches work, when they should not be yeilding a new page");
            Assert.assertFalse(!luckySearchButton.isEnabled(), "Empty string keeps the button in disabled state");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test_emptyTextBoxSearch_useEnterButton(){
        try {
            ocObject.driver.navigate().to(sphObject.sourceUrl);
            WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
            textFieldSrc.click();
            textFieldSrc.sendKeys("");
            WebElement searchButton = ocObject.driver.findElementByName(sphObject.googleSearchButton);
            WebElement luckySearchButton = ocObject.driver.findElementByName(sphObject.googleSearchLuckyButton);
            Actions action = new Actions(ocObject.driver);
            action.sendKeys(Keys.ENTER).build().perform();
            luckySearchButton.submit();
            Assert.assertFalse(searchButton.isDisplayed(), "Empty string searches work, when they should not be yeilding a new page");
            Assert.assertFalse(!searchButton.isEnabled(), "Empty string keeps the button in disabled state");
            Assert.assertFalse(luckySearchButton.isDisplayed(), "Empty string searches work, when they should not be yeilding a new page");
            Assert.assertFalse(!luckySearchButton.isEnabled(), "Empty string keeps the button in disabled state");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
