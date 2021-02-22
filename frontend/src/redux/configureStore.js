import {createStore,combineReducers,applyMiddleware} from 'redux';
import thunk from 'redux-thunk';
import logger from 'redux-logger';
import working from './working';

export const configureStore=()=>{
    const store=createStore(
        combineReducers({
            working,
        }),
        applyMiddleware(thunk,logger)
    )
    return store;
}