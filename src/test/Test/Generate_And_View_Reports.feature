Feature: Generate and View Reports
  Description: As a project manager, they want to generate weekly reports for my team's productivity to track their progress on ongoing projects. Additionally, they want to be able to view previously generated reports for future reference.

  Scenario: Generate Reports
    Given there is a project with the id "23001"
    And the project manager with the id "mang" is logged in
    And there is an activity with a name "Make diagram", timebudget "100", weeks "3", start week "10"
    And there are completed activities for the project with the id "23001"
    When I generate a report for the project
    Then the report shows the hours worked on each activity and the total time for the project

#  Scenario: Generate a weekly report for the team
#    Given there is a project with the id "23001"
#    And the project manager with the id "mang" is logged in
#    When the user click the "Generate Report" button and have selected the date range
#    Then the report should be generated and the file should be downloaded
#
