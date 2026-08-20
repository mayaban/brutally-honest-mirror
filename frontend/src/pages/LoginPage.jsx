import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import AuthForm from '../components/AuthForm';
import { login, setToken } from '../api/client';

function LoginPage() {
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  async function handleLogin(email, password) {
    setErrorMessage('');
    try {
      const data = await login(email, password);
      setToken(data.token);
      navigate('/mirror');
    } catch (error) {
      setErrorMessage(error.message);
    }
  }

  return (
    <AuthForm
      title="Welcome back"
      submitLabel="Log in"
      onSubmit={handleLogin}
      errorMessage={errorMessage}
      footer={
        <span>
          No account yet? <Link to="/register">Register</Link>
        </span>
      }
    />
  );
}

export default LoginPage;
