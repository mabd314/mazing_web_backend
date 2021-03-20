import React from 'react';
import {Card,
        CardBody,
        CardHeader,
        Container,
        Button
    } from 'reactstrap'

import {Accordion} from 'react-bootstrap'
import CommandsHelp from './CommandsHelp';
function HowToPlay (props){
    return(
        <Container >
            <Accordion defaultActiveKey="0">
                <Card>
                    <CardHeader>
                        <Accordion.Toggle className='bg-primary text-dark btn-outline-warning btn-block' as={Button} variant="link" eventKey="0">
                            About
                        </Accordion.Toggle>
                    </CardHeader>
                    <Accordion.Collapse eventKey="0">
                        <CardBody>
                            <p>
                                Mazing is a multiplayer web based game where you start the game in a certain room, and you gather items and use them to make your way out of the maze on time!
                            </p>
                            <p>
                                The maze is made up of several rooms connected by locked or unlocked doors.
                            </p>
                            <p>
                                You win if you are the first one to make their way out of the maze and reach the end room.
                            </p>
                            <p>
                                You lose if you someone beats you to it, or if the time needed to win elapses.
                            </p>
                        </CardBody>
                    </Accordion.Collapse>
                </Card>
                <Card>
                    <CardHeader>
                        <Accordion.Toggle className='bg-primary text-dark btn-outline-warning btn-block' as={Button} variant="link" eventKey="1">
                            Game Play
                        </Accordion.Toggle>
                    </CardHeader>
                    <Accordion.Collapse eventKey="1">
                        <CardBody>
                            <p>The game play is all text based, you execute commands and get responses back</p>
                            <p>There are no graphics or visualisations, but it is part of the fun! It is all in your mind!</p>
                        </CardBody>
                    </Accordion.Collapse>
                </Card>
                <Card>
                    <CardHeader>
                        <Accordion.Toggle className='bg-primary text-dark btn-outline-warning btn-block' as={Button} variant="link" eventKey="2">
                            Commands
                        </Accordion.Toggle>
                    </CardHeader>
                    <Accordion.Collapse eventKey="2">
                        <CardBody>
                            <CommandsHelp/>
                        </CardBody>
                    </Accordion.Collapse>
                </Card>
            </Accordion>      
        </Container>
    )
}

export default HowToPlay;