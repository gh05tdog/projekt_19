package domain;

import app.SoftwareApp;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Project {


    private String ProjectName;
    private String ProjectId;

    private User ProjectManager;

    private List<User> workersList = new ArrayList<>();

    private List<Activities> ActivityList = new ArrayList<>();

    public Project(String ProjectName) {

        this.ProjectName = ProjectName;

        //Count all projects in the system
        //Add 1 to the count
        //Set the ProjectId to the count

        int Year = Calendar.getInstance().get(Calendar.YEAR) % 100;

        if (SoftwareApp.getNumberOfProject() < 10) {
            this.ProjectId = Year + "00" + (SoftwareApp.getNumberOfProject() + 1);
        } else if (SoftwareApp.getNumberOfProject() < 100) {
            this.ProjectId =Year + "0" + (SoftwareApp.getNumberOfProject() + 1);
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


    public void setProjectManager(User user) {
            ProjectManager = user;
    }

    public User getProjectManager() {
        return ProjectManager;
    }

    public void addActivity(String name, String timebudget, String weeks, String startWeek) {
        Activities activity = new Activities(name, timebudget, weeks, startWeek);
        ActivityList.add(activity);
    }

    public List<Activities> getActivityList() {
        return ActivityList;
    }

    public class Activities extends Project {
        private String ActivityName;
        private String ActivityId;
        private String TimeBudget;
        private String Weeks;
        private String StartWeek;

        public Activities(String ActivityName, String TimeBudget, String Weeks, String StartWeek) {
            super(ProjectName);
            this.ActivityName = ActivityName;
            this.TimeBudget = TimeBudget;
            this.Weeks = Weeks;
            this.StartWeek = StartWeek;
            this.ActivityId = ProjectId + "A" + (ActivityList.size() + 1);
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
    }
}

