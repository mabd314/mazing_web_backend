import { useAuth0 } from '@auth0/auth0-react';
import react from 'react';
import {
        Row,
        Col,
        Card,
        CardBody,
        CardImg,
        CardTitle,
        CardSubtitle,
        CardText,
        Badge,
        Button,
} from 'reactstrap';


function GameDetail(props){

    const {getAccessTokenSilently} = useAuth0();

    const enterGameButtonClicked=async()=>{
        const token=await getAccessTokenSilently();
        props.chooseGame(token,props.game.gameId)
    }
    
    return (
        <Col xs='12' sm='6' key={props.game.gameId} className='my-3' >
            <Card>
                <CardBody>
                    <CardSubtitle><Badge pill color='info'>{props.game.playersNames.length} Players</Badge></CardSubtitle>
                    <CardText className='cardTextHeight'>
                        <Row>
                            {props.game.playersNames.map(playerName=>{
                                return(
                                    <Col xs='12'><small>{playerName.userName}</small></Col>
                                )
                            })}
                        </Row>
                    </CardText>
                </CardBody>
                    <Button color="primary" onClick={enterGameButtonClicked}>Enter Game</Button>
            </Card>
        </Col>
    )

}

export default GameDetail;