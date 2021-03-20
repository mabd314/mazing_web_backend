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
import LogoutButton from './LogoutButton';
import LoginButton from './LoginButton';
import { useAuth0 } from '@auth0/auth0-react';
  
function Header (props){
    const [isNavOpen,setIsNavOpen]=useState(false);
    const toggleNav=()=>setIsNavOpen(!isNavOpen);
    const {isAuthenticated}=useAuth0();
    return(
        <Navbar color='light' expand='sm' light fixed='top'>
            <NavbarBrand href='/home'>
                <img src="logo.png" alt='logo' width='120px' />
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
                        <NavLink className='nav-link' to='/how'>
                            <span className='fa fa-info fa-lg'></span> How To Play
                        </NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink className='nav-link' to='/contact'>
                            <span className='fa fa-id-card fa-lg'></span> Contact
                        </NavLink>
                    </NavItem>
                    <NavItem>
                        <NavLink className='nav-link' to='/play'>
                            <span className='fa fa-gamepad fa-lg'></span> Play
                        </NavLink>
                    </NavItem>
                </Nav>
                <Nav className='ml-auto' navbar>
                    <NavItem>
                        {isAuthenticated?<LogoutButton/>:<LoginButton/>}
                    </NavItem>
                </Nav>
            </Collapse>
        </Navbar>
    )
}



export default Header;