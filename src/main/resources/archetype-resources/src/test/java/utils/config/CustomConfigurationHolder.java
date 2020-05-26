package ${package}.utils.config;

import ${package}.utils.logging.Loggable;

public class CustomConfigurationHolder implements Loggable {
#if (${mockserver} == 'true')

    private final String mockServerAddress = System.getProperty("mock.server.address");
#end
#if (${mockserver} == 'true')

    public String mockServerAddress() {
        logger().info("Mock server address: " + mockServerAddress);
        return mockServerAddress;
    }
#end
}
