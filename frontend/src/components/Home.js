import React from 'react';
import {Card,
        CardBody,
        CardHeader,
        Container,
        Col,
        Row
    } from 'reactstrap'

function Home (props){
    return(
        <Container>
            <Row>
                <Col xs='12' className='text-center mb-5 mt-5'>
                    <h1>Welcome to Bazaar!</h1>
                    <h6>The wonders of people are one click away.</h6>
                </Col>
            </Row>
            <Row className='justify-content-center background-1 align-items-center'>
                <Col xs='12' sm='10' md='8' lg='4'>
                    <Card className='bg-warning mb-5 opac'>
                        <CardHeader><h2>What is a Bazaar</h2></CardHeader>
                        <CardBody>
                            <h3>A bazaar is a permanently enclosed marketplace or street where goods and services are exchanged or sold.</h3>
                        </CardBody>
                    </Card>
                </Col>
                <Col xs='12' sm='10' md='8' lg='4'>
                    <Card className='bg-warning mb-5 opac'>
                        <CardHeader><h2>Our Mission</h2></CardHeader>
                        <CardBody>
                            <h3>We want you to get the extraordinary experience a bazaar in the digital world.</h3>
                        </CardBody>
                    </Card>
                </Col>
                <Col xs='12' sm='10' md='8' lg='4'>
                    <Card className='bg-warning mb-5 opac'>
                        <CardHeader><h2>How it works</h2></CardHeader>
                        <CardBody>
                            <h3>Explore the wonders of our bazaar either by wandering around the table or by renting one of them.</h3>
                        </CardBody>
                    </Card>
                </Col>
            </Row>
        </Container>
    )
}

export default Home;