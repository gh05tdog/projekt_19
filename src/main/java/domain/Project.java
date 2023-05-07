package domain;

import app.SoftwareApp;


import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;



public class Project {

    private String ProjectName;
    private String ProjectId;
    public List<User> workersList = new ArrayList<>();
    public final List<Activities> ActivityList = new ArrayList<>();
    private User ProjectManager;

    public Project(String projectName) {
        this.ProjectName = projectName;
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

    public User getWorkersList(String id) {
        return workersList.stream().filter(user -> user.getUserId().equals(id)).findFirst().orElse(null);
    }

    public boolean userInProject (User user) {
        return workersList.contains(user);
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
        // Generate a new ActivityId for the added activity
        String activityId = ProjectId + "A" + ActivityList.size();
        activity.setActivityId(activityId);
    }



    public List<Activities> getActivityList() {

        return ActivityList;
    }
    public int getNumberOfActivities() {
        return getActivityList().size();
    }

    public void assignActivityToUser(String userID, String activityID) {
        //Loop through the list of activities
        for (Activities activity : ActivityList) {
            if (activity.getActivityId().equals(activityID)) {
                //Add the user to the activity
                //If the activityID matches the activityID of the activity in the list
                activity.addWorkerToActivity(SoftwareApp.getUserFromID(userID));
            }
        }
    }

    public Activities getActivity(String s) {
        for (Activities activities : ActivityList) {
            if (activities.getActivityId().equals(s)) {
                return activities;
            }
        }
        return null;
    }

    public int getTotalTimeSpentOnProject() {
        int totalTimeSpentOnProject = 0;
        for (Activities activity : ActivityList) {
            totalTimeSpentOnProject += activity.LoggedTime;
        }
        return totalTimeSpentOnProject;
    }

    public void changeProjectId(String newId) {
        ProjectId = newId;
    }

    public void changeProjectName(String newName) {
        ProjectName = newName;
    }

    public void setProjectName(String newProjectName) {
        this.ProjectName = newProjectName;
    }

    public User getManager() {
        return ProjectManager;
    }


    public class Activities extends Project {
        public List<Activities> ActivityList = new ArrayList<>();
        private final List<User> UserAssignedActivities = new ArrayList<>();
        private final String ActivityName;
        private String ActivityId;
        private String TimeBudget;
        private String Weeks;
        private String StartWeek;
        private int LoggedTime = 0;
        private Boolean isCompleted;
        private final ActivityTimeSheet activityTimeSheet;

        public Activities(String ActivityName, String TimeBudget, String Weeks, String StartWeek) {
            super(ProjectName);
            this.ActivityName = ActivityName;
            this.TimeBudget = TimeBudget;
            this.Weeks = Weeks;
            this.StartWeek = StartWeek;
            this.ActivityId = ProjectId + "A" + (ActivityList.size() + 1);
            this.isCompleted = false;
            this.activityTimeSheet = new ActivityTimeSheet(ActivityId, 0, LocalDate.now());
        }
        public void addWorkerToActivity(User user) {
            //Add the user to the list of users assigned to the activity
            UserAssignedActivities.add(user);
            //Add the activity to the list of activities assigned to the user
            User.addActivityToUser(this, user);

            SoftwareApp.getProject(ProjectId).addWorker(user);

        }

        public List<Activities> getActivityList() {
            return this.ActivityList;
        }

        //return the list of users assigned to the activity
        public List<User> getUserAssignedActivities() {
            return UserAssignedActivities;
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

        public void logHours(User user, int hours, LocalDate date) {
            LoggedTime += hours;
            user.updateTimeSheet(ActivityId, hours, date);
            //activityTimeSheet.addHours(hours, date);
        }

        public int getUsedTime() {
            return LoggedTime;
        }

        public void setCompleted() {
            //set the activity to completed
            isCompleted = true;
        }

        public String getEndWeek() {
            return String.valueOf(Integer.parseInt(StartWeek) + Integer.parseInt(Weeks) - 1);
        }

        public ActivityTimeSheet getActivityTimeSheet() {
            return activityTimeSheet;
        }
        public boolean isCompleted() {
            return isCompleted;
        }

        public void setActivityId(String activityId) {
            ActivityId = activityId;
        }

        public void setStartWeek(String text) {
            this.StartWeek = text;
        }

        public void setEndWeek(String text) {
            //Calculate the number of weeks based on the start week and the end week.
            //Then change the number of weeks to the calculated number of weeks
            this.Weeks = String.valueOf(Integer.parseInt(text) - Integer.parseInt(StartWeek) + 1);
        }

        public boolean getSpecialActivity() {
            return ActivityId.contains("11111A");
        }
        public void setAllocatedTime(String text) {
            this.TimeBudget = text;
        }

        public String getAllocatedTime() {
            return TimeBudget;
        }

        public double getPercentTime() {
            return (double) LoggedTime / Integer.parseInt(TimeBudget) * 100;
        }

        public void updatePercentTime(String userId, int registeredTime) {
            if (UserAssignedActivities.contains(SoftwareApp.getUserFromID(userId))) {
                LoggedTime += registeredTime;
            }
        }

    }

}

