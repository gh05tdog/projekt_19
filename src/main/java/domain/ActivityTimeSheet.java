package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ActivityTimeSheet {
    private final String activityId;
    private int totalHours;
    private static List<TimeLogEntry> timeLog = new ArrayList<>();


    public ActivityTimeSheet(String activityId,int hours, LocalDate date) {
        this.activityId = activityId;
        this.totalHours = 0;
        timeLog = new ArrayList<>();
        addHours(hours, date);
    }

    public void addHours(int hours, LocalDate date) {
        totalHours += hours;
        timeLog.add(new TimeLogEntry(hours, date));
    }

    public String getActivityId() {
        return activityId;
    }

    public int getTotalHours() {
        return totalHours;
    }
    public static List<TimeLogEntry> getTimeLog() {
        return timeLog;
    }
    public static String getDateAndHours() {
        StringBuilder dateAndHours = new StringBuilder();
        for (TimeLogEntry entry : timeLog) {
            dateAndHours.append(entry.getDate()).append(" ").append(entry.getHours()).append("\n");
        }
        return dateAndHours.toString();
    }





    public static class TimeLogEntry {
        private final int hours;
        private final LocalDate date;

        public TimeLogEntry(int hours, LocalDate date) {
            this.hours = hours;
            this.date = date;
        }
        public int getHours() {
            return hours;
        }

        public LocalDate getDate() {
            return date;
        }
    }
}