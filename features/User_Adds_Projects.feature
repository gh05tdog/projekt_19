Feature: User adds project
  Description: A user adds a project to the software. The User chooses a project manager. The user also adds an activity to an existing projekt.
  Actors: User

  Scenario: Add a project successfully
    Given that the user with the id "abcd" is logged in
    And there is a project with and the year is "22" and the name of the project is "Lommeregner"
    When the project is added
    Then there is a project named "Lommeregner" with an id
#
#  Scenario: Add a co-worker and a project manager to a project
#    Given that the user with the id "abcd" is logged in
#    And there is a project with the id "22001"
#    When user adds a co-worker with the id "john"
#    And the users select a project manager with the id "mang"
#    Then the project with the id "22001" has a worker with the id "john" and a project manager with the id "mang" assigned
#
#  Scenario: Adds an activity to a project
#    Given that the user with the id "abcd" is logged in
#    And there is a project with the id "22001"
#    When the user adds an activity wto the project with a name "Make diagram", timebudget "100", weeks "3", start week "10"
#    Then the project has an activiy with the name "Make diagram", timebudget "100", weeks "3", start week "10"