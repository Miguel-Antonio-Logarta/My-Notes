public class Date {
    private final int year;
    private final int month;
    private final int day;

    public Date(String dateString) throws Exception {
        String[] elements = dateString.split("-");

        if (elements.length != 3) {
            throw new Exception("Invalid Date String");
        }

        year = Integer.parseInt(elements[0]);
        month = Integer.parseInt(elements[1]);
        day = Integer.parseInt(elements[2]);
    }

    public String toString() {
        return getYear() + "-" + getMonth() + "-" + getDay();
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }
}
