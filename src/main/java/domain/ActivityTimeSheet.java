package domain;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ActivityTimeSheet {
    private final String activityId;
    private int totalHours;
    private List<TimeLogEntry> timeLog;

    public ActivityTimeSheet(String activityId, int initialHours, LocalDate initialDate) {
        this.activityId = activityId;
        this.totalHours = initialHours;
        this.timeLog = new ArrayList<>();
        addHours(initialHours, initialDate);
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

    public List<TimeLogEntry> getTimeLog() {
        return new ArrayList<>(timeLog);
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