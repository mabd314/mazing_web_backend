import * as actionTypes from './actionTypes';
import * as configs from '../requestBodies/configs';
import * as difficulties from '../util/difficulties';
import serverBase from './serverBase';

const generalErrorMessage="Something went wrong. Refresh and try again.";
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

export const responseLoading=()=>({
    type:actionTypes.RESPONSE_LOADING
});

export const editResponse=(response)=>({
    type:actionTypes.EDIT_RESPONSE,
    payload:{
        description:response.description,
        type:response.type,
        color:getColor(response.type)
    }
});

const getColor=responseType=>{
    switch(responseType){
        case 'FAILURE':
            return 'danger';
        case 'SUCCESS':
            return 'primary';
        case 'INVALID':
            return 'danger';
        case 'LOCKED':
            return 'warning';
        case 'EMPTY':
            return 'warning';
        case 'UNLOCKED':
            return 'primary';
        case 'WON':
            return 'success';
        case 'STATUS':
            return 'info';
        case 'LOST':
            return 'danger';
    }
}

export const editCommand=(newCommandText)=>({
    type:actionTypes.EDIT_COMMAND,
    payload:newCommandText
});


export const createGame=(token,difficulty)=>async dispatch=>{
    try{
        let data="";
        switch(difficulty){
            case difficulties.EASY:
                data=configs.easyConfig;
                break;
            case difficulties.MEDIUM:
                data=configs.mediumConfig;
                break;
            case difficulties.HARD:
                data=configs.hardConfig;
                break;
        }
        const response=await fetch(serverBase+"/games/create",{
            method: 'POST',
            headers: {
              'Content-Type': 'application/json',
              'Authorization': 'Bearer '+token
            },
            body: JSON.stringify(data)
        })
        if(response.status>=400)
            dispatch(gamesFailed(generalErrorMessage))
        const gameId=await response.json();
        dispatch(fetchGames());
        dispatch(chooseGame(token,gameId));
    }catch(err){
        dispatch(gamesFailed("Something went wrong. Refresh and try again. "+err.message))
    }
}

export const chooseGame=(token,gameId)=>async dispatch=>{
    try{
        const response=await fetch(serverBase+`/players/chooseGame/${gameId}`,{
            method:'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer '+token
            },  
        })
        if(response.status>=400)
            dispatch(gamesFailed("Something went wrong. Refresh and try again. "))
        const jsonPlayer=await response.json();
        dispatch(getFetchedPlayer(jsonPlayer));
        dispatch(fetchGames());
    }catch(err){
        dispatch(gamesFailed("Something went wrong. Refresh and try again. "+err.message))
    }
}

export const leaveGame=token=>async dispatch=>{
    try{
        const response=await fetch(serverBase+`/players/leaveGame`,{
            method:'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer '+token
            },  
        })
        if(response.status>=400)
            dispatch(gamesFailed("Something went wrong. Refresh and try again. "));
        const jsonPlayer=await response.json();
        dispatch(getFetchedPlayer(jsonPlayer));
        dispatch(fetchGames());
    }catch(err){
        dispatch(gamesFailed("Something went wrong. Refresh and try again. "+err.message));
    }
}


export const fetchPlayer=(token)=>async dispatch=>{
    try{
        const response=await fetch(serverBase+`/players/getPlayer`,{
            method:'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer '+token
              },  
        })
        if(response.status>=400)
            dispatch(gamesFailed("Something went wrong. Refresh and try again. "))
        const jsonPlayer=await response.json();
        dispatch(getFetchedPlayer(jsonPlayer));
        dispatch(fetchGames());
    }catch(err){
        dispatch(gamesFailed("Something went wrong. Refresh and try again. "+err.message));
    }
}


export const startGame=(gameId,token)=>async dispatch=>{
    dispatch(gameStarting());
    try{
        const response= await fetch(serverBase+`/games/${gameId}/start`,{
            method:'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer '+token
              },  
        });
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



export const executeCommand=(query,token)=>async dispatch=>{
    dispatch(responseLoading());
    try{
        const response=await fetch(serverBase+`/games/execute?`+new URLSearchParams({
            query
        }),{
            method:'POST',
            headers: {
                'Content-Type': 'application/json',
                'Authorization': 'Bearer '+token
            },  
        })
        const jsonResponse= await response.json();
        dispatch(editResponse(jsonResponse));
        dispatch(fetchGames());
    }catch(err){
        dispatch(editResponse({
            type:"Server Error",
            description:err.message
        }))
    }
};

