# Retrospective zu Iteration 1

**Was lief gut?** <br>
Die Implementierung der grundlegenden Logik sowie der verschiedenen Schnittstellen verlief insgesamt reibungslos
und ohne größere Probleme. Besonders die Umsetzung der Kernfunktionen konnte schnell realisiert werden, da die
einzelnen Komponenten klar voneinander getrennt waren. Auch die Kommunikation zwischen den verschiedenen Klassen
funktionierte nach der Anpassung der Struktur zuverlässig. Dadurch konnte die Anwendung Schritt für Schritt
erweitert und getestet werden.

**Was lief nicht so gut?** <br>
Zu Beginn des Projekts musste der gesamte Code nahezu vollständig umgeschrieben werden, da die ursprüngliche
Struktur der Anwendung nicht ausreichend durchdacht war. Die Logik wurde auf mehrere Klassen verteilt, wodurch
schnell Unübersichtlichkeit entstand. Zwar war die Idee eines zentralen Managers bereits vorhanden, allerdings
wurde dieser Ansatz nicht konsequent genug umgesetzt. Dadurch kam es zu Problemen bei der Kommunikation zwischen
den einzelnen Komponenten. <br>
Zusätzlich wurde der Manager anfangs als oberste Instanz gewählt. Während der weiteren Entwicklung stellte sich
jedoch heraus, dass diese Struktur die Implementierung unnötig kompliziert machte. Aus diesem Grund mussten sowohl
der Manager als auch die UI-Klasse teilweise überarbeitet und neu strukturiert werden. Dies führte zu zusätzlichem
Zeitaufwand, half jedoch dabei, die Architektur der Anwendung besser zu organisieren.

**Lessons learned:** <br>
Durch das Projekt wurde deutlich, wie wichtig eine gute Planung der Klassenstruktur bereits vor Beginn der
eigentlichen Implementierung ist. Eine klar definierte Architektur hätte viele spätere Anpassungen und
Umstrukturierungen vermeiden können. Außerdem wurde gelernt, dass Konzepte wie ein zentraler Manager nicht nur
geplant, sondern auch konsequent umgesetzt werden müssen, damit die Anwendung langfristig übersichtlich und wartbar
bleibt.
