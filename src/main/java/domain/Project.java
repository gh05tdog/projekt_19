package domain;

import app.SoftwareApp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Project {


    private final String ProjectName;
    private final String ProjectId;
    private final List<User> workersList = new ArrayList<>();
    public static final List<Activities> ActivityList = new ArrayList<>();
    private User ProjectManager;


    public Project(String ProjectName) {

        this.ProjectName = ProjectName;

        //Count all projects in the system
        //Add 1 to the count
        //Set the ProjectId to the count

        int Year = Calendar.getInstance().get(Calendar.YEAR) % 100;

        if (SoftwareApp.getNumberOfProject() < 10) {
            this.ProjectId = Year + "00" + (SoftwareApp.getNumberOfProject() + 1);
        } else if (SoftwareApp.getNumberOfProject() < 100) {
            this.ProjectId = Year + "0" + (SoftwareApp.getNumberOfProject() + 1);
        } else {
            this.ProjectId = String.valueOf(Year + (SoftwareApp.getNumberOfProject() + 1));
        }
    }

    public String getProjectName() {
        return ProjectName;
    }

    public String getProjectId() {
        return ProjectId;
    }

    public void addWorker(User user) {
        workersList.add(user);
    }

    public List<User> getWorkersList() {
        return workersList;
    }

    public User getProjectManager() {
        return ProjectManager;
    }

    public void setProjectManager(User user) {
        ProjectManager = user;
    }

    public void addActivity(String name, String timebudget, String weeks, String startWeek) {
        Activities activity = new Activities(name, timebudget, weeks, startWeek);
        ActivityList.add(activity);
    }

    public List<Activities> getActivityList() {
        return ActivityList;
    }

    public void assignActivityToUser(String userID, String activityID) {

        for (Activities activity : ActivityList) {
            if (activity.getActivityId().equals(activityID)) {
                activity.addWorkerToActivity(SoftwareApp.getUserFromID(userID));
            }
        }
    }

    public Activities getActivity(String s) {
        for (Activities activity : ActivityList) {
            if (activity.getActivityId().equals(s)) {
                return activity;
            }
        }
        return null;
    }

    public class Activities extends Project {

        private final List<User> UserAssignedActivities = new ArrayList<>();
        private final String ActivityName;
        private final String ActivityId;
        private final String TimeBudget;
        private final String Weeks;
        private final String StartWeek;
        private int LoggedTime;

        public Activities(String ActivityName, String TimeBudget, String Weeks, String StartWeek) {
            super(ProjectName);
            this.ActivityName = ActivityName;
            this.TimeBudget = TimeBudget;
            this.Weeks = Weeks;
            this.StartWeek = StartWeek;
            this.ActivityId = ProjectId + "A" + (ActivityList.size() + 1);

        }

        public void addWorkerToActivity(User user) {
            UserAssignedActivities.add(user);
            User.UserActivityList.add(Activities.this);
        }

        public String getActivityName() {
            return ActivityName;
        }

        public String getActivityId() {
            return ActivityId;
        }

        public String getTimeBudget() {
            return TimeBudget;
        }

        public String getWeeks() {
            return Weeks;
        }

        public String getStartWeek() {
            return StartWeek;
        }
        public void logHours(User user, int hours) {
                LoggedTime += hours;
                user.updateTimeSheet(ActivityId, hours);
        }

        public Integer getUsedTime() {
            return LoggedTime;
        }
    }
}

