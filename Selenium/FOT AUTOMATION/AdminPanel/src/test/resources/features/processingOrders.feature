
  
Feature: Create Transform Order
  As an operations user
  I want to create a Product Transform Order
  So that I can convert a primary product into a processed product based on business rules
  Feature: Login functionality
  
 Background: Login functionality
    Given user is on the login page
    When user logs in with valid credentials
    Then user should be logged in successfully



  @UOM1
  Scenario: Create, process UOM conversion order and validate stock transaction

    Given user creates processing order with below data
      | transformationType | UOM Conversion |
      | deliveryHub        | Warehouse4A, Belhasa Compound, Al Quoz Industrial Area 3, Dubai, UAE |
      | subCategory        | Mushroom |
      | product            | White Button Mushroom |
      | seller             | THEMAR AL EMARAT LLC |
      | uom                | Case |
      | requestedQty       | 1.5 |
    Then the processing order ID should be captured
    When user searches the order using created order id
    And processing order should be created in Draft status
    
    And user updates the order status from Draft to Created
    Then order status should be Created
    When user processes the order with below data
      | processUom | Kg |
      | processQty | 2.1 |
    Then order status should be Completed
    When user navigates to stock summary page
    Then search the product and UOM for the variant used in the transformation  
    And select the seller from which the variant was picked for transformation  
    Then select the batch details and verify the deducted quantity and the performed action "Stock-transform-Dispatched"  
    Then search the product and UOM for the processed variant  
    And select the seller to which the processed variant is credited  
    Then select the batch details and verify the added quantity and the performed action "Stock Entry"

    
    @SKU1
    Scenario: Create, process SKU change order and validate stock transactio

     Given user creates processing order with below data
      | transformationType | SKU Change |
      | deliveryHub        | Warehouse4A, Belhasa Compound, Al Quoz Industrial Area 3, Dubai, UAE |
      | subCategory        | Tomato |
      | product            | Candy Tomato |
      | transformedProduct | Mix Candy Tomato |
      | seller             | Trovec General Trading LLC |
      | uom                | Case |
      | requestedQty       | 2.99 |
    Then the processing order ID should be captured
    When user searches the order using created order id
    And processing order should be created in Draft status
    
    And user updates the order status from Draft to Created
    Then order status should be Created
    When user processes the order with below data
      | sourceSeller | EAST EMIRATES TRADING LLC |
      | processQty   | 2.99 |
    Then order status should be Completed
    When user navigates to stock summary page
    Then search the product and UOM for the variant used in the transformation  
    And select the seller from which the variant was picked for transformation  
    Then select the batch details and verify the deducted quantity and the performed action "Stock-transform-Dispatched"  
    Then search the product and UOM for the processed variant  
    And select the seller to which the processed variant is credited  
    Then select the batch details and verify the added quantity and the performed action "Stock Entry"
    
    @ProductTransformation1
  Scenario: Create and process product transformation order
    Given user creates transformation order with below data
      | transformationType | Product Transformation |
      | deliveryHub        | Warehouse4A, Belhasa Compound, Al Quoz Industrial Area 3, Dubai, UAE |
      | subCategory        | Fish |
      | product      | Fresh Sea bream 1-2 kg |

    And user enters processing product details
      | transformedProduct | Fresh Sea Bass Fillet Skin on 160-200gm |
      | seller             | Local Seafood - Cash Purchase |
      | uom                | Kg |
      | requestedQty       | 1.4 |
      | minYield           | 80 |
      | maxYield           | 100 |

    And user enters wastage product details
      | wastageProduct | Fish Head - Bones - Belly - Trimmings |
      | minYield       | 20 |
      | maxYield       | 40 |
      
    When user sends the order for processing
    Then the processing order ID should be captured
    When user searches the order using created order id
    And processing order should be created in Draft status
    
    And user updates the order status from Draft to Created
    Then order status should be Created
    When user processes the order with below data
      | sourceSeller | Blue Waters LLC |
      | processQty   | 1.5 |
      |actualYield   | 1.4 |
   
    Then order status should be Completed
    When user navigates to stock summary page
    Then search the product and UOM for the variant used in the transformation  
    And select the seller from which the variant was picked for transformation  
    Then select the batch details and verify the deducted quantity and the performed action "Stock-transform-Dispatched"  
    Then search the product and UOM for the processed variant  
    And select the seller to which the processed variant is credited  
    Then select the batch details and verify the added quantity and the performed action "Stock Entry"
    Then search the wastage product and UOM used in the transformation
    And  select the seller "FreshonTable DWC LLC" to which the wastage product is credited
    Then select the batch details and verify the added quantity for wastage and the performed action "Stock Entry"
    
    @ProductTransformation2
    Scenario: Achieving Product transformation on changing requested quantity during draft-to-created order transition
     Given user creates transformation order with below data
      | transformationType | Product Transformation |
      | deliveryHub        | Warehouse4A, Belhasa Compound, Al Quoz Industrial Area 3, Dubai, UAE |
      | subCategory        | Fish |
      | product            | Fresh Sea bream 1-2 kg |

    And user enters processing product details
      | transformedProduct | Fresh Sea Bass Fillet Skin on 160-200gm |
      | seller             | Local Seafood - Cash Purchase |
      | uom                | Kg |
      | requestedQty       | 1.4 |
      | minYield           | 80 |
      | maxYield           | 100 |

    And user enters wastage product details
      | wastageProduct | Fish Head - Bones - Belly - Trimmings |
      | minYield       | 20 |
      | maxYield       | 40 |
      When user sends the order for processing
    Then the processing order ID should be captured
    When user searches the order using created order id
    And processing order should be created in Draft status
    And at draft status user changes the requested quantity to "2.5"
    And user updates the order status from Draft to Created
    Then order status should be Created
    When user processes the order with below data
      | sourceSeller | Blue Waters LLC |
      | processQty   | 3 |
      |actualYield   | 2.4 |
    When user navigates to stock summary page
    Then search the product and UOM for the variant used in the transformation  
    And select the seller from which the variant was picked for transformation  
    Then select the batch details and verify the deducted quantity and the performed action "Stock-transform-Dispatched"  
    Then search the product and UOM for the processed variant  
    And select the seller to which the processed variant is credited  
    Then select the batch details and verify that stock entry is created for the updated requested quantity with the action "Stock Entry".
    And  select the seller "FreshonTable DWC LLC" to which the wastage product is credited
    Then select the batch details and verify the added quantity for wastage and the performed action "Stock Entry"
    
    @productTransformation3
    Scenario: Validate Product transformation on seller change during draft-to-created order transition
     Given user creates transformation order with below data
      | transformationType | Product Transformation |
      | deliveryHub        | Warehouse4A, Belhasa Compound, Al Quoz Industrial Area 3, Dubai, UAE |
      | subCategory        | Fish |
      | product            | Fresh Sea bream 1-2 kg |

    And user enters processing product details
      | transformedProduct | Fresh Sea Bass Fillet Skin on 160-200gm |
      | seller             | Local Seafood - Cash Purchase |
      | uom                | Kg |
      | requestedQty       | 1.4 |
      | minYield           | 80 |
      | maxYield           | 100 |

    And user enters wastage product details
      | wastageProduct | Fish Head - Bones - Belly - Trimmings |
      | minYield       | 20 |
      | maxYield       | 40 |
      When user sends the order for processing
    Then the processing order ID should be captured
    When user searches the order using created order id
    And processing order should be created in Draft status
    And at draft status user changes the seller to "AHMED FISH TRADING"
    And User selects the UOM as "Kg"
    And user updates the order status from Draft to Created
    Then order status should be Created
    When user processes the order with below data
      | sourceSeller | Blue Waters LLC |
      | processQty   | 1.5 |
      |actualYield   | 1.4 |
   
    Then order status should be Completed
    When user navigates to stock summary page
    Then search the product and UOM for the variant used in the transformation  
    And select the seller from which the variant was picked for transformation  
    Then select the batch details and verify the deducted quantity and the performed action "Stock-transform-Dispatched"  
    Then search the product and UOM for the processed variant  
    And select the seller used while converting the order from Draft to Created.  
    Then select the batch details and verify the added quantity and the performed action "Stock Entry"
    Then search the wastage product and UOM used in the transformation
    And  select the seller "FreshonTable DWC LLC" to which the wastage product is credited
    Then select the batch details and verify the added quantity for wastage and the performed action "Stock Entry"
    
    @SKU2
    Scenario: Achieving the SKU change for same seller with different product products 

     Given user creates processing order with below data
      | transformationType | SKU Change |
      | deliveryHub        | Warehouse4A, Belhasa Compound, Al Quoz Industrial Area 3, Dubai, UAE |
      | subCategory        | Tomato |
      | product            | Candy Tomato |
      | transformedProduct | Mix Candy Tomato |
      | seller             | Green Agri Foodstuff Trading LLC|
      | uom                | Kg |
      | requestedQty       | 1.99 |
    Then the processing order ID should be captured
    When user searches the order using created order id
    And processing order should be created in Draft status
    
    And user updates the order status from Draft to Created
    Then order status should be Created
    When user processes the order with below data
      | sourceSeller | Green Agri Foodstuff Trading LLC |
      | processQty   | 1.99 |
    Then order status should be Completed
    When user navigates to stock summary page
      
    Then search the product and UOM for the variant used in the transformation  
    And select the seller from which the variant was picked for transformation  
    Then select the batch details and verify the deducted quantity and the performed action "Stock-transform-Dispatched"  
    Then search the product and UOM for the processed variant  
    And select the seller to which the processed variant is credited  
    Then select the batch details and verify the added quantity and the performed action "Stock Entry"
     
    
   @UOM2

    Scenario: Acheiving UOM conversion on UOM change during draft-to-created order transition
       Given user creates processing order with below data
      | transformationType | UOM Conversion |
      | deliveryHub        | Warehouse4A, Belhasa Compound, Al Quoz Industrial Area 3, Dubai, UAE |
      | subCategory        | Mushroom |
      | product            | White Button Mushroom |
      | seller             | THEMAR AL EMARAT LLC |
      | uom                | Case |
      | requestedQty       | 1.5 |
    Then the processing order ID should be captured
    When user searches the order using created order id
    And processing order should be created in Draft status
    And at draft status user changes the uom to "Lbs"
    
    And user updates the order status from Draft to Created
    Then order status should be Created
    When user processes the order with below data
      | processUom | Kg |
      | processQty | 2.1 |
    Then order status should be Completed
    When user navigates to stock summary page
    Then search the product and UOM for the variant used in the transformation  
    And select the seller from which the variant was picked for transformation  
    Then select the batch details and verify the deducted quantity and the performed action "Stock-transform-Dispatched"  
    Then search the product and select the updated UOM used while converting the order from Draft to Created.  
    And select the seller to which the processed variant is credited  
    Then select the batch details and verify the added quantity and the performed action "Stock Entry"
    
     @UOM3

    Scenario: Validate UOM conversion on seller change during draft-to-created order transition
       Given user creates processing order with below data
      | transformationType | UOM Conversion |
      | deliveryHub        | Warehouse4A, Belhasa Compound, Al Quoz Industrial Area 3, Dubai, UAE |
      | subCategory        | Mushroom |
      | product            | White Button Mushroom |
      | seller             | THEMAR AL EMARAT LLC |
      | uom                | Case |
      | requestedQty       | 1.5 |
    Then the processing order ID should be captured
    When user searches the order using created order id
    And processing order should be created in Draft status
    And at draft status user changes the seller to "CAVIAR CLASSIC LAND SEAFOOD CANNING"
    And User selects the UOM as "Case"
    
    And user updates the order status from Draft to Created
    Then order status should be Created
    When user processes the order with below data
      | processUom | Kg |
      | processQty | 2.1 |
    Then order status should be Completed
    When user navigates to stock summary page
    Then search the product and UOM for the variant used in the transformation  
    And  select the seller used while converting the order from Draft to Created.  
    Then select the batch details and verify the deducted quantity and the performed action "Stock-transform-Dispatched"  
    Then search the product and UOM for the processed variant  
    And select the seller used while converting the order from Draft to Created.  
    Then select the batch details and verify the added quantity and the performed action "Stock Entry"

    
     
