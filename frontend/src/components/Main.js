import React,{Component} from 'react';
import Header from './Header';
import Footer from './Footer';
import Home from './Home';
import About from './About';
import Contact from './Contact';
import Profile from './Profile';
import Bazaar from './Bazaar';
import Sell from './Sell';
import Table from './Table';

import {Switch,
    Redirect,
    Route,
    withRouter} from 'react-router-dom';

import {Modal,
    ModalBody,
    ModalHeader,
    ModalFooter,
    Row,
    Col,
    Container,
    Button
} from 'reactstrap'

import {connect} from 'react-redux';
import {setAnimating,
        carouselNext,
        carouselPrev,
        startCarouselAnimating,
        startCarouselIndex,
        sellItem,
        prepareItem,
        toggleConfirmModal,
        toggleConfirmedModal,
        editItem,
        removeItem,
        toggleEditingModal,
        addItem,
        fetchItems,
        fetchTables,
        loadActiveUser,
        addTable,
        editUser,
        toggleSignModal} from '../redux/actionCreators'

const mapStateToProps=state=>({
    animating: state.carouselAnimating,
    activeIndex: state.carouselActiveIndex,
    users: state.users,
    tables: state.tables,
    confirmModalOpen: state.confirmModalOpen,
    confirmedModalOpen: state.confirmedModalOpen,
    itemBeingProcessed: state.itemBeingProcessed,
    editingModalOpen: state.editingModalOpenm,
    items: state.items,
    activeUser: state.activeUser,
    signModalOpen: state.signModalOpen
})

const mapDispatchToProps=dispatch=>({
    setAnimating: (bool,index)=>dispatch(setAnimating(bool,index)),
    carouselNext: (length,index)=>dispatch(carouselNext(length,index)),
    carouselPrev: (length,index)=>dispatch(carouselPrev(length,index)),
    startCarouselAnimating: (length)=>dispatch(startCarouselAnimating(length)),
    startCarouselIndex: (length)=>dispatch(startCarouselIndex(length)),
    sellItem: (itemId,buyerId)=>dispatch(sellItem(itemId,buyerId)),
    toggleConfirmModal: t=>dispatch(toggleConfirmModal(t)),
    toggleConfirmedModal: t=>dispatch(toggleConfirmedModal(t)),
    prepareItem: id=>dispatch(prepareItem(id)),
    editItem: sent=>dispatch(editItem(sent)),
    removeItem: item=>dispatch(removeItem(item)),
    toggleEditingModal: ()=>dispatch(toggleEditingModal()),
    addItem: (tableId,values)=>dispatch(addItem(tableId,values)),
    fetchItems: ()=>dispatch(fetchItems()),
    fetchTables: ()=>dispatch(fetchTables()),
    loadActiveUser: id=>dispatch(loadActiveUser(id)),
    addTable: user=>dispatch(addTable(user)),
    editUser: sent=>dispatch(editUser(sent)),
    toggleSignModal: ()=>dispatch(toggleSignModal())
})

