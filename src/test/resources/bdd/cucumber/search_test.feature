Feature: Find reports by regexp
  I want to find reports based on given regexp

  Scenario: Regexp doesnt match anything
    Given initial data
      | id | title | riskType | time | weight |
      |  1 | foo   | bar      |   10 |    0.1 |
      |  2 | xyz   | car      |   20 |    0.2 |
      |  3 | abc   | def      |   30 |    0.3 |
    When using regexp in search like g
    Then it should return a list
      | id | title | riskType | time | weight |

  Scenario: Regexp matches one row by title
    Given initial data
      | id | title | riskType | time | weight |
      |  1 | foo   | bar      |   10 |    0.1 |
      |  2 | xyz   | car      |   20 |    0.2 |
      |  3 | abc   | def      |   30 |    0.3 |
    When using regexp in search like ^x.*
    Then it should return a list
      | id | title | riskType | time | weight |
      |  2 | xyz   | car      |   20 |    0.2 |

  Scenario: Regexp matches one row by riskType
    Given initial data
      | id | title | riskType | time | weight |
      |  1 | foo   | bar      |   10 |    0.1 |
      |  2 | xyz   | car      |   20 |    0.2 |
      |  3 | abc   | def      |   30 |    0.3 |
    When using regexp in search like .*f$
    Then it should return a list
      | id | title | riskType | time | weight |
      |  3 | abc   | def      |   30 |    0.3 |

  Scenario: Regexp matches two rows by title and riskType
    Given initial data
      | id | title | riskType | time | weight |
      |  1 | foo   | bar      |   10 |    0.1 |
      |  2 | xyz   | car      |   20 |    0.2 |
      |  3 | abc   | def      |   30 |    0.3 |
    When using regexp in search like .*(ba|yz).*
    Then it should return a list
      | id | title | riskType | time | weight |
      |  1 | foo   | bar      |   10 |    0.1 |
      |  2 | xyz   | car      |   20 |    0.2 |