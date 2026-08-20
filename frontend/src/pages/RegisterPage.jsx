import { useState } from 'react';
import { useNavigate, Link } from 'react-router-dom';
import AuthForm from '../components/AuthForm';
import { register, setToken } from '../api/client';

function RegisterPage() {
  const [errorMessage, setErrorMessage] = useState('');
  const navigate = useNavigate();

  async function handleRegister(email, password) {
    setErrorMessage('');
    try {
      const data = await register(email, password);
      setToken(data.token);
      navigate('/mirror');
    } catch (error) {
      setErrorMessage(error.message);
    }
  }

  return (
    <AuthForm
      title="Create an account"
      submitLabel="Register"
      onSubmit={handleRegister}
      errorMessage={errorMessage}
      footer={
        <span>
          Already have an account? <Link to="/login">Log in</Link>
        </span>
      }
    />
  );
}

export default RegisterPage;
