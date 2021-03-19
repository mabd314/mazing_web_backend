import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import App from './App';
import reportWebVitals from './reportWebVitals';
import { Auth0Provider } from '@auth0/auth0-react';

import 'bootstrap/dist/css/bootstrap.min.css';
import 'font-awesome/css/font-awesome.min.css';
import 'bootstrap-social/bootstrap-social.css';

ReactDOM.render(
  <React.StrictMode>
    <Auth0Provider
        domain="moapps.us.auth0.com"
        clientId="ciZkBUWVuSZq6y9Yz7aNDY9VgHNAB5RR"
        audience="https://SpringTest.com"
        redirectUri="http://localhost:3000/play"
        >
      <App />
    </Auth0Provider>
  </React.StrictMode>,
  document.getElementById('root')
);

// If you want to start measuring performance in your app, pass a function
// to log results (for example: reportWebVitals(console.log))
// or send to an analytics endpoint. Learn more: https://bit.ly/CRA-vitals
reportWebVitals();
