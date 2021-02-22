import react from 'react';

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


function Play(props){

    return(
        <Container className='mt-5'>
            <Row>
                <Col xs='12'>
                    <Form>
                        <FormGroup>
                            <Input type="textarea" name="commandText" id="commandText" />
                        </FormGroup>
                        <Button outline color="primary" size="lg" block>Execute</Button>
                    </Form>
                </Col>
            </Row>
        </Container>
    )
}

export default Play;