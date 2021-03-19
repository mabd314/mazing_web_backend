import React from 'react';
import {Row,
    Col,
    Container} from 'reactstrap';

function Footer(props) {
    return(
    <div className="footer">
        <Container className='mt-5'>
            <Row className="row justify-content-center">             
                <Col xs='12' className="align-self-center">
                    <div className="text-center">
                        <a className="btn btn-social-icon btn-google mx-1 px-1" href="http://google.com/+"><i className="fa fa-google-plus"></i></a>
                        <a className="btn btn-social-icon btn-facebook mx-1 px-1" href="http://www.facebook.com/profile.php?id="><i className="fa fa-facebook"></i></a>
                        <a className="btn btn-social-icon btn-linkedin mx-1 px-1" href="http://www.linkedin.com/in/"><i className="fa fa-linkedin"></i></a>
                        <a className="btn btn-social-icon btn-twitter mx-1 px-1" href="http://twitter.com/"><i className="fa fa-twitter"></i></a>
                        <a className="btn btn-social-icon btn-google mx-1 px-1" href="http://youtube.com/"><i className="fa fa-youtube"></i></a>
                    </div>
                </Col>
            </Row>
            <Row className="row justify-content-center">             
                <Col xs='auto'>
                    <p>© Copyright 2021 Mazing</p>
                </Col>
            </Row>
        </Container>
    </div>
    )
}

export default Footer;