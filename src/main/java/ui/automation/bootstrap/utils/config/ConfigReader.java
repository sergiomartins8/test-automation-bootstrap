package ui.automation.bootstrap.utils.config;

import ui.automation.bootstrap.base.BrowserType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public final class ConfigReader {

    private ConfigReader() {
    }

    public static Config populateConfigs() {
        InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("tests.properties");
        Properties properties = new Properties();

        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        boolean runTestsLocal = Boolean.parseBoolean(properties.getProperty("run.tests.local"))
                || System.getProperty("environment").equals("local");

        if (runTestsLocal) {
            return localConfiguration();
        } else {
            return remoteConfiguration();
        }
    }

    private static Config localConfiguration() {
        InputStream inputStream = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config.local.properties");
        Properties properties = new Properties();

        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Config.Builder(true, BrowserType.valueOf(properties.getProperty("browser.type")))
                .withBaseUrl(properties.getProperty("base.url"))
                .withScreenshots(Boolean.parseBoolean(properties.getProperty("screenshots")))
                .withHeadless(Boolean.parseBoolean(properties.getProperty("headless")))
                .build();
    }

    private static Config remoteConfiguration() {
        String environment = System.getProperty("environment") != null
                ? System.getProperty("environment") : "config.qa.properties";

        InputStream inputStream = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config." + environment + ".properties");
        Properties properties = new Properties();

        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Config.Builder(false, BrowserType.valueOf(properties.getProperty("browser.type")))
                .withBaseUrl(properties.getProperty("base.url"))
                .withScreenshots(Boolean.parseBoolean(properties.getProperty("screenshots")))
                .withHeadless(Boolean.parseBoolean(properties.getProperty("headless")))
                .withRemoteWebDriverUrl(properties.getProperty("remote.webdriver.url"))
                .build();
    }
}
