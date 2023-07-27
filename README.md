
Funktionen im Main Programm:

dateiLesen : Die Produkte aus der .csv Datei werden gelesen

einschreibenInDatei : Produkte werden in der .csv Datei gespeichert

sortimentAusdrucken : Alle Daten im Regal werden ausgedruckt

Funktionen für das SQL Modul:
mitDatabaseVerbinden : Die Verbindung mit der Postgresql Tabelle wird hergestellt

datenSQLAusdrucken : Daten aus der Postgresql Tabelle ausdrucken

weinproduktEinfügen : Weinprodukt wird in Postgresql Tabelle eingefügt

produktEinfügen : allgemeines Produkt wird in Postgresql Tabelle eingefügt

tabelleErstellen : erstellt eine Tabelle in Postgresql

Funktionen in der Produkt,GenericProdukt ,Wein ,Käse Klasse:

okayFürRegal : 		 Function die prüft ob man das Produkt in das Regal hinzufügen kann


ausRegalAusräumen : 	 Function die prüft ob das Produkt aus dem Regal entfernt werden muss


täglichePreisänderung :  	 Function die den Preis ändert


täglicheQualitätsänderung : 	 Function die die Qualitat ändert


