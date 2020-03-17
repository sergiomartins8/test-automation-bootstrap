package utils.config;

import base.BrowserType;

public final class Config {

    private final boolean runTestsLocal;
    private final BrowserType browserType;
    private final String baseUrl;
    private final boolean screenshots;
    private final boolean headless;
    private final String remoteWebDriverUrl;

    private Config(Builder builder) {
        this.runTestsLocal = builder.runTestsLocal;
        this.browserType = builder.browserType;
        this.baseUrl = builder.baseUrl;
        this.screenshots = builder.screenshots;
        this.headless = builder.headless;
        this.remoteWebDriverUrl = builder.remoteWebDriverUrl;
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

    public static class Builder {

        private final boolean runTestsLocal;
        private final BrowserType browserType;
        private String baseUrl;
        private boolean screenshots;
        private boolean headless;
        private String remoteWebDriverUrl;

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

        public Config build() {
            return new Config(this);
        }
    }
}
