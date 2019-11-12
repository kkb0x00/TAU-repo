Given a search functionality with initial data:
| id | title | riskType | time | weight |
| 1  | foo   | bar      | 10   | 0.1    |
| 2  | xyz   | car      | 20   | 0.2    |
| 3  | abc   | def      | 30   | 0.3    |

When using regexp in search like g
Then it should return a list:
| id | title | riskType | time | weight |

When using regexp in search like ^x.*
Then it should return a list:
| id | title | riskType | time | weight |
| 2  | xyz   | car      | 20   | 0.2    |

When using regexp in search like .*(ba|yz).*
Then it should return a list:
| id | title | riskType | time | weight |
| 1  | foo   | bar      | 10   | 0.1    |
| 2  | xyz   | car      | 20   | 0.2    |

When using regexp in search like .*f$
Then it should return a list:
| id | title | riskType | time | weight |
| 3  | abc   | def      | 30   | 0.3    |


