Feature: Login Test in Orange portal

  Scenario: Test Login Portal
    Given User enters username
    And User enters password
    And The user clicks on Login button
    Then Verify the user is logged in

  Scenario: Validate user exists on System Admin list
    When the user is logged in
    And the user clicks on Admin
    Then User Admin exists on System Admin list

  Scenario: With an Admin User get System Users from User List
    When the Admin user is Logged in
    And the user clicks on Admin
    Then User Admin exists on System Admin list