import { useState, useEffect } from 'react';
import { Link } from 'react-router-dom';
import HistoryList from '../components/HistoryList';
import { getHistory } from '../api/client';
import './HistoryPage.css';

function HistoryPage() {
  const [entries, setEntries] = useState([]);
  const [errorMessage, setErrorMessage] = useState('');
  const [isLoading, setIsLoading] = useState(true);

  useEffect(() => {
    async function loadHistory() {
      try {
        const data = await getHistory();
        setEntries(data);
      } catch (error) {
        setErrorMessage(error.message);
      } finally {
        setIsLoading(false);
      }
    }

    loadHistory();
  }, []);

  return (
    <div className="history-page">
      <nav className="history-page-nav">
        <Link to="/mirror">Back to Mirror</Link>
      </nav>

      <h1 className="history-page-title">Your History</h1>

      {isLoading && <p className="history-page-loading">Loading...</p>}
      {errorMessage && <p className="history-page-error">{errorMessage}</p>}
      {!isLoading && !errorMessage && <HistoryList entries={entries} />}
    </div>
  );
}

export default HistoryPage;
