package com.sairam.fanout.sink;

public class MockSink {

    private final String name;

    public MockSink(String name) {
        this.name = name;
    }

    public void send(String data) throws InterruptedException {
        Thread.sleep(50);
        System.out.println(name + " processed: " + data);
    }
}
