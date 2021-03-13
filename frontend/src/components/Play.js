import react,{useEffect} from 'react';

import GamesContainer from './GamesContainer';
import GameArea from './GameArea';
import LogIn from './LogIn';

function Play(props){

    useEffect(() => {
        props.fetchGames();
      },[]);

    if(props.games.errMess)
        return <h1>{props.games.errMess}</h1>

    else if(props.games.isLoading)
        return <h1>Loading........</h1>

    else if(props.activePlayer.player && props.activePlayer.player.gameId)
        return (
            <GameArea leaveGame={props.leaveGame} game={props.games.games.find(game=>(game.gameId===props.activePlayer.player.gameId))} activePlayer={props.activePlayer} executeCommand={props.executeCommand} commandText={props.commandText} editCommand={props.editCommand} response={props.response} startGame={props.startGame}/>
        )

    else if(props.activePlayer.player &&!props.activePlayer.gameId)
        return(
            <GamesContainer createGame={props.createGame} chooseGame={props.chooseGame} activePlayer={props.activePlayer} games={props.games}></GamesContainer>
        ) 

    else 
        return (
            <LogIn activePlayer={props.activePlayer} choosePlayer={props.choosePlayer}></LogIn>
        )

}

export default Play;