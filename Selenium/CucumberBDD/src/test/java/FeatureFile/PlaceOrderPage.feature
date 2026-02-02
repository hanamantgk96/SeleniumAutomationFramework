
@tag
Feature: Order Module
  I want to use this template for my feature file

 Background:
	  Given Launch the browser
    When Login with username and password
    Then User is landing on the dashboard
    And Veryfy the login is successful

  @placeorder
 Scenario Outline: Validate All Test Cases for Order Creation
Given the user navigates to the Place Order page
When pagination is validated for business selection
Then the user selects a random business; if a product does not exist, the user selects another business
And the user selects random products from the list and enters random prices
And the user selects the payment method and delivery user
And the shipping date is validated
And the user selects the billing address and shipping address
And the LPO number is validated
And Click the Review button and validate the total Amount of the Order
    
