package ${package}.tests;

import ${package}.utils.mocks.Mock;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

public class ExampleMockedTest extends BaseTest {

    @Mock(path = {"/mocks/example-expectation.json"})
    @Test(description = "Test based on mock server expectations")
    public void shouldUserBeLoggedIn() {
        // This is just an example of the expectation currently being mocked by using MockServer
        open("http://mockserver:1080/login");

        logger().info("Example info log");
        logger().warn("Example warn log");
    }
}
