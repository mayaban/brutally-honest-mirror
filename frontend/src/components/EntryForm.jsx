import './EntryForm.css';

function EntryForm({ value, onChange, onFocus, onBlur, onSubmit, isSubmitting }) {
  function handleSubmit(event) {
    event.preventDefault();
    onSubmit();
  }

  return (
    <form className="entry-form" onSubmit={handleSubmit}>
      <textarea
        className="entry-form-textarea"
        value={value}
        onChange={(event) => onChange(event.target.value)}
        onFocus={onFocus}
        onBlur={onBlur}
        placeholder="What's the excuse this time?"
        rows={6}
        required
      />
      <button type="submit" disabled={isSubmitting || value.trim() === ''}>
        {isSubmitting ? 'Bracing itself...' : 'Hit Me With The Truth'}
      </button>
    </form>
  );
}

export default EntryForm;
