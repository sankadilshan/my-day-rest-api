package com.sankadilshan.myday.utils;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

@Component
public class MapUtil {

    public Map<String, Object> cloneMap(Map<String, Object> map, String... keys) {
        Map<String, Object> cloneMap = new HashMap<>();
        Stream.of(keys).forEach(key->{
            cloneMap.put(key,map.get(key));
        });
        return cloneMap;
    }

    public Map<String, Object> mergeMap(Map<String,Object> map1, Map<String,Object> map2) {
        map1.putAll(map2);
        return map1;
    }
}
