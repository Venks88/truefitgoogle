package commonfactory;

import googlesetupChrome.TestGoogleHomePageOpeningChrome;
import googlesetupFireFox.TestGoogleHomePageOpeningFirefox;
import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CommonFactoryClass {

    StringPlaceHolderClass spcObject = new StringPlaceHolderClass();

    public HttpURLConnection responseData() throws IOException {
        URL url = new URL(spcObject.sourceUrl);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        return connection;
    }

    public void screenShotMechanismOnFailureChrome(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE) {
            TestGoogleHomePageOpeningChrome ocObject = new TestGoogleHomePageOpeningChrome();
            System.out.println("A Test failure has occured, a screenshot has been taken");
            File file = ocObject.driver.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(file,
                        new File("/Users/venkata.narasimhan/Documents/truefitrepo/truefitgoogle/screenshots/" + result.getName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(file.getAbsolutePath());
        }
    }

    public void screenShotMechanismOnFailureFirefox(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE) {
            TestGoogleHomePageOpeningFirefox ocObject = new TestGoogleHomePageOpeningFirefox();
            System.out.println("A Test failure has occured, a screenshot has been taken");
            File file = ocObject.driver.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(file,
                        new File("/Users/venkata.narasimhan/Documents/truefitrepo/truefitgoogle/screenshots/" + result.getName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(file.getAbsolutePath());
        }
    }


}

