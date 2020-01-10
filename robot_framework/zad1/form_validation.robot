*** Settings ***
Documentation     Form values validation
Library           SeleniumLibrary

*** Variables ***
${LOGIN URL}      http://automationpractice.com/index.php?controller=authentication&back=my-account
${BROWSER}        Chrome
${CREATE BUTTON XPATH}     xpath=(.//*[normalize-space(text()) and normalize-space(.)='Email address'])[1]/following::span[1]

*** Test Cases ***
Check Required Values
    ${sec} =    Get Time    epoch
    ${email} =  Catenate    SEPARATOR=  ${sec}_testing@test.com
    Open Browser To Login Page
    Input Email    ${email}
    Click Create Account Button
    Create Account Should Be Open
    Click Register Button
    Check Error Displayed
    [Teardown]    Close Browser

*** Keywords ***
Open Browser To Login Page
    Open Browser    ${LOGIN URL}    ${BROWSER}

Input Email
    [Arguments]    ${email}
    Input Text    id:email_create    ${email}

Click Create Account Button
    Click Element    ${CREATE BUTTON XPATH}

Create Account Should Be Open
    Wait Until Element Is Visible   customer_firstname    

Click Register Button
    Click Button    submitAccount

Check Error Displayed
    Sleep   2s
    Element Should Be Visible   class:alert-danger