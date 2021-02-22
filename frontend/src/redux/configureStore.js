import {createStore,combineReducers,applyMiddleware} from 'redux';
import thunk from 'redux-thunk';
import logger from 'redux-logger';
import carouselAnimating from './carouselAnimating';
import carouselActiveIndex from './carouselActiveIndex';
import tables from './tables';
import users from './users';
import confirmModalOpen from './confirmModalOpen';
import confirmedModalOpen from './confirmedModalOpen';
import itemBeingProcessed from './itemBeingProcessed';
import {createForms} from 'react-redux-form'
import {initialConfirm} from './forms';
import editingModalOpen from './editingModalOpen';
import signModalOpen from './signModalOpen';
import items from './items';
import activeUser from './activeUser';

export const configureStore=()=>{
    const store=createStore(
        combineReducers({
            carouselAnimating,
            carouselActiveIndex,
            tables,
            users,
            confirmModalOpen,
            confirmedModalOpen,
            itemBeingProcessed,
            editingModalOpen,
            items,
            activeUser,
            signModalOpen
            // ...createForms({
            //     confirmForm: initialConfirm
            // })
        }),
        applyMiddleware(thunk,logger)
    )
    return store;
}