package tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pageobjects.pages.ExamplePage;

import static com.codeborne.selenide.Selenide.open;
import static org.testng.Assert.fail;

public class ExampleTest extends BaseTest {

    private ExamplePage examplePage;

    @BeforeMethod
    public void setup() {
        logger().debug("Setup test");
        examplePage = new ExamplePage();
    }

    @AfterMethod
    public void teardown() {
        logger().debug("Teardown test");
    }

    @Test(description = "Open up a google page and search for the word 'dogs'")
    public void testExampleOne() {
        open(""); // empty string opens the base URL defined on <config.${environment}.properties>

        examplePage.exampleComponent()
                .waitPageLoaded()
                .exampleAction();

        fail();
    }

    @Test(description = "Test based on mock server expectations")
    public void testExampleTwo() {
        logger().info("Example info log");
        logger().warn("Example warn log");
    }
}
