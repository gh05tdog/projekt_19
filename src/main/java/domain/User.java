package domain;

import app.SoftwareApp;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class User {

    private final String name;
    private final String userId;
    public final List<ActivityTimeSheet> timeSheet = new ArrayList<>();
    private final List<Project.Activities> UserActivityList;

    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
        this.UserActivityList = new ArrayList<>();
    }

    public static User createUser(String name, String userId) throws UserAlreadyExistsException {
        if (SoftwareApp.getUserFromID(userId) != null) {
            return SoftwareApp.getUserFromID(userId);
        }

        User newUser = new User(name, userId);
        SoftwareApp.UserList.add(newUser);
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

    public float getTimeSpentOnActivity(String activityId) {
        for (ActivityTimeSheet activityTimeSheet : timeSheet) {
            if (activityTimeSheet.getActivityId().equals(activityId)) {
                return activityTimeSheet.getTotalHours();
            }
        }
        return 0;
    }

    public void updateTimeSheet(String activityId, float hours, LocalDate date) {
        for (ActivityTimeSheet activity : timeSheet) {
            if (activity.getActivityId().equals(activityId)) {
                activity.addHours(hours, date);
                return;
            }
        }
        ActivityTimeSheet newActivity = new ActivityTimeSheet(activityId, hours, date);
        timeSheet.add(newActivity);
    }

    public void editTimeSpent(Text activityID, float oldTimeSpent, float newTimeSpent) {
        for (ActivityTimeSheet activity : timeSheet) {
            if (activity.getActivityId().equals(activityID.getText())) {
                activity.editHours(oldTimeSpent, newTimeSpent);
                return;
            }
        }
    }

    public void removeTimeSpent(String activityID, float timeSpent) {
        for (ActivityTimeSheet activity : timeSheet) {
            if (activity.getActivityId().equals(activityID)) {
                activity.editHours(timeSpent, 0);
                return;
            }
        }
    }
    public List<Project.Activities> getUserActivityList () {
        return UserActivityList;
    }

    public ActivityTimeSheet getActivityTimeSheet(String activityId) {
        for (ActivityTimeSheet activityTimeSheet : timeSheet) {
            if (activityTimeSheet.getActivityId().equals(activityId)) {
                return activityTimeSheet;
            }
        }
        return null;
    }

    public void addActivityTimeSheet(ActivityTimeSheet newActivityTimeSheet) {
        timeSheet.add(newActivityTimeSheet);
    }

}
