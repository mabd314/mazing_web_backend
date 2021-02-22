import React from 'react';
import {Card,
        CardImg,
        CardBody,
        CardImgOverlay,
        CardHeader,
        CardTitle,
        CardText,
        Container,
        Col,
        Row,
        Collapse,
        Button
    } from 'reactstrap'

import {Accordion} from 'react-bootstrap'
function About (props){
    return(
        <Container >
            <Accordion defaultActiveKey="0">
                <Card>
                    <CardHeader>
                        <Accordion.Toggle className='bg-warning text-dark btn-outline-warning btn-block' as={Button} variant="link" eventKey="0">
                            Idea
                        </Accordion.Toggle>
                    </CardHeader>
                    <Accordion.Collapse eventKey="0">
                        <CardBody>
                            <>
                                <p>Bazaar is a website that is designed to emulate the real-world traditional idea of bazaars, we focus on capturing every element that makes the bazaar the wondrous and magical place that brings people together.</p>
                                <p>The idea was created to help people bring their items to the online world without capitalizing on them, the transaction between the seller and the buyer is in peer to peer fashion, the website only charges for the table rent, the website also allows sellers to add as many items as they like, as long as they rent a table.</p>
                            </>
                        </CardBody>
                    </Accordion.Collapse>
                </Card>
                <Card>
                    <CardHeader>
                        <Accordion.Toggle className='bg-warning text-dark btn-outline-warning btn-block' as={Button} variant="link" eventKey="1">
                            How to participate
                        </Accordion.Toggle>
                    </CardHeader>
                    <Accordion.Collapse eventKey="1">
                        <CardBody>
                            <p>You can participate in the Bazaar as a seller after logging in, where you will have the ability to rent tables to sell your items for 5 JOD a day that you will pay when we come to take your items to their new owners, if you sell items with a total worth less than 5 JOD, your table is for free. Renting a table is a one click process, our website supports a limited number of tables each day so sellers will have to race their way to reserve a table, all tables will be emptied after 24 hours and the process repeats.</p>
                            <p>You can also participate as a buyer, where you will be roaming tables searching for the unknown, each item is unique, buying an item is only a one click process, after which we will contact you and deliver the item to you, you can pay us when you receive it and make sure it is as displayed in pictures, buying an item will immediately mark it as sold.</p>
                        </CardBody>
                    </Accordion.Collapse>
                </Card>
                <Card>
                    <CardHeader>
                        <Accordion.Toggle className='bg-warning text-dark btn-outline-warning btn-block' as={Button} variant="link" eventKey="2">
                            Shoping process
                        </Accordion.Toggle>
                    </CardHeader>
                    <Accordion.Collapse eventKey="2">
                        <CardBody>
                            <p>after renting the table renters can add as many items as they want, they can also edit the items and remove them as they see fit, the users also have the ability to view the tables by entering the bazaar, and if an interesting table catches their eyes, they have the ability to enter the table to view more details and make transactions.</p>
                        </CardBody>
                    </Accordion.Collapse>
                </Card>
            </Accordion>      
        </Container>
    )
}

export default About;