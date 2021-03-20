import { useAuth0 } from '@auth0/auth0-react';
import react from 'react';

import {
    Button,
} from 'reactstrap'


function Start(props){

    const {getAccessTokenSilently,user} =useAuth0();

    const startButtonClicked=async ()=>{
        const token=await getAccessTokenSilently();
        props.startGame(props.game.gameId,token);
    }

    return(
        <Button disabled={user.email!==props.game.creator} outline color="warning" size="lg" block onClick={startButtonClicked}>Start Game</Button>
    )
}

export default Start;