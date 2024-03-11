
@tag
Feature: Error Validation
  I want ot use this temple for my feature file

  @ErrorValidation
  Scenario Outline: Title of your scenario outline

    Given I landed on Ecommerce Page
    When Logged in with username <name> and password <password>
    Then "Incorrect email or password." message is displayed on ConfirmationPage

    Examples:
      |  name            |  password      |
      | mam96@abv.bg     | Manolov1       |