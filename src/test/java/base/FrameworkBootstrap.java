package base;

import com.codeborne.selenide.Configuration;
import org.testng.annotations.BeforeSuite;
import utils.config.Config;
import utils.config.ConfigReader;
import utils.logging.Loggable;

public abstract class FrameworkBootstrap implements Loggable {

    private static Config config;

    @BeforeSuite
    public void initializeFramework() {
        logger().info("Initializing framework");
        config = ConfigReader.populateConfigs();
        initializeSelenide();
        logger().info("Framework is initialized");
    }

    public static Config getConfig() {
        return config;
    }

    private void initializeSelenide() {
        Configuration.baseUrl = config.getBaseUrl();
        Configuration.screenshots = config.takeScreenshots();
        Configuration.headless = config.isHeadless();
    }
}
