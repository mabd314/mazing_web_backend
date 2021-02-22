import React,{Component} from 'react';
import {Col,
    Row,
    Container,
    Carousel,
    CarouselControl,
    CarouselCaption,
    CarouselItem,
    Media,
    Button} from 'reactstrap';
import {Link} from 'react-router-dom';
      
class Bazaar extends Component{
    render(){
        return (
            <Container>
                {this.props.tables.tables.map((table,tableIndex)=>{
                    const items=this.props.items.items.filter(item=>item.tableId===table.id);
                    return(
                        <div key={table.id} className='my-5'>
                            <Row>
                                <Col xs='12' sm='4'>
                                <Media>
                                    <Media left>
                                        <Media object src={table.ownerImage} alt={table.ownerName} className='mediaImg mr-1'/>
                                    </Media>
                                    <Media body>
                                        <Media heading>
                                        {table.ownerName}         
                                    </Media>
                                        <Media bottom>
                                            <Link className='card-link' to={this.props.activeUser.tableId!==table.id? '/bazaar/'+table.id: '/sell'} onClick={()=>{document.documentElement.scrollTop = 0;}}>
                                                <Button color='warning' outline>Enter table</Button> 
                                            </Link>
                                        </Media>
                                    </Media>
                                </Media>
                                </Col>        
                                <Col xs='12' sm='8'>
                                    <Carousel
                                    activeIndex={this.props.activeIndex[tableIndex]}
                                    next={()=>this.props.carouselNext(items.length,tableIndex)}
                                    previous={()=>this.props.carouselPrev(items.length,tableIndex)} >
                                        {items.map(item=>{
                                            return(
                                                <CarouselItem onExiting={() => this.props.setAnimating(true,tableIndex)}
                                                onExited={() => this.props.setAnimating(false,tableIndex)}
                                                key={item.id} 
                                                className='carouselHeight'>
                                                    <img src={item.image} alt={item.name} className='w-100 h-100'/>
                                                    <CarouselCaption className='d-block' captionText={'JOD '+item.price} captionHeader={item.name}/>
                                                </CarouselItem>
                                            )
                                        })}
                                        <CarouselControl direction="prev" directionText="Previous" onClickHandler={()=>this.props.carouselPrev(items.length,tableIndex)} />
                                        <CarouselControl direction="next" directionText="Next" onClickHandler={()=>this.props.carouselNext(items.length,tableIndex)} />
                                    </Carousel>
                                </Col>
                            </Row>
                            <hr/>
                        </div>
                    )
                })}
            </Container>
        )
    
    }
}

export default Bazaar;

