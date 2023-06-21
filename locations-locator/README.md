# locations locator

Getting the actual Geo coordinates for a given string (that could be the name of district, place, street, etc.) by calling the API of a Nominatim server.

Code originates from project [lvz-viz](https://github.com/CodeforLeipzig/lvz-viz). 

## Build and Publish
`gradle build -x test`
`docker-compose build webapp`
`docker tag locations-locator_webapp:latest sannsie/locations-locator:latest`
`docker login`
`docker push sannsie/locations-locator:latest`


## Start up
`docker-compose up -d nominatim`
`gradle run`

## Example
```
curl --location 'http://localhost:8082/api/locations/locate?location=S%C3%BCdplatz'
```
gives
```
[
    "Connewitz",
    "Südplatz"
]
```
