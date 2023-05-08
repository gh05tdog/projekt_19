package domain;

public class ActivityTimeSheet {
    private final String activityId;
    private int totalHours;

    public ActivityTimeSheet(String activityId, int initialHours) {
        this.activityId = activityId;
        this.totalHours = initialHours;
    }

    public void addHours(int hours) {
        totalHours += hours;
    }

    public String getActivityId() {
        return activityId;
    }

    public int getTotalHours() {
        return totalHours;
    }

    public Project.Activities getActivity(String s) {
        for (Project.Activities activity : Project.ActivityList) {
            if (activity.getActivityId().equals(s)) {
                return activity;
            }
        }
        return null;
    }
}
