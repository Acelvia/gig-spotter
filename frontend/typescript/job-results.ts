import {Component, Inject, FORM_DIRECTIVES, CORE_DIRECTIVES} from 'angular2/angular2';
import {JobListService} from './job-list';

@Component({
  selector: 'job-results',
  templateUrl: 'templates/job-results.html',
  directives: [FORM_DIRECTIVES, CORE_DIRECTIVES]
})
export class JobResults {
  constructor(@Inject(JobListService) public jls){
  }
}