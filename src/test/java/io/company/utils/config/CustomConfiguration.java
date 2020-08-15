package io.company.utils.config;

/**
 * Custom configuration settings.
 * <br>
 * This class is designed so that every setting can be set either via system property or programmatically
 * <br>
 * Please note that all fields are static, meaning that every change
 * will immediately reflect in all threads (if you run tests in parallel)
 */
public class CustomConfiguration {

    /**
     * Private constructor to avoid instantiation.
     */
    private CustomConfiguration() {
    }

    /**
     * Default configuration settings holder.
     */
    private static final CustomConfigurationHolder DEFAULTS = new CustomConfigurationHolder();

    /**
     * Custom configuration example.
     * Can be configured either programmatically or by system property: {@code -Dcustom.configuration=somethingYouWant}
     * <br>
     * Default value: null (Custom configuration is not set)
     */
    public static String exampleConfiguration = DEFAULTS.exampleConfiguration();
}
