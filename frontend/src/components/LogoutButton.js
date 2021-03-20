import React from 'react';
import { useAuth0 } from '@auth0/auth0-react';
import {
  Button
} from 'reactstrap';

function LogoutButton() {
  const {
    isAuthenticated,
    logout,
    user
  } = useAuth0();

  return isAuthenticated && (
    <>
      <small>{user.email}</small>
      <Button color='light' outline className='nav-link' onClick={(event) => {
        event.preventDefault();
        logout({ returnTo: window.location.origin });
      }}>
      <span className='fa fa-sign-out'></span> logout
      </Button>
    </>
  );
}

export default LogoutButton;
