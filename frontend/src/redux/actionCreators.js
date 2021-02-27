import * as actionTypes from './actionTypes';
import serverBase from './serverBase';

export const gameOn=()=>({
    type: actionTypes.GAME_ON
});

export const gameOff=()=>({
    type: actionTypes.GAME_OFF
});

export const gameLoading=()=>({
    type:actionTypes.GAME_LOADING
})

export const gameFailed=(errMess)=>({
    type:actionTypes.GAME_FAILED,
    payload:errMess
})

export const startGame=()=>async dispatch=>{
    dispatch(gameLoading());
    try{
        const response= await fetch(serverBase+"/start");
        dispatch(gameOn());
    }catch(err){
        dispatch(gameFailed(err.message));
    }
};

export const executeCommand=(query)=>async dispatch=>{
    dispatch(responseLoading());
    try{
        const response=await fetch(serverBase+"/execute?"+new URLSearchParams({
            query
        }))
        const jsonResponse= await response.json();
        dispatch(editResponse(jsonResponse));
    }catch(err){
        dispatch(editResponse({
            type:"Server Error",
            description:err.message
        }))
    }
};

export const responseLoading=()=>({
    type:actionTypes.RESPONSE_LOADING
});

export const editResponse=(response)=>({
    type:actionTypes.EDIT_RESPONSE,
    payload:response
});

export const editCommand=(newCommandText)=>({
    type:actionTypes.EDIT_COMMAND,
    payload:newCommandText
});


