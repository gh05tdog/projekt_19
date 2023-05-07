Feature: User interacts with activities
    Description: The user will be assigned to multiple activities, but no more than 20. The user has to be able to register hours used on activities.
Scenario: User registered 2 hours on activity
    Given that there is a user with the id "abcd" and the name "John Doe"
    And there is a project with the id "23001"
    And the user adds an activity To the project with a name "Make diagram", timebudget "100", weeks "3", start week "10" to the project with the id "23001"
    When the user with the id "abcd" logs 2 hours on activity "23001A2"
    Then the used time on activity is 2 hours
    And the user timesheet should be updated with the logged time

