import {Component, bootstrap, FORM_DIRECTIVES} from 'angular2/angular2';

class Search {
  job: string;
  city: string;
  apartment: string;
}

@Component({
  selector: 'my-app',
  templateUrl: 'templates/test.html',
  directives: [FORM_DIRECTIVES]
})
class AppComponent {
  public search: Search = {
    job: 'job there',
    city: 'city here',
    apartment: 'apartment here'
  };
}

bootstrap(AppComponent);