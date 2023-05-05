Feature: The user asks for Vacation and puts in dates for the vacation. The same applies for sick leave
  Description: A user can ask for vacation and sick leave and put in the dates for the vacation and sick leave

  Scenario: The user asks for vacation and puts in dates for the vacation
    Given a user has the id "kasp"
    And the user with the id "kasp" is logged in
    And the user with the id "kasp" is not already on vacation
    And the user with the id "kasp" registers vacation with start date "2023-01-01" and end date "2023-01-14"
    Then the user with the id "kasp" is on vacation from "2023-01-01" to "2023-01-014"
    Then the users availability should be updated

#  Scenario: The user is sick
#    Given that there is a user with the id "abcd"
#    And that the user with the id "abcd" is logged in
#    And the user is not already sick
#    And the user registers sick leave from "2023-01-01" to "2023-01-02"
#    Then the user with the id "abcd" is sick from "2023-01-01" to "2023-01-02"
#    Then the users availability should be updated