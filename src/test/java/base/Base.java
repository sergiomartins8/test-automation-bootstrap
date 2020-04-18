package base;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

/**
 * Bridge between the framework level and the tests.
 * <br>
 * Class' single responsibility is to setup and teardown the webdriver.
 */
public abstract class Base extends FrameworkBootstrap {

    @BeforeMethod
    public void setupWebDriver() {
        logger().info("Initialize webdriver");
        DriverContext.initializeRemoteWebDriver();
    }

    @AfterMethod
    public void teardownWebDriver() {
        logger().info("Teardown webdriver");
        DriverContext.quitRemoteWebDriver();
    }
}
