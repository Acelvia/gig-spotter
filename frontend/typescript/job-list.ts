import {Job} from './classes/job';
import {Injectable} from 'angular2/angular2';

@Injectable ()
export class JobListService {
  private _jobs: Job[];
  constructor() {
    this._jobs = [];
  }

  getJobs() {
    return this._jobs;
  }

  setJobs(js) {
    this._jobs = js;
  }
}