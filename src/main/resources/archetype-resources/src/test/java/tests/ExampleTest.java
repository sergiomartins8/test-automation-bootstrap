package ${package}.tests;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ${package}.pageobjects.pages.ExamplePage;
#if (${mockserver} == 'true')
import ${package}.utils.mocks.Mock;
#end

import static com.codeborne.selenide.Selenide.open;

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
        open("http://google.com");

        examplePage.exampleComponent()
                .waitPageLoaded()
                .exampleAction();

//        fail();
    }
#if (${mockserver} == 'true')

    @Mock(path = {"/mocks/example-expectation.json"})
    @Test(description = "Test based on mock server expectations")
    public void testExampleTwo() {
        // This is just an example of the expectation currently being mocked by using MockServer on ${localhost}
        open("http://mockserver:1080/login");

        logger().info("Example info log");
        logger().warn("Example warn log");
    }
#end
}
