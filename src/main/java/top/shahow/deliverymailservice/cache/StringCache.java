package top.shahow.deliverymailservice.cache;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author shahow
 * @date 2022-05-14
 */
@Component
public class StringCache {
    private final static Map<String, String> cache = new ConcurrentHashMap<>();

    public void put(String key, String value) {
        if(cache.containsKey(key)) {
            return;
        }
        cache.put(key, value);
    }

    public Optional<String> get(String key) {
        return Optional.ofNullable(cache.get(key));
    }

    public Optional<String> getAndRemove(String key) {
        return Optional.ofNullable(remove(key));
    }

    public String remove(String key) {
        return cache.remove(key);
    }
}

