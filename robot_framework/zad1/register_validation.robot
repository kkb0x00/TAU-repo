*** Settings ***
Documentation     Registration validation
Library           SeleniumLibrary

*** Variables ***
${LOGIN URL}      http://automationpractice.com/index.php?controller=authentication&back=my-account
${BROWSER}        Chrome
${CREATE BUTTON XPATH}     xpath=(.//*[normalize-space(text()) and normalize-space(.)='Email address'])[1]/following::span[1]  
${MY ACCOUNT URL}         http://automationpractice.com/index.php?controller=my-account

*** Test Cases ***
Successful Registration
    Open Browser To Login Page
    Input Test Email
    Click Create Account Button
    Create Account Should Be Open
    Fill Required Data
    Click Register Button
    My Account Should Be Open
    [Teardown]    Close Browser

*** Keywords ***
Open Browser To Login Page
    Open Browser    ${LOGIN URL}    ${BROWSER}

Input Test Email
    ${sec} =   Get Time    epoch
    ${email} =   Catenate    SEPARATOR=  ${sec}_testing@test.com
    Input Text    id:email_create    ${email}

Click Create Account Button
    Click Element    ${CREATE BUTTON XPATH}

Create Account Should Be Open
    Wait Until Element Is Visible   customer_firstname   

Fill Required Data
    Input Text    id:customer_firstname    Firstname
    Input Text    id:customer_lastname    Lastname
    Input Text    id:passwd    test_passwd
    Input Text    id:address1    Address of nowhere
    Input Text    id:city    The City Of Testing
    Input Text    id:postcode    12345
    Input Text    id:phone_mobile    123123123
    Select From List By Value   id_state    1

My Account Should Be Open
    Sleep   2s
    Location Should Be  ${MY ACCOUNT URL}

Click Register Button
    Click Button    submitAccount