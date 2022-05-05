@Regression  @Login
Feature: This is a test feature

  @smoke  #1
  Scenario Outline: Login
    Given "<role>" logs in to the platform
    Then "<page>" page should be displayed
    Examples:
      | role       | page      |
      | student    | Dashboard |
      | instructor | Courses   |
      | moderator  | Courses   |
      | admin      | Programs  |
     
