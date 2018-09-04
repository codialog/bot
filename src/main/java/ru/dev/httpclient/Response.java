package ru.dev.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import ru.dev.response.parser.HttpParser;

import java.io.IOException;

/**
 * Response.
 * Класс работы с классом Response.
 *
 * @author Druzhinin Vladimir (mailto:dialog.txt@gmail.com).
 * @version 1.
 * @since 02.09.2018.
 */
public class Response {

    private HttpResponse response;

    /**
     * Response канструктор.
     *
     * @param response экземпляр класса HttpResponse.
     */
    public Response(HttpResponse response) {
        this.response = response;
    }

    /**
     * getBoby получение тела ответа.
     *
     * @return body тело ответа.
     */
    public String getBoby() throws IOException {
        String html = EntityUtils.toString(this.response.getEntity());
        HttpParser httpParser = new HttpParser(html);
        String body = httpParser.getBody();
        return body;
    }

    /**
     * getStatus получение статуса ответа.
     *
     * @return статус ответа int.
     */
    public int getStatus() {
        return this.response.getStatusLine().getStatusCode();
    }
}
