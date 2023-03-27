Feature: User interacts with activities
  Description: The user will be assigned to multiple activities, but no more than 20. The user has to be able to register hours used on activities.

  Scenario: User is more than 10 activities
    Given there is a project with the id "23001"
    And the projekt manager with the id "mang" is logged in
    When the user adds an activity To the project with a name "Make diagram", timebudget "100", weeks "3", start week "10" to the project with the id "23001"
    And if the user with an id "abcd" is assigned 10 activities
    When the user with an id "abcd" is assigned the activity
    Then the user will get a warning "This user has more than 10 activities assigned"
    And the user is assigned the activity

#  Scenario: User registered 2 hours on activity
#    Given that the user with the id "abcd" is logged in
#    And there is a project with the id "23001"
#    And there is an activity with a name "Make diagram", timebudget "100", weeks "3", start week "10"
#    When the user navigates to their timesheet for the current week
#    And the user logs "2" hours on activity "Make diagram"
#    Then the used time on activity is 2
#    And the user timesheet should be updated with the logged time
#
#  Scenario: View availability of employees
#    Given the projekt manager with the id "mang" is logged in
#    And there are employees registered in the system
#    And there is a project with the id "23001"
#    And there is an activity with a name "Make diagram", timebudget "100", weeks "3", start week "10"
#    When the user navigates to the resource allocation page
#    Then the user should see a list of available employees for each project
#    And I should be able to filter employees by availability