package ru.dev.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import ru.dev.response.parser.HttpParser;

import java.io.IOException;

public class Response {

    private HttpResponse response;

    public Response(HttpResponse response) {
        this.response = response;
    }

    public String getBoby() throws IOException {
        String html = EntityUtils.toString(this.response.getEntity());
        HttpParser httpParser = new HttpParser(html);
        String body = httpParser.getBody();
        return body;
    }

    public int getStatus() {
        return this.response.getStatusLine().getStatusCode();
    }
}
