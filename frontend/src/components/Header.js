import React,{useState} from 'react';
import {
    Collapse,
    Navbar,
    NavbarToggler,
    NavbarBrand,
    Nav,
    NavItem,
    Jumbotron,
    Container,
    Row,
    Col,
    Button,
    Modal,
    ModalBody,
    ModalHeader,
    Input,
    Label,
    ModalFooter
  } from 'reactstrap';

import {NavLink,Link} from 'react-router-dom';
  
function Header (props){
    const [confirmButtonDisabled,setConfirmButtonDisabled]=useState(true);

    const handleRental=()=>{
        props.toggleConfirmModal('rent');
        props.addTable(props.user);
    }

    const checkConfirm=evt=>{
        if(evt.target.value==='rent table')
            setConfirmButtonDisabled(false)
        else
            setConfirmButtonDisabled(true);
    }

    const [isNavOpen,setIsNavOpen]=useState(false);
    const toggleNav=()=>setIsNavOpen(!isNavOpen);
    const rentTable=(evt)=>{
        if(props.user.tableId===0){
            evt.preventDefault();
            props.toggleConfirmModal('rent');
        }
        else
            return;
    }
    return(
        <>
            <Modal isOpen={props.confirmModalOpen.rent} toggle={()=>props.toggleConfirmModal('rent')} >
                <ModalHeader toggle={()=>props.toggleConfirmModal('rent')}>Confirm renting</ModalHeader>
                <ModalBody>
                    <p>Renting a table costs 5 JOD all inclusive, you pay when we to take your sold items to their new owners.</p>
                    <p>If you get less than 5 JOD worth of money from your sold items, we will <strong>not</strong> charge you anything!</p>
                    <Label htmlFor='confirm' >Type <strong className='text-danger'>rent table</strong> to confirm your payment</Label>
                    <Input model='.confirm' id='confirm' className='mb-2' autoComplete='off' onChange={checkConfirm} className='form-control' />
                </ModalBody>
                <ModalFooter>
                <p><small>Make sure the mobile number you provided in your account is correct, we will use it to contact you</small></p>
                <Button disabled={confirmButtonDisabled} color='warning' onClick={handleRental} >Confirm</Button>
                </ModalFooter>
            </Modal>
            <Modal isOpen={props.confirmedModalOpen.rent} toggle={()=>props.toggleConfirmedModal('rent')}>
                <ModalHeader toggle={()=>props.toggleConfirmedModal('rent')}>Rental Confirmed!</ModalHeader>
                <ModalBody>
                    <h2>You have rented a table <strong> successfully!</strong></h2>
                    <p>We will reach to you to confirm your address, and we will deliver all your sold items!</p>
                </ModalBody>
                <ModalFooter>
                    <p className='mr-auto'><small>Click <Link onClick={()=>props.toggleConfirmedModal('rent')} to='/sell'>here</Link> to go to your table and start adding items!</small></p>
                </ModalFooter>
                <Button onClick={()=>props.toggleConfirmedModal('rent')} color='warning'>Okay</Button>
            </Modal>
            <Navbar color='light' expand='sm' light fixed='top'>
                <NavbarBrand href='/home'>
                    <img src="https://bazaarbucket113324-dev.s3.amazonaws.com/public/logo.png" alt='logo' width='120px' />
                </NavbarBrand>
                <NavbarToggler onClick={toggleNav}/>
                <Collapse isOpen={isNavOpen} navbar>
                    <Nav className='mx-auto' navbar>
                        <NavItem>
                            <NavLink className='nav-link' to='/home'>
                                <span className='fa fa-home fa-lg'></span> Home
                            </NavLink>
                        </NavItem>
                        <NavItem>
                            <NavLink className='nav-link' to='/about'>
                                <span className='fa fa-info fa-lg'></span> About
                            </NavLink>
                        </NavItem>
                        {/* <NavItem>
                            <NavLink className='nav-link' to='/contact'>
                                <span className='fa fa-id-card fa-lg'></span> Contact Us
                            </NavLink>
                        </NavItem> */}
                    </Nav>
                    <Nav className='ml-auto' navbar>
                        <NavItem>
                            <NavLink className='nav-link' to={'/profiles/'+props.user.id}>
                                <span className='fa fa-user fa-lg'></span> {props.user.name}
                            </NavLink>
                        </NavItem>
                    </Nav>
                </Collapse>
            </Navbar>
            <Jumbotron className='jubmotron fluid-jumbotron'>
                <Container>
                    <Row className='align-items-center jumbotron-row'>
                        <Col xs='12' sm='4'>
                            <h1 className='font-weight-bold'>Bazaar</h1>
                            <p className='font-italic'>We merge the beauty of tradition with the flexibility of technology! </p>
                        </Col>
                        <Col xs='12' sm='4'>
                            <Link to='/bazaar' className='nav-link'>
                            <Button className='jumbButton' outline color='success' block ><span className='fa fa-shopping-cart fa-lg'></span> Enter Bazaar</Button>
                            </Link>
                        </Col>
                        <Col xs='12' sm='4'>
                            <Link onClick={rentTable} to='/sell' className='nav-link'>
                                <Button className='jumbButton' outline color='info' block >
                                    <span className='fa fa-dollar fa-lg'></span> {props.user.tableId===0?'Rent a table':'Go to your table'}
                                </Button>
                            </Link>
                        </Col>
                    </Row>
                </Container>
            </Jumbotron>
        </>
    )
}



export default Header;