export class Autofill {
  value: string;
  suggestions: string[];

  fetchSuggestions() {};

  onChange(event) {
    this.fetchSuggestions();
  }

  onSuggestionClick(event) {
    this.value = event.toElement.innerHTML;
    this.suggestions = [];
  }
}