package fast.com.MobileAutomationBasic;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;

public class AppTest {
	
	 private AndroidDriver driver;
	
	 @BeforeTest
	    public void setUp() throws MalformedURLException {
		 
		    File f = new File("src\\test\\java\\resources");
	        File apk_file = new File(f, "General-Store.apk");
	        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
	        desiredCapabilities.setCapability("appium:deviceName", "local");
	        desiredCapabilities.setCapability("appium:udid", "emulator-5554");
	        desiredCapabilities.setCapability("platformName", "android");
	        desiredCapabilities.setCapability("appium:automationName", "UiAutomator2");
	        desiredCapabilities.setCapability("appium:appPackage", "com.androidsample.generalstore");
	        desiredCapabilities.setCapability("appium:appActivity", "com.androidsample.generalstore.SplashActivity");
	        desiredCapabilities.setCapability("app", apk_file.getAbsolutePath());
	        URL remoteUrl = new URL("http://127.0.0.1:4723");
	        driver = new AndroidDriver(remoteUrl, desiredCapabilities);
	        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	    }
	 
	 @Test
	 public void testGeneralStoreApk() {
		    driver.findElement(By.id("com.androidsample.generalstore:id/spinnerCountry")).click();
	        driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector().scrollable(true).instance(0)).scrollIntoView(new UiSelector().text(\"Belarus\"))"));
	        driver.findElement(By.xpath("//*[@text='Belarus']")).click();
	        driver.findElement(By.id("com.androidsample.generalstore:id/nameField")).sendKeys("hello %233");
	        driver.findElement(By.id("com.androidsample.generalstore:id/radioFemale")).click();
	        driver.findElement(By.id("com.androidsample.generalstore:id/btnLetsShop")).click();
	        driver.findElement(By.id("com.androidsample.generalstore:id/productAddCart")).click();
	        Assert.assertTrue(driver.findElement(By.xpath("//*[@text='ADDED TO CART']")).isDisplayed());
	        String countText = driver.findElement(By.id("com.androidsample.generalstore:id/counterText")).getText();
	        Assert.assertEquals(countText, "1");
	        
	        driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
	        WebElement checkbox = driver.findElement(By.xpath("//*[@text='Send me e-mails on discounts related to selected products in future']"));
	        checkbox.click();
		
	 }
	 
	 
	 @AfterTest
	    public void closeApp(){
	       // driver.removeApp("com.androidsample.generalstore");
	    }

}
