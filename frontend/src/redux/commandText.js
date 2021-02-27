import * as actionTypes from './actionTypes';

const commandText=(state={
    text:""
},action)=>{
    switch(action.type){
        case actionTypes.EDIT_COMMAND:
            return {text:action.payload};
        default:
            return state;
    }
}

export default commandText