/// <reference path="./typings/google.maps.d.ts" />
/// <reference path="./typings/markerclustererplus.d.ts" />
import {Http} from 'angular2/http';
import {Component} from 'angular2/angular2';

@Component({
    selector: 'map',
    templateUrl: 'templates/map.html'
})
export class GoogleMap {

    markerClusterer;
    latitude = 60.1733343;
    longitude = 24.93227;

    constructor(http: Http) {
        var self = this;

        var map = new google.maps.Map(document.getElementById('mapCanvas'), {
            // Center on Helsinki
            center: new google.maps.LatLng(this.latitude, this.longitude),
            zoom: 13
        });
        this.markerClusterer = new MarkerClusterer(map, [], {
            gridSize: 200,
            zoomOnClick: false,
            averageCenter: true,
            minimumClusterSize: 1,
            enableRetinaIcons: true,
        });

        // Update the map when the user is not interacting with it,
        // including when the map has finished loading.
        google.maps.event.addListener(map, 'idle', function() {
            var mapBounds = self.markerClusterer.getMap().getBounds();
            var neLat = mapBounds.getNorthEast().lat(),
                neLng = mapBounds.getNorthEast().lng(),
                swLat = mapBounds.getSouthWest().lat(),
                swLng = mapBounds.getSouthWest().lng();

            http.get('/accommodation?neLat=' + neLat + '&neLng=' + neLng + '&swLat=' + swLat + '&swLng=' + swLng)
                .map(result => result.json())
                .subscribe(result => self.renderAccommodations(result));
        });
    }

    renderAccommodations(result) {
        this.markerClusterer.clearMarkers();
        this.markerClusterer.addMarkers(result.listings.map(function(listing) {
            var latLng = new google.maps.LatLng(listing.latitude, listing.longitude);
            return new google.maps.Marker({ position: latLng });
        }), false);
    }
}