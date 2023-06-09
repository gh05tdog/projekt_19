package app;

import domain.Project;
import domain.Project.Activities;
import domain.User;

import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.temporal.IsoFields;
import java.util.List;

//Hele denne Klasse er skrevet af Marcus Christoffersen
public class CSVgenerator {
    private final Project project;

    public CSVgenerator(Project project) {
        this.project = project;
    }


    private String makeReportName() {
        LocalDate date = LocalDate.now();
        int Week = Integer.parseInt(String.valueOf(date.get(IsoFields.WEEK_OF_WEEK_BASED_YEAR)));
        return "Weekly Report - " + Week + ".csv";
    }

    public String generateCSVReport(int date) {
        assert isValidValues(date) : "Prebetingelse: datoen skal være et positivt tal, og projektet skal findes og ikke være null";
        StringBuilder reportBuilder = new StringBuilder();
        reportBuilder.append("Week: ").append(date).append("\n");
        reportBuilder.append("Project Name: ").append(project.getProjectName()).append("\n");
        reportBuilder.append("Project ID: ").append(project.getProjectId()).append("\n");
        reportBuilder.append("\n");
        List<Activities> activityList = project.getActivityList(); //1
        for (Project.Activities activity : activityList) { //2
            for (User user : activity.getUserAssignedActivities()) { //3
                reportBuilder.append("Activity Name: ").append(activity.getActivityName()).append("\n");
                reportBuilder.append("Activity ID: ").append(activity.getActivityId()).append("\n");
                reportBuilder.append("Time Budget: ").append(activity.getTimeBudget()).append("\n");
                reportBuilder.append("Weeks: ").append(activity.getWeeks()).append("\n");
                reportBuilder.append("Start Week: ").append(activity.getStartWeek()).append("\n");
                reportBuilder.append("Is completed: ").append(activity.isCompleted()).append("\n");
                reportBuilder.append("Assigned User ID: ").append(user.getUserId()).append("\n");
                reportBuilder.append("Assigned User Name: ").append(user.getName()).append("\n");
                //add how much time the user has spent on the activity
                reportBuilder.append("Time Spent on Activity: ").append(user.getTimeSpentOnActivity(activity.getActivityId())).append("\n");
                reportBuilder.append("\n");
            }
        }
        assert isReportGeneratedAndNotEmpty(reportBuilder.toString()) : "Postbetingelse: rapporten skal være genereret og ikke være tom";
        return reportBuilder.toString();
    }

    private boolean isValidValues(int date) {
        return date > 0 && project != null;
    }

    private boolean isReportGeneratedAndNotEmpty(String csvReport) {
        return csvReport != null && !csvReport.isEmpty();
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

