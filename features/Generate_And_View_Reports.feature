Feature: Generate and View Reports
  Description: As a project manager, they want to generate weekly reports for my team's productivity to track their progress on ongoing projects. Additionally, they want to be able to view previously generated reports for future reference.

  Scenario: Generate Reports
    Given there is a project with the id "23001"
    And the project manager with the id "mang" is logged in
    And there is an activity with a name "Make diagram", timebudget "100", weeks "3", start week "10"
    And there are completed activities
    When I generate a report for the project
    Then the report shows the hours worked on each activity and the total time for the project

  Scenario: Generate a weekly report for the team
    Given there is a project with the id "23001"
    And the project manager with the id "mang" is logged in
    And the user navigate to the "Weekly Reports" page
    And select the date range for the report
    When the user click the "Generate Report" button
    Then the user should see a table displaying the productivity of each team member for the selected time period
    And the user should be able to download the report as a CSV or PDF file

  Scenario: View previously generated weekly reports
    Given there is a project with the id "23001"
    And the project manager with the id "mang" is logged in
    And the user navigate to the "Weekly Reports" page
    When the user click the "View Previous Reports" button
    Then the user should see a list of previously generated weekly reports
    And the user should be able to download any of them as a CSV or PDF file