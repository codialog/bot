package ru.dev;

import static java.lang.Thread.sleep;

public class solution {

    public static void main(String[] args) {
        for (int i =1; i<10; i++)
            getCode(i);
    }

    public static void getCode(int count) {
        String codeLimit = "";
        while (codeLimit.length() != count)
            codeLimit += "9";
        String code = "";
        for (int i = 0; i < Integer.parseInt(codeLimit) + 1; i++) {
            code = Integer.toString(i);
            while(code.length() != count)
                code = "0" + code;

            if (Counter.countCreatedThreads >= 100)
                System.out.println("waiting thread: ");
                int time = 0;
            while (Counter.countCreatedThreads >= 10) {
                try {
                    System.out.print("\r" + time);
                    sleep(1000);
                    time++;
                } catch (InterruptedException e) {
                    //e.printStackTrace();
                    System.out.println("wait error");
                }
            }
            new Counter(code);
        }
    }
}
