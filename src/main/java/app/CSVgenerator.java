package app;

import domain.ActivityTimeSheet;
import domain.Project;
import domain.Project.Activities;
import domain.User;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.List;

public class CSVgenerator {
    private final Project project;

    public CSVgenerator(Project project) {
        this.project = project;
    }
    private String makeReportName(){
        LocalDate date = LocalDate.now();
        int Week = Integer.parseInt(String.valueOf(date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR)));
        return "Weekly Report - " + Week + ".csv";
    }
    public String generateCSVReport(int date) {
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Week: ").append(date).append("\n");
        reportBuilder.append("Project Name,Project ID,Activity Name,Activity ID,Time Budget,Weeks,Start Week,Assigned User ID,Assigned User Name,Time Spent on Activity, Time Left for Project\n");

        List<Activities> activityList = project.getActivityList();
        for (Project.Activities activity : activityList) {
            for (User user : activity.getUserAssignedActivities()) {
                reportBuilder.append(String.join(",", project.getProjectName(),
                        project.getProjectId(),
                        activity.getActivityName(),
                        activity.getActivityId(),
                        activity.getTimeBudget(),
                        activity.getWeeks(),
                        activity.getStartWeek(),
                        user.getUserId(),
                        user.getName()));
                        reportBuilder.append(ActivityTimeSheet.getDateAndHours());
                        reportBuilder.append(project.getTimeLeftForProject());
                        reportBuilder.append("\n");
            }
        }
        return reportBuilder.toString();
    }

    public void saveCSVReportToFile(int date) {
        String path = makeReportName();
        String csvReport = generateCSVReport(date);
        try (FileWriter fileWriter = new FileWriter(path)) {
            fileWriter.write(csvReport);
        } catch (IOException e) {
            System.err.println("Error writing CSV report to file: " + e.getMessage());
        }
    }
}

