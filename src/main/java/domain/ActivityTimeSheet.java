package domain;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class ActivityTimeSheet {
    private final String activityId;
    private float totalHours;
    private final List<TimeLogEntry> timeLog;

    public ActivityTimeSheet(String activityId, float hours, LocalDate date) {
        this.activityId = activityId;
        this.totalHours = 0;
        this.timeLog = new ArrayList<>();
        addHours(hours, date);
    }

    public void addHours(float hours, LocalDate date) {
        totalHours += hours;
        timeLog.add(new TimeLogEntry(hours, date));
    }

    public String getActivityId() {
        return activityId;
    }

    public float getTotalHours() {
        return totalHours;
    }


    public String getDateAndHours() {
        StringBuilder dateAndHours = new StringBuilder();
        for (TimeLogEntry entry : this.timeLog) {
            dateAndHours.append(entry.getDate()).append("\n");
        }
        return dateAndHours.toString();
    }


    public void editHours(float oldTimeSpent, float newTimeSpent) {
        totalHours -= oldTimeSpent;
        totalHours += newTimeSpent;
    }

    public String getLastDateAndHours() {
        if (timeLog.isEmpty()) {
            return "No time log found";
        }
        TimeLogEntry lastEntry = timeLog.get(timeLog.size() - 1);
        return lastEntry.getDate().toString() + " - " + lastEntry.getHours() + " hours";
    }

    public void removeHours(Float hours, LocalDate date) {
        totalHours -= hours;
        timeLog.remove(new TimeLogEntry(hours, date));
    }

    public List<String> getAllDatesAndHours() {
        List<String> datesAndHours = new ArrayList<>();
        for (TimeLogEntry entry : this.timeLog) {
            datesAndHours.add(entry.getDate().toString() + " - " + entry.getHours() + " hours");
        }
        return datesAndHours;
    }


    public static class TimeLogEntry {
        private final float hours;
        private final LocalDate date;

        public TimeLogEntry(float hours, LocalDate date) {
            this.hours = hours;
            this.date = date;
        }
        public float getHours() {
            return hours;
        }





        public LocalDate getDate() {
            return date;
        }
    }
}