import react,{Component} from 'react';

import {Switch,
    Redirect,
    Route,
    withRouter} from 'react-router-dom';


import {connect} from 'react-redux';

import {startGame,
        executeCommand,
        editCommand,
        fetchGames,
        chooseGame,
        choosePlayer,
        leaveGame,
    } from '../redux/actionCreators'

import Play from './Play';

const mapStateToProps=state=>({
    response:state.response,
    commandText:state.commandText,
    games:state.games,
    activePlayer:state.activePlayer,
})

const mapDispatchToProps=dispatch=>({
    startGame: gameId=>dispatch(startGame(gameId)),
    executeCommand: (query,userName)=>dispatch(executeCommand(query,userName)),
    editCommand: newCommandText=>dispatch(editCommand(newCommandText)),
    fetchGames: ()=>dispatch(fetchGames()),
    chooseGame: (userName,gameId)=>dispatch(chooseGame(userName,gameId)),
    choosePlayer:userName=>dispatch(choosePlayer(userName)),
    leaveGame: userName=>dispatch(leaveGame(userName)),
})

        
class Main extends Component{

    // componentDidMount(){
    //     this.props.fetchGames();
    // }

    render(){
        return(
            <Switch>
                <Route path='/play'>
                    <Play leaveGame={this.props.leaveGame} choosePlayer={this.props.choosePlayer} activePlayer={this.props.activePlayer} chooseGame={this.props.chooseGame} games={this.props.games} fetchGames={this.props.fetchGames} executeCommand={this.props.executeCommand} commandText={this.props.commandText} editCommand={this.props.editCommand} response={this.props.response} startGame={this.props.startGame}></Play>
                    {/* <GameArea user={this.props.user} executeCommand={this.props.executeCommand} commandText={this.props.commandText} editCommand={this.props.editCommand} response={this.props.response} startGame={this.props.startGame} game={this.props.game}/> */}
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
