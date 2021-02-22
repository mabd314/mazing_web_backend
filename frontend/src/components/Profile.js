import React,{Component} from 'react';
import {
    Card,
    CardBody,
    CardHeader,
    CardImg,
    Button,
    Container,
    Row,
    Col,
    Label,
    Modal,
    ModalBody,
    ModalHeader,
    Tooltip,


} from 'reactstrap';

import {
    Errors,
    Control,
    LocalForm
} from 'react-redux-form';

import {validEmail,isNumber,minLength,maxLength,required} from '../util/validators'
import {Link} from 'react-router-dom';
class Profile extends Component{
    constructor(props){
        super(props);
        this.state={
            editingModalOpen: false,
            user: props.user,
            confirmPicture: false,
            tooltips:{
                name:false,
                phone:false,
                email: false,
                image: false
            }
        }
    }

    render(){
        const toggleTooltip=(type)=>this.setState({tooltips:{...this.state.tooltips,[type]:!this.state.tooltips[type]}});

        const handleSumbit=values=>{
            toggleEditingModal();
            const sent={...this.state.user,...values};
            this.props.editUser(sent);
        }

        const toggleEditingModal=()=>this.setState({editingModalOpen:!this.state.editingModalOpen});

        const soldItems=this.props.items.filter(item=>{
            if(item.tableId===this.props.user.tableId&&item.available===false)
                return true
        })
        const countSold=soldItems.length;
        const boughtItems=this.props.items.filter(item=>{
            if(item.buyerId===this.props.user.id)
                return true
        })
        const countBought=boughtItems.length;
        const totalSold=soldItems.reduce((acc,current)=>acc+current.price,0);
        const totalBought=boughtItems.reduce((acc,current)=>acc+current.price,0);
        const total=totalSold-totalBought-5;
        console.log(total)
        console.log(totalSold);
        console.log(totalBought);
        const NoTable=()=>{
            return(
                <>
                    <h4>You do not have a table for today</h4>
                    <h5 className='mt-5'>Click the Rent table button up there to rent a table</h5>
                    <Row className='mt-5'>
                        <Col xs='6'>
                            <h6>Number of bought Items:</h6>
                        </Col>
                        <Col xs='6'>
                            <h6><strong>{countBought}</strong></h6>
                        </Col>
                    </Row>
                    <Row className='mt-5'>
                        <Col xs='6'>
                            <h6>Bought Items total worth:</h6>
                        </Col>
                        <Col xs='6'>
                            <h6><strong>{totalBought} JOD</strong></h6>
                        </Col>
                    </Row>
                    <Row className='mt-5'>
                        <Col xs='6'>
                            <h6>Total:</h6>
                        </Col>
                        <Col xs='6'>
                            <h6><strong>{total+5>0?`We owe you ${total+5} JOD!`: `You owe us ${-1*(total+5)} JOD`}</strong></h6>
                        </Col>
                    </Row>

                </>
            )
        }
    
        const YesTable=()=>{
            return(
                <>
                    <Row className='mt-5'>
                        <Col xs='6'>
                            <h6>Number of sold items:</h6>
                        </Col>
                        <Col xs='6'>
                            <h6><strong>{countSold}</strong></h6>
                        </Col>
                    </Row>
                    <Row className='mt-5'>
                        <Col xs='6'>
                            <h6>Sold Items total worth:</h6>
                        </Col>
                        <Col xs='6'>
                            <h6><strong>{totalSold} JOD</strong></h6>
                        </Col>
                    </Row>
                    <Row className='mt-5'>
                        <Col xs='6'>
                            <h6>Number of bought Items:</h6>
                        </Col>
                        <Col xs='6'>
                            <h6><strong>{countBought}</strong></h6>
                        </Col>
                    </Row>
                    <Row className='mt-5'>
                        <Col xs='6'>
                            <h6>Bought Items total worth:</h6>
                        </Col>
                        <Col xs='6'>
                            <h6><strong>{totalBought} JOD</strong></h6>
                        </Col>
                    </Row>
                    <Row className='mt-5'>
                        <Col xs='6'>
                            <h6>Table Reservation Cost:</h6>
                        </Col>
                        <Col xs='6'>
                            <h6><strong>5 JOD</strong></h6>
                        </Col>
                    </Row>
                    <Row className='mt-5'>
                        <Col xs='6'>
                            <h6>Total:</h6>
                        </Col>
                        <Col xs='6'>
                            <h6><strong>{total>0?`We owe you ${total} JOD!`: `You owe us ${-1*total} JOD`}</strong></h6>
                        </Col>
                    </Row>
                </>
            )
        }
        return(
            <>
                <Modal isOpen={this.state.editingModalOpen} toggle={toggleEditingModal}>
                    <ModalHeader toggle={toggleEditingModal}>Edit item</ModalHeader>
                    <ModalBody>
                        <LocalForm initialState={this.state.user} onSubmit={values=>handleSumbit(values)}>
                            <Row className='form-group'>
                                <Label className='py-auto my-auto' htmlFor='name' sm='3' id='nameLabel'>Name</Label>
                                <Tooltip placement='left' toggle={()=>toggleTooltip('name')} isOpen={this.state.tooltips.name} target='nameLabel'>Choose a nice nickname</Tooltip>
                                <Col sm='9'>
                                    <Control.text model='.name' id='name' name='name' className='form-control' value={this.state.user.name} onChange={(evt)=>this.setState({user: {...this.state.user,name: evt.target.value}})}
                                    validators={{
                                        required,
                                        maxLength: maxLength(15)
                                    }} />
                                    
                                    <Errors className='text-danger' model='.name' show='touched' component='li' messages={{
                                        required: 'Required',
                                        maxLength: 'Name can not be more than 15 characters'
                                    }} />


                                </Col>
                            </Row>  
                            <Row className='form-group'>
                                <Label className='py-auto my-auto' htmlFor='email' sm='3' id='emailLabel'>Email</Label>
                                <Tooltip placement='left' toggle={()=>toggleTooltip('email')} isOpen={this.state.tooltips.email} target='emailLabel'>Choose a valid email address</Tooltip>
                                <Col sm='9'>
                                    <Control.text model='.email' id='email' name='email' className='form-control'  value={this.state.user.email} onChange={(evt)=>this.setState({user: {...this.state.user,email: evt.target.value}})}
                                    validators={{
                                        required,
                                        validEmail
                                    }} />

                                    <Errors className='text-danger' model='.email' show='touched' component='li' messages={{
                                        required: 'Required',
                                        validEmail: 'Choose a valid email address'
                                    }}/>
                                </Col>
                            </Row>                   
                            <Row className='form-group'>
                                <Label className='py-auto my-auto' htmlFor='phone' sm='3' id='phoneLabel'>Phone Number</Label>
                                <Tooltip placement='left' toggle={()=>toggleTooltip('phone')} isOpen={this.state.tooltips.phone} target='phoneLabel'>Choose a working mobile phone number, we will use it to contact you</Tooltip>
                                <Col sm='9'>
                                    <Control.text model='.phone' id='phone' name='phone' className='form-control'  value={this.state.user.phone} onChange={(evt)=>this.setState({user: {...this.state.user,phone: evt.target.value}})}
                                    validators={{
                                        required,
                                        isNumber,
                                        minLength: minLength(6),
                                        maxLength: maxLength(15)
                                    }} />

                                    <Errors className='text-danger' model='.phone' show='touched' component='li' messages={{
                                        required: 'Required',
                                        isNumber: 'Choose a valid mobile number',
                                        minLength: 'Can not be less than 6 numbers',
                                        maxLength: 'Can not be more than 15 number'
                                    }}/>
                                </Col>
                            </Row>
                            <Row className='form-group'>
                                <Label className='py-auto my-auto' htmlFor='file' sm='3' id='imageLabel'>Change Image</Label>
                                    <Tooltip placement='left' toggle={()=>toggleTooltip('image')} isOpen={this.state.tooltips.image} target='imageLabel'>Upload a new image only if you want to change the original</Tooltip>
                                <Col sm='9'>
                                    <Control.file model='.file' id='file' name='file' className='form-control-file'/>
                                </Col>
                            </Row>                         
                            <h5 className='mb-3'>Address Information:</h5> 
                            <Row className='form-group'>
                                <Label className='py-auto my-auto' htmlFor='city' sm='3' id='city'>City</Label>
                                <Col sm='9'>
                                    <Control.text model='.address.city' id='city' name='city' className='form-control'  value={this.state.user.address.city} onChange={(evt)=>this.setState({user: {...this.state.user,address:{...this.state.address,city:evt.target.value}}})}/>
                                </Col>
                            </Row>                      
                            <Row className='form-group'>
                                <Label className='py-auto my-auto' htmlFor='area' sm='3' id='area'>Area</Label>
                                <Col sm='9'>
                                    <Control.text model='.address.area' id='area' name='area' className='form-control'  value={this.state.user.address.area} onChange={(evt)=>this.setState({user: {...this.state.user,address:{...this.state.address,area:evt.target.value}}})}/>
                                </Col>
                            </Row>                                  
                            <Row className='form-group'>
                                <Label className='py-auto my-auto' htmlFor='street' sm='3' id='street'>Street</Label>
                                <Col sm='9'>
                                    <Control.text model='.address.street' id='street' name='street' className='form-control'  value={this.state.user.address.street} onChange={(evt)=>this.setState({user: {...this.state.user,address:{...this.state.address,street:evt.target.value}}})}/>
                                </Col>
                            </Row>                                              
                            <Row className='form-group'>
                                <Label className='py-auto my-auto' htmlFor='house' sm='3' id='house'>House</Label>
                                <Col sm='9'>
                                    <Control.text model='.address.house' id='house' name='house' className='form-control'  value={this.state.user.address.house} onChange={(evt)=>this.setState({user: {...this.state.user,address:{...this.state.address,house:evt.target.value}}})}/>
                                </Col>
                            </Row>
                            <Row className='form-group'>
                                <Label className='py-auto my-auto' htmlFor='description' sm='3' id='description'>Description</Label>
                                <Col sm='9'>
                                    <Control.textarea model='.address.description' id='description' name='description' className='form-control'  value={this.state.user.address.description} onChange={(evt)=>this.setState({user: {...this.state.user,address:{...this.state.address,description:evt.target.value}}})}/>
                                </Col>
                            </Row>                                        
                            <Button block color='warning' type='submit'>Save</Button>
                        </LocalForm>
                    </ModalBody>
                </Modal>
                <Container>
                    <Row>
                        <Col xs='12' md='6'>
                            <Card>
                                <CardHeader><h3>Your Profile</h3></CardHeader>
                                <CardBody className='container cardHeight'>
                                    <Row>
                                        <Col xs='6'>
                                            <CardImg className='mediaImg' src={this.props.user.image} />
                                        </Col>
                                    </Row>
                                    <Row className='mt-3'>
                                        <Col xs='12' className='mx-auto'>
                                            <h6><strong>Name:</strong> {this.props.user.name}</h6>
                                        </Col>
                                    </Row>
                                    <Row className='mt-3'>
                                        <Col xs='12' className='mx-auto'>
                                            <h6><strong>Phone Number:</strong> {this.props.user.phone}</h6>
                                        </Col>
                                    </Row>
                                    <Row className='mt-3'>
                                        <Col xs='12' className='mx-auto'>
                                            <h6><strong>Email:</strong> {this.props.user.email}</h6>
                                        </Col>
                                    </Row>
                                    <Row className='mt-3'>
                                        <Col xs='12' className='mx-auto'>
                                            <h4>Address Information:</h4>
                                            <h6><strong>City:</strong> {this.props.user.address.city}</h6>
                                            <h6><strong>Area:</strong> {this.props.user.address.area}</h6>
                                            <h6><strong>Street:</strong> {this.props.user.address.street}</h6>
                                            <h6><strong>House:</strong> {this.props.user.address.house}</h6>
                                            <h6><strong>description:</strong> {this.props.user.address.description}</h6>
                                        </Col>
                                    </Row>
                                    <Row className='mt-5'>
                                        <Button outline color='warning' block onClick={toggleEditingModal}>Edit Profile</Button>
                                    </Row>
                                </CardBody>
                            </Card>
                        </Col>
                        <Col xs='12' md='6'>
                            <Card>
                                <CardHeader><h3>Money activity</h3></CardHeader>
                                <CardBody className='cardHeight'>
                                    {this.props.user.tableId===0? <NoTable/>: <YesTable/>}
                                </CardBody>
                            </Card>             
                        </Col>
                    </Row>
                </Container>

            </>
        )
    
    }
}

export default Profile;