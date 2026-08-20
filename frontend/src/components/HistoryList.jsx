import ReactMarkdown from 'react-markdown';
import './HistoryList.css';

function formatDate(isoString) {
  return new Date(isoString).toLocaleString(undefined, {
    dateStyle: 'medium',
    timeStyle: 'short',
  });
}

function HistoryList({ entries }) {
  if (entries.length === 0) {
    return <p className="history-empty">No entries yet. Go face the truth.</p>;
  }

  return (
    <ul className="history-list">
      {entries.map((entry) => (
        <li key={entry.id} className="history-item">
          <p className="history-item-date">{formatDate(entry.createdAt)}</p>
          <p className="history-item-raw">{entry.rawText}</p>
          <div className="history-item-reflection">
            <ReactMarkdown>{entry.aiResponse}</ReactMarkdown>
          </div>
        </li>
      ))}
    </ul>
  );
}

export default HistoryList;
