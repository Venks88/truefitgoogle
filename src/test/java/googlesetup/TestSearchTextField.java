package googlesetup;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

//@Listeners({googlesetup.TestSearchTextField.class})

public class TestSearchTextField extends TestListenerAdapter {

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

    @Override
    public void onTestFailure(ITestResult result) {
        cfcObject.screenShotMechanismOnFailure(result);
    }

    @Test
    public void test_checkForExistenceOfTextField(){
        ocObject.driver.navigate().to(sphObject.sourceUrl);
        WebElement textFieldSrc = ocObject.driver.findElementById(sphObject.googleTextField);

        Assert.assertTrue(textFieldSrc.isDisplayed(), "Text field is displayed");

        textFieldSrc.click();
        textFieldSrc.sendKeys("Hello");

        Assert.assertTrue(textFieldSrc.getTagName().equals("input"), "Tag name is incorrectly set");
        Assert.assertNotNull(textFieldSrc.getLocation(), "Location is null, it does not lie within an x and y axes");
        Assert.assertTrue(textFieldSrc.getAttribute("type").equals("text"), "Type attribute is incorrect for the search field");
        Assert.assertTrue(textFieldSrc.getAttribute("title").equals("Search"), "Title attribute is incorrect for the search field");
        Assert.assertTrue(textFieldSrc.getCssValue("font").equals("normal normal 400 normal 16px / 34px arial, sans-serif"),
                "The font is incorrect in the search field");

        WebElement voiceMic = ocObject.driver.findElementById(sphObject.googleTextFieldMic);

        Assert.assertTrue(voiceMic.isDisplayed(), "Mic image is displayed");
    }

    @Test
    public void test_checkForExistenceOfInputsInTextField_valid(){

    }

    @Test
    public void test_checkForExistenceOfInputsInTextField_invalid(){

    }

    @Test
    public void test_checkForExistenceOfInputsInTextField_specialChars_UTF8(){

    }

    @Test
    public void test_checkForExistenceOfInputsInTextField_specialChars_ASCII(){

    }

    @Test
    public void test_checkForExistenceOfInputsInTextField_specialChars_Unicode(){

    }

    @Test
    public void test_checkForExistenceOfInputsInTextField_SQLInjection(){

    }

    @Test
    public void test_checkForExistenceOfInputsInTextField_JSInjection(){

    }
}
