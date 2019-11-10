Given a searchController
When set phrase to ""
Then search should return []

When set phrase to "rce"
Then search should return ["test for imagetragick"]
!--| search poprzez risk jedna rzecz (risk=rce)

When set phrase to "ato"
Then search should return ["account takeover poprzez wyciek referera z tokenem", "takeover csrf zmiana meila"]
!--| search poprzez risk dwie rzeczy, ten sam risk (risk=ato)

When set phrase to "xss"
Then search should return ["stored cross-site-scripting in foo", "cross site file inclusion at xxx"]
!--| search poprzez risk dwie rzeczy, rozny risk (risk=xss, risk=xssi)

When set phrase to "xml"
Then search should return ["external xml entities in abc"]
!--| search poprzez tytul sesji jedna rzecz (risk=xxe)

When set phrase to "takeover"
Then search should return ["account takeover poprzez wyciek referera z tokenem", "takeover csrf zmiana meila"]
!--| search poprzez tytul sesji dwie rzeczy ten sam risk (risk=ato)

When set phrase to "file inclusion"
Then search should return ["remote file inclusion at xyz", "local file inclusion at abc"] #search poprzez title 2 rzeczy
!--| search poprzez tytul sesji dwie rzeczy, rozny risk (risk=lfi, risk=rfi)

When set phrase to "sql"
Then search should return ["database query injection at nnn", "plik z haslem do mysql"]
!--| search poprzez risk i tytul, rozny risk (risk=sql injection, risk=information disclosure)
