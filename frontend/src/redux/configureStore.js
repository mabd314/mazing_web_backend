import {createStore,combineReducers,applyMiddleware} from 'redux';
import { composeWithDevTools } from 'redux-devtools-extension';
import thunk from 'redux-thunk';
import logger from 'redux-logger';
import game from './game';
import response from './response';
import commandText from './commandText';



export const configureStore=()=>{
    const store=createStore(
        combineReducers({
            game,
            response,
            commandText
        }),composeWithDevTools(applyMiddleware(thunk,logger))
    )
    return store;
}