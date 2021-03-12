import react from 'react';

import {
    Button,
} from 'reactstrap'


function Start(props){

    return(
        <Button outline color="warning" size="lg" block onClick={()=>{props.startGame(props.gameId)}}>Start Game</Button>
    )
}

export default Start;