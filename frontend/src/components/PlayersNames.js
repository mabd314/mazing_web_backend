import React from 'react';
import {
    Col,
    Row
} from 'reactstrap';

function PlayersNames (props){
    return (
        <Row className='m-5'>
            {props.game.playersNames.map(playerName=>{
                return(
                    <Col xs='12' md='6'>
                        <small>{playerName.userName} </small>
                        {playerName.userName===props.game.creator? <i class="fa fa-star"></i>:null}
                    </Col>
                )
            })}
        </Row>
    )
}

export default PlayersNames;

