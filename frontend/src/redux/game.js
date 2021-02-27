import * as actionTypes from './actionTypes';

const game=(state={
    isOn:false,
    isLoading:false,
    errMess:null
},action)=>{
    switch(action.type){
        case actionTypes.GAME_FAILED:
            return {...state,isOn:false, isLoading:false,errMess:action.payload};
        case actionTypes.GAME_LOADING:
            return {...state,isOn:false, isLoading:true,errMess:null};
        case actionTypes.GAME_ON:
            return {...state,isOn:true,isLoading:false,errMess:null };
        case actionTypes.GAME_OFF:
            return {...state,isOn:false,isLoading:false,errMess:null};
            
        default:
            return state;
    }
}

export default game