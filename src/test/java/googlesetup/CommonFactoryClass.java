package googlesetup;

import org.codehaus.plexus.util.FileUtils;
import org.openqa.selenium.OutputType;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Random;

public class CommonFactoryClass {

    StringPlaceHolderClass spcObject = new StringPlaceHolderClass();

    public HttpURLConnection responseData() throws IOException {
        URL url = new URL(spcObject.sourceUrl);
        HttpURLConnection connection = (HttpURLConnection)url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        return connection;
    }

    public void screenShotMechanismOnFailure(ITestResult result){
        if (result.getStatus() == ITestResult.FAILURE) {
            OpenChrome ocObject = new OpenChrome();
            System.out.println("A Test failure has occured, a screenshot has been taken");
            Random rand = new Random();
            File file = ocObject.driver.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(file, new File("/Users/venkata.narasimhan/Documents/truefitrepo/truefitgoogle/screenshots/" + result.getName() + ".png"));
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println(file.getAbsolutePath());
        }
    }
}

