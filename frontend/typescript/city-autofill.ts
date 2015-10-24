/// <reference path="./google.maps.d.ts" />
import {Component, bootstrap, FORM_DIRECTIVES, CORE_DIRECTIVES} from 'angular2/angular2';
import {Autofill} from './classes/autofill';

@Component({
  selector: 'city-autofill',
  templateUrl: 'templates/autofill.html',
  directives: [FORM_DIRECTIVES, CORE_DIRECTIVES]
})
class CityAutofillComponent extends Autofill {
  public service = null;
  constructor() {
    super();
    this.service = new google.maps.places.AutocompleteService();
  }
  fetchSuggestions() {
    var self = this;
    if(this.value) {
      this.service.getPlacePredictions({ 
          input: this.value, 
          types: ['(cities)']
        }, function(predictions, status) {
          if (status != google.maps.places.PlacesServiceStatus.OK) {
            return;
          }
          self.suggestions = predictions.map(function(p) { return p.description; });
      });
    }
  }
}

bootstrap(CityAutofillComponent);