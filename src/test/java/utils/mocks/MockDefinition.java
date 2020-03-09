package utils.mocks;

import com.google.gson.Gson;

/***
 * Concrete definition of a mock.
 * To be used by a mapper; from json to {@link MockDefinition} object.
 */
@SuppressWarnings("unused")
public final class MockDefinition {

    /**
     * Holds the mocked request params.
     */
    private Request request;

    /**
     * Holds the mocked response params.
     */
    private Response response;

    public Request getRequest() {
        return request;
    }

    public Response getResponse() {
        return response;
    }

    public void setResponse(Response response) {
        this.response = response;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public static class Request {

        /**
         * Contains the mapping request path.
         */
        private String path;

        /**
         * Contains the type of request (e.g. GET, POST).
         */
        private String method;

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public String getMethod() {
            return method;
        }

        public void setMethod(String method) {
            this.method = method;
        }
    }

    public static class Response {

        /**
         * Holds the whole response body.
         */
        private Object body;

        /**
         * Status code response.
         */
        private Integer statusCode;

        public String getBody() {
            return new Gson().toJson(this.body);
        }

        public void setBody(Object body) {
            this.body = body;
        }

        public Integer getStatusCode() {
            return statusCode;
        }

        public void setStatusCode(Integer statusCode) {
            this.statusCode = statusCode;
        }
    }
}
