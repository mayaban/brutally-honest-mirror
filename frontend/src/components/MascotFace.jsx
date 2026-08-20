import './MascotFace.css';

const MOOD_EMOJI = {
  idle: '😐',
  typing: '🤨',
  thinking: '🤔',
  reflecting: '😏',
  error: '😵‍💫',
};

function MascotFace({ mood }) {
  return (
    <div className="mascot-face" role="img" aria-label={`mascot mood: ${mood}`}>
      {MOOD_EMOJI[mood]}
    </div>
  );
}

export default MascotFace;
