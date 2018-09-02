package ru.dev.response.parser;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpParser {

    private String html;

    public HttpParser(String html) {
        this.html = html;
    }

    public String getBody() {
        return getTag("body");
    }

    public String getTag(String tag) {
        String html = this.html;
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(html);
        html = matcher.replaceAll(" ");
        String regex = String.format("<%s.*>(.*)</%s>", tag, tag);
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(html);
        matcher.find();
        String body = matcher.group(1);
        return body.trim();
    }
}
