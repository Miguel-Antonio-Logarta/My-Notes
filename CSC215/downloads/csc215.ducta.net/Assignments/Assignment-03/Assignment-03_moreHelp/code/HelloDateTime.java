package asmt03_moreHelp;

import java.util.Calendar;

public class HelloDateTime {

    public static void main(String[] args) {

        Calendar calendar = Calendar.getInstance();

        System.out.println();
        System.out.println("Date and time, real time: " + calendar.getTime());
        System.out.println();
        System.out.printf("%tb %te, %ty", calendar, calendar, calendar);
        System.out.println();
        System.out.println();
        System.out.printf("%tl:%tM", calendar, calendar);
        System.out.println();

        // https://docs.oracle.com/javase/8/docs/api/java/util/Calendar.html
        // http://www.java2s.com/Tutorials/Java/Java_Format/0120__Java_Format_Dates_Times.htm

    }
}

// OUTPUT
/*

Date and time, real time: Mon Feb 26 19:56:13 PST 2024

Feb 26, 24

7:56

*/
