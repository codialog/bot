package ru.dev;


import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class Counter extends Thread {
    static volatile boolean codFound = false;
    static volatile int countCreatedThreads;

    public Counter(String code) {
        super(String.valueOf(code));
        countCreatedThreads++;
        start();
    }

    public static String postRequest(String url, List<BasicNameValuePair> params) throws IOException {
        while (true) {
            try {
                HttpClient httpClient = HttpClients.createDefault();
                HttpPost httpPost = new HttpPost(url);
                httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
                HttpResponse response = null;
                response = httpClient.execute(httpPost);
                if (response.getStatusLine().getStatusCode() == 200)
                    return EntityUtils.toString(response.getEntity(), "UTF-8");

            } catch (Exception e) {
                //e.printStackTrace();
                System.out.println("try post request, " + e.toString());
                try {
                    sleep(1000);
                } catch (InterruptedException e1) {
                    e1.printStackTrace();
                }
            }
        }
    }

    public static String getHtmlBody(String html) {
        String openTag = "<body>";
        String closeTag = "</body>";
        int begIndex = html.indexOf(openTag) + openTag.length();
        int endIndex = html.indexOf(closeTag, begIndex);
        String body = html.substring(begIndex, endIndex).trim();
        return body;
    }

    @Override
    public void run() {
        if (!codFound) {
            String url = "http://173.254.16.85:8080/test.php";
            List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
            params.add(new BasicNameValuePair("code", super.getName()));
            try {
                String link = getHtmlBody(postRequest(url, params));
                if (!link.equals("WRONG =(")) {
                    System.out.println("code:" + super.getName());
                    System.out.println(link);
                } else {
                    System.out.println(super.getName() + " WRONG =(");
                }
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                countCreatedThreads--;
            }
        }
    }
}
