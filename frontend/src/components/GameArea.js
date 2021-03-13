import react, { Component } from 'react';

import {
    Button,
    Form,
    FormGroup,
    Input,
    Row,
    Col,
    Label,
    Container
} from 'reactstrap'

import Start from './Start';
import Response from './Response';
import { leaveGame } from '../redux/actionCreators';
function GameArea(props){

    const isKeyClickedIsEnter=(value)=>{
        const numberOfLineBreaks = (value.match(/\n/g)||[]).length;
        if(numberOfLineBreaks>0)
            return true;
        return false
    }

    const commandTextChanged=event=>{
        if(isKeyClickedIsEnter(event.target.value))
            buttonClicked();
        else
            props.editCommand(event.target.value)
    }

    const buttonClicked=()=>{
        props.executeCommand(props.commandText.text,props.activePlayer.player.userName)
    }

    if(props.game.hasStarted)
        return(
        <Container className='mt-5'>
            <Row>
                <Col xs='12'>
                    <Form>
                        <FormGroup>
                            <Input type="textarea" name="commandText" id="commandText" value={props.commandText.text} onChange={commandTextChanged}/>
                        </FormGroup>
                        <Button outline color="primary" size="lg" block onClick={buttonClicked}>Execute</Button>
                    </Form>
                </Col>
            </Row>
            <Row>
                <Col xs='12'>
                    <Response response={props.response}/>
                </Col>
            </Row>
            <Row xs='1' className='m-5'>
                <Col xs={{size:4,offset:4}}>
                    <Button block outline color="danger" onClick={()=>props.leaveGame(props.activePlayer.player.userName)}>Leave</Button>
                </Col>
            </Row>
            <Row className='m-5'>
                {props.game.playersNames.map(playerName=>{
                    return(
                        <Col xs='12' md='6'><small>{playerName.userName}</small></Col>
                    )
                })}
            </Row>
        </Container>
        )

    if(props.activePlayer.errMess)
    return <h1>{props.activePlayer.errMess}</h1>

    if(props.activePlayer.isGameStarting)
    return <h1>Loading........</h1>

    return (
        <>
            <Start gameId={props.game.gameId} startGame={props.startGame}/>
            <Row className='m-5'>
                <Col xs={{size:4,offset:4}}>
                    <Button block outline color="danger" onClick={()=>props.leaveGame(props.activePlayer.player.userName)}>Leave</Button>
                </Col>
            </Row>
            <Row className='m-5'>
                {props.game.playersNames.map(playerName=>{
                    return(
                        <Col xs='12' md='6'><small>{playerName.userName}</small></Col>
                    )
                })}
            </Row>
        </>
    )

}

export default GameArea;