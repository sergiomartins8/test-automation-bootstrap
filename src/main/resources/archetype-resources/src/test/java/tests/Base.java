package ${package}.tests;

import ${package}.utils.logging.Loggable;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

/**
 * Where it all starts.
 * <br>
 * The framework is initialized here with all the required configurations.
 */
public abstract class Base implements Loggable {

    @BeforeSuite
    public void initializeGlobalConfigurations() {
        logger().info("Initialize global configurations");
    }

    @AfterSuite
    public void teardownGlobalConfigurations() {
        logger().info("Teardown global configurations");
    }
}
