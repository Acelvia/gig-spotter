/// <reference path="./google.maps.d.ts" />

class GoogleMap {
    constructor(container: Element) {
        var options = {
            // Center on Helsinki
            center: new google.maps.LatLng(60.1733343, 24.93227),
            zoom: 13
        };
        var map = new google.maps.Map(container, options);
    }
}