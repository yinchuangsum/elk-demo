package com.ycsum.elkdemo.data.logging;

public class Request {
    private final String url;
    private final String host;
    private final String address;
    private final String query;
    private final String uri;
    private final String requestBody;

    public Request(String url, String host, String address, String query, String uri, String body) {
        this.url = url;
        this.host = host;
        this.address = address;
        this.query = query;
        this.uri = uri;
        this.requestBody = body;
    }

    @Override
    public String toString() {
        return "{" +
                "'url':'" + url + '\'' +
                ", 'host':'" + host + '\'' +
                ", 'address':'" + address + '\'' +
                ", 'query':'" + query + '\'' +
                ", 'uri':'" + uri + '\'' +
                ", 'requestBody':'" + requestBody + '\'' +
                '}';
    }
}
