# locations extractor

## Build and Publish
`gradle build -x test`
`docker-compose build webapp`
`docker tag locations-extractor_webapp:latest sannsie/locations-extractor:latest`
`docker login`
`docker push sannsie/locations-extractor:latest`

## Start up
`gradle run`

## Example
```
curl --location 'http://localhost:8081/api/address/extract' \
--header 'Content-Type: text/plain' \
--data 'Die Karl-Liebknecht-Straße (umgangssprachlich Karli) ist eine bedeutende Straße in Leipzig, die sich vom südlichen Stadtzentrum nach Süden zieht. Die etwa 2,5 Kilometer lange Straße erstreckt sich als Fortsetzung des Peterssteinwegs vom Abzweig der Emilienstraße im Stadtgebiet Zentrum-Süd über die Südvorstadt bis zum Connewitzer Kreuz in Connewitz.

Die Karl-Liebknecht-Straße entstand während des Baubooms Ende des 19. Jahrhunderts beziehungsweise um 1880. Davor befand sich hier ein weniger stark befestigter Weg, der dem Verlauf der heutigen Kochstraße folgte. Der Weg verband die Leipziger Innenstadt mit Alt-Connewitz.

Der nördliche Straßenabschnitt bis zum Südplatz bestand schon im Mittelalter und war Teil der Via Imperii. Bis etwa Mitte des 19. Jahrhunderts hieß dieser Abschnitt Connewitzer Chaussee[1] und von 1839 mit dem Ausbau der Südvorstadt bis 1933 hieß er Zeitzer Straße.[2] Bis 1856 stand an seinem südlichen Ende das „äußere Peterstor“ („Zeitzer Tor“). Der sich anschließende Straßenabschnitt stadtauswärts ab Schenkendorfstraße[1] hieß von 1874 bis 1933 Südstraße.'
```
gives
```
[
    "Connewitz",
    "Südplatz"
]
```