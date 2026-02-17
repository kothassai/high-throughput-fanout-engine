package com.sairam.fanout.orchestrator;

import com.sairam.fanout.ingestion.FileIngestionService;
import com.sairam.fanout.sink.MockSink;
import com.sairam.fanout.transform.JsonTransformer;

import java.util.concurrent.*;

public class Orchestrator {

    public void start(String filePath) throws Exception {

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(100);

        FileIngestionService ingestion = new FileIngestionService();
        JsonTransformer transformer = new JsonTransformer();
        MockSink restSink = new MockSink("REST");

        ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor();

        // Producer
        executor.submit(() -> {
            try {
                ingestion.ingest(filePath, queue);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        // Consumer
        executor.submit(() -> {
            try {
                while (true) {
                    String record = queue.poll(2, TimeUnit.SECONDS);
                    if (record == null) break; // Stop when no more data

                    String transformed = transformer.transform(record);
                    restSink.send(transformed);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        executor.shutdown();
        executor.awaitTermination(10, TimeUnit.SECONDS);
    }
}

