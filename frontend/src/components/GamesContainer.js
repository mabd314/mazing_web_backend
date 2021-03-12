import react,{useEffect} from 'react';

import {
    Container,
    Row
} from 'reactstrap'
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
        </Container>
    )
}

export default GamesContainer;