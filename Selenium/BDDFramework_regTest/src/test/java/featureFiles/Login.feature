@sanitytesting

Feature: Login

Scenario: Successful Login with Valid Credentials
    Given User Launch the Chrome browser
    When User opens URL "https://uatadmin.freshontable.com/"
    And User enters username "UAEADMIN" and password "Admin@1234"
    And Click on login button
    Then Page Title should be "FRESHONTABLE"
    And Close the browser

Scenario Outline: Login with Invalid Credentials
    Given User Launch the Chrome browser
    When User opens URL "https://mktadmin.freshontable.com/"
    And User enters username "<username>" and password "<password>"
    And Click on login button
    Then Page Title should be "FRESHONTABLE"
    When User clcik on the Log out link
    Then Page title should be the "Your login page"
    And Close the browser

    Examples: 
        | username | password   |
        | UAEADMIN | Admin@1234 |
        | UAEADMIN | Admin@12345 |
