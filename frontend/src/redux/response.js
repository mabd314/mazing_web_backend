import * as actionTypes from './actionTypes';

const response=(state={
    isLoading:false,
    type:null,
    description:null,
    color:null,
},action)=>{
    switch(action.type){
        case actionTypes.EDIT_RESPONSE:
            return {type: action.payload.type, description: action.payload.description, color:action.payload.color, isLoading:false};
        case actionTypes.RESPONSE_LOADING:
            return {isLoading:true}
        default:
            return state;
    }
}

export default response