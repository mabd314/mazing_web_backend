import react from 'react';

import {Link} from 'react-router-dom';
import {
    Button,
} from 'reactstrap'


function Play(props){

    return(
        <Button outline color="warning" size="lg" block onClick={()=>{props.startGame()}}>Start Game</Button>
    )
}

export default Play;