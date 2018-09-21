package googlesetupFireFox;

import commonfactory.CommonFactoryClass;
import commonfactory.StringPlaceHolderClass;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.HttpURLConnection;

//@Listeners({googlesetupChrome.TestGoogleHomePageOpeningChrome.class})

public class TestGoogleHomePageOpeningFirefox extends TestListenerAdapter {

    private StringPlaceHolderClass sphObject = new StringPlaceHolderClass();
    private CommonFactoryClass cfcObject = new CommonFactoryClass();

    //private class variables:
    public FirefoxDriver driver = new FirefoxDriver();

    @BeforeTest
    public void testSetup() {
        System.out.println("@BeforeTest has been executed");
        System.setProperty("webdriver.gecko.driver","firefox");
    }

    @AfterTest
    public void testTearDown() {
        System.out.println("@AfterTest has been executed");
        driver.close();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        cfcObject.screenShotMechanismOnFailureFirefox(result);
    }

    @Test
    public void checkForPageExistence() {
        try {
            driver.navigate().to(sphObject.sourceUrl);
            HttpURLConnection responseCode = cfcObject.responseData();
            Assert.assertEquals(responseCode.getResponseCode(), 200);
            Assert.assertEquals(responseCode.getResponseMessage(), "OK");
            Assert.assertEquals(responseCode.getContentType(), "text/html; charset=ISO-8859-1");
            Assert.assertEquals(responseCode.getPermission().getName(), "www.google.com:80");
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        } catch (ElementNotVisibleException e) {
            e.printStackTrace();
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
