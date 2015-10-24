export class Autofill {
  value: string;
  suggestions: string[];

  fetchSuggestions() {}

  fetchResults() {}

  onChange(event) {
    this.fetchSuggestions();
  }

  onSuggestionClick(event) {
    this.value = event.toElement.innerHTML;
    this.suggestions = [];
    this.fetchResults();
  }
}