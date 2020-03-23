package utils.mocks;

import com.google.gson.Gson;

@SuppressWarnings("unused")
public final class MockDefinition {

    private Request request;
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
        private String path;
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

        private Object body;
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
