package tests;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExampleTest extends BaseTest {

    @BeforeMethod
    public void setup() {
        logger().debug("Setup");
    }

    @Test
    public void testExample() {
        logger().info("Example info log");
        logger().warn("Example warn log");
    }
}
