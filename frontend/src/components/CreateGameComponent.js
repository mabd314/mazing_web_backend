import react,{useEffect, useState} from 'react';
import * as difficulties from '../util/difficulties';
import { useAuth0 } from '@auth0/auth0-react';

import {
    Button,
    Col,
    Form,
    FormGroup,
    Input,
    Label,
    Row
} from 'reactstrap'


function CreateGameComponent(props){

    const [difficulty,setDifficulty]=useState("");
    const {getAccessTokenSilently} = useAuth0();

    const handleCreateGameButtonClick=async()=>{
        const token=await getAccessTokenSilently();
        props.createGame(token,difficulty)
    }

    return(
        <Col xs={{size:6,offset:3}}>
            <Form>
                <Button disabled={difficulty===""} outline color="success" size="lg" block onClick={handleCreateGameButtonClick}>Create Game</Button>
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
                            <Input onChange={()=>setDifficulty(difficulties.MEDIUM)} id="medium" type="radio" name="difficulty"/>
                            <Label check for="medium">
                                Medium
                            </Label>
                        </FormGroup>
                    </Col>
                    <Col>
                        <FormGroup check>
                            <Input onChange={()=>setDifficulty(difficulties.HARD)} id="hard" type="radio" name="difficulty" />
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

export default CreateGameComponent;