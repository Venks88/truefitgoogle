package googlesetup;

import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.*;

import java.net.HttpURLConnection;

//@Listeners({googlesetup.TestGoogleHomePageOpening.class})

public class TestGoogleHomePageOpening extends TestListenerAdapter {

    private StringPlaceHolderClass sphObject = new StringPlaceHolderClass();
    private CommonFactoryClass cfcObject = new CommonFactoryClass();

    //private class variables:
    public ChromeDriver driver = new ChromeDriver();

    @BeforeTest
    public void testSetup() {
        System.out.println("@BeforeTest has been executed");
        System.setProperty("webdriver.chrome.driver","chromedriver");
    }

    @AfterTest
    public void testTearDown() {
        System.out.println("@AfterTest has been executed");
        driver.close();
    }

    @Override
    public void onTestFailure(ITestResult result) {
        cfcObject.screenShotMechanismOnFailure(result);
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
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
