package net.mobile.testing.demo.engine;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import net.mobile.testing.demo.Configuration;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.concurrent.TimeUnit;

public class AndroidDriverFactory {
    private static final AndroidDriverFactory instance = new AndroidDriverFactory();
    private final Configuration configuration = Configuration.getInstance();
    private final String deviceName = configuration.getProperty("device.name");
    private final String appName = configuration.getProperty("app.name");
    private final String appPath = configuration.getProperty("app.path");

    public static AndroidDriverFactory getInstance() {
        return instance;
    }

    public AndroidDriver<AndroidElement> getDriver(String testName) {
        return createDriver(testName);
    }

    private AndroidDriver<AndroidElement> createDriver(String testName) {
        AndroidDriver<AndroidElement> driver = null;
        File appDir = new File(appPath);
        File app = new File(appDir, appName);
        DesiredCapabilities capabilities = new DesiredCapabilities();
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
        capabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "uiautomator2");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, Long.parseLong(configuration.getProperty("driver.implicit_wait", "20")));

        String serverUrl = configuration.getProperty("server.url");
        try {
            driver = new AndroidDriver<>(new URL(serverUrl), capabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        assert driver != null;
        driver.manage().timeouts().implicitlyWait(Long.parseLong(configuration.getProperty("driver.implicit_wait", "20")), TimeUnit.SECONDS);
        driver.manage().timeouts().pageLoadTimeout(Long.parseLong(configuration.getProperty("driver.timeout", "30")), TimeUnit.SECONDS);
        driver.manage().deleteAllCookies();

        return driver;
    }


    }
