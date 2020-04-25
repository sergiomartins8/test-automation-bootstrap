package ${package}.utils.config;

/**
 * Configuration settings.
 * <br>
 * This class is designed so that every setting can be set either via system property or programmatically
 * <br>
 * Please note that all fields are static, meaning that every change
 * will immediately reflect in all threads (if you run tests in parallel)
 */
public class CustomConfiguration {

    /**
     * Default configuration settings holder.
     */
    private static final CustomConfigurationHolder DEFAULTS = new CustomConfigurationHolder();
#if (${mockserver} == 'true')

    /**
     * URL for the mock server.
     * Can be configured either programmatically or by system property: {@code -Dmock.server.address=localhost:3000}
     * <br>
     * Default value: null (Mock server it not used)
     */
    public static String mockServerAddress = DEFAULTS.mockServerAddress();
#end
}
