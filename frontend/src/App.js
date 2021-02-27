import './App.css';
import Main from './components/Main' 
import {BrowserRouter} from 'react-router-dom';
import {Provider} from 'react-redux';
import {configureStore} from './redux/configureStore';
export const store=configureStore();

function App() {
  return(
    <Provider store={store}>
      <BrowserRouter>
        <Main/>
      </BrowserRouter>
    </Provider>
  );
}

export default App;
