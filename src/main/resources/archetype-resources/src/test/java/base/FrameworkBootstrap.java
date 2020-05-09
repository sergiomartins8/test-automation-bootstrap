package ${package}.base;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ${package}.utils.logging.Loggable;

/**
 * Where it all starts.
 * <br>
 * The framework is initialized here with all the required configurations.
 */
public abstract class FrameworkBootstrap implements Loggable {

    @BeforeSuite
    public void initializeGlobalConfigurations() {
        logger().info("Initialize global configurations");
    }

    @AfterSuite
    public void teardownGlobalConfigurations() {
        logger().info("Teardown global configurations");
    }
}
