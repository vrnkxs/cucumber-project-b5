Feature: Docuport Sample Scenario

  @sampleDocuport
  Scenario: Practice click buttons on different pages as a client
    Given user is on Docuport login page
    When user inserts "b1g2_client@gmail.com" to "username" field on "Login" page
    And user inserts "Group2" to "password" field on "Login" page
    And user clicks "login" button on "Login" page
    And user clicks "continue" button on "Choose account" page
    And user should be able to see the home page for client
    And user clicks "My uploads" button on "Left navigate" page
    And user clicks "Invitations" button on "Left navigate" page
    And user clicks "1099 Form" button on "Left navigate" page
    And user clicks "Reconciliations" button on "Left navigate" page
    And user clicks "Home" button on "Left navigate" page
    And user clicks "Received docs" button on "Left navigate" page
    And user clicks "Search" button on "header"
    And user inserts "tax document" to "document name" field on "Received docs" page
    And user selects option on "tags" field on "Received docs" page
      | license    |
      | tax return |
    And user selects "30" on "upload date" field on "Received docs" page
    And user selects "advisor advisor" on "Uploaded by" field on "Received docs" page
    And user clicks "Search" button on "footer"
    And user should be able to see the message "Your search returned no results. Make sure you search properly"
    And user clicks "My uploads" button on "Left Navigate" page
    And user clicks "Upload Documents" button on "My uploads" page
#    if sending to input from html works u do not need below step
    And user clicks "Upload file" button on "My uploads" page
    And user attaches the document and enters the required information
    And user uploads a document

    And user clicks "Invitations" button on "Left Navigate" page
    And user clicks "Search" button on "header"
    And user inserts "loop" to "recipient" field on "Invitations" page
    And user clicks sent button
    And user clicks "Search" button on "footer"
    And user should be able to see the message "Your search returned no results. Make sure you search properly"