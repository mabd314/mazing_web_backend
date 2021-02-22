import * as actionTypes from './actionTypes';

const users=(state={
    loadingInfo:{
        isLoading: true,
        errMess: null,
    }
}, action)=>{
    switch(action.type){
        case actionTypes.LOAD_ACTIVE_USER:
            return {...state, loadingInfo:{isLoading: false,errMess: null}, ...action.payload};
        case actionTypes.ACTIVE_USER_FAILED:
            return {...state, loadingInfo:{isLoading: false, errMess: action.payload}};
        case actionTypes.ACTIVE_USER_LOADING:
            return {...state, loadingInfo: {isLoading: true, errMess: null}};
        case actionTypes.UPDATE_ACTIVE_USER:
            return {...state,...action.payload};
        case actionTypes.CHOOSE_ACTIVE_USER:
            return {...state}
        default:
            return state;
    }
}

export default users;