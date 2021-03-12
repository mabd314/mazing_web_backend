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
import activePlayer from '../redux/activePlayer';

function GameDetail(props){
    return (
        <Col xs='6' sm='6' md='3' lg='2' key={props.game.gameId} className='my-3' >
            <Card>
                <CardImg width='100%' top src="./mazeIcon.jpg" alt={props.game.gameId} image cap/>
                <CardBody>
                    <CardTitle className='font-weight-bold'>gameName</CardTitle>
                    <CardSubtitle><Badge pill color='info'>{props.game.playersNames.length} Players</Badge></CardSubtitle>
                    <CardText className='cardTextHeight'>
                        <Row>
                            {props.game.playersNames.map(playerName=>{
                                return(
                                    <Col xs='12' md='6'><small>{playerName.userName}</small></Col>
                                )
                            })}
                        </Row>
                    </CardText>
                </CardBody>
                    <Button color="primary" onClick={()=>props.chooseGame(props.activePlayer.player.userName,props.game.gameId)}>Enter Game</Button>
            </Card>
        </Col>
    )

}

export default GameDetail;