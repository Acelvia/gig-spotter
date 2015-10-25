import {Job} from './classes/job';
import {Injectable} from 'angular2/angular2';

@Injectable ()
export class JobListService {
  jobs: Job[];
  constructor() {}

  getJobs() {
    return this.jobs;
  }
}
