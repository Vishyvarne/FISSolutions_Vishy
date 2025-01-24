@Test
Feature: Verify eBay
  I want to use this template for my feature file

  @Ebay
  Scenario: verify ebay
    Given Launch Ebay
    And Search for Book
    Then Click on first book in the list and add to cart

  @ValidateGet
  Scenario: Validate the get response from API
    Given Validate the response from Get call from API
