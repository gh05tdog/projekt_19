package domain;

import app.SoftwareApp;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private final String userId;

   // public static List<Project.Activities> UserActivityList = new ArrayList<>();
    private final List<ActivityTimeSheet> timeSheet = new ArrayList<>();
    private final List<Project.Activities> UserActivityList;


    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
        this.UserActivityList = new ArrayList<>();
    }

    public static User createUser(String name, String userId) {
        //Check if username is already in use
        //If it is, throw an exception
        if (SoftwareApp.getUserFromID(userId) != null) {
            return SoftwareApp.getUserFromID(userId);
        }
        User newUser = new User(name, userId);
        SoftwareApp.UserList.add(newUser);
        System.out.println("User " + name + " created");
        return newUser;
    }

    public static void addActivityToUser(Project.Activities activities, User user){
        user.UserActivityList.add(activities);
    }


    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }


    public int getAssignedActivitiesNumber(){
        return UserActivityList.size();
    }
    public void updateTimeSheet(String activityId, int hours) {
        for (ActivityTimeSheet activity : timeSheet) {
            if (activity.getActivityId().equals(activityId)) {
                activity.addHours(hours);
                return;
            }
        }
        // If the activity is not already in the timesheet, add it
        ActivityTimeSheet newActivity = new ActivityTimeSheet(activityId, hours);
        timeSheet.add(newActivity);
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
}
