package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.pages.ExamplePage;

import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.assertTrue;

public class ExampleTest extends BaseTest {

    private ExamplePage examplePage;

    @BeforeMethod
    public void setup() {
        logger().debug("Setup test");
        examplePage = new ExamplePage();
        open("");
    }

    @AfterMethod
    public void teardown() {
        logger().debug("Teardown test");
    }

    @Test
    public void testExampleOne() {
        examplePage.exampleComponent()
                .waitPageLoaded()
                .exampleAction();

        assertTrue(true);
    }

    @Test
    public void testExampleTwo() {
        logger().info("Example info log");
        logger().warn("Example warn log");
    }
}
