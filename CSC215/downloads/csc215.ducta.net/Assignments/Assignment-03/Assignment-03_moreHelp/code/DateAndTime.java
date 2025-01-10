package asmt03_moreHelp;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class DateAndTime {
    public static void main(String[] args) {

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        String timeZone = "Pacific Standard Time";
        dateFormat.setTimeZone(TimeZone.getTimeZone("America/Los_Angeles"));
        System.out.println(timeZone + ": " + dateFormat.format(new Date()));

        timeZone = "Central Standard Time";
        dateFormat.setTimeZone(TimeZone.getTimeZone("America/Chicago"));
        System.out.println(timeZone + ": " + dateFormat.format(new Date()));

        timeZone = "Eastern Standard Time";
        dateFormat.setTimeZone(TimeZone.getTimeZone("America/New_York"));
        System.out.println(timeZone + ": " + dateFormat.format(new Date()));

        // For more timezones: https://www.timeanddate.com/time/current-number-time-zones.html
        // Date and Time formatter: https://docs.oracle.com/en/java/javase/13/docs/api/java.base/java/time/format/DateTimeFormatter.html
    }
}
