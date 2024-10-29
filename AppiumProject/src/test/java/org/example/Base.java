package org.example;

import io.appium.java_client.android.AndroidDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Objects;

public class Base {
    public static AndroidDriver driver;
    public static Config conf = Config.getInstance();

    @BeforeSuite
    public static void setUp() throws MalformedURLException {
        DesiredCapabilities cap = new DesiredCapabilities();
        cap.setCapability("appium:automationName", "UiAutomator2");
        cap.setCapability("appium:udid", conf.read("udid"));
        cap.setCapability("appium:appPackage", conf.read("appPackage"));
        cap.setCapability("appium:appActivity", conf.read("appActivity"));
        cap.setCapability("appium:skipUnlock", true);
        cap.setCapability("appium:noReset", true);
        cap.setCapability("appium:autoGrantPermissions", true);
        driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), cap);
    }

    @AfterSuite
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }

    }


}
