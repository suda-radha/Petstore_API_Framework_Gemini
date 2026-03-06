Feature: Petstore API Validations

  @regression
  Scenario: Verify user can find pets by status
    Given I set the base URL for Petstore
    When I search for pets with status "available"
    Then the response status code should be 200
    And the first pet in the list should have status "available"
    
   @regression
   Scenario: Verify fetching a pet by ID
    Given I set the base URL for Petstore
    When I send a GET request to find a pet with ID "1"
    Then the response status code should be 200
    And the pet name should be "doggie"
    And the category name should be "string"
   
   @regression @ui
   Scenario: Hybrid Test - Create pet via API and verify on UI
    Given I set the base URL for Petstore
    When I create a new pet with the following details:
      """
      {
        "id": 9988,
        "name": "Cloudy",
        "status": "available"
      }
      """
    And I open the browser to verify the pet "Cloudy" exists
    Then the browser should show the pet name correctly
   
   #This flow is just for reference it code may not  work becaus teh formats are not right
   @regression
   Scenario: End to End Pet Lifecycle - Create, Verify, and Delete
    Given I set the base URL for Petstore
    When I create a new pet with the following details:
      """
      {
        "id": 556677,
        "name": "Sparky",
        "status": "available"
      }
      """
    Then I verify the pet is created and capture the ID
    And I delete the newly created pet
    Then the response status code should be 200
    
    @regression
    Scenario Outline: Data-Driven Pet Lifecycle with Schema Validation
    Given I set the base URL for Petstore
    When I create a new pet with the following details:
      """
      {
        "id": <id>,
        "name": "<name>",
        "status": "<status>"
      }
      """
    Then the response status code should be 200
    And the response should match the "pet-schema.json" schema
    Then I verify the pet is created and capture the ID
    And I delete the newly created pet

    Examples:
      | id     | name    | status    |
      | 112233 | Bruno   | available |
      
   #Database testing - jdbc
    @focus @regression
    Scenario: API to DB Validation
    Given I set the base URL for Petstore
    When I create a new pet with the following details:
      """
      { "id": 998877, "name": "Rex", "status": "available" }
      """
    Then the response status code should be 200
    And I verify the pet with ID 998877 exists in the database
    
