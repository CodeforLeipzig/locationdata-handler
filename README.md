# Location data handler

The projects locations-extractor and locations-locator has been extracted from project [lvz-viz](https://github.com/CodeforLeipzig/lvz-viz). 

Idea is to have several micro services: 
 * one for extract named entities from texts that are likely to describe Geo-referenceable places
 * a second one hosting a Nominatim server via [nominatim-docker](https://github.com/mediagis/nominatim-docker), that is configured to only contain data from Leipzig, as provided by [BBBike](http://download.bbbike.org/osm/bbbike/Leipzig/)
 * a third one for getting the actual Geo coordinates for the before extracted entity from the hosted Nominatim server

These two micro services should then be used by
 * [lvz-viz](https://github.com/CodeforLeipzig/lvz-viz) and 
 * [stadtratmonitor](https://github.com/CodeforLeipzig/stadtratmonitor) 

Future improvements can be:
 * manually post processing results for cases where no, wrong or not relevant places have been found in texts

