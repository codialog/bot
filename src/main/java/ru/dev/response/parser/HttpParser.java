package ru.dev.response.parser;

/**
 * HttpParser.
 * Класс поиска содержимого внтри html тега.
 *
 * @author Druzhinin Vladimir (mailto:dialog.txt@gmail.com).
 * @version 1.
 * @since 02.09.2018.
 */

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HttpParser {

    private String html;

    /**
     * HttpParser канструктор.
     *
     * @param html - строка html.
     */
    public HttpParser(String html) {
        this.html = html;
    }

    /**
     * getBody получение значения в теге body.
     *
     * @return body значения в теге body.
     */
    public String getBody() {
        return getTag("body");
    }

    /**
     * getTag получение значения в теге.
     *
     * @param tag - html тэг
     * @return tagVal значения в теге.
     */
    public String getTag(String tag) {
        String html = this.html;
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(html);
        html = matcher.replaceAll(" ");
        String regex = String.format("<%s.*>(.*)</%s>", tag, tag);
        pattern = Pattern.compile(regex);
        matcher = pattern.matcher(html);
        matcher.find();
        String tagVal = matcher.group(1).trim();
        return tagVal;
    }
}
