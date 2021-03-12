import {createStore,combineReducers,applyMiddleware} from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import thunk from 'redux-thunk';
import logger from 'redux-logger';
import games from './games';
import response from './response';
import commandText from './commandText';
import activePlayer from './activePlayer';


export const configureStore=()=>{
    const store=createStore(
        combineReducers({
            games,
            response,
            commandText,
            activePlayer,
        }),composeWithDevTools(applyMiddleware(thunk,logger))
    )
    return store;
}