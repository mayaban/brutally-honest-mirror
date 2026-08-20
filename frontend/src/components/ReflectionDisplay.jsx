import ReactMarkdown from 'react-markdown';
import './ReflectionDisplay.css';

function ReflectionDisplay({ aiResponse }) {
  if (!aiResponse) {
    return null;
  }

  return (
    <div className="reflection-display">
      <ReactMarkdown>{aiResponse}</ReactMarkdown>
    </div>
  );
}

export default ReflectionDisplay;
