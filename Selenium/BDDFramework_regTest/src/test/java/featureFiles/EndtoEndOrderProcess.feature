
Feature: Order Process

Background:
    Given User Launch the Chrome browser
    When User opens URL "https://mktadmin.freshontable.com/"
    And User enters username "UAEADMIN" and password "Admin@4321"
    And Click on login button
    Then Page Title should be "FRESHONTABLE"
    And Close the browser

  Scenario: Place an order
    Given User navigates to the Place Order page
    And User selects a business from the list
    And User selects the products
    And User enters the product quantity
    Then User selects the payment method
    And User selects the shipping date
    And User selects the billing address and shipping address
    Then User enters the LPO number
    And User selects the Outlet (optional) and Delivery User (optional)
    Then The order is created successfully
  
  @Regression  
  ##testing
  Scenario: Lpo creating
    Given User Navigate to the create LPO page
    And Select products which is approved
    And Got to second page
    And Got to third page and select the Delivery Hub
    Then Click submit button and click confirm
    And LPO is successfully created 
    
  #  Scenario: Lpo fulfillment   
  #  Scenario: Stok in Process 
  #  Scenario: Order Compilation
  #  Scenario: Delivery Schedule
  #  Scenario: Order Invoicing
    
    
