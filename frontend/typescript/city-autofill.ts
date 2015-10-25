/// <reference path="./google.maps.d.ts" />
import {Http, HTTP_PROVIDERS} from 'angular2/http';
import {Component, Inject, bootstrap, FORM_DIRECTIVES, CORE_DIRECTIVES} from 'angular2/angular2';
import {Autofill} from './classes/autofill';
import {JobListService} from './job-list';
import {Job} from './classes/job';
import {Injectable} from 'angular2/angular2';

@Injectable ()
@Component({
  selector: 'city-autofill',
  templateUrl: 'templates/autofill.html',
  providers: [JobListService, HTTP_PROVIDERS],
  directives: [FORM_DIRECTIVES, CORE_DIRECTIVES]
})
export class CityAutofillComponent extends Autofill {
  service: google.maps.places.AutocompleteService = null;

  constructor(public http: Http, public jobListService: JobListService) {
    super();
    this.service = new google.maps.places.AutocompleteService();
    this.inputLabel = 'City';
    this.suggestionsHidden = true;
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
          self.suggestionsHidden = !(self.suggestions.length > 0);
      });
    }
  }

  fetchResults() {
      this.http.get("/jobs?city=" + this.value)
          .map(res => res.json())
          .subscribe(jobs => this.process(jobs));
  }

  process(jobs) {
      this.jobListService.jobs = [];

      for(var i = 0; i < jobs.length; i++) {
        var obj = jobs[i];
        this.jobListService.jobs.push(new Job(obj.jobTitle, obj.jobUrl));
      }

      this.suggestionsHidden = !(this.jobListService.jobs.length > 0);
  }
}