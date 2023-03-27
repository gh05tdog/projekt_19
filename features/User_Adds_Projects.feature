Feature: User adds project
  Description: A user adds a project to the software. The User chooses a project manager. The user also adds an activity to an existing project.
  Actors: User

  Scenario: Add a project successfully
    Given that there is a user with the id "abcd"
    And that the user with the id "abcd" is logged in
    When the user adds a project with the name "Lommeregner"
    Then there is a project named "Lommeregner" the project has an id "23001"

  Scenario: Add a co-worker and a project manager to a project
    Given that there is a user with the id "abcd"
    And that the user with the id "abcd" is logged in
    And there is a project with the id "23001"
    When user adds a co-worker with the id "john" to the project with the id "23001"
    And the users select a project manager with the id "mang" for the project with the id "23001"
    Then the project with the id "23001" has a worker with the id "john" and a project manager with the id "mang" assigned

  Scenario: Adds an activity to a project
    Given that there is a user with the id "abcd"
    And that the user with the id "abcd" is logged in
    And there is a project with the id "23001"
    When the user adds an activity To the project with a name "Make diagram", timebudget "100", weeks "3", start week "10" to the project with the id "23001"
    Then the project with the id "23001" has an activiy with the name "Make diagram", timebudget "100", weeks "3", start week "10"