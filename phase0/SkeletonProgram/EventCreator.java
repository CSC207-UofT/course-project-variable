import java.util.Calendar;

public class EventCreator {
    public CalendarEvent createEvent(String name, float start, float end, String day) {
        // if recurring and not given a date
        return new CalendarEvent(name, start, end, day);
    }

    public CalendarEvent createEvent(String name, float start, float end, float date) {
        // if not recurring
        return new OneOffEvent(name, start, end, date);
    }
}