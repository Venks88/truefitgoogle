package googlesetup;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class OpenChrome extends TestListenerAdapter {

    private StringPlaceHolderClass sphObject = new StringPlaceHolderClass();

    //private class variables:
    public ChromeDriver driver = new ChromeDriver();

    @BeforeMethod
    public void beforeMethod() {
        System.out.println("@BeforeMethod");
        System.setProperty("webdriver.chrome.driver","chromedriver");
    }

    //The after method also serves as a tear down
    @AfterMethod
    public void afterMethod(ITestResult result) throws IOException {
        System.out.println("@AfterMethod");
        if (result.getStatus() == ITestResult.FAILURE) {
            System.out.println("A Test failure has occured, a screenshot has been taken");
            Random rand = new Random();
            File file = driver.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(file, new File("/Users/venkata.narasimhan/Documents/truefitrepo/truefitgoogle/screenshots/"+rand+".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(file.getAbsolutePath());
        }
        driver.close();
    }

    @Test
    public void checkForPageExistence() {
        try {
            driver.navigate().to(sphObject.sourceUrl);
            driver.getSessionId();
            //Assert.assertEquals(driver.getSessionId(), "33");
            Thread.sleep(5000);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
