package com.example.task14.model;

import java.util.Random;

public class ActionChain {
    Handler chain;
    public static int SUCCESS = 1;
    public static int LOSS = 3;
    Random generate;
    final int NUMHANDLER = 2;
    final int NUMMAX = 7;

    public ActionChain() {
        generate = new Random();
        buildChain();
    }

    private void buildChain() {
        chain = new NegativeHandler(new PositiveHandler(null));
    }

    public boolean process() {
        int type = generate.nextInt(NUMHANDLER);//розыгрыш
        return process(type);
    }

    public boolean process(Integer a) {
        return chain.process(1 + a % NUMHANDLER);//обрезка по числу имеющихся обработчиков
    }
}