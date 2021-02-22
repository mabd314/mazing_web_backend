import react,{Component} from 'react';

import Play from './Play';

import {Switch,
    Redirect,
    Route,
    withRouter} from 'react-router-dom';


import {connect} from 'react-redux';

import {enable,
        disable} from '../redux/actionCreators'


const mapStateToProps=state=>({
    working:state.working
})

const mapDispatchToProps=dispatch=>({
    enable: ()=>dispatch(enable),
    diable: ()=>dispatch(disable)
})
        
class Main extends Component{

    render(){
        return(
            <Switch>
                <Route path='/play'>
                    <Play/>
                </Route>
            <Route path='/home'>
                {/* <Home/> */}
                home
            </Route>
            <Route path='/profiles'>
                {/* <Profile user={this.props.activeUser} items={this.props.items.items} editUser={this.props.editUser}/> */}
                profiles
            </Route>
            <Route path='/about'>
                {/* <About/> */}
                about
            </Route>
            <Route path='/contact'>
                {/* <Contact/> */}
                contact
            </Route>
            <Route exact path='/bazaar'>
                bazaar
                {/* <Bazaar tables={this.props.tables} setAnimating={this.props.setAnimating}
                carouselNext={this.props.carouselNext} carouselPrev={this.props.carouselPrev}
                animating={this.props.animating} activeIndex={this.props.activeIndex} items={this.props.items} 
                activeUser={this.props.activeUser} /> */}
            </Route>
            <Route path='/sell'>

                {/* <Sell table={this.props.tables.tables.find(table=>table.id===activeUser.tableId)}
                sellItem={this.props.sellItem} removeItem={this.props.removeItem} editItem={this.props.editItem}
                editingModalOpen={this.props.editingModalOpen} toggleEditingModal={this.props.toggleEditingModal}
                 addItem={this.props.addItem} items={this.props.items}  /> */}
            </Route>
            {/* <Route path='/bazaar/:tableId' component={TableWithId}/> */}
            <Redirect to='/play'/>
        </Switch>
        )
    }
}

export default withRouter(connect(mapStateToProps,mapDispatchToProps)(Main));
