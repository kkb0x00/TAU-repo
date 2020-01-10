*** Settings ***
Library    Process
Suite Teardown    Terminate All Processes    kill=True

Test Template     Should return correct quadrangle


*** Test Cases ***      a       b       c       d       expected
Niepoprawne dane #1     x       x       x       x       nierozpoznano
Niepoprawne dane #2     -1      -1      -1      -1      nierozpoznano
Niepoprawne dane #3     0       0       0       0       nierozpoznano

Kwadrat                 1       1       1       1       Kwadrat

Prostokąt a=b c=d       2       2       1       1       prostokąt
Prostokąt a=d b=c       1       2       2       1       prostokąt

Czworobok #1            10      5       6       1       czworobok
Czworobok #2            10      5       5       1       Czworobok

Nieprawidłowy #1        10      5       4       1       nierozpoznano
Nieprawidłowy #2        5       10      4       1       nierozpoznano
Nieprawidłowy #3        4       5       10      1       nierozpoznano
Nieprawidłowy #4        1       5       4       10      nierozpoznano

Jeden argument          1   ${EMPTY}    ${EMPTY}    ${EMPTY}    nierozpoznano
Dwa argumenty           1       1       ${EMPTY}    ${EMPTY}    nierozpoznano

*** Keywords ***
Should return correct quadrangle
    [Arguments]     ${a}    ${b}   ${c}     ${d}   ${expected}
    ${result} =     Run Process    java    GeometricFigure     ${a}   ${b}   ${c}  ${d}
    Should Be Equal As Strings    ${result.stdout}    ${expected}