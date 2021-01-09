package io.company.utils.config;

import io.company.utils.logging.Loggable;

public class CustomConfigurationHolder implements Loggable {
    private final String customConfiguration = System.getProperty("custom.configuration");
    private final int pipelineID = System.getenv("CI_PIPELINE_ID") == null
            ? -1 : Integer.parseInt(System.getenv("CI_PIPELINE_ID"));

    public String exampleConfiguration() {
        logger().info("Custom config: " + customConfiguration);
        return customConfiguration;
    }

    public int pipelineID() {
        return pipelineID;
    }
}
