package googlesetupFireFox;

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

//@Listeners({googlesetupChrome.TestGoogleSearchLogo.class})

public class TestGoogleSearchLogo extends TestListenerAdapter {

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

    @Override
    public void onTestFailure(ITestResult result) {
        cfcObject.screenShotMechanismOnFailureFirefox(result);
    }


    //Notes: replace the hard coded values hplogo, xpath, attribute
    @Test
    public void test_checkForGoogleLogo_Existence() {
        try {
            ocObject.driver.navigate().to(sphObject.sourceUrl);
            WebElement imgSource = ocObject.driver.findElement(By.id("hplogo"));
            String tagType = ocObject.driver.findElementById("hplogo").getTagName();
            Assert.assertTrue(imgSource.isDisplayed(), "Google Logo is displayed");
            if(tagType.equals("div")) {
                Assert.assertTrue(tagType.equals("div"), "The returned web element is not an image");
                Assert.assertEquals(imgSource.getAttribute("baseURI"), sphObject.sourceUrl);
                Assert.assertEquals(imgSource.getAttribute("namespaceURI"), "http://www.w3.org/1999/xhtml");
            }if(tagType.equals("img")){
                Assert.assertTrue(imgSource.getAttribute("alt").equals("Google"));
                Assert.assertNotNull(imgSource.getAttribute("src"),"The source is not null");
                Assert.assertNotNull(imgSource.getAttribute("srcset"),"The source set is not null");
                Assert.assertNotNull(imgSource.getAttribute("style"),"The source set is not null");
            }
            String src = "";
            // extra step to handle doodles
            try{
                src = imgSource.getAttribute("localName");
                if(!src.isEmpty()) {
                    if (src.equals("div")) {
                        Assert.assertTrue(src.equals("div"),
                                "The returned image source is incorrect");
                    }
                }
            } catch (NullPointerException e){
                e.printStackTrace();
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_checkForLogoAlignment(){
        try {
            ocObject.driver.navigate().to(sphObject.sourceUrl);
            WebElement imgSource = ocObject.driver.findElementById("hplogo");
            Point location = imgSource.getLocation();
            String alignment = imgSource.getCssValue("text-align");
            Assert.assertNotNull(location, "The location of the image is not null, it exists in an x and y axes");
            if(alignment.equals("center") || alignment.equals("-webkit-center") || !alignment.isEmpty()) {
                Assert.assertTrue(alignment.equals("center") || alignment.equals("-webkit-center") || !alignment.isEmpty(), "The image is not aligned centrally");
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_checkForLogoCSSProperties(){
        try {
            ocObject.driver.navigate().to(sphObject.sourceUrl);
            WebElement imgSource = ocObject.driver.findElementById("hplogo");
            String fontStyle = imgSource.getCssValue("font-family");
            String logoBackground = imgSource.getCssValue("background");
            Assert.assertTrue(fontStyle.equals("arial, sans-serif"),"The image does not have proper fonts");
            Assert.assertNotNull(logoBackground,"The image does not have proper background color");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
