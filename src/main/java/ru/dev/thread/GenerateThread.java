package ru.dev.thread;

import ru.dev.httpclient.CodeRequest;

import java.util.Date;

/**
 * GenerateThread.
 * Класс создания нити для поиска корректого кода.
 *
 * @author Druzhinin Vladimir (mailto:dialog.txt@gmail.com).
 * @version 1.
 * @since 02.09.2018.
 */
public class GenerateThread extends Thread {

    public static volatile int countCreatedThreads;
    public static volatile String code = null;
    public static volatile String link = null;

    /**
     * GenerateThread канструктор.
     *
     * @param code - код отправляемый в post запросе.
     */
    public GenerateThread(String code) {
        super(String.valueOf(code));
    }

    /**
     * findCode запуск нити.
     */
    public void findCode() {
        countCreatedThreads = countCreatedThreads + 1;
        this.start();
    }

    /**
     * run переопределение метода базового класса, post request
     */
    @Override
    public void run() {
        long start = new Date().getTime();
        String message = new CodeRequest().postCode(super.getName());
        countCreatedThreads = countCreatedThreads - 1;
        long end = new Date().getTime();
        if (!message.contains("WRONG =(")) {
            code = super.getName();
            link = message;
        } else {
            System.out.println("code: " + this.getName() + " WRONG =(" + " waiting: " + (end - start) + " ms");
        }
    }
}
