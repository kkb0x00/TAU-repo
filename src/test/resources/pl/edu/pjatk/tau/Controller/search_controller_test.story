Given a search with initial data:
| title | riskType | time | weight |
| foo   | bar      | 10   | 0.1    |
| xyz   | car      | 20   | 0.2    |
| abc   | def      | 30   | 0.3    |
When using regexp like g
Then search should return:
| title | riskType | time | weight |

When using regexp like ^x
Then search should return:
| title | riskType | time | weight |
| xyz   | car      | 20   | 0.2    |

When using regexp like ba|yz
Then search should return:
| title | riskType | time | weight |
| foo   | bar      | 10   | 0.1    |
| xyz   | car      | 20   | 0.2    |

When using regexp like e
Then search should return:
| title | riskType | time | weight |
| abc   | def      | 30   | 0.3    |


