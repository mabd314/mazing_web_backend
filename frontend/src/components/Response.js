import react from 'react';
import response from '../redux/response';
import {
    Row,
    Col,
} from 'reactstrap'

function Play(props){
    if(props.response.isLoading)
    return <h1>Loading...</h1>
    return(
        <Row>
        <Col xs='12' className='mt-5'>
            <p>
                Type: {props.response.type}
                <br/>
                description: {props.response.description}
            </p>
        </Col>
        </Row>
    )
}

export default Play;