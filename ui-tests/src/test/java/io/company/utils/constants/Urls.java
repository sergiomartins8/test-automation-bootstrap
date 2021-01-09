package io.company.utils.constants;

/**
 * Utility class that holds url constants.
 */
public class Urls {

    /**
     * Private constructor to avoid instantiation.
     */
    private Urls() {
    }

    /**
     * Page related.
     */
    public static class Page {

        /**
         * Private constructor to avoid instantiation.
         */
        private Page() {
        }

        public static final String HOME = "home/";
        public static final String MY_ACCOUNT = "my-account/";
    }

    /**
     * Api related.
     */
    public static class Api {

        /**
         * Private constructor to avoid instantiation.
         */
        private Api() {
        }

        public static final String BASE_URL_DEV = "https://your-awesome-api-dev-base-url/";
        public static final String BASE_URL_PROD = "https://your-awesome-api-prod-base-url/";

        public static final String USERS = "users/";
    }
}
