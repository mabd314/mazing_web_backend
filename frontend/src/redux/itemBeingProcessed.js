import * as actionTypes from './actionTypes';

const itemBeingProcessed=(state={
    id:-1
},action)=>{
    switch(action.type){
        case actionTypes.PREPARE_ITEM:
            return action.payload;
        default:
            return state;
    }
}

export default itemBeingProcessed;