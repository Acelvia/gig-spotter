/// <reference path="./google.maps.d.ts" />
import {Http, HTTP_PROVIDERS} from 'angular2/http';
import {Component, View, bootstrap} from 'angular2/angular2';
import {CORE_DIRECTIVES, FORM_DIRECTIVES} from 'angular2/angular2';

@Component({
    selector: 'map',
    templateUrl: 'templates/map.html'
})
class GoogleMap {

    map;
    markers = [];
    latitude = 60.1733343;
    longitude = 24.93227;

    constructor(public http: Http) {
        var self = this;

        this.map = new google.maps.Map(document.getElementById('mapCanvas'), {
            // Center on Helsinki
            center: new google.maps.LatLng(this.latitude, this.longitude),
            zoom: 13
        });

        // Update the map when the user is not interacting with it,
        // including when the map has finished loading.
        google.maps.event.addListener(this.map, 'idle', function() {
            var mapBounds = self.map.getBounds();
            var neLat = mapBounds.getNorthEast().lat(),
                neLng = mapBounds.getNorthEast().lng(),
                swLat = mapBounds.getSouthWest().lat(),
                swLng = mapBounds.getSouthWest().lng();

            //http.get('/accommodation?latitude=' + self.latitude + '&longitude=' + self.longitude)
            http.get('/accommodation?neLat=' + neLat + '&neLng=' + neLng + '&swLat=' + swLat + '&swLng=' + swLng)
                .map(result => result.json())
                .subscribe(accommodations => self.renderAccommodations(accommodations));
        });
    }

    renderAccommodations(accommodations) {
        var self = this;
        this.deleteMarkers();
        accommodations.result.map(function(result) {
            return new google.maps.LatLng(result.latLng[0], result.latLng[1]);
        }).forEach(function(latLng) {
            self.markers.push(new google.maps.Marker({
                position: latLng,
                map: self.map
            }));
        });
    }

    deleteMarkers() {
        for (var i = 0; i < this.markers.length; i++) {
            this.markers[i].setMap(null);
        }
        this.markers = [];
    }
}

bootstrap(GoogleMap, [HTTP_PROVIDERS]);