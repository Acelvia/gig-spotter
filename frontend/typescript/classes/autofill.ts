class Autofill {
  value: string;
  suggestions: string[];

  fetchSuggestions() {}

  fetchResults() {}

  onChange(event) {
      if (event.keyCode == 13) {
        // User pressed enter, start the search
        this.fetchResults();
      } else {
        this.fetchSuggestions();
      }
  }

  onSuggestionClick(event) {
    this.value = event.toElement.innerHTML;
  }
}