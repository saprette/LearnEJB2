package packt;

import javax.ejb.Singleton;
import java.text.SimpleDateFormat;
import java.util.Calendar;

@Singleton
public class TimeOfDay {
    private static final String DATE_TIME = "yyyy-MM-dd HH:mm:ss";

    public String timeOfDay() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_TIME);
        return simpleDateFormat.format(calendar.getTime());
    }
}
