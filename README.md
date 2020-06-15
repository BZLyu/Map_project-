**Programm ausführen.:(in der Konsole )**

1. Benutzen **cd** + Path der Routingplan. (pom.xml in der Routingplan)
2. Einfügen: mvn assembly:assembly
3. Einfügen: java -Xmx8g -jar ./target/Routingplan-jar-with-dependencies.jar

Auführen direkt Graph laden: java -Xmx8g -jar ./target/Routingplan-jar-with-dependencies.jar {pathTo*.fmi}

Auführen direkt Graph laden und Que berechnen: java -Xmx8g -jar ./target/outingplan-jar-with-dependencies.jar r {pathTo*.fmi} {pathTo*.que}

(*.sol wird unter gleichen Pfad wie *.que gespeichert)



**Main Menu:**

1. Geben sie die Dateipfad von Map( .fmi) ein.
2. In Webbrowser eingeben: http://localhost:8080/Aufgabe1.html um Aufgabe 1 zu anschauen.
3. In Webbrowser eingeben: http://localhost:8080 um Aufgabe 2 zu anschauen.
   1. Klicken Map und Button "Set start", um Startpunkt zu wählen. 
   2. Warten,  bis zeigen "Start point has been set." (Gezeichnete Punkt in Map ist nächste Punkt der gewählte Punkt. )
   3. Klicken Map und Button "Set destination", um Zielpunkt zu wählen.
   4. Warten,  bis zeigen "Destination has been set." (Gezeichnete Punkt in Map ist nächste Punkt der gewählte Punkt. )
   5. Klicken Button "Routingplan" um Routenplanung zu suchen. (Ganz unten links)
   6. Klicken Button "CleanMap" um Map zu sauber machen. Falls Sie eine Neue Start Punkt oder Ziel Punkt auswählen möchten, bitte auch Button"cleanMap" klicken.



1. ~~Wählen 1 : (Lade Graphen)~~
   1. ~~fmi. Dateipfad eingeben~~ 
   2. ~~Graph geladen wird:~~
      1. ~~Loadzeit;~~
      2. ~~die Anzahl der Knoten und Kanten~~
   3. ~~Zurück zu Main Menu~~
2. ~~Wählen 2:(OneToAll):~~
   1. ~~StartKnoten eingeben;~~
   2. ~~Von Startknoten zu alle andere Knoten gehen:~~
      1. ~~Suchenszeit;~~
      2. ~~Die Kosten;~~
   3. ~~Zurück zu Main Menu~~
3. ~~Wählen 3: (OnToOne)~~
   1. ~~StartKnoten eingeben;~~
   2. ~~ZielKnoten eingeben;~~
   3. ~~Die Suchenszeit und Kosten von StartKnoten zu Zielknoten~~
4. ~~Wählen 4:(Berechne.que)~~
   1. ~~.que DateiPfad eingeben.~~
   2. ~~Eine .sol Datei in der gleiche Pfad ausgeben.~~
   3. ~~Dauer Zeit~~
   4. ~~Zurück zu Main Menu~~
5. ~~Wählen 5 :(FindNext Knoten Simple)~~

   1. ~~Lat und Lon eingeben.~~
   2. ~~Nächste Knoten ausgeben.~~
6. ~~Wählen 6(FindNext Knoten kdTree)// manche Punkte kann in 1ms , manche nicht.~~ 
   1. ~~Lat und Lon eingeben.~~
   2. ~~Nächste Knoten ausgeben.~~
8. ~~Wählen 8:(Programm ended)~~

