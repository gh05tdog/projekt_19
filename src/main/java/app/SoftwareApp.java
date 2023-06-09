package app;

import domain.Project;
import domain.User;

import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.ArrayList;
import java.util.List;


public class SoftwareApp {

    public SoftwareApp() {
        //Linje 15(herfra) til linje 23 er skrevet af Jazmin
        //Print all projects
        for (Project project : projectList) {
            System.out.println(project.getProjectName());
        }
    }

    public static List<Project> projectList = new ArrayList<>();
    public static List<User> UserList = new ArrayList<>();

    //addproject er lavet af Martin
    public static void addProject(String projectName) {
        System.out.println("Project added");
        Project project = new Project(projectName);
        projectList.add(project);
    }

    //Lavet af Martin
    public static int getNumberOfProject() {
        return projectList.size();
    }

    //Lavet af Oliver
    public static User getUserFromID(String id) {
        // Check if the user with the id is in UserList and return the user if it is
        // else return null
        return UserList.stream().filter(user -> user.getUserId().equals(id)).findFirst().orElse(null);
    }
    //Lavet af oliver
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

    //Lavet af Hannah
    public static Project getProject(String projectId) {
        for (Project project : projectList) {
            if (project.getProjectId().equals(projectId)) {
                return project;
            }
        }
        return null;
    }
    //Lavet af Hannah
    public static void addActivity(String name, String timebudget, String weeks, String startWeek, String projectId) {
        Project project = getProject(projectId);
        if (project != null) {
            project.addActivity(name, timebudget, weeks, startWeek);
        }
    }
    //Lavet af Jazmin
    public static void assignActivityToUser(String userID, String projectID, String activityID) throws TooManyActivities, WayTooManyActivities {
        Project project = getProject(projectID);
        if (project != null) {
            if (getUserFromID(userID).getAssignedActivitiesNumber() >= 10 && getUserFromID(userID).getAssignedActivitiesNumber() < 20) {
                project.assignActivityToUser(userID, activityID);
                throw new TooManyActivities("This user has more than 10 activities assigned");
            } else if (getUserFromID(userID).getAssignedActivitiesNumber() == 20) {
                throw new WayTooManyActivities("This user has 20 activities assigned. Cannot assign more activities");
            }
            project.assignActivityToUser(userID, activityID);
        }
    }

    //Sidste 2 lavet af Oliver
    public static void addUser(User user) {
        UserList.add(user);
    }
    public static int getCurrentWeek() {
        LocalDate date = LocalDate.now();
        return date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR);
    }
}