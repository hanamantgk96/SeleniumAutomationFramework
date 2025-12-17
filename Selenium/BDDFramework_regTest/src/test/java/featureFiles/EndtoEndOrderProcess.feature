@Regression
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
    And User selects the products and enters product quantity
    Then User selects the payment method
    And User selects the shipping date
    And User selects the billing address and shipping address
    Then User enters the LPO number
    And User selects the Outlet (optional) and Delivery User (optional)
    Then The order is created successfully
  
 
  #Scenario: Lpo creating
    #Given User Navigate to the create LPO page
    #And Select products which is approved
    #And Got to second page
    #And Got to third page and select the Delivery Hub
    #Then Click submit button and click confirm
    #And LPO is successfully created 
    
    
    @compilation  
  Scenario: Order Compilation
  Given User navigates to the Order Compilation page
  When the user reserves the stock
  Then the stock is reserved successfully
  And close the browser
  
 @delivery
Scenario: Create a delivery schedule
  Given the user navigates to the Delivery module
  Then the user clicks on the "Create Delivery Schedule" button
  And the user filters the order by Order ID and updates the driver
  Then the user saves the changes and performs drag-and-drop
  And the user selects the order and proceeds to the next step
  Then the user selects the departure time and vehicle
  And the user verifies that all selected orders are displayed and clicks Submit
  Then the delivery schedule is created successfully
 
  
    
  #  Scenario: Lpo fulfillment   
  #  Scenario: Stok in Process 
  #  Scenario: Order Compilation
  #  Scenario: Delivery Schedule
  #  Scenario: Order Invoicing
    
    
