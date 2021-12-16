
Feature: Todo

  @smoke
  Scenario: Create Todo with valid data
    Given I'm in Screen Create Todo
    When I type title and description todo
    And Click Save Button
    Then Back to List Todo and todo nambah 1

  @smoke
  Scenario: Edit Todo with valid data
    Given I'm in Screen Edit Todo
    When I edit title and description todo
    And Click Save Button
    Then Back to List Todo