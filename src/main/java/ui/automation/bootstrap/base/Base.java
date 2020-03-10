package ui.automation.bootstrap.base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public abstract class Base extends FrameworkBootstrap {

    @BeforeMethod
    public void setupWebDriver() {
        logger().info("Initialize webdriver");
        DriverContext.initializeRemoteWebDriver(getConfig().getBrowserType(),
                getConfig().runTestsLocal(),
                getConfig().getRemoteWebDriverUrl());
    }

    @AfterMethod
    public void teardownWebDriver() {
        logger().info("Teardown webdriver");
        DriverContext.quitRemoteWebDriver();
    }
}
