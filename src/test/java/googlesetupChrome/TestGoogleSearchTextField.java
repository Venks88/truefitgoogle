package googlesetupChrome;

import commonfactory.CommonFactoryClass;
import commonfactory.StringPlaceHolderClass;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.xml.dom.Tag;

import java.io.IOException;

//@Listeners({googlesetupChrome.TestGoogleSearchTextField.class})

public class TestGoogleSearchTextField extends TestListenerAdapter {

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

    @Override
    public void onTestFailure(ITestResult result) {
        cfcObject.screenShotMechanismOnFailureChrome(result);
    }

    @Test
    @Tag(name = "TC0014")
    public void test_checkForExistenceOfTextField() throws InterruptedException {
        try {
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
            Assert.assertNotNull(textFieldSrc.getCssValue("font"), "The given fonts are as expected for the browser that is running the test");

            //WebElement voiceMic = ocObject.driver.findElementById(sphObject.googleTextFieldMic); - CA only
            WebElement voiceMic = ocObject.driver.findElementByCssSelector("div.voice_search_button");

            Assert.assertTrue(voiceMic.isDisplayed(), "Mic image is displayed");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Tag(name = "TC0015")
    public void test_checkForExistenceOfInputsInTextField_valid(){
        try {
            ocObject.driver.navigate().to(sphObject.sourceUrl);
            WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
            textFieldSrc.click();
            textFieldSrc.sendKeys("Hello");
            Assert.assertEquals(textFieldSrc.getAttribute("value"), "Hello");
            Assert.assertNotNull(textFieldSrc.getAttribute("value"));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Tag(name = "TC0016")
    public void test_checkForExistenceOfInputsInTextField_blankSpaces(){
        try {
            ocObject.driver.navigate().to(sphObject.sourceUrl);
            WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
            textFieldSrc.click();
            textFieldSrc.sendKeys("      ");
            Assert.assertEquals(textFieldSrc.getAttribute("value"), "      ");
            Assert.assertNotNull(textFieldSrc.getAttribute("value"));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Tag(name = "TC0017")
    public void test_checkForExistenceOfInputsInTextField_specialChars_UTF8(){
        try {
            ocObject.driver.navigate().to(sphObject.sourceUrl);
            WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
            textFieldSrc.click();
            textFieldSrc.sendKeys("私tわたしワタシ");
            Assert.assertEquals(textFieldSrc.getAttribute("value"), "私tわたしワタシ");
            Assert.assertNotNull(textFieldSrc.getAttribute("value"));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }


    @Test
    @Tag(name = "TC0018")
    public void test_checkForExistenceOfInputsInTextField_specialChars_ASCII(){
        try {
            ocObject.driver.navigate().to(sphObject.sourceUrl);
            WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
            textFieldSrc.click();
            textFieldSrc.sendKeys("&#32;&#33;&#34;&#35;");
            Assert.assertEquals(textFieldSrc.getAttribute("value"), "&#32;&#33;&#34;&#35;");
            Assert.assertNotNull(textFieldSrc.getAttribute("value"));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Tag(name = "TC0019")
    public void test_checkForExistenceOfInputsInTextField_specialChars_Unicode(){
        try {
            ocObject.driver.navigate().to(sphObject.sourceUrl);
            WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
            textFieldSrc.click();
            textFieldSrc.sendKeys("¡¢£¤¥¦§¨©ª«¬®¯°±²³´µ¶·¸¹º»¼");
            Assert.assertEquals(textFieldSrc.getAttribute("value"), "¡¢£¤¥¦§¨©ª«¬®¯°±²³´µ¶·¸¹º»¼");
            Assert.assertNotNull(textFieldSrc.getAttribute("value"));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Tag(name = "TC0020")
    public void test_checkForExistenceOfInputsInTextField_SQLInjection(){
        try {
            ocObject.driver.navigate().to(sphObject.sourceUrl);
            WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
            textFieldSrc.click();
            textFieldSrc.sendKeys("SELECT * FROM Table name WHERE Table.size = 5");
            Assert.assertEquals(textFieldSrc.getAttribute("value"), "SELECT * FROM Table name WHERE Table.size = 5");
            Assert.assertNotNull(textFieldSrc.getAttribute("value"));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Test
    @Tag(name = "TC0021")
    public void test_checkForExistenceOfInputsInTextField_JSInjection(){
        try {
            ocObject.driver.navigate().to(sphObject.sourceUrl);
            WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
            textFieldSrc.click();
            textFieldSrc.sendKeys("<button type=\"button onclick=\"document.getElementById('demo').innerHTML = Date()\">");
            Assert.assertEquals(textFieldSrc.getAttribute("value"), "<button type=\"button onclick=\"document.getElementById('demo').innerHTML = Date()\">");
            Assert.assertNotNull(textFieldSrc.getAttribute("value"));
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
