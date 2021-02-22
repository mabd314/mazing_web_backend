import * as actionTypes from './actionTypes';

const confirmedModalOpen=(state={
    rent: false,
    buy: false
},action)=>{
    switch(action.type){
        case actionTypes.TOGGLE_CONFIRMED_MODAL:
            return {...state,[action.payload]:!state[action.payload]}
        default:
            return state;
    }
}

export default confirmedModalOpen;