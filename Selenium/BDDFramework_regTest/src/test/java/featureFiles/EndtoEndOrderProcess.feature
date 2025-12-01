@tag
Feature: Order Process

  @EndtoEndProcess
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
    
    Scenario: Lpo creating
    Given User Navigate to the create LPO page
    And Select products which is approved
    And Got to second page
    And Got to third page and select the Delivery Hub
    Then Click submit button and click confirm
    And LPO is successfully created 
    
    Scenario: Lpo fulfillment
    Given enter user name
    When user click buttons
    And enter second name
    And enter country code
    
    Scenario: Stok in Process 
    Scenario: Order Compilation
    Scenario: Delivery Schedule
    Scenario: Order Invoicing
    
