package xyz.thewind.global.util;

public class NumberUtil {

    public static long getOrDefault(String num, long defaultNum) {
        try {
            return Long.decode(num);
        } catch (Exception e) {

        }
        return defaultNum;
    }

}
