package cloud.lambdas.payload.response;

import java.util.Map;

public class Response {

    Integer statusCode;
    String message;

    public Response() {
    }

    public Response(Integer statusCode, String message) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }
}
