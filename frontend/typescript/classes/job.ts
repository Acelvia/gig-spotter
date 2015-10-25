export class Job {
  jobTitle: string;
  jobUrl: string;

  constructor(jobtitle : string, url : string) {
    this.jobTitle = jobtitle;
    this.jobUrl = url;
  }
}