package com.sairam.fanout.ingestion;

import java.io.*;
import java.nio.file.*;
import java.util.concurrent.BlockingQueue;

public class FileIngestionService {

    public void ingest(String filePath, BlockingQueue<String> queue) throws Exception {
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                queue.put(line);
            }
        }
    }
}
