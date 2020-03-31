package utils.config;

import base.BrowserType;

/**
 * Builder Config.
 */
public final class Config {

    /**
     * True if tests are executed locally.
     */
    private final boolean runTestsLocal;

    /**
     * Browser type in which the tests are executed.
     */
    private final BrowserType browserType;

    /**
     * Solution's base url to be used across the framework.
     */
    private final String baseUrl;

    /**
     * Allow Selenide to take screenshots on test failure.
     */
    private final boolean screenshots;

    /**
     * Tells if the tests are going to be executed in headless mode.
     */
    private final boolean headless;

    /**
     * Holds the remote web driver URL for the selenium grid.
     */
    private final String remoteWebDriverUrl;

    /**
     * Mock server url.
     */
    private final String mockServerAddress;

    /**
     * Mock server port.
     */
    private final int mockServerPort;

    private Config(Builder builder) {
        this.runTestsLocal = builder.runTestsLocal;
        this.browserType = builder.browserType;
        this.baseUrl = builder.baseUrl;
        this.screenshots = builder.screenshots;
        this.headless = builder.headless;
        this.remoteWebDriverUrl = builder.remoteWebDriverUrl;
        this.mockServerAddress = builder.mockServerAddress;
        this.mockServerPort = builder.mockServerPort;
    }

    public boolean runTestsLocal() {
        return runTestsLocal;
    }

    public BrowserType getBrowserType() {
        return browserType;
    }

    public String getBaseUrl() {
        return baseUrl;
    }

    public boolean takeScreenshots() {
        return screenshots;
    }

    public boolean isHeadless() {
        return headless;
    }

    public String getRemoteWebDriverUrl() {
        return remoteWebDriverUrl;
    }

    public String getMockServerAddress() {
        return mockServerAddress;
    }

    public int getMockServerPort() {
        return mockServerPort;
    }

    public static class Builder {

        /**
         * {@code runTestsLocal} builder.
         */
        private final boolean runTestsLocal;

        /**
         * {@code browserType} builder.
         */
        private final BrowserType browserType;

        /**
         * {@code baseUrl} builder.
         */
        private String baseUrl;

        /**
         * {@code screenshots} builder.
         */
        private boolean screenshots;

        /**
         * {@code headless} builder.
         */
        private boolean headless;

        /**
         * {@code remoteWebDriverUrl} builder.
         */
        private String remoteWebDriverUrl;

        /**
         * {@code mockServerAddress} builder.
         */
        private String mockServerAddress;

        /**
         * {@code mockServerPort} builder.
         */
        private int mockServerPort;

        public Builder(boolean runTestsLocal, BrowserType browserType) {
            this.runTestsLocal = runTestsLocal;
            this.browserType = browserType;
        }

        public Builder withBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
            return this;
        }

        public Builder withScreenshots(boolean screenshots) {
            this.screenshots = screenshots;
            return this;
        }

        public Builder withHeadless(boolean headless) {
            this.headless = headless;
            return this;
        }

        public Builder withRemoteWebDriverUrl(String remoteWebDriverUrl) {
            this.remoteWebDriverUrl = remoteWebDriverUrl;
            return this;
        }

        public Builder withMockServerAddress(String mockServerAddress) {
            this.mockServerAddress = mockServerAddress;
            return this;
        }

        public Builder withMockServerPort(int mockServerPort) {
            this.mockServerPort = mockServerPort;
            return this;
        }

        public Config build() {
            return new Config(this);
        }
    }
}
