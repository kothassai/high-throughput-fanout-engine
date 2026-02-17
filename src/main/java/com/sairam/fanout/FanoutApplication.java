package com.sairam.fanout;

import com.sairam.fanout.orchestrator.Orchestrator;

public class FanoutApplication {

    public static void main(String[] args) throws Exception {
        System.out.println("Starting High-Throughput Fan-Out Engine...");
        new Orchestrator().start("sample-data.csv");
    }
}
