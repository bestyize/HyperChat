package xyz.thewind.music.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DataUtils {
    public static String getTodayStr() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        return sdf.format(new Date());
    }
}
