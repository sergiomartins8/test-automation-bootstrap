package base;

import com.codeborne.selenide.Configuration;
import org.mockserver.client.MockServerClient;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import utils.config.Config;
import utils.config.ConfigReader;
import utils.logging.Loggable;

/**
 * Where it all starts.
 * <p>
 * The framework is initialized here with all the required configurations.
 * It also takes care of the {@link MockContext} initialization and teardown.
 * </p>
 */
public abstract class FrameworkBootstrap implements Loggable {

    /**
     * Builder that holds all the configurations to be reused at the framework level.
     * Has to be static so that it can be shared across parallel executions.
     */
    private static Config config;

    @BeforeSuite
    public void initializeFramework() {
        config = ConfigReader.populateConfigs();
        initializeSelenide();
    }

    /**
     * Use {@code @BeforeMethod} when running tests in {@code parallel=methods}. Edit accordingly.
     * If sequential test execution use {@code @BeforeSuite} annotation.
     */
    @BeforeMethod
    public void initializeMockServer() {
        MockContext.setMockServerClient(new MockServerClient(config.getMockServerAddress(), config.getMockServerPort()));
    }

    /**
     * Related to the {@link #initializeMockServer()} method.
     * <p>
     * Use {@code @AfterMethod} when running tests in {@code parallel=methods}. Edit accordingly.
     * If sequential test execution use {@code @AfterSuite} annotation.
     * </p>
     */
    @AfterMethod
    public void teardownMockServer() {
        MockContext.resetMockServerClient();
    }

    public static Config getConfig() {
        return config;
    }

    /**
     * Assigns current configuration values to internal Selenide variables that configure the whole framework.
     */
    private void initializeSelenide() {
        Configuration.baseUrl = config.getBaseUrl();
        Configuration.screenshots = config.takeScreenshots();
        Configuration.headless = config.isHeadless();
    }
}
