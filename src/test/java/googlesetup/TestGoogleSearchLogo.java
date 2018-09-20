package googlesetup;

import org.openqa.selenium.By;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.*;

import java.io.IOException;

//@Listeners({googlesetup.TestGoogleSearchLogo.class})

public class TestGoogleSearchLogo extends TestListenerAdapter {

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


    //Notes: replace the hard coded values hplogo, xpath, attribute
    @Test
    public void test_checkForGoogleLogo_Existence() {
        try {
            ocObject.driver.navigate().to(sphObject.sourceUrl);
            WebElement imgSource = ocObject.driver.findElement(By.xpath("//img[@id='hplogo']"));
            String tagType = ocObject.driver.findElementById("hplogo").getTagName();
            String src = imgSource.getAttribute("src");
            Assert.assertTrue(imgSource.isDisplayed(), "Google Logo is displayed");
            Assert.assertTrue(tagType.equals("img"), "The returned web element is not an image");
            Assert.assertTrue(src.equals("https://www.google.com/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png"),
                    "The returned image source is incorrect");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_checkForLogoAlignment(){
        try {
            ocObject.driver.navigate().to(sphObject.sourceUrl);
            WebElement imgSource = ocObject.driver.findElementByXPath("//img[@id='hplogo']");
            Point location = imgSource.getLocation();
            String alignment = imgSource.getCssValue("text-align");
            Assert.assertNotNull(location, "The location of the image is not null, it exists in an x and y axes");
            Assert.assertTrue(alignment.equals("-webkit-center"),"The image is not aligned centrally");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test_checkForLogoCSSProperties(){
        try {
            ocObject.driver.navigate().to(sphObject.sourceUrl);
            WebElement imgSource = ocObject.driver.findElementByXPath("//img[@id='hplogo']");
            String fontStyle = imgSource.getCssValue("font-family");
            String logoBackground = imgSource.getCssValue("background");
            String srcSet = imgSource.getAttribute("srcset");
            Assert.assertTrue(fontStyle.equals("arial, sans-serif"),"The image does not have proper fonts");
            Assert.assertTrue(logoBackground.equals("rgba(0, 0, 0, 0) none repeat scroll 0% 0% / auto padding-box border-box"),
                    "The image does not have proper background color");
            Assert.assertTrue(srcSet.equals("/images/branding/googlelogo/1x/googlelogo_color_272x92dp.png 1x, " +
                    "/images/branding/googlelogo/2x/googlelogo_color_272x92dp.png 2x"), "The source of the image is wrong");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}