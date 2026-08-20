import { useState } from 'react';
import './AuthForm.css';

function AuthForm({ title, submitLabel, onSubmit, errorMessage, footer }) {
  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [isSubmitting, setIsSubmitting] = useState(false);

  async function handleSubmit(event) {
    event.preventDefault();
    setIsSubmitting(true);
    await onSubmit(email, password);
    setIsSubmitting(false);
  }

  return (
    <form className="auth-form" onSubmit={handleSubmit}>
      <h1 className="auth-form-title">{title}</h1>

      <label className="auth-form-label" htmlFor="email">
        Email
      </label>
      <input
        id="email"
        type="email"
        value={email}
        onChange={(event) => setEmail(event.target.value)}
        required
      />

      <label className="auth-form-label" htmlFor="password">
        Password
      </label>
      <input
        id="password"
        type="password"
        value={password}
        onChange={(event) => setPassword(event.target.value)}
        required
      />

      {errorMessage && <p className="auth-form-error">{errorMessage}</p>}

      <button type="submit" disabled={isSubmitting}>
        {isSubmitting ? 'Please wait...' : submitLabel}
      </button>

      {footer && <div className="auth-form-footer">{footer}</div>}
    </form>
  );
}

export default AuthForm;
