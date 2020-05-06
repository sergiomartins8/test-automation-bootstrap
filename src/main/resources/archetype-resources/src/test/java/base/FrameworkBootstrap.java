package ${package}.base;

#if (${mockserver} == 'true')
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ${package}.utils.config.CustomConfiguration;
#end
import ${package}.utils.logging.Loggable;

/**
 * Where it all starts.
 * <br>
 * The framework is initialized here with all the required configurations.
#if (${mockserver} == 'true')
 * It also takes care of the {@link MockContext} initialization and teardown.
#end
 */
public abstract class FrameworkBootstrap implements Loggable {
#if (${mockserver} == 'true')

    /**
     * Tells {@link MockContext} to initialize the mock server if it is present as a system property.
     */
    @BeforeSuite
    public void initializeMockServer() {
        if (CustomConfiguration.mockServerAddress != null) {
            MockContext.initializeMockServerClient();
        }
    }

    /**
     * Tells {@link MockContext} to reset the mock server data if it is present as a system property.
     */
    @AfterSuite
    public void teardownMockServer() {
        if (CustomConfiguration.mockServerAddress != null) {
            MockContext.resetMockServerClient();
        }
    }
#end
}
