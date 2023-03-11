package xyz.thewind.music.util;

public class NumberHelper {
    public static int parseNumber(String num, int i) {
        try {
            return Integer.parseInt(num);
        } catch (Exception e) {

        }
        return i;
    }
}
