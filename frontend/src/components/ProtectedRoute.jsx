import { Navigate } from 'react-router-dom';
import { getToken } from '../api/client';

// This is a client-side convenience only, so the app doesn't render a
// protected page and then immediately fail its API calls. It is NOT the
// real security boundary — the backend independently validates the JWT
// on every request and rejects anything invalid or missing regardless of
// what this component does.
function ProtectedRoute({ children }) {
  const token = getToken();

  if (!token) {
    return <Navigate to="/login" replace />;
  }

  return children;
}

export default ProtectedRoute;
