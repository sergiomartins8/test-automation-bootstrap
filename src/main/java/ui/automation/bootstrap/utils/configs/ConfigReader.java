package ui.automation.bootstrap.utils.configs;

import ui.automation.bootstrap.base.BrowserType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    public static Config populateConfigs() {
        try {
            InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("configs.properties");
            Properties properties = new Properties();
            properties.load(inputStream);

            boolean runTestsLocal = Boolean.parseBoolean(properties.getProperty("run.tests.local"));

            if (runTestsLocal) {
                return new Config.Builder(true, BrowserType.valueOf(properties.getProperty("browser.type")))
                        .withBaseUrl(properties.getProperty("base.url"))
                        .withScreenshots(Boolean.parseBoolean(properties.getProperty("screenshots")))
                        .withHeadless(Boolean.parseBoolean(properties.getProperty("headless")))
                        .build();
            } else
                return new Config.Builder(false, BrowserType.valueOf(properties.getProperty("browser.type")))
                        .withBaseUrl(properties.getProperty("base.url"))
                        .withScreenshots(Boolean.parseBoolean(properties.getProperty("screenshots")))
                        .withHeadless(Boolean.parseBoolean(properties.getProperty("headless")))
                        .withRemoteWebDriverUrl(properties.getProperty("remote.webdriver.url"))
                        .build();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
