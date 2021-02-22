import * as actionTypes from './actionTypes';

const signModalOpen=(state=true,action)=>{
    switch(action.type){
        case actionTypes.TOGGLE_SIGN_MODAL:
            return !state;
        default:
            return state;
    }
}

export default signModalOpen;