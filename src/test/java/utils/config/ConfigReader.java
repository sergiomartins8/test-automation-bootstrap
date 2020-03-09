package utils.config;

import base.BrowserType;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Single responsibility of reading system properties as well as configurations from the resources
 * to build the {@link Config} object that holds them.
 */
public final class ConfigReader {

    private ConfigReader() {
    }

    public static Config populateConfigs() {
        Properties properties = getPropertiesFromResource("tests.properties");

        boolean runTestsLocal = System.getProperty("environment") != null && System.getProperty("environment").equals("local")
                || Boolean.parseBoolean(properties.getProperty("run.tests.local"));

        if (runTestsLocal) {
            return localConfiguration();
        } else {
            return remoteConfiguration();
        }
    }

    private static Config localConfiguration() {
        Properties properties = getPropertiesFromResource("config/config.local.properties");

        return new Config.Builder(true, BrowserType.valueOf(properties.getProperty("browser.type")))
                .withBaseUrl(properties.getProperty("base.url"))
                .withScreenshots(Boolean.parseBoolean(properties.getProperty("screenshots")))
                .withHeadless(Boolean.parseBoolean(properties.getProperty("headless")))
                .withMockServerAddress(properties.getProperty("mock.server.url"))
                .withMockServerPort(Integer.parseInt(properties.getProperty("mock.server.port")))
                .build();
    }

    private static Config remoteConfiguration() {
        String environment = System.getProperty("environment") != null
                ? System.getProperty("environment") : "qa";

        Properties properties = getPropertiesFromResource("config/config." + environment + ".properties");

        return new Config.Builder(false, BrowserType.valueOf(properties.getProperty("browser.type")))
                .withBaseUrl(properties.getProperty("base.url"))
                .withScreenshots(Boolean.parseBoolean(properties.getProperty("screenshots")))
                .withHeadless(Boolean.parseBoolean(properties.getProperty("headless")))
                .withRemoteWebDriverUrl(properties.getProperty("remote.webdriver.url"))
                .withMockServerAddress(properties.getProperty("mock.server.url"))
                .withMockServerPort(Integer.parseInt(properties.getProperty("mock.server.port")))
                .build();
    }

    /**
     * @param resource to be read into an input stream.
     * @return properties from the {@code resource} parameter.
     */
    private static Properties getPropertiesFromResource(String resource) {
        InputStream inputStream = ConfigReader.class
                .getClassLoader()
                .getResourceAsStream(resource);

        Properties properties = new Properties();

        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
