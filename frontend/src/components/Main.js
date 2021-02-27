import react,{Component} from 'react';

import Game from './Game';
import Play from './Play';
import {Switch,
    Redirect,
    Route,
    withRouter} from 'react-router-dom';


import {connect} from 'react-redux';

import {startGame,
        executeCommand,
        editCommand} from '../redux/actionCreators'


const mapStateToProps=state=>({
    game:state.game,
    response:state.response,
    commandText:state.commandText
})

const mapDispatchToProps=dispatch=>({
    startGame: ()=>dispatch(startGame()),
    executeCommand: query=>dispatch(executeCommand(query)),
    editCommand: newCommandText=>dispatch(editCommand(newCommandText))
})
        
class Main extends Component{

    render(){
        return(
            <Switch>
                <Route path='/game'>
                    <Game executeCommand={this.props.executeCommand} commandText={this.props.commandText} editCommand={this.props.editCommand} response={this.props.response} startGame={this.props.startGame} game={this.props.game}/>
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
            <Redirect to='/game'/>
        </Switch>
        )
    }
}

export default withRouter(connect(mapStateToProps,mapDispatchToProps)(Main));
