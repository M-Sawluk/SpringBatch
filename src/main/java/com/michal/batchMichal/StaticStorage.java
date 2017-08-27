package com.michal.batchMichal;

import org.springframework.core.io.InputStreamResource;

import java.io.IOException;
import java.io.PushbackInputStream;
import java.util.HashMap;
import java.util.Map;

import static com.michal.batchMichal.BatchStaticStorageKey.INPUT;

public class StaticStorage {
    private static final Map<BatchStaticStorageKey, Object> STORAGE = new HashMap<>();

    public static void put(FileData metadata) throws IOException {
        STORAGE.putAll(new HashMap<BatchStaticStorageKey, Object>() {
            {
                put(INPUT, new InputStreamResource(new PushbackInputStream(metadata.getFile().getInputStream())));
            }
        });
    }

    public static Object get(BatchStaticStorageKey key){
        return STORAGE.get(key);
    }
}