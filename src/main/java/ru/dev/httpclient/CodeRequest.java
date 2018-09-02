package ru.dev.httpclient;

/**
 * CodeRequest.
 * Класс для получения html на post запрос.
 *
 * @author Druzhinin Vladimir (mailto:dialog.txt@gmail.com).
 * @version 1.
 * @since 02.09.2018.
 */

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class CodeRequest {
    /**
     * postCode.
     *
     * @param code - код отправляемый в запросе в поле ввода.
     * @return body - тело страници принимаемой на запрос.
     */
    public String postCode(String code) {
        while (true) {
            String url = "http://173.254.16.85:8080/test.php";
            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
            params.add(new BasicNameValuePair("code", code));
            try {
                HttpClientHelper httpClientHelper = new HttpClientHelper();
                Response response = new Response(httpClientHelper.httpPostRequest(url, params));
                if (response.getStatus() == 200) {
                    String body = response.getBoby();
                    return body;
                } else {
                    System.out.println("Error in thread " + code + " " + response.getBoby());
                }
            } catch (Exception e) {
                System.out.println("Error in thread " + code + " " + e.toString());
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e1) {
                    System.out.println(e1.toString());
                }
            }
        }
    }
}
