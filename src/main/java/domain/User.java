package domain;

import java.util.ArrayList;
import java.util.List;

public class User {
    private final String name;
    private final String userId;

    public static List<Project.Activities> UserActivityList = new ArrayList<>();


    public User(String name, String userId) {
        this.name = name;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public String getUserId() {
        return userId;
    }

    public int getAssignedActivitiesNumber(){
        return UserActivityList.size();
    }
}
