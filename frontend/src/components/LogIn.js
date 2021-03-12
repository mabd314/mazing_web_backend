import react from 'react';

import {
    Button,
    Row,
    Col
} from 'reactstrap'


function LogIn(props){

    return(
        <Row>
            <Col xs='3'>
                <Button outline color="warning" size="lg" onClick={()=>{props.choosePlayer("player1")}}>player1</Button>
            </Col>
            <Col xs='3'>
                <Button outline color="warning" size="lg" onClick={()=>{props.choosePlayer("player2")}}>player2</Button>
            </Col>
            <Col xs='3'>
                <Button outline color="warning" size="lg" onClick={()=>{props.choosePlayer("player3")}}>player3</Button>
            </Col>
            <Col xs='3'>
                <Button outline color="warning" size="lg" onClick={()=>{props.choosePlayer("player4")}}>player4</Button>
            </Col>
        </Row>
    )
}

export default LogIn;