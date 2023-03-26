package xyz.thewind.global.util;

import java.util.HashMap;
import java.util.Map;

public class UrlUtil {

    public static Map<String, String> urlToKv(String url) {
        Map<String, String> map = new HashMap<>();
        if (url.isEmpty()) return map;
        int start = url.indexOf("?");
        if (start == -1) {
            start = 0;
        }
        url = url.substring(start);
        String[] pairs = url.split("&");
        for (String s : pairs) {
            String[] pair = s.split("=");
            if (pair.length == 2) {
                map.put(pair[0], pair[1]);
            }
        }
        return map;
    }

}
