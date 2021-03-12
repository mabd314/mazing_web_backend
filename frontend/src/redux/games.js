import * as actionTypes from './actionTypes';

const games=(state={
    isLoading:true,
    errMess:null,
    games:[]
},action)=>{
    switch(action.type){
        case actionTypes.GAMES_FAILED:
            return {...state, isLoading:false,errMess:action.payload};
        case actionTypes.GAMES_LOADING:
            return {...state,isLoading:true,errMess:null};
        case actionTypes.GET_FETCHED_GAMES:
            return {...state,games:action.payload,isLoading:false,errMess:null};
        default:
            return state;
    }
}

export default games