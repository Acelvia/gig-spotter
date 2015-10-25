import {Component, bootstrap, FORM_DIRECTIVES} from 'angular2/angular2';
import {HTTP_PROVIDERS} from 'angular2/http';
import {CityAutofillComponent} from './city-autofill';
import {GoogleMap} from './mapping';
import {JobResults} from './job-results';
import {JobListService} from './job-list';

@Component({
  selector: 'my-app',
  templateUrl: 'templates/app.html',
  directives: [FORM_DIRECTIVES, JobResults, CityAutofillComponent, GoogleMap]
})
class AppComponent {
  constructor() {}
}

bootstrap(AppComponent, [JobListService, HTTP_PROVIDERS]);