package com.sairam.fanout.transform;

public class JsonTransformer implements Transformer {

    @Override
    public String transform(String record) {
        return "{\"data\":\"" + record + "\"}";
    }
}
