import react,{useState} from 'react';
import * as difficulties from '../util/difficulties';

import {
    Button,
    Col,
    Form,
    FormGroup,
    Input,
    Label,
    Row
} from 'reactstrap'


function CreateGame(props){

    const [difficulty,setDifficulty]=useState("");

    return(
        <Col xs={{size:6,offset:3}}>
            <Form>
                <Button disabled={difficulty===""} outline color="success" size="lg" block onClick={()=>props.createGame(props.userName,difficulty)}>Create Game</Button>
                <Row className='mt-2'>
                    <Col>
                        <FormGroup check>
                            <Input onChange={()=>setDifficulty(difficulties.EASY)} id="easy" type="radio" name="difficulty" />
                            <Label check for="easy">
                                Easy
                            </Label>
                        </FormGroup>
                    </Col>
                    <Col>
                        <FormGroup check>
                            <Input disabled onChange={()=>setDifficulty(difficulties.MEDIUM)} id="medium" type="radio" name="difficulty"/>
                            <Label check for="medium">
                                Medium
                            </Label>
                        </FormGroup>
                    </Col>
                    <Col>
                        <FormGroup check>
                            <Input disabled onChange={()=>setDifficulty(difficulties.HARD)} id="hard" type="radio" name="difficulty" />
                            <Label check for="hard">
                                Hard
                            </Label>
                        </FormGroup>
                    </Col>
                </Row>
            </Form>
        </Col>
    )
}

export default CreateGame;