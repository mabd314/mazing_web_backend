import React from 'react';
import { useAuth0 } from '@auth0/auth0-react';
import {Button} from 'reactstrap';
function LoginButton() {
  const {
    isAuthenticated,
    loginWithPopup,
  } = useAuth0();

  return !isAuthenticated && (
    <Button color='light' outline className='nav-link' onClick={loginWithPopup}>
      <span className='fa fa-sign-out'></span> login
      </Button>  
  );
}

export default LoginButton;