class Main extends Component{
    componentDidMount(){
        this.props.fetchTables();
        this.props.fetchItems();
        // this.props.loadActiveUser(10);
    }
    render(){
        const activeUser=this.props.activeUser
        const TableWithId=({match})=><Table table={this.props.tables.tables.find(table=>table.id===Number(match.params.tableId))} 
        sellItem={this.props.sellItem} 
        toggleConfirmModal={this.props.toggleConfirmModal}
        toggleConfirmedModal={this.props.toggleConfirmedModal}
        prepareItem={this.props.prepareItem}
        confirmModalOpen={this.props.confirmModalOpen}
        confirmedModalOpen={this.props.confirmedModalOpen}
        itemBeingProcessed={this.props.itemBeingProcessed}
        items={this.props.items} 
        activeUser={this.props.activeUser}   />
        if(this.props.tables.isLoading||this.props.items.isLoading)
            return null
        else if(this.props.tables.errMess||this.props.activeUser.loadingInfo.errMess||this.props.items.errMess)
            return (
                <div>
                    <h1>Something is wrong right now, we are definetly working on it, return later!</h1>
                    <h5>Thank you for your patience...</h5>
                </div>
            )
        return(
            <>
                <Modal isOpen={this.props.signModalOpen}>
                    <ModalHeader>Choose a fictional person to sign in with</ModalHeader>
                    <ModalBody>
                        <Row className='mb-5'>
                            <Col xs='6'>
                                <Button block color='primary' onClick={()=>{this.props.loadActiveUser(232); this.props.toggleSignModal()}}>Sobhi</Button>
                            </Col>
                            <Col xs='6'>
                                <Button block color='primary' onClick={()=>{this.props.loadActiveUser(12345); this.props.toggleSignModal()}}>Moe</Button>
                            </Col>
                        </Row>
                        <Row>
                            <Col xs='6'>
                                <Button block color='primary' onClick={()=>{this.props.loadActiveUser(3); this.props.toggleSignModal()}}>Sara</Button>
                            </Col>
                            <Col xs='6'>
                                <Button block color='primary' onClick={()=>{this.props.loadActiveUser(10); this.props.toggleSignModal()}}>House</Button>
                            </Col>
                        </Row>
                    </ModalBody>
                    <ModalFooter>
                        <p>This is only a demo for you to walk through the website and test its features, all features are connected to a well designed server which itself is connected to a well defined database!</p>
                        <p>These 4 fake users are defined in a table in dynamodb, choosing one of them will load the website from their perspective, initially non of them has rented a table, try letting them interact with each other</p>
                        <p>The changes you make with these characters will be commited to the database, refreshing the page will not undo these changes.</p>
                        <p>Clicking the logo will take you to this sign in modal again, we know it is not professional but it is only a demo, the real website would have a sign in and authentication process, actually that is the only thing that is missing.</p>
                        <p>Refreshing the page while not on home page will result in some errors, these errors are a result of implementing this demo only, the website itself is all fine.</p>
                        <p>The names in this fake sign in window are hardcoded, which means that they are static and would not change with profile editing. This does not reflect the quality of the website itself, please feel free to prove it yourselves.</p>
                    </ModalFooter>
                </Modal>
                <Header user={activeUser} 
                toggleConfirmModal={this.props.toggleConfirmModal}
                toggleConfirmedModal={this.props.toggleConfirmedModal}
                confirmModalOpen={this.props.confirmModalOpen}
                confirmedModalOpen={this.props.confirmedModalOpen}
                addTable={this.props.addTable} />
                <Switch>
                    <Route path='/home'>
                        <Home/>
                    </Route>
                    <Route path='/profiles'>
                        <Profile user={this.props.activeUser} items={this.props.items.items} editUser={this.props.editUser}/>
                    </Route>
                    <Route path='/about'>
                        <About/>
                    </Route>
                    <Route path='/contact'>
                        <Contact/>
                    </Route>
                    <Route exact path='/bazaar'>
                        <Bazaar tables={this.props.tables} setAnimating={this.props.setAnimating}
                        carouselNext={this.props.carouselNext} carouselPrev={this.props.carouselPrev}
                        animating={this.props.animating} activeIndex={this.props.activeIndex} items={this.props.items} 
                        activeUser={this.props.activeUser} />
                    </Route>
                    <Route path='/sell'>
                        <Sell table={this.props.tables.tables.find(table=>table.id===activeUser.tableId)}
                        sellItem={this.props.sellItem} removeItem={this.props.removeItem} editItem={this.props.editItem}
                        editingModalOpen={this.props.editingModalOpen} toggleEditingModal={this.props.toggleEditingModal}
                         addItem={this.props.addItem} items={this.props.items}  />
                    </Route>
                    <Route path='/bazaar/:tableId' component={TableWithId}/>
                    <Redirect to='/home/'/>
                </Switch>
                <Footer/>
            </>
        );
    }
}



export default withRouter(connect(mapStateToProps,mapDispatchToProps)(Main));




