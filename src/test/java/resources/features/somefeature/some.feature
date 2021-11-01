@enrollments_actions  @Regression
Feature: Enrollment actions

  @smoke
  Scenario:1.Create a new user
    Given "...." is on the "....." page
    When user creates new user
    Then The user should be displayed
    When admin deletes the user
    Then The user should be deleted