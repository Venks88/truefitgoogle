package googlesetup;

import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class TestGoogleSearchButton extends TestListenerAdapter{
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

    public void textSearchSetup(String inputKey){
        ocObject.driver.navigate().to(sphObject.sourceUrl);
        WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
        Assert.assertTrue(textFieldSrc.isDisplayed(), "Text field is displayed");
        Assert.assertTrue(textFieldSrc.isEnabled(), "Text field is not enabled and usable");
        textFieldSrc.click();
        textFieldSrc.sendKeys(inputKey);
    }

    @Test
    public void test_LookForSearchButton(){
        ocObject.driver.navigate().to(sphObject.sourceUrl);
        WebElement searchButton = ocObject.driver.findElementByName(sphObject.googleSearchButton);
        Assert.assertFalse(searchButton.isDisplayed(), "The search button is not displayed");
        Assert.assertTrue(searchButton.isEnabled(), "The search button is not enabled");
        Assert.assertEquals(searchButton.getTagName(), "input");
        Assert.assertEquals(searchButton.getAttribute("defaultValue"), "Google Search");
        Assert.assertEquals(searchButton.getAttribute("type"), "submit");
    }

    @Test
    public void test_LookForLuckSearchButton(){
        ocObject.driver.navigate().to(sphObject.sourceUrl);
        WebElement searchButton = ocObject.driver.findElementByName(sphObject.googleSearchLuckyButton);
        Assert.assertEquals(searchButton.getTagName(), "input");
        Assert.assertEquals(searchButton.getAttribute("defaultValue"), "I'm Feeling Lucky");
        Assert.assertEquals(searchButton.getAttribute("type"), "submit");
        Assert.assertFalse(searchButton.isDisplayed(), "The search button is not displayed");
        Assert.assertTrue(searchButton.isEnabled(), "The search button is not enabled");
    }

    //https://www.google.com/search?source=hp&q=Hello&btnK=Google+Search&oq=Hello
    @Test
    public void test_SearchButtonWithText() {
        textSearchSetup("Hello");
        WebElement searchButton = ocObject.driver.findElementByName(sphObject.googleSearchButton);
        searchButton.submit();
        WebDriverWait wait = new WebDriverWait(ocObject.driver, 10);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(sphObject.googleTextFieldname)));
        WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
        Assert.assertTrue(textFieldSrc.isDisplayed(), "Text field is available on the redirected page");
        Assert.assertTrue(textFieldSrc.isEnabled(), "Text field is NOT enabled");
        Assert.assertEquals(textFieldSrc.getAttribute("value"), "Hello");
        Assert.assertEquals(textFieldSrc.getAttribute("title"), "Search");
        Assert.assertEquals(textFieldSrc.getAttribute("type"), "text");
        Assert.assertEquals(textFieldSrc.getTagName(), "input");
    }

    @Test
    public void test_SearchButtonWithNoText() throws InterruptedException{
        textSearchSetup("");
        WebElement searchButton = ocObject.driver.findElementByName(sphObject.googleSearchButton);
        WebElement luckySearchButton = ocObject.driver.findElementByName(sphObject.googleSearchLuckyButton);
        searchButton.submit();
        luckySearchButton.submit();
        Assert.assertFalse(searchButton.isDisplayed(),"Empty string searches work, when they should not be yeilding a new page");
        Assert.assertTrue(searchButton.isEnabled(), "Empty string keeps the button in disabled state");
        Assert.assertFalse(luckySearchButton.isDisplayed(),"Empty string searches work, when they should not be yeilding a new page");
        Assert.assertTrue(luckySearchButton.isEnabled(), "Empty string keeps the button in disabled state");
    }

    @Test
    public void test_SearchButtonWithNumbers(){
        ArrayList<String> inputArray = new ArrayList<String>();
        Random rand = new Random();
        for(int i = 0; i<=10; i++){
            inputArray.add(Integer.toString(rand.nextInt()));
        }
        int length = inputArray.size();
        for(int i=0; i<=length-1;i++){
            textSearchSetup(inputArray.get(i));
            WebElement searchButton = ocObject.driver.findElementByName(sphObject.googleSearchButton);
            searchButton.submit();
            WebDriverWait wait = new WebDriverWait(ocObject.driver, 4);
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.name(sphObject.googleTextFieldname)));
            WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
            Assert.assertEquals(textFieldSrc.getAttribute("value"), inputArray.get(i));
            Assert.assertEquals(textFieldSrc.getAttribute("title"), "Search");
            Assert.assertEquals(textFieldSrc.getAttribute("type"), "text");
            Assert.assertEquals(textFieldSrc.getTagName(), "input");
            Assert.assertTrue(textFieldSrc.isDisplayed(), "Text field is available on the redirected page");
            Assert.assertTrue(textFieldSrc.isEnabled(), "Text field is NOT enabled");
        }
    }

    @Test
    public void test_SearchButtonWithSpecialChars(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        ArrayList<String> inputArray = new ArrayList<String>();
        for(int i = 0; i<=10; i++){
            String specialCharString = RandomStringUtils.random( 15, characters );
            inputArray.add(specialCharString);
        }
        int length = inputArray.size();
        for(int i=0; i<=length-1;i++){
            textSearchSetup(inputArray.get(i));
            WebElement searchButton = ocObject.driver.findElementByName(sphObject.googleSearchButton);
            searchButton.submit();
            WebDriverWait wait = new WebDriverWait(ocObject.driver, 4);
            WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
            Assert.assertTrue(textFieldSrc.isDisplayed(), "Text field is available on the redirected page");
            Assert.assertTrue(textFieldSrc.isEnabled(), "Text field is NOT enabled");
            Assert.assertEquals(textFieldSrc.getAttribute("value"), inputArray.get(i));
            Assert.assertEquals(textFieldSrc.getTagName(), "input");
        }
    }

    @Test
    public void test_SearchButtonWithTextAndRedirect(){
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789~`!@#$%^&*()-_=+[{]}\\|;:\'\",<.>/?";
        ArrayList<String> inputArray = new ArrayList<String>();
        String specialCharString = RandomStringUtils.random( 15, characters );
        inputArray.add(specialCharString);
        textSearchSetup(inputArray.get(0));
        WebElement searchButton = ocObject.driver.findElementByName(sphObject.googleSearchButton);
        searchButton.submit();
        WebElement textFieldSrc = ocObject.driver.findElementByName(sphObject.googleTextFieldname);
        Assert.assertTrue(textFieldSrc.isDisplayed(), "Text field is available on the redirected page");
        Assert.assertTrue(textFieldSrc.isEnabled(), "Text field is NOT enabled");
        ocObject.driver.navigate().back();
        WebElement imgSource = ocObject.driver.findElement(By.xpath("//img[@id='hplogo']"));
        Assert.assertTrue(imgSource.isDisplayed(), "Google Logo is displayed");
    }

    @Test
    public void test_SearchButtonWithTextJSONResponse() throws IOException {
        Scanner s = new Scanner(new File("../../truefitrepo/truefitgoogle/src/test/java/searchInputText.txt"));
        ArrayList<String> list = new ArrayList<String>();
        while (s.hasNext()){
            list.add(s.next());
        }
        s.close();
        for(int i=0; i<=list.size()-1;i++) {
            System.out.println("->  "+i);
            URL url = new URL(sphObject.searchUrl +list.get(i)+sphObject.searchUrlClientPsy);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();
            Assert.assertEquals(connection.getResponseCode(),200);
            Assert.assertEquals(connection.getResponseMessage(),"OK");
            Assert.assertEquals(connection.getContentType(),"application/json; charset=ISO-8859-1");
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    connection.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null)
                Assert.assertTrue(inputLine.contains(list.get(i)),"The JSON content is invalid " + list.get(i) + inputLine);
            in.close();
        }
    }

    @Test
    public void test_SearchButtonWithEmptyTextFailTest() throws IOException{
        URL url = new URL(sphObject.searchUrl +""+sphObject.searchUrlClientPsy);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.connect();
        Assert.assertEquals(connection.getResponseCode(),200);
        Assert.assertEquals(connection.getResponseMessage(),"OK");
        Assert.assertEquals(connection.getContentType(),"application/json; charset=ISO-8859-1");
        BufferedReader in = new BufferedReader(new InputStreamReader(
                connection.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null)
            Assert.assertTrue(inputLine.contains("{\"bpc\":false,\"tlw\":false}"),"The expected false notations are missing for empty search requests");
        in.close();
    }

}
