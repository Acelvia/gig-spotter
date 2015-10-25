import {Component, Inject, bootstrap, FORM_DIRECTIVES, CORE_DIRECTIVES} from 'angular2/angular2';
import {JobListService} from './job-list';
import {Job} from './classes/job';

@Component({
  selector: 'job-results',
  templateUrl: 'templates/job-results.html',
  directives: [FORM_DIRECTIVES, CORE_DIRECTIVES]
})
export class JobResults {
  jobs: Job[];
  constructor(jls: JobListService){
    this.jobs = jls.getJobs();
  }
}