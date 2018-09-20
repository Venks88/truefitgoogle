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
    public void test_checkForExistenceOfTextField() throws InterruptedException {
        ocObject.driver.navigate().to(sphObject.sourceUrl);
        //WebElement textFieldSrc = ocObject.driver.findElementById(sphObject.googleTextField); - CA only
        WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);

        Assert.assertTrue(textFieldSrc.isDisplayed(), "Text field is displayed");
        Assert.assertTrue(textFieldSrc.isEnabled(), "Text field is not enabled and usable");

        textFieldSrc.click();
        textFieldSrc.sendKeys("Hello");

        Assert.assertTrue(textFieldSrc.getTagName().equals("input"), "Tag name is incorrectly set");
        Assert.assertNotNull(textFieldSrc.getLocation(), "Location is null, it does not lie within an x and y axes");
        Assert.assertTrue(textFieldSrc.getAttribute("type").equals("text"), "Type attribute is incorrect for the search field");
        Assert.assertTrue(textFieldSrc.getAttribute("title").equals("Search"), "Title attribute is incorrect for the search field");
        Assert.assertTrue(textFieldSrc.getCssValue("font").equals("normal normal 400 normal 16px / 34px arial, sans-serif"),
                "The font is incorrect in the search field");

        //WebElement voiceMic = ocObject.driver.findElementById(sphObject.googleTextFieldMic); - CA only
        WebElement voiceMic = ocObject.driver.findElementByCssSelector("div.voice_search_button");

        Assert.assertTrue(voiceMic.isDisplayed(), "Mic image is displayed");
    }

    @Test
    public void test_checkForExistenceOfInputsInTextField_valid(){
        ocObject.driver.navigate().to(sphObject.sourceUrl);
        WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
        textFieldSrc.click();
        textFieldSrc.sendKeys("Hello");
        Assert.assertEquals(textFieldSrc.getAttribute("value"), "Hello");
        Assert.assertNotNull(textFieldSrc.getAttribute("value"));
    }

    @Test
    public void test_checkForExistenceOfInputsInTextField_blankSpaces(){
        ocObject.driver.navigate().to(sphObject.sourceUrl);
        WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
        textFieldSrc.click();
        textFieldSrc.sendKeys("      ");
        Assert.assertEquals(textFieldSrc.getAttribute("value"), "      ");
        Assert.assertNotNull(textFieldSrc.getAttribute("value"));
    }

    @Test
    public void test_checkForExistenceOfInputsInTextField_specialChars_UTF8(){
        ocObject.driver.navigate().to(sphObject.sourceUrl);
        WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
        textFieldSrc.click();
        textFieldSrc.sendKeys("私tわたしワタシ");
        Assert.assertEquals(textFieldSrc.getAttribute("value"), "私tわたしワタシ");
        Assert.assertNotNull(textFieldSrc.getAttribute("value"));
    }


    @Test
    public void test_checkForExistenceOfInputsInTextField_specialChars_ASCII(){
        ocObject.driver.navigate().to(sphObject.sourceUrl);
        WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
        textFieldSrc.click();
        textFieldSrc.sendKeys("&#32;&#33;&#34;&#35;");
        Assert.assertEquals(textFieldSrc.getAttribute("value"),"&#32;&#33;&#34;&#35;");
        Assert.assertNotNull(textFieldSrc.getAttribute("value"));
    }

    @Test
    public void test_checkForExistenceOfInputsInTextField_specialChars_Unicode(){
        ocObject.driver.navigate().to(sphObject.sourceUrl);
        WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
        textFieldSrc.click();
        textFieldSrc.sendKeys("¡¢£¤¥¦§¨©ª«¬®¯°±²³´µ¶·¸¹º»¼");
        Assert.assertEquals(textFieldSrc.getAttribute("value"),"¡¢£¤¥¦§¨©ª«¬®¯°±²³´µ¶·¸¹º»¼");
        Assert.assertNotNull(textFieldSrc.getAttribute("value"));
    }

    @Test
    public void test_checkForExistenceOfInputsInTextField_SQLInjection(){
        ocObject.driver.navigate().to(sphObject.sourceUrl);
        WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
        textFieldSrc.click();
        textFieldSrc.sendKeys("SELECT * FROM Table name WHERE Table.size = 5");
        Assert.assertEquals(textFieldSrc.getAttribute("value"),"SELECT * FROM Table name WHERE Table.size = 5");
        Assert.assertNotNull(textFieldSrc.getAttribute("value"));
    }

    @Test
    public void test_checkForExistenceOfInputsInTextField_JSInjection(){
        ocObject.driver.navigate().to(sphObject.sourceUrl);
        WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
        textFieldSrc.click();
        textFieldSrc.sendKeys("<button type=\"button onclick=\"document.getElementById('demo').innerHTML = Date()\">");
        Assert.assertEquals(textFieldSrc.getAttribute("value"),"<button type=\"button onclick=\"document.getElementById('demo').innerHTML = Date()\">");
        Assert.assertNotNull(textFieldSrc.getAttribute("value"));
    }
}