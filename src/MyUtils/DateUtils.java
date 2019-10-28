package MyUtils;

import org.junit.Test;

import java.util.Calendar;

public class DateUtils {
    private long time;
    private String calendarString;

    public long getCurrentMilliSeconds(){
        time = System.currentTimeMillis();
        return time;
    }
    public long getCurrentNanoSeconds(){
        time = System.nanoTime();
        return time;
    }
    public String representsCurrentCalendar(){
        Calendar calendar = Calendar.getInstance();
        return calendar.getTime().toString();
    }


}
