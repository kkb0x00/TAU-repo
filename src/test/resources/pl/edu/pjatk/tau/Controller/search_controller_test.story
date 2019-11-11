Given a search functionality with initial data:
| title | riskType | time | weight |
| foo   | bar      | 10   | 0.1    |
| xyz   | car      | 20   | 0.2    |
| abc   | def      | 30   | 0.3    |

When using regexp in search like g
Then it should return a list:
| title | riskType | time | weight |

When using regexp in search like ^x.*
Then it should return a list:
| title | riskType | time | weight |
| xyz   | car      | 20   | 0.2    |

When using regexp in search like .*(ba|yz).*
Then it should return a list:
| title | riskType | time | weight |
| foo   | bar      | 10   | 0.1    |
| xyz   | car      | 20   | 0.2    |

When using regexp in search like .*f$
Then it should return a list:
| title | riskType | time | weight |
| abc   | def      | 30   | 0.3    |


