package utils.listeners;

import base.MockContext;
import org.testng.ITestListener;
import org.testng.ITestResult;
import utils.logging.Loggable;
import utils.mocks.Mock;
import utils.mocks.MockDefinition;
import utils.mocks.MockParser;

import java.util.Optional;

import static java.util.Objects.requireNonNull;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;

/**
 * {@link ITestListener} implementation responsible for mocking requests and responses
 * during runtime, based on the {@link Mock} annotation.
 * <br>
 * The {@link MockContext} object holds the mock server details.
 */
public class MockServerListener implements ITestListener, Loggable {

    @Override
    public void onTestStart(ITestResult result) {
        extractMockAnnotation(result).ifPresent(mock -> {
            for (String path : mock.path()) {
                logger().debug("Mocking file on path: " + path);
                MockDefinition mockDefinition = MockParser.toObject(path);
                logger().debug("Mocking content: " + mockDefinition.prettyPrint());
                MockContext.getMockServerClient()
                        .when(request()
                                .withMethod(requireNonNull(mockDefinition).getRequest().getMethod())
                                .withPath(mockDefinition.getRequest().getPath())
                                .withQueryStringParameters(mockDefinition.getRequest().getQueryStringParameters())
                                .withCookies(mockDefinition.getRequest().getCookies())
                                .withBody(mockDefinition.getRequest().getBody())
                                .withHeaders(mockDefinition.getRequest().getHeaders())
                        )
                        .respond(response()
                                .withStatusCode(mockDefinition.getResponse().getStatusCode())
                                .withBody(mockDefinition.getResponse().getBody())
                                .withHeaders(mockDefinition.getResponse().getHeaders())
                                .withCookies(mockDefinition.getResponse().getCookies())
                        );
            }
        });
    }

    private Optional<Mock> extractMockAnnotation(ITestResult result) {
        return Optional.ofNullable(result.getMethod()
                .getConstructorOrMethod()
                .getMethod()
                .getAnnotation(Mock.class));
    }
}
