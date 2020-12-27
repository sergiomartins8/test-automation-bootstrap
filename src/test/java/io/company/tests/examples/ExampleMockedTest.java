package io.company.tests.examples;

import io.company.tests.BaseTest;
import io.company.utils.mocks.Mock;
import io.company.utils.mocks.model.MockExampleModel;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selenide.open;

/**
 * Use @Listeners(...) in order to execute the tests with mocked responses from the @Mock annotation "clazz".
 * <br>
 * On the other hand, simply  execute the tests by passing the listener maven property (check the documentation).
 **/
//@Listeners(MockListener.class)
public class ExampleMockedTest extends BaseTest {

    @BeforeMethod
    public void setup() {
        open("http://www.google.pt");
    }

    @Mock(clazz = MockExampleModel.class)
    @Test(description = "Test based on mock expectations")
    public void shouldUserBeLoggedIn() {
        logger().info("Performing a mocked request");
        open("https://www.google.pt/mock");
        logger().warn("Received mocked response");
    }
}
