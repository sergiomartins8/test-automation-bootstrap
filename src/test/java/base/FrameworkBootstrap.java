package base;

import com.codeborne.selenide.Configuration;
import org.mockserver.client.MockServerClient;
import org.testng.annotations.BeforeSuite;
import utils.config.Config;
import utils.config.ConfigReader;
import utils.logging.Loggable;

public abstract class FrameworkBootstrap implements Loggable {

    private static Config config;

    @BeforeSuite
    public void initializeFramework() {
        config = ConfigReader.populateConfigs();
        initializeMockServer();
        initializeSelenide();
    }

    public static Config getConfig() {
        return config;
    }

    private void initializeSelenide() {
        Configuration.baseUrl = config.getBaseUrl();
        Configuration.screenshots = config.takeScreenshots();
        Configuration.headless = config.isHeadless();
    }

    private void initializeMockServer() {
        MockContext.setMockServerClient(new MockServerClient(config.getMockServerAddress(), config.getMockServerPort()));
    }
}
