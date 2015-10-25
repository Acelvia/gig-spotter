export class Autofill {
  value: string;
  inputLabel: string;
  suggestions: string[];
  suggestionsHidden: boolean;

  fetchSuggestions() {}

  fetchResults() {}

  onChange(event) {
    if(!this.value) {
      this.suggestions = [];
    } else {
      this.fetchSuggestions();
    }
  }

  onSuggestionClick(event) {
    this.value = event.toElement.innerHTML;
    this.suggestions = [];
    this.suggestionsHidden = true;
    this.fetchResults();
  }
}