import * as actionTypes from './actionTypes';

const editingModalOpen=(state=false,action)=>{
    switch(action.type){
        case actionTypes.TOGGLE_EDITING_MODAL:
            return !state;
        default:
            return state;
    }
}

export default editingModalOpen;