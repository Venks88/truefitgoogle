package googlesetupFireFox;

import commonfactory.CommonFactoryClass;
import commonfactory.StringPlaceHolderClass;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

public class TestCopyPasteAndSearch extends TestListenerAdapter{
    private StringPlaceHolderClass sphObject = new StringPlaceHolderClass();
    private CommonFactoryClass cfcObject = new CommonFactoryClass();
    private TestGoogleHomePageOpeningFirefox ocObject = new TestGoogleHomePageOpeningFirefox();

    @BeforeTest
    public void testSetup() {
        ocObject.testSetup();
    }

    @AfterTest
    public void testTearDown() throws IOException {
        ocObject.testTearDown();
    }

    @Test
    public void test_copyPasteAndSearch(){
        try {
            ocObject.driver.navigate().to(sphObject.sourceUrl);
            //Copy the text for about
            WebElement aboutLink = ocObject.driver.findElementById("hptl");
            String linkText = aboutLink.getText();
            WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
            textFieldSrc.click();
            //paste it on the text box
            textFieldSrc.sendKeys(linkText);
            WebElement searchButton = ocObject.driver.findElementByName(sphObject.googleSearchButton);
            searchButton.submit();
            ocObject.driver.navigate().back();
            textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
            Assert.assertNotNull(textFieldSrc.getText(), "Empty string searches work, when they should not be yeilding a new page");
            Assert.assertTrue(textFieldSrc.isEnabled(), "Empty string keeps the button in disabled state");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    public void test_copyPasteAndSearch_useEnter(){
        try {
            ocObject.driver.navigate().to(sphObject.sourceUrl);
            //Copy the text for about
            WebElement aboutLink = ocObject.driver.findElementById("hptl");
            String linkText = aboutLink.getText();
            WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
            textFieldSrc.click();
            //paste it on the text box
            textFieldSrc.sendKeys(linkText);
            Actions action = new Actions(ocObject.driver);
            action.sendKeys(Keys.ENTER).build().perform();
            ocObject.driver.navigate().back();
            textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
            Assert.assertNotNull(textFieldSrc.getText(), "Empty string searches work, when they should not be yeilding a new page");
            Assert.assertTrue(textFieldSrc.isEnabled(), "Empty string keeps the button in disabled state");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
