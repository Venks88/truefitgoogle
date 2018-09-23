package googlesetupChrome;

import commonfactory.CommonFactoryClass;
import commonfactory.StringPlaceHolderClass;
import org.openqa.selenium.*;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.ArrayList;

public class TestURLusingGoogleSearch extends TestListenerAdapter{
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

    String[] urlListArray = new String[]{ "www.google.com","www.gmail.com","www.com","www."+"+s+"+".com", "s.com","www.",".com"};

    @Test
    public void test_URLSearchingUsingGoogle(){
        for(int i = 0; i<=urlListArray.length-1;i++) {
            try {
                ocObject.driver.navigate().to(sphObject.sourceUrl);
                //Copy the text for about
                WebElement aboutLink = ocObject.driver.findElementById("hptl");
                String linkText = aboutLink.getText();
                WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
                textFieldSrc.click();
                //paste it on the text box
                textFieldSrc.sendKeys(urlListArray[i]);
                WebElement searchButton = ocObject.driver.findElementByName(sphObject.googleSearchButton);
                searchButton.submit();
                ocObject.driver.navigate().back();
                textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
                Assert.assertNotNull(textFieldSrc.getText(), "Empty string searches work, when they should not be yeilding a new page");
                Assert.assertTrue(textFieldSrc.isEnabled(), "Empty string keeps the button in disabled state");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Test
    public void test_URLSearchingUsingGoogle_32limit(){
            try {
                ocObject.driver.navigate().to(sphObject.sourceUrl);
                //Copy the text for about
                WebElement aboutLink = ocObject.driver.findElementById("hptl");
                String linkText = aboutLink.getText();
                WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
                textFieldSrc.click();
                //paste it on the text box
                textFieldSrc.sendKeys("Empty string searches work, when they should not be yeilding a new page Assert." +
                        "assertTrue(textFieldSrc.isEnabled(), Empty string keeps the button in disabled state" +
                        "Empty string searches work, when they should not be yeilding a new page Assert." +
                        "assertTrue(textFieldSrc.isEnabled(), Empty string keeps the button in disabled state");
                WebElement searchButton = ocObject.driver.findElementByName(sphObject.googleSearchButton);
                searchButton.submit();
                WebElement errorMessage = ocObject.driver.findElementById("ucs");
                String error = errorMessage.getAttribute("textContent");
                Assert.assertTrue(error.contains("   \"textFieldSrc\" (and any subsequent words) was ignored because we limit queries to 32 words."),
                        "The 32 word limit has been broken");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    @Test
    public void test_URLSearchingUsingGoogle_LongString(){
        try {
            ocObject.driver.navigate().to(sphObject.sourceUrl);
            //Copy the text for about
            WebElement aboutLink = ocObject.driver.findElementById("hptl");
            String linkText = aboutLink.getText();
            WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
            textFieldSrc.click();
            //paste it on the text box
            textFieldSrc.sendKeys("Emptystringsearcheswork,whentheyshouldnotbeeildinganewpageAssert." +
                    "assertTrue(textFieldSrc.isEnabled(),Emptystringkeepsthebuttonindisabledstate");
            WebElement searchButton = ocObject.driver.findElementByName(sphObject.googleSearchButton);
            searchButton.submit();
            WebElement errorMessage = ocObject.driver.findElementById("topstuff");
            String error = errorMessage.getAttribute("textContent");
            Assert.assertTrue(error.contains("- did not match any documents."));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
