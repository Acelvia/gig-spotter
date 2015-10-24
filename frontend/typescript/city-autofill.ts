import {Component, bootstrap, FORM_DIRECTIVES, CORE_DIRECTIVES} from 'angular2/angular2';
/// <reference path="./classes/autofill.ts" />

@Component({
  selector: 'city-autofill',
  templateUrl: 'templates/autofill.html',
  directives: [FORM_DIRECTIVES, CORE_DIRECTIVES]
})
class CityAutofillComponent extends Autofill {
  fetchSuggestions() {
    this.suggestions = this.value.split('');
  }
}

bootstrap(CityAutofillComponent);