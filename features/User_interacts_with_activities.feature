Feature: User interacts with activities
  Description: The user will be assigned to multiple activities, but no more than 20. The user has to be able to register hours used on activities.

  Scenario: User is more than 10 activities
    Given there is a project with the id "23001"
    And the projekt manager with the id "mang" is logged in
    When the user adds an activity To the project with a name "Make diagram", timebudget "100", weeks "3", start week "10" to the project with the id "23001"
    And if the user with an id "tejs" is assigned 10 activities
    When the user with an id "tejs" is assigned the activity
    Then the user will get a warning "This user has more than 10 activities assigned"
    And the user is assigned the activity

  Scenario: User registered 2 hours on activity
    Given that there is a user with the id "abcd"
    And that the user with the id "abcd" is logged in
    And there is a project with the id "23001"
    And the user adds an activity To the project with a name "Make diagram", timebudget "100", weeks "3", start week "10" to the project with the id "23001"
    When the user logs 2 hours on activity "23001A1"
    Then the used time on activity is 2
    And the user timesheet should be updated with the logged time

