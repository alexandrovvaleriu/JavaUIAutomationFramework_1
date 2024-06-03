Feature: Registration flow

  @Smoke
  Scenario: Access the account page after successful registration
    Given The Home Page is accessed
    And RegisterOption is selected from the header drop-down
    And the register form is populated with valid data
    And Privacy toggle is enabled
    When the Continue button is clicked
    Then the Page url contains the "success" keyword

  @Obsolete
  Scenario: User remains on register page when registering without accepting the privacy rules
    Given The Home Page is accessed
    And RegisterOption is selected from the header drop-down
    And the register form is populated with valid data
    When the Continue button is clicked
    Then the Page url contains the "route=account/register&language=en-gb" keyword

  @Regression
  Scenario: User remains on register page when registering without completing the mandatory fields
    Given The Home Page is accessed
    And RegisterOption is selected from the header drop-down
    And Privacy toggle is enabled
    When the Continue button is clicked
    Then the Page url contains the "route=account/register&language=en-gb" keyword