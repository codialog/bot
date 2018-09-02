package ru.dev;
/** Solution.
 *
 * Класс поиска валидного кода и получения ссылки.
 * @author Druzhinin Vladimir (mailto:dialog.txt@gmail.com).
 * @since 02.09.2018.
 * @version 1.
 */
import ru.dev.thread.GenerateThread;

import java.util.Date;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class Solution {

    /**
     *Main.
     *@param args - массив строк.
     */
    public static void main(String[] args) {
        int maxCountThread = 15;
        int minCountDigit = 1;
        int maxCountDigit = 10;
        long start = new Date().getTime();
        HashMap result = new HashMap();
        for (int i = minCountDigit; i < maxCountDigit; i++) {
            result = getCode(i, maxCountThread);
            if (result != null)
                break;
        }
        long end = new Date().getTime();
        System.out.println("code: " + result.get("code"));
        System.out.println("link: " + result.get("link"));
        System.out.println("search time: " + ((end - start) / (1000 * 60)));
    }

    /**
     * Method getResult -
     * @return Echo plus your name.
     */
    public static HashMap<String, String> getResult() {
        HashMap<String, String> result = new HashMap();
        result.put("code", GenerateThread.code);
        result.put("link", GenerateThread.link);
        return result;
    }

    public static void waitFreeTread(int maxCountThread) {
        int time = 0;
        while (GenerateThread.countCreatedThreads >= maxCountThread) {
            try {
                TimeUnit.SECONDS.sleep(1);
                time++;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static HashMap<String, String> getCode(int count, int countThread) {
        int codeLimit = (int) Math.pow(10, count);
        String code;
        for (int i = 0; i < codeLimit; i++) {
            code = String.format("%0" + count + "d", i);
            waitFreeTread(countThread);
            if (GenerateThread.code == null) {
                new GenerateThread(code).findCode();
            } else {
                return getResult();
            }
        }
        return null;
    }
}