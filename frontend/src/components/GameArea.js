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
import { useAuth0 } from '@auth0/auth0-react';
import PlayersNames from './PlayersNames';

function GameArea(props){

    const {getAccessTokenSilently} = useAuth0();

    const isKeyClickedIsEnter=(value)=>{
        const numberOfLineBreaks = (value.match(/\n/g)||[]).length;
        if(numberOfLineBreaks>0)
            return true;
        return false
    }

    const commandTextChanged=event=>{
        if(isKeyClickedIsEnter(event.target.value))
            commitButtonClicked();
        else
            props.editCommand(event.target.value)
    }

    const commitButtonClicked=async()=>{
        const token=await getAccessTokenSilently();
        props.executeCommand(props.commandText.text,token)
    }

    const leaveGameButtonClicked=async()=>{
        const token=await getAccessTokenSilently();
        props.leaveGame(token);
    }

    if(!props.game)
        leaveGameButtonClicked();

    if(props.game.hasStarted)
        return(
        <Container className='mt-5'>
            <Row>
                <Col xs='12'>
                    <Form>
                        <FormGroup>
                            <Input type="textarea" name="commandText" id="commandText" onChange={commandTextChanged} value={props.commandText.text} autoFocus onFocus={(event)=>{event.target.selectionStart=event.target.value.length}}/>
                        </FormGroup>
                        <Button outline color="primary" size="lg" block onClick={commitButtonClicked}>Execute</Button>
                    </Form>
                </Col>
            </Row>
            <Row>
                <Col xs='12' className='mt-5'>
                    <Response response={props.response}/>
                </Col>
            </Row>
            <PlayersNames game={props.game} />
            <Row xs='1' className='m-5'>
                <Col xs={{size:4,offset:4}}>
                    <Button block outline color="danger" onClick={leaveGameButtonClicked}>Leave Game</Button>
                </Col>
            </Row>
        </Container>
        )

    if(props.activePlayer.errMess)
    return <h1>{props.activePlayer.errMess}</h1>

    if(props.activePlayer.isGameStarting)
    return <h1>Loading........</h1>

    return (
        <>
            <Start game={props.game} startGame={props.startGame}/>
            <PlayersNames game={props.game} />
            <Row className='m-5'>
                <Col xs={{size:4,offset:4}}>
                    <Button block outline color="danger" onClick={leaveGameButtonClicked}>Leave Game</Button>
                </Col>
            </Row>
        </>
    )

}

export default GameArea;