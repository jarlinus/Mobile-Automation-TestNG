package net.mobile.testing.demo.engine;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.AndroidElement;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;

import java.util.HashMap;
import java.util.Map;

/**
 * The test context that will be used for the different tests.
 * It holds the test state: the current driver and a test data {@link Map}.
 * <p>
 * A new instance should be initialised per test flow, and should be destroyed at the end of its lifecycle via the teardown method.
 */
public class TestContext {
    public static final String DEFAULT_DRIVER = "DEFAULT_DRIVER";

    private final Map<String, AndroidDriver<AndroidElement>> drivers = new HashMap<>();
    private final Map<String, Object> testData = new HashMap<>();
    ITestResult testResult;

    public AndroidDriver<AndroidElement> getDriver(String name) {
        AndroidDriver<AndroidElement> driver = drivers.get(name);
        if (driver == null) {
            driver = getDriverFromFactory();
            drivers.put(name, driver);
        }
        return driver;
    }

    public AndroidDriver<AndroidElement> getDriver() {
        return getDriver(DEFAULT_DRIVER);
    }

    public void getServerStart() {
        ServerStart.getInstance().startServer();
    }

    public void getEmulator() {
        Emulator.getInstance().startEmulator();
    }

    protected AndroidDriver<AndroidElement> getDriverFromFactory() {
        return AndroidDriverFactory.getInstance().getDriver(testResult.getName());
    }


    public void quitDriver() {
        quitDriver(DEFAULT_DRIVER);
    }

    public void quitDriver(String driverName) {
        WebDriver driver = drivers.get(driverName);
        if (driver != null) {
            driver.quit();
            drivers.remove(driverName);
        }
    }

    public Map<String, Object> getTestData() {
        return testData;
    }

    public void tearDown() {
        if (drivers != null) {
            drivers.entrySet().stream().forEach(entry -> entry.getValue().quit());
            drivers.clear();
        }
        testData.clear();
        testResult = null;
    }

    public void setScenario(ITestResult testResult) {
        this.testResult = testResult;
    }

    public ITestResult getScenario() {
        return testResult;
    }

}
