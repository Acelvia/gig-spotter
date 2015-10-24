import {Http, HTTP_PROVIDERS} from 'angular2/http';
import {Component, bootstrap, FORM_DIRECTIVES, CORE_DIRECTIVES} from 'angular2/angular2';
import {Autofill} from './classes/autofill';

@Component({
  selector: 'job-autofill',
  templateUrl: 'templates/autofill.html',
  directives: [FORM_DIRECTIVES, CORE_DIRECTIVES]
})
class JobAutofillComponent extends Autofill {

  jobs: Job[] = [];

  constructor(public http: Http) {
    super();
  }

  fetchSuggestions() {
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

bootstrap(JobAutofillComponent, [HTTP_PROVIDERS]);