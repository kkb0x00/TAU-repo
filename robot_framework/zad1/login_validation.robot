*** Settings ***
Documentation     Login validation
Library           SeleniumLibrary

*** Variables ***
${LOGIN URL}      http://automationpractice.com/index.php?controller=authentication&back=my-account
${BROWSER}        Chrome
${CREATE BUTTON XPATH}     xpath=(.//*[normalize-space(text()) and normalize-space(.)='Email address'])[1]/following::span[1]  
${CREATE ACCOUNT ERROR}    //div[@id='create_account_error']/ol/li
${MY ACCOUNT URL}         http://automationpractice.com/index.php?controller=my-account

*** Test Cases ***
Successful Login
    ${sec} =    Get Time    epoch
    ${EMAIL} =  Catenate    SEPARATOR=  ${sec}_testing@test.com

    Open Browser To Login Page
    Input Email     ${EMAIL}
    Click Create Account Button
    Create Account Should Be Open
    Fill Required Data
    Click Register Button
    My Account Should Be Open
    Delete All Cookies
    Reload Page
    Input Credentials For Email     ${EMAIL}
    Click Sign In Button
    My Account Should Be Open
    [Teardown]    Close Browser

Unsuccessful Login With Correct Email and Wrong Password
    ${sec} =    Get Time    epoch
    ${EMAIL} =  Catenate    SEPARATOR=  ${sec}_testing@test.com

    Open Browser To Login Page
    Input Email     ${EMAIL}
    Click Create Account Button
    Create Account Should Be Open
    Fill Required Data
    Click Register Button
    My Account Should Be Open
    Delete All Cookies
    Reload Page
    Input Credentials With Wrong Password For Email     ${EMAIL}
    Click Sign In Button
    Login Error Should Display
    [Teardown]    Close Browser

Successful Login On Mobile Size
    ${sec} =    Get Time    epoch
    ${EMAIL} =  Catenate    SEPARATOR=  ${sec}_testing@test.com

    Open Browser To Login Page
    Set Window Size     80     800
    Reload Page
    
    Input Email     ${EMAIL}
    Click Create Account Button
    Create Account Should Be Open
    Fill Required Data
    Click Register Button
    My Account Should Be Open
    Delete All Cookies
    Reload Page
    Input Credentials For Email     ${EMAIL}
    Click Sign In Button
    My Account Should Be Open
    [Teardown]    Close Browser


*** Keywords ***
Open Browser To Login Page
    Open Browser    ${LOGIN URL}    ${BROWSER}

Input Email
    [Arguments]    ${email}
    Input Text    id:email_create    ${email}

Input Credentials For Email
    [Arguments]    ${email}
    Input Text    id:email    ${email}
    Input Text    id:passwd    test_passwd

Input Credentials With Wrong Password For Email
    [Arguments]    ${email}
    Input Text    id:email    ${email}
    Input Text    id:passwd    wrong_passwd

Click Sign In Button
    Click Button    SubmitLogin

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

Create Account Error Should Be Displayed
    Sleep   2s
    Element Should Be Visible  ${CREATE ACCOUNT ERROR}

Click Register Button
    Click Button    submitAccount

Login Error Should Display
    Sleep   2s
    Element Should Be Visible   class:alert-danger