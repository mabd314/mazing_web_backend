import * as actionTypes from './actionTypes';

const confirmModalOpen=(state={
    rent: false,
    buy: false
},action)=>{
    switch(action.type){
        case actionTypes.TOGGLE_CONFIRM_MODAL:
            return {...state,[action.payload]:!state[action.payload]}
        default:
            return state;
    }
}

export default confirmModalOpen;