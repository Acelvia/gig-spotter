/// <reference path="./google.maps.d.ts" />
import {Http, HTTP_PROVIDERS} from 'angular2/http';
import {Component, bootstrap, FORM_DIRECTIVES, CORE_DIRECTIVES} from 'angular2/angular2';
import {Autofill} from './classes/autofill';

@Component({
  selector: 'city-autofill',
  templateUrl: 'templates/autofill.html',
  directives: [FORM_DIRECTIVES, CORE_DIRECTIVES]
})
class CityAutofillComponent extends Autofill {
  jobs: Job[] = [];
  service: google.maps.places.AutocompleteService = null;

  constructor(public http: Http) {
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

  fetchResults() {
      this.http.get("/jobs?city=" + this.value)
          .map(res => res.json())
          .subscribe(jobs => this.process(jobs));
  }

  process(jobs) {
      this.jobs = [];

      for(var i = 0; i < jobs.length; i++) {
        var obj = jobs[i];
        this.jobs.push(new Job("" + obj.jobtitle, "" + obj.url));
      }
  }
}

@Component({
  selector: 'job',
  directives: [ CORE_DIRECTIVES, FORM_DIRECTIVES ]
})
class Job {

    constructor(public jobtitle : string, public url : string) {
    }
}

bootstrap(CityAutofillComponent, [HTTP_PROVIDERS]);