@UOM4
    Scenario: Validate UOM conversion on changing requested quantity during draft-to-created order transition
       Given user creates processing order with below data
      | transformationType | UOM Conversion |
      | deliveryHub        | Warehouse4A, Belhasa Compound, Al Quoz Industrial Area 3, Dubai, UAE |
      | subCategory        | Mushroom |
      | product            | White Button Mushroom |
      | seller             | THEMAR AL EMARAT LLC |
      | uom                | Case |
      | requestedQty       | 1.5 |
    Then the processing order ID should be captured
    When user searches the order using created order id
    And processing order should be created in Draft status
    And at draft status user changes the requested quantity to "2.5"
    
    And user updates the order status from Draft to Created
    Then order status should be Created
    When user processes the order with below data
      | processUom | Kg |
      | processQty | 2.1 |
    Then order status should be Completed
    When user navigates to stock summary page
    Then search the product and UOM for the variant used in the transformation  
    And select the seller from which the variant was picked for transformation  
    Then select the batch details and verify the deducted quantity and the performed action "Stock-transform-Dispatched"  
    Then search the product and UOM for the processed variant  
    And select the seller to which the processed variant is credited  
    Then select the batch details and verify that stock entry is created for the updated requested quantity with the action "Stock Entry".
    
    
    @UOM5
    Scenario: System should not allow to craete the UOM conversion order with seller which has only one UOM for selected product
     Given User enters the following details and select the select which has only one UOM for the selected product
      | transformationType | UOM Conversion |
      | deliveryHub        | Warehouse4A, Belhasa Compound, Al Quoz Industrial Area 3, Dubai, UAE |
      | subCategory        | Mushroom |
      | product            | White Button Mushroom |
      | seller             | Berrymount Vegetable and fruit Trading LLC |
      | uom                | Kg |
      | requestedQty       | 1.5 | 
      Then click on send for process button
      Then system should display error message "Uom not available to convert!" 
      
       @SKU3
    Scenario: Achieving the SKU change for same product with different seller 

     Given user creates processing order with below data
      | transformationType | SKU Change |
      | deliveryHub        | Warehouse4A, Belhasa Compound, Al Quoz Industrial Area 3, Dubai, UAE |
      | subCategory        | Tomato |
      | product            | Mix Candy Tomato |
      | transformedProduct | Mix Candy Tomato |
      | seller             | Trovec General Trading LLC|
      | uom                | Kg |
      | requestedQty       | 1.99 |
    Then the processing order ID should be captured
    When user searches the order using created order id
    And processing order should be created in Draft status
    
    And user updates the order status from Draft to Created
    Then order status should be Created
    When user processes the order with below data
      | sourceSeller | Green Agri Foodstuff Trading LLC |
      | processQty   | 1.99 |
    Then order status should be Completed
    When user navigates to stock summary page
    Then search the product and UOM for the variant used in the transformation  
    And select the seller from which the variant was picked for transformation  
    Then select the batch details and verify the deducted quantity and the performed action "Stock-transform-Dispatched"  
    Then search the product and UOM for the processed variant  
    And select the seller to which the processed variant is credited  
    Then select the batch details and verify the added quantity and the performed action "Stock Entry"  
    
    @SKU4
 Scenario: Validate SKU change on changing requested quantity during draft-to-created order transition
  Given user creates processing order with below data
      | transformationType | SKU Change |
      | deliveryHub        | Warehouse4A, Belhasa Compound, Al Quoz Industrial Area 3, Dubai, UAE |
      | subCategory        | Tomato |
      | product            | Mix Candy Tomato |
      | transformedProduct | Candy Tomato |
      | seller             | EAST EMIRATES TRADING LLC |
      | uom                | Case |
      | requestedQty       | 2.99 |
    Then the processing order ID should be captured
    When user searches the order using created order id
    And processing order should be created in Draft status
    And at draft status user changes the requested quantity to "2.5"
    
    And user updates the order status from Draft to Created
    Then order status should be Created
    When user processes the order with below data
      | sourceSeller | Trovec General Trading LLC |
      | processQty   | 2.99 |
    Then order status should be Completed
    When user navigates to stock summary page
    Then search the product and UOM for the variant used in the transformation  
    And select the seller from which the variant was picked for transformation  
    Then select the batch details and verify the deducted quantity and the performed action "Stock-transform-Dispatched"  
    Then search the product and UOM for the processed variant  
    And select the seller to which the processed variant is credited  
    Then select the batch details and verify that stock entry is created for the updated requested quantity with the action "Stock Entry".
    
    @SKU5
    Scenario: Validate SKU chnage on seller change during draft-to-created order transition
     Given user creates processing order with below data
      | transformationType | SKU Change |
      | deliveryHub        | Warehouse4A, Belhasa Compound, Al Quoz Industrial Area 3, Dubai, UAE |
      | subCategory        | Tomato |
      | product            | Candy Tomato |
      | transformedProduct | Mix Candy Tomato |
      | seller             | Trovec General Trading LLC |
      | uom                | Case |
      | requestedQty       | 2.99 |
    Then the processing order ID should be captured
    When user searches the order using created order id
    And processing order should be created in Draft status
    And at draft status user changes the seller to "FINE FRESH FARM FOODSTUFF TRADING L.L.C"
    And User selects the UOM as "Case"
    And user updates the order status from Draft to Created
    Then order status should be Created
    When user processes the order with below data
     | sourceSeller | EAST EMIRATES TRADING LLC |
     | processQty   | 2.99 |
    Then order status should be Completed
    When user navigates to stock summary page
    Then search the product and UOM for the variant used in the transformation  
    And select the seller from which the variant was picked for transformation  
    Then select the batch details and verify the deducted quantity and the performed action "Stock-transform-Dispatched"  
    Then search the product and UOM for the processed variant  
    And select the seller used while converting the order from Draft to Created.  
    Then select the batch details and verify the added quantity and the performed action "Stock Entry"
    
      
      

     
    
    
    




