package googlesetupFireFox;

import commonfactory.CommonFactoryClass;
import commonfactory.StringPlaceHolderClass;
import okhttp3.*;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.xml.dom.Tag;

import java.io.IOException;
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
    public void test_checkForPageExistence() {
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

    @Test
    @Tag(name = "TC0021")
    public void test_checkForPageFailureToLoad() throws IOException {
        OkHttpClient client = new OkHttpClient();

        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8");
        RequestBody body = RequestBody.create(mediaType, "404");
        Request request = new Request.Builder()
                .url("https://www.google.com/l")
                .get()
                .addHeader("origin", "https://notifications.google.com")
                .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) AppleWebKit/537.36 " +
                        "(KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36")
                .addHeader("content-type", "application/x-www-form-urlencoded;charset=UTF-8")
                .addHeader("accept", "*/*")
                .addHeader("x-client-data", "CI22yQEIpbbJAQjEtskBCKmdygEI153KAQjZncoBCKijygEY+aXKAQ==")
                .addHeader("referer", "https://notifications.google.com/")
                .addHeader("accept-encoding", "gzip, deflate, br")
                .addHeader("accept-language", "en-US,en;q=0.9")
                .addHeader("cache-control", "no-cache")
                .build();

        Response response = client.newCall(request).execute();
        Assert.assertEquals(response.networkResponse().code(), 404);
        Assert.assertEquals(response.networkResponse().message(), "Not Found");
        Assert.assertFalse(response.networkResponse().isSuccessful(), "There was no issue with failing of the test");
    }

    @Test
    @Tag(name = "TC0030")
    public void test_checkForPageResponseOn_PageSearch() {
        try {
            OkHttpClient client = new OkHttpClient();

            MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded;charset=UTF-8");
            RequestBody body = RequestBody.create(mediaType, "news");
            Request request = new Request.Builder()
                    .url("https://news.google.com/?hl=en-US&gl=US&ceid=US%3Aen")
                    .get()
                    .addHeader("origin", "https://notifications.google.com")
                    .addHeader("user-agent", "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_13_4) " +
                            "AppleWebKit/537.36 (KHTML, like Gecko) Chrome/69.0.3497.100 Safari/537.36")
                    .addHeader("content-type", "application/x-www-form-urlencoded;charset=UTF-8")
                    .addHeader("accept", "*/*")
                    .addHeader("x-client-data", "CI22yQEIpbbJAQjEtskBCKmdygEI153KAQjZncoBCKijygEY+aXKAQ==")
                    .addHeader("referer", "https://notifications.google.com/")
                    .addHeader("accept-encoding", "gzip, deflate, br")
                    .addHeader("accept-language", "en-US,en;q=0.9")
                    .addHeader("cache-control", "no-cache")
                    .addHeader("postman-token", "0362c1e3-cc97-79c9-1863-efd87b938255")
                    .build();
            Response response = client.newCall(request).execute();
            Assert.assertEquals(response.networkResponse().code(), 200);
            Assert.assertEquals(response.networkResponse().message(), "OK");
            Assert.assertTrue(response.networkResponse().isSuccessful(), "There was no issue with failing of the test");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
