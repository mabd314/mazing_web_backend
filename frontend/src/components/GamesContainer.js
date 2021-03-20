import react from 'react';

import {
    Container,
    Row
} from 'reactstrap'

import CreateGameComponent from './CreateGameComponent';
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
                <CreateGameComponent userName={props.activePlayer.player.userName} createGame={props.createGame}></CreateGameComponent>
            </Row>
        </Container>
    )
}

export default GamesContainer;