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
     * This piece of logic extracts the parameter class within the {@link Mock} annotation.
     * Then, converts it into a {@link MockDefinition} object.
     * <br>
     * Having the object, we use it to add its fields to the http response - through a filter.
     *
     * @param result the current test result.
     */
    @Override
    public void onTestStart(ITestResult result) {
        extractMockAnnotation(result).ifPresent(mock -> {
            try {
                MockDefinition mockDefinition = mock
                        .clazz()
                        .getDeclaredConstructor()
                        .newInstance();

                WebDriverRunner
                        .getSelenideProxy()
                        .addResponseFilter(mock.getClass().getSimpleName(), (response, contents, messageInfo) -> {
                            if (messageInfo.getOriginalRequest().method().equals(mockDefinition.methodName())
                                    && Pattern.compile(mockDefinition.url()).matcher(messageInfo.getOriginalUrl()).matches()) {
                                contents.setTextContents(mockDefinition.contentBody());
                                response.setStatus(mockDefinition.responseStatus());
                                response.headers().add(mockDefinition.headers());
                            }
                        });
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
