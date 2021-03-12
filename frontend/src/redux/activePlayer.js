import * as actionTypes from './actionTypes';

const activePlayer=(state={
    isGameStarting:false,
    errMess:null,
    player:null
},action)=>{
    switch(action.type){
        case actionTypes.GAME_FAILED:
            return {...state,isGameStarting:false,errMess:action.payload};
        case actionTypes.GAME_STARTING:
            return {...state,isGameStarting:true,errMess:null};
        case actionTypes.GAME_STARTING_FINISHED:
            return {...state, isLoading:false,errMess:null};    
        case actionTypes.GET_FETCHED_PLAYER:
            return {...state,player:action.payload};

        default:
            return state;
    }
}

export default activePlayer