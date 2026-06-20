# Retrospektive zu Iteration 3

**Was lief gut?** <br>
Die Einführung der simulierten Zeit verlief reibungslos und bildete eine solide Grundlage für die
Timer-Funktion. Auch die Überarbeitung der Benutzeroberfläche war erfolgreich: Sie orientiert sich nun
deutlich näher an der gewählten Vorlage und konnte durch die bewusste Begrenzung auf fünf Bedienknöpfe
übersichtlich und minimalistisch gehalten werden.

**Was lief nicht so gut?** <br>
Die Unterteilung der UI in einzelne Funktionen musste nach einem ersten, zu unübersichtlichen Ansatz
komplett neu umgesetzt werden. Beim Löschen von Timern kam es anfangs zu Problemen, da fehlerhafte
Ausgangswerte gesetzt waren, wodurch teilweise falsche Timer entfernt wurden. Zudem konnten blinkende
UI-Komponenten nicht vollständig stabil umgesetzt werden, da sich diese teilweise leicht verschieben.
Außerdem wurde ursprünglich mit .json Dateien begonnen, welche im Verlauf der Iteration aus Gründen der
einfacheren Handhabung durch .properties Dateien ersetzt wurden.

**Lessons learned:** <br>
Es zeigte sich, dass eine Benutzeroberfläche von Beginn an in einzelne Funktionen unterteilt werden sollte,
anstatt sie in einem einzigen Konstruktor aufzubauen, da der Code sonst schnell unübersichtlich und sehr
lang wird und im Nachhinein vollständig überarbeitet werden muss. Außerdem wurde deutlich, dass es sich
lohnt, bei zukünftigen Projekten je nach Anforderungen auch andere UI-Frameworks als Swing in Betracht zu
ziehen, anstatt sich von vornherein auf ein einzelnes Framework festzulegen.