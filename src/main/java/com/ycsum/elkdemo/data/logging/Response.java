package com.ycsum.elkdemo.data.logging;

public class Response {
    private final int status;
    private final String contentType;
    private final String responseBody;

    public Response(int status, String contentType, String responseBody) {
        this.status = status;
        this.contentType = contentType;
        this.responseBody = responseBody;
    }


    @Override
    public String toString() {
        return "{" +
                "status:" + status +
                ", contentType:'" + contentType + '\'' +
                ", responseBody:'" + responseBody + '\'' +
                '}';
    }
}
