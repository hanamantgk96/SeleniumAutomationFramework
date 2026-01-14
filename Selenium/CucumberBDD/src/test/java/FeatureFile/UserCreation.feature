
@tag
Feature: User Creation


  Background:
	  Given Launch the browser
    When Login with username and password
    Then User is landing on the dashboard
    And Veryfy the login is successful
    
    @BusinessAccount
    Scenario: creating a Business Account
    Given The user clicks on the Administration grid
    And Clicks on the "Create Organization" option
    Then The user selects an organization type from the dropdown
    And Selects "Business" as the organization type
    And Fills in all mandatory fields for Organization Details
    Then Fills in all mandatory fields for Organization Admin Details
    And Fills in all necessary address details
    And Enables all communication channels
    And Uploads all mandatory documents
    And Provides additional required information
    
 #   @SellerAccount
 #   Scenario: creating a Supplier Account
 #   Given Navigate the organisation page
 #   And Clicks on the "Create Seller" option
    
    
    
