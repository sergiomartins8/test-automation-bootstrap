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
 * {@link ITestListener} implementation responsible for mocking requests and responses during runtime
 * based on the {@link Mock} annotation.
 * <p>
 * The mocks are injected recurring to the {@link MockContext}.
 * </p>
 */
public class MockListener implements ITestListener, Loggable {

    @Override
    public void onTestStart(ITestResult result) {
        extractMockAnnotation(result).ifPresent(mock -> {
            for (String mockPath : mock.path()) {
                MockDefinition mockDefinition = MockParser.toObject(mockPath);
                MockContext.getMockServerClient()
                        .when(request()
                                .withMethod(requireNonNull(mockDefinition).getRequest().getMethod())
                                .withPath(mockDefinition.getRequest().getPath()))
                        .respond(response()
                                .withStatusCode(mockDefinition.getResponse().getStatusCode())
                                .withBody(mockDefinition.getResponse().getBody()));
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
