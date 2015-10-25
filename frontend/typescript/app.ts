import {Component, bootstrap, FORM_DIRECTIVES} from 'angular2/angular2';
import {Http, HTTP_PROVIDERS} from 'angular2/http';
import {CityAutofillComponent} from './city-autofill';
import {GoogleMap} from './mapping';
import {JobResults} from './job-results';
import {JobListService} from './job-list';

@Component({
  selector: 'my-app',
  templateUrl: 'templates/app.html',
  providers: [CityAutofillComponent],
  directives: [FORM_DIRECTIVES]
})
class AppComponent {
  constructor(cityaf: CityAutofillComponent, private gmap: GoogleMap, jres: JobResults) {
  }

  afterViewInit() {
    this.gmap.init();
    bootstrap(CityAutofillComponent);
  }
}

bootstrap(AppComponent, [JobListService, GoogleMap, JobResults, HTTP_PROVIDERS]);