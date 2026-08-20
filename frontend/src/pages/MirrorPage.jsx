import { useState } from 'react';
import { Link, useNavigate } from 'react-router-dom';
import MascotFace from '../components/MascotFace';
import EntryForm from '../components/EntryForm';
import ReflectionDisplay from '../components/ReflectionDisplay';
import { createEntry, clearToken } from '../api/client';
import './MirrorPage.css';

function MirrorPage() {
  const [rawText, setRawText] = useState('');
  const [mood, setMood] = useState('idle');
  const [aiResponse, setAiResponse] = useState('');
  const [errorMessage, setErrorMessage] = useState('');
  const [isSubmitting, setIsSubmitting] = useState(false);
  const navigate = useNavigate();

  function handleTextChange(value) {
    setRawText(value);
    if (value.trim() !== '') {
      setMood('typing');
    }
  }

  function handleFocus() {
    setMood('typing');
  }

  function handleBlur() {
    if (rawText.trim() === '') {
      setMood('idle');
    }
  }

  async function handleSubmit() {
    setIsSubmitting(true);
    setMood('thinking');
    setErrorMessage('');

    try {
      const data = await createEntry(rawText);
      setAiResponse(data.aiResponse);
      setMood('reflecting');
      setRawText('');
    } catch (error) {
      setErrorMessage(error.message);
      setMood('error');
    } finally {
      setIsSubmitting(false);
    }
  }

  function handleLogout() {
    clearToken();
    navigate('/login');
  }

  return (
    <div className="mirror-page">
      <nav className="mirror-page-nav">
        <Link to="/history">History</Link>
        <button className="mirror-page-logout" onClick={handleLogout}>
          Log out
        </button>
      </nav>

      <MascotFace mood={mood} />

      <EntryForm
        value={rawText}
        onChange={handleTextChange}
        onFocus={handleFocus}
        onBlur={handleBlur}
        onSubmit={handleSubmit}
        isSubmitting={isSubmitting}
      />

      {errorMessage && <p className="mirror-page-error">{errorMessage}</p>}

      <ReflectionDisplay aiResponse={aiResponse} />
    </div>
  );
}

export default MirrorPage;
