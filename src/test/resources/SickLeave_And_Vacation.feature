Feature: The user asks for Vacation and puts in dates for the vacation. The same applies for sick leave
  Description: A user can ask for vacation and sick leave and put in the dates for the vacation and sick leave

  Scenario: The user asks for vacation and puts in dates for the vacation
    Given that there is a user with the id "kasp" and the name "Kasper"
    And the user with the id "kasp" is not already on vacation
    And the user with the id "kasp" registers vacation with start date "2023-01-01" and end date "2023-01-14"
    Then the user with the id "kasp" is on vacation from "2023-01-01" to "2023-01-14"


  Scenario: The user is sick
    Given that there is a user with the id "kasp" and the name "Kasper"
    And the user with the id "kasp" is not already sick
    And the user with the id "kasp" registers sick leave from "2023-01-01" to "2023-01-02"
    Then the user with the id "kasp" is sick from "2023-01-01" to "2023-01-02"
