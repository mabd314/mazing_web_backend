import react from 'react';

import {
    Container,
    Row
} from 'reactstrap'

import CreateGame from './CreateGame';
import GameDetail from './GameDetail';



function GamesContainer(props){
    return(
        <Container>
            <Row>
                {props.games.games.map(game=>{
                    return(
                        <GameDetail key={game.gameId} chooseGame={props.chooseGame} activePlayer={props.activePlayer} game={game}></GameDetail>
                    )
                })}
            </Row>
            <Row className='m-5'>
                <CreateGame userName={props.activePlayer.player.userName} createGame={props.createGame}></CreateGame>
            </Row>
        </Container>
    )
}

export default GamesContainer;