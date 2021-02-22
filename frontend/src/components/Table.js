import React,{useState} from 'react';
import {Container,
        Row,
        Col,
        Card,
        CardBody,
        CardImg,
        CardTitle,
        CardSubtitle,
        CardText,
        Badge,
        Button,
        Modal,
        ModalBody,
        ModalHeader,
        ModalFooter,
        Label,
        Input} from 'reactstrap';

function Table (props){
    const [confirmButtonDisabled,setConfirmButtonDisabled]=useState(true);
    const handleBuying=id=>{
        props.prepareItem(id)
        props.toggleConfirmModal('buy');
    }

    const handlePayment=()=>{
        props.toggleConfirmModal('buy');
        const item=props.itemBeingProcessed
        props.sellItem(item,props.activeUser.id);
    }

    const checkConfirm=evt=>{
        if(evt.target.value==='buy item')
            setConfirmButtonDisabled(false)
        else
            setConfirmButtonDisabled(true);
    }

    const items=props.items.items.filter(item=>item.tableId===props.table.id);

    return (
        <>
            <Modal isOpen={props.confirmModalOpen.buy} toggle={()=>props.toggleConfirmModal('buy')} >
                <ModalHeader toggle={()=>props.toggleConfirmModal('buy')}>Confirm payment</ModalHeader>
                <ModalBody>
                    <p>You pay for this item when you recieve it, return it if it is not as displayed in pictures.</p>
                    <p>Confirming payment and then disappearing will result in you <strong>blacklisted.</strong></p>
                    <Label htmlFor='confirm' >Type <strong className='text-danger'>buy item</strong> to confirm your payment</Label>
                    <Input model='.confirm' id='confirm' className='mb-2' autoComplete='off' onChange={checkConfirm} className='form-control' />
                </ModalBody>
                <ModalFooter>
                <p><small>Make sure the mobile number you provided in your account is correct, we will use it to contact you</small></p>
                <Button disabled={confirmButtonDisabled} color='warning' onClick={handlePayment} >Confirm</Button>
                </ModalFooter>
            </Modal>
            <Modal isOpen={props.confirmedModalOpen.buy} toggle={()=>props.toggleConfirmedModal('buy')}>
                <ModalHeader toggle={()=>props.toggleConfirmedModal('buy')}>Payment Confirmed!</ModalHeader>
                <ModalBody>
                    <h2>Your payment has been <strong>confirmed!</strong></h2>
                    <p>We will reach to you to confirm your address, and we will deliver this item to you in 2 days!</p>
                </ModalBody>
                <Button onClick={()=>props.toggleConfirmedModal('buy')} color='warning'>Okay</Button>
            </Modal>
            <Container>
                <Row>
                    {items.map(item=>{
                        return(
                            <Col xs='12' sm='12' md='6' lg='4' key={item.id} className='my-3' >
                                <Card >
                                    <CardImg width='100%' top src={item.image} alt={item.name} image cap height='300px'/>
                                    <CardBody>
                                        <CardTitle className='font-weight-bold'>{item.name}</CardTitle>
                                        <CardSubtitle><Badge pill color='info'>{item.price} JOD</Badge></CardSubtitle>
                                        <CardText className='cardTextHeight'>{item.description}</CardText>
                                    </CardBody>
                                        <Button onClick={()=>handleBuying(item.id)} color={item.available?'warning':'secondary'} disabled={!item.available} block>{item.available? 'Buy this':'Sold'}</Button>
                                </Card>
                            </Col>
                        )
                    })}
                </Row>
            </Container>

        </>
    )

}

export default Table;