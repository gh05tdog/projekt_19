package app;

import domain.Project;
import domain.User;

import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.List;

public class SoftwareApp {

    public static List<Project> projectList = new ArrayList<>();
    public static List<User> UserList = new ArrayList<>();

    public static String CurrentUser;

    public static void addProject(String projectName) {
        Project project = new Project(projectName);
        projectList.add(project);
        //System.out.println(project.getProjectId());
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
        for (Project project : projectList) {
            if (project.getProjectId().equals(projectId)) {
                return project;
            }
        }
        return null;
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