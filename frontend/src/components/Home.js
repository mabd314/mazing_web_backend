import React from 'react';
import {Card,
        CardBody,
        CardHeader,
        Container,
        Col,
        Row,
        Jumbotron,
        Button
    } from 'reactstrap'

    import {Link} from 'react-router-dom';

function Home (props){
    return(
        <Container>
            <Jumbotron className='jubmotron fluid-jumbotron'>
                <Container>
                    <Row className='align-items-center jumbotron-row'>
                        <Col xs='12' sm='4'>
                            <h1 className='font-weight-bold'>Mazing</h1>
                            <p className='font-italic'>Can you be the first one to get out? </p>
                        </Col>
                        <Col xs='12' sm={{size:6,offset:1}}>
                            <h1>
                                <Link to='/play' className='nav-link'>
                                    <Button outline color='primary' block ><span className='fa fa-gamepad fa-lg'></span> Play The Game</Button>
                                </Link>
                            </h1>
                        </Col>
                    </Row>
                </Container>
            </Jumbotron>
        </Container>
    )
}

export default Home;