package domain;

import app.SoftwareApp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class User {

    private final String name;
    private String userId;

    // public static List<Project.Activities> UserActivityList = new ArrayList<>();
    private final List<ActivityTimeSheet> timeSheet = new ArrayList<>();
    private final List<Project.Activities> UserActivityList;
    public ArrayList<Date> UnAvailableDates = new ArrayList<>();




    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
        this.UserActivityList = new ArrayList<>();
    }

    public static User createUser(String name, String userId) throws UserAlreadyExistsException {
        //Check if username is already in use
        //If it is, throw an exception
            if (SoftwareApp.getUserFromID(userId) != null) {
                throw new UserAlreadyExistsException("User already exists");
            }
        User newUser = new User(name, userId);
        SoftwareApp.UserList.add(newUser);
        System.out.println("User " + name + " created");
        return newUser;
    }

    public static void addActivityToUser(Project.Activities activities, User user) {
        user.UserActivityList.add(activities);
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public int getAssignedActivitiesNumber() {
        return UserActivityList.size();
    }


    public int getTimeSpentOnActivity(String activityId) {
        // Search for the activity in the timesheet
        for (ActivityTimeSheet activityTimeSheet : timeSheet) {
            if (activityTimeSheet.getActivityId().equals(activityId)) {
                // If the activity is found, return the logged hours
                return activityTimeSheet.getTotalHours();
            }
        }
        // If the activity is not in the timesheet, return 0
        return 0;
    }

    public ArrayList<Date> getUnAvailableDates() {
        return UnAvailableDates;
    }

    public void setVacation(String startDate, String endDate) {
        LocalDate start = LocalDate.parse(startDate);
        LocalDate end = LocalDate.parse(endDate);
        for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
            UnAvailableDates.add(java.sql.Date.valueOf(date));
        }
    }

    public void updateTimeSheet(String activityId, int hours, LocalDate date) {
        for (ActivityTimeSheet activity : timeSheet) {
            if (activity.getActivityId().equals(activityId)) {
                activity.addHours(hours, date);
                return;
            }
        }
        // If the activity is not already in the timesheet, add it
        ActivityTimeSheet newActivity = new ActivityTimeSheet(activityId, hours, date);
        timeSheet.add(newActivity);
    }

    public boolean isOnVacation(Date date) {
        for (Date vacationDate : UnAvailableDates) {
            if (vacationDate.equals(date)) {
                return true;
            }
        }
        return false;
    }
}
