package ru.dev.thread;

import ru.dev.httpclient.CodeRequest;

import java.util.Date;

public class GenerateThread extends Thread {

    public static volatile int countCreatedThreads;
    public static volatile String code = null;
    public static volatile String link = null;

    public GenerateThread(String code) {
        super(String.valueOf(code));
    }

    public void findCode() {
        countCreatedThreads = countCreatedThreads + 1;
        this.start();
    }

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
