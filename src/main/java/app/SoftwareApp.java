package app;

import domain.Project;
import domain.User;

import java.util.ArrayList;
import java.util.List;

public class SoftwareApp {

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

    public static void addUser(User user) {
        UserList.add(user);
    }

    public static Project getProject(String projectId) {
        for (Project project : projectList) {
            if (project.getProjectId().equals(projectId)) {
                return project;
            }
        }
        return null;
    }
}

