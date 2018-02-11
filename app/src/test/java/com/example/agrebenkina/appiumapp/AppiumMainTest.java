package com.example.agrebenkina.appiumapp;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.DriverCommand;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

/**
 * Created by agrebenkina on 2/11/2018.
 */

public class AppiumMainTest {
    public WebDriver driver;

    @BeforeTest
    public  void setUp() throws MalformedURLException {
        DesiredCapabilities capabil = new DesiredCapabilities();
        capabil.setCapability(CapabilityType.BROWSER_NAME, "Android");
        capabil.setCapability("platformName", "Android");
        capabil.setCapability(CapabilityType.VERSION, "5.1.1");
        capabil.setCapability("deviceName", "Pixel XL API 22");
        capabil.setCapability("appPackage", "com.android.calculator2");
        capabil.setCapability("appActivity","com.android.calculator2.Calculator");
        driver = new RemoteWebDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabil);
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test
    public void testCalculation() {
        driver.findElement(By.xpath("//android.widget.Button")).click();
        driver.findElement(By.id("del")).click();
        driver.findElement(By.id("digit_7")).click();
        driver.findElement(By.id("op_add")).click();
        driver.findElement(By.id("digit_8")).click();
        driver.findElement(By.id("eq")).click();
        Assert.assertEquals(driver.findElement(By.id("formula")).getText(), "15");

        driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
