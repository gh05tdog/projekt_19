package app;

import domain.Project;
import domain.User;

import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SoftwareApp {

    public SoftwareApp() {
        addProject("Off work Activities");
        addActivity("Sick days", "0", "0", "1", "23001");
        addActivity("Vacation days", "0", "0", "1", "23001");
        addActivity("Other", "0", "0", "1", "23001");
    }
    public static List<Project> projectList = new ArrayList<>();
    public static List<User> UserList = new ArrayList<>();
    public static String CurrentUser;

    public static void addProject(String projectName) {
        Project project = new Project(projectName);
        projectList.add(project);
    }

    public static int getNumberOfProject() {
        return projectList.size();
    }

    public static User getUserFromID(String id) {
        // Check if the user with the id is in UserList and return the user if it is
        // else return null
        return UserList.stream().filter(user -> user.getUserId().equals(id)).findFirst().orElse(null);
    }

    public static void addCoWorker(String userId, String projectId) {
        for (Project project : projectList) {
            if (project.getProjectId().equals(projectId)) {
                for (User user : UserList) {
                    if (user.getUserId().equals(userId)) {
                        project.addWorker(user);
                    }
                }
            }
        }
    }

    public static Project getProject(String projectId) {
        //use a stream filter to get the project from an id
        return projectList.stream().filter(project -> project.getProjectId().equals(projectId)).findFirst().orElse(null);
    }

    public static void addActivity(String name, String timebudget, String weeks, String startWeek, String projectId) {
        Project project = getProject(projectId);
        if (project != null) {
            project.addActivity(name, timebudget, weeks, startWeek);
        }
    }


    public static void assignActivityToUser(String userID, String projectID, String activityID) throws TooManyActivities {
        Project project = getProject(projectID);
        if (project != null) {
            if (getUserFromID(userID).getAssignedActivitiesNumber() >= 10) {
                project.assignActivityToUser(userID, activityID);
                throw new TooManyActivities("This user has more than 10 activities assigned");
            }
            project.assignActivityToUser(userID, activityID);
        }
    }

    public static void addUser(User user) {
        UserList.add(user);
    }

    public static String getCurrentWeek() {
        LocalDate date = LocalDate.now();
        return String.valueOf(date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR));
    }

}