package io.company.tests;

import io.company.utils.logging.Loggable;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Where it all starts.
 * <br>
 * The framework is initialized here with all the required configurations.
 */
public abstract class Base implements Loggable {

    @BeforeSuite
    public void initializeGlobalConfiguration() {
        logger().info("Initialize global configuration");
    }

    @AfterSuite
    public void teardownGlobalConfiguration() {
        logger().info("Teardown global configuration");
    }
}
