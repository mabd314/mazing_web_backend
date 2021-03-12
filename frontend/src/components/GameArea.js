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
            <Response response={props.response}/>
        </Container>
        )

    if(props.activePlayer.errMess)
    return <h1>{props.activePlayer.errMess}</h1>

    if(props.activePlayer.isLoading)
    return <h1>Loading........</h1>

    return <Start gameId={props.game.gameId} startGame={props.startGame}/>
}

export default GameArea;