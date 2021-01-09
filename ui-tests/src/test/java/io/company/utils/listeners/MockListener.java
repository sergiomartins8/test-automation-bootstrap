package io.company.utils.listeners;

import com.codeborne.selenide.WebDriverRunner;
import io.company.utils.logging.Loggable;
import io.company.utils.mocks.Mock;
import io.company.utils.mocks.MockDefinition;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.lang.reflect.InvocationTargetException;
import java.util.Optional;
import java.util.regex.Pattern;

/**
 * {@link ITestListener} implementation responsible for mocked data to be used during runtime, for a single test.
 */
public class MockListener implements ITestListener, Loggable {

    /**
     * <b>TL;DR:</b>
     * For this to work, we're using the selenide proxy implementation. Thus, allowing us to intercept and swap any http responses.
     * <br>
     * This piece of logic extracts the clazz field within the {@link Mock} annotation, containing the array of model objects to be mocked.
     * Then, for each model, instantiates it back into a {@link MockDefinition} object.
     * <br>
     * Having the object, we use it to add its fields to the http response - through a filter.
     *
     * @param result the current test result.
     */
    @Override
    public void onTestStart(ITestResult result) {
        extractMockAnnotation(result).ifPresent(mockAnnotation -> {
            try {
                for (Class<? extends MockDefinition> mockedModel : mockAnnotation.clazz()) {
                    MockDefinition mockDefinition = mockedModel.getDeclaredConstructor().newInstance();

                    logger().info("Adding response filter from model '{}.class'", mockedModel.getSimpleName());
                    logger().info("Adding response filter '{}'", mockedModel.getSimpleName().toLowerCase());

                    WebDriverRunner
                            .getSelenideProxy()
                            .addResponseFilter(mockedModel.getSimpleName().toLowerCase(), (response, contents, messageInfo) -> {
                                if (messageInfo.getOriginalRequest().method().equals(mockDefinition.methodName())
                                        && Pattern.compile(mockDefinition.urlPattern()).matcher(messageInfo.getOriginalUrl()).matches()) {
                                    logger().info("Original url request: {} {}", mockDefinition.methodName(), mockDefinition.urlPattern());
                                    logger().info("Matching pattern: {}", mockDefinition.urlPattern());

                                    logger().info("Mocking content body: {}", mockDefinition.contentBody());
                                    contents.setTextContents(mockDefinition.contentBody());

                                    logger().info("Mocking response status: {}", mockDefinition.responseStatus());
                                    response.setStatus(mockDefinition.responseStatus());

                                    logger().info("Mocking response headers: {}", mockDefinition.headers());
                                    response.headers().add(mockDefinition.headers());
                                }
                            });
                }
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                logger().error(e.getMessage());
            }
        });
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        extractMockAnnotation(result).ifPresent(mock ->
                WebDriverRunner.getWebDriver().quit());
    }

    @Override
    public void onTestFailure(ITestResult result) {
        extractMockAnnotation(result).ifPresent(mock ->
                WebDriverRunner.getWebDriver().quit());
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        extractMockAnnotation(result).ifPresent(mock ->
                WebDriverRunner.getWebDriver().quit());
    }

    private Optional<Mock> extractMockAnnotation(ITestResult result) {
        return Optional.ofNullable(result.getMethod()
                .getConstructorOrMethod()
                .getMethod()
                .getAnnotation(Mock.class));
    }
}
