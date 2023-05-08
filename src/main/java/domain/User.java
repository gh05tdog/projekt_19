package domain;

import app.SoftwareApp;
import javafx.scene.text.Text;



import java.time.LocalDate;
import java.util.ArrayList;

import java.util.List;



public class User {
    //Fields og constructor lavet af Oliver
    private final String name;

    private final String userId;

    public final List<ActivityTimeSheet> timeSheet = new ArrayList<>();
    private final List<Project.Activities> UserActivityList;


    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
        this.UserActivityList = new ArrayList<>();
    }

    //Lavet af Martin
    public static User createUser(String name, String userId) throws UserAlreadyExistsException {
        if (SoftwareApp.getUserFromID(userId) != null) {
            return SoftwareApp.getUserFromID(userId);
        }
        User newUser = new User(name, userId);
        SoftwareApp.UserList.add(newUser);
        return newUser;
    }
    //Lavet af Martin
    public static void addActivityToUser(Project.Activities activities, User user) {
        user.UserActivityList.add(activities);
    }
    //Lavet af Martin
    public String getName() {
        return name;
    }
    //Lavet af Martin
    public String getUserId() {
        return userId;
    }
    //Lavet af Martin
    public int getAssignedActivitiesNumber() {
        return UserActivityList.size();
    }
    //Lavet af Marcus
    public float getTimeSpentOnActivity(String activityId) {
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
    //Lavet af Marcus
    public void updateTimeSheet(String activityId, float hours, LocalDate date) {
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

    //Lavet af Marcus
    public void editTimeSpent(Text activityID, float oldTimeSpent, float newTimeSpent) {
        for (ActivityTimeSheet activity : timeSheet) {
            if (activity.getActivityId().equals(activityID.getText())) {
                activity.editHours(oldTimeSpent, newTimeSpent);
                return;
            }
        }
    }
    //Lavet af Marcus
    public void removeTimeSpent(Text activityID, float timeSpent) {
        for (ActivityTimeSheet activity : timeSheet) {
            if (activity.getActivityId().equals(activityID.getText())) {
                activity.editHours(timeSpent, 0);
                return;
            }
        }
    }
    //Lavet af Marcus
    public List<Project.Activities> getUserActivityList () {
        return UserActivityList;
    }
}
