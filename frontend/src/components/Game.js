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

import Play from './Play';
import Response from './Response';
function Game(props){
    if(props.game.isOn)
    return(
    <Container className='mt-5'>
        <Row>
            <Col xs='12'>
                <Form>
                    <FormGroup>
                        <Input type="textarea" name="commandText" id="commandText" value={props.commandText.text} onChange={event=>props.editCommand(event.target.value)}/>
                    </FormGroup>
                    <Button outline color="primary" size="lg" block onClick={()=>props.executeCommand(props.commandText.text)}>Execute</Button>
                </Form>
            </Col>
        </Row>
        <Response response={props.response}/>
    </Container>
    )

    if(props.game.errMess)
    return <h1>Failed to start a game</h1>

    if(props.game.isLoading)
    return <h1>Loading........</h1>

    return <Play startGame={props.startGame}/>
}

export default Game;