package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExampleTest extends BaseTest {

    @BeforeMethod
    public void setup() {
        logger().debug("Setup test");
    }

    @AfterMethod
    public void teardown() {
        logger().debug("Teardown test");
    }

    @Test
    public void testExampleOne() {
        logger().info("Example info log");
        logger().warn("Example warn log");
    }

    @Test
    public void testExampleTwo() {
        logger().info("Example info log");
        logger().warn("Example warn log");
        System.err.println("OLAAAA: " + System.getProperty("environment"));
    }
}
