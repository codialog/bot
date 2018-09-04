package ru.dev.httpclient;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.util.List;

/**
 * HttpClientHelper.
 * Класс класс работы с сервером.
 *
 * @author Druzhinin Vladimir (mailto:dialog.txt@gmail.com).
 * @version 1.
 * @since 02.09.2018.
 */
public class HttpClientHelper {

    /**
     * httpPostRequest.
     *
     * @param url    - строкоовый адресс сервера.
     * @param params - параметризированный лист, содержит значения запроса
     * @return HttpResponse ответ на запрос.
     */
    public HttpResponse httpPostRequest(String url, List<BasicNameValuePair> params) throws IOException {
        HttpClient httpClient = HttpClients.createDefault();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
        return httpClient.execute(httpPost);
    }
}

