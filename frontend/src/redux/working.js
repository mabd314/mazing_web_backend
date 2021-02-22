import * as actionTypes from './actionTypes';

const working=(state={
    enabled:true
},action)=>{
    switch(action.type){
        case actionTypes.ENABLE:
            return {...state, enabled:true};
        case actionTypes.DISABLE:
            return {...state, enabled:false};
        default:
            return state;
    }
}

export default working