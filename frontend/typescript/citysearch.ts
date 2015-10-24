import {Http, HTTP_PROVIDERS} from 'angular2/http';
import {Component, View, bootstrap} from 'angular2/angular2';
import {CORE_DIRECTIVES, FORM_DIRECTIVES} from 'angular2/angular2';

class CityModel {
    city: string;
}

@Component({
    selector: 'citysearch'
})

@View({
    directives: [ CORE_DIRECTIVES, FORM_DIRECTIVES ],
    template: `
  <form (ng-submit)="submit()">
    <p>
      <label for="city">
        City
        <input
          type="text"
          id="city"
          ng-control="city"
          [(ng-model)]="model.city"
          required>
      </label>
    </p>
    <button> Submit</button>
    </form>
    <job *ng-for="#job of jobs"><a href="{{job.url}}">{{job.jobtitle}}</a><br/></job>
`
})
class CitySearch {

    jobs: Job[] = [];

    constructor(public http: Http) {
    }

    model = new CityModel();

    process(jobs) {
        this.jobs = [];
        for(var i = 0; i < jobs.length; i++) {
            var obj = jobs[i];
            this.jobs.push(new Job("" + obj.jobtitle, "" + obj.url));
        }
    }
    submit() {
        console.log('Submit Form ', this.model);

        this.http.get("/jobs?city=" + this.model.city)
            .map(res => res.json())
            .subscribe(jobs => this.process(jobs));
    }
}
@Component({selector: 'job'})
class Job {

    constructor(public jobtitle : string, public url : string) {
    }
}

bootstrap(CitySearch, [HTTP_PROVIDERS]);