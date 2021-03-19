import React from 'react';
import {Card,
        CardBody,
        CardHeader,
        Container,
        Button
    } from 'reactstrap'

import {Accordion} from 'react-bootstrap'
function HowToPlay (props){
    return(
        <Container >
            <Accordion defaultActiveKey="0">
                <Card>
                    <CardHeader>
                        <Accordion.Toggle className='bg-primary text-dark btn-outline-warning btn-block' as={Button} variant="link" eventKey="0">
                            lorem ipsum
                        </Accordion.Toggle>
                    </CardHeader>
                    <Accordion.Collapse eventKey="0">
                        <CardBody>
                            <p>lorem ipsum</p>
                        </CardBody>
                    </Accordion.Collapse>
                </Card>
                <Card>
                    <CardHeader>
                        <Accordion.Toggle className='bg-primary text-dark btn-outline-warning btn-block' as={Button} variant="link" eventKey="1">
                            lorem ipsum
                        </Accordion.Toggle>
                    </CardHeader>
                    <Accordion.Collapse eventKey="1">
                        <CardBody>
                            <p>lorem ipsum</p>
                        </CardBody>
                    </Accordion.Collapse>
                </Card>
                <Card>
                    <CardHeader>
                        <Accordion.Toggle className='bg-primary text-dark btn-outline-warning btn-block' as={Button} variant="link" eventKey="2">
                            lorem ipsum
                        </Accordion.Toggle>
                    </CardHeader>
                    <Accordion.Collapse eventKey="2">
                        <CardBody>
                            <p>lorem ipsum</p>
                        </CardBody>
                    </Accordion.Collapse>
                </Card>
            </Accordion>      
        </Container>
    )
}

export default HowToPlay;