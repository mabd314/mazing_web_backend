import * as actionTypes from './actionTypes';
import serverBase from './serverBase';

export const gameStarting=()=>({
    type:actionTypes.GAME_STARTING
})

export const gameFailed=(errMess)=>({
    type:actionTypes.GAME_FAILED,
    payload:errMess
})

export const gamesLoading=()=>({
    type:actionTypes.GAMES_LOADING
})

export const gamesFailed=(errMess)=>({
    type:actionTypes.GAMES_FAILED,
    payload:errMess
})

export const getFetchedGames=(jsonGames)=>({
    type:actionTypes.GET_FETCHED_GAMES,
    payload:jsonGames
})

export const getFetchedPlayer=(jsonPlayer)=>({
    type:actionTypes.GET_FETCHED_PLAYER,
    payload:jsonPlayer
})

export const gameStartingFinished=()=>({
    type:actionTypes.GAME_STARTING_FINISHED,
})

export const chooseGame=(userName,gameId)=>async dispatch=>{
    try{
        const response=await fetch(serverBase+`/players/${userName}/chooseGame/${gameId}`)
        if(response.status>=400)
            return alert("can not enter this game");
        const jsonPlayer=await response.json();
        dispatch(getFetchedPlayer(jsonPlayer));
        dispatch(fetchGames());
    }catch(err){
        alert(err.message);
    }
}

export const leaveGame=(userName)=>async dispatch=>{
    try{
        const response=await fetch(serverBase+`/players/${userName}/leaveGame`)
        if(response.status>=400)
            return alert("can not leave this game");
        const jsonPlayer=await response.json();
        dispatch(getFetchedPlayer(jsonPlayer));
        dispatch(fetchGames());
    }catch(err){
        alert(err.message);
    }
}


export const choosePlayer=(userName)=>async dispatch=>{
    try{
        const response=await fetch(serverBase+`/players/${userName}`)
        if(response.status>=400)
            return alert("can not fetch this player");
        const jsonPlayer=await response.json();
        dispatch(getFetchedPlayer(jsonPlayer));
    }catch(err){
        alert(err.message);
    }
}


export const startGame=(gameId)=>async dispatch=>{
    dispatch(gameStarting());
    try{
        const response= await fetch(serverBase+`/games/${gameId}/start`);
        if(response.status>=400)
            return dispatch(gameFailed("Failed To Start The Game"));
        dispatch(gameStartingFinished());
        dispatch(fetchGames());
    }catch(err){
        dispatch(gameFailed(err.message));
    }
};

export const fetchGames=()=>async dispatch=>{
    dispatch(gamesLoading());
    try{
        const response= await fetch(serverBase+"/games");
        if(response.status>=400)
            return dispatch(gameFailed("Failed To Get All Games"));
        const jsonGames=await response.json();
        for(let i=0;i<jsonGames.length;i++){
            const jsonGame=jsonGames[i];
            jsonGame.playersNames=await getPlayersNamesFromGameId(jsonGame.gameId);
        }
        dispatch(getFetchedGames(jsonGames));
    }catch(err){
        dispatch(gamesFailed(err.message));
    }
};

export const getPlayersNamesFromGameId=async gameId=>{
    try{
        const response=await fetch(serverBase+`/games/${gameId}/playersNames`)
        if(response.status>=400)
            return ["can not fetch player names"];
        const jsonPlayerNames=await response.json();
        return jsonPlayerNames;
    }catch(err){
        return "can not fetch player names";
    }
}



export const executeCommand=(query,userName)=>async dispatch=>{
    dispatch(responseLoading());
    try{
        const response=await fetch(serverBase+`/games/execute/${userName}?`+new URLSearchParams({
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


