package domain;

import app.SoftwareApp;
import javafx.scene.text.Text;



import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;


public class User {

    private final String name;

    private final String userId;

    private final List<ActivityTimeSheet> timeSheet = new ArrayList<>();
    private final List<Project.Activities> UserActivityList;
    public static final ArrayList<LocalDate> VacationDaysList = new ArrayList<>();
    private static boolean isCreated = false;
    private static boolean isCreated2 = false;




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

    public static void addVacationDays(LocalDate start, LocalDate end) {

        for (LocalDate date = start; date.isBefore(end); date = date.plusDays(1)) {
            VacationDaysList.add(date);
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


    public void editTimeSpent(Text activityID, int oldTimeSpent, int newTimeSpent) {
        for (ActivityTimeSheet activity : timeSheet) {
            if (activity.getActivityId().equals(activityID.getText())) {
                activity.editHours(oldTimeSpent, newTimeSpent);
                return;
            }
        }
    }

    public void removeTimeSpent(Text activityID, int timeSpent) {
        for (ActivityTimeSheet activity : timeSheet) {
            if (activity.getActivityId().equals(activityID.getText())) {
                activity.editHours(timeSpent, 0);
                return;
            }
        }
    }
    public List<Project.Activities> getUserActivityList () {
        return UserActivityList;
    }
}
