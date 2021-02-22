import * as actionTypes from './actionTypes';

const carouselAnimating=(state=[],action)=>{
    switch(action.type){
        case actionTypes.START_CAROUSEL_ANIMATING:
            const length=action.payload
            return new Array(length).fill(false);
        case actionTypes.SET_ANIMATING:
            const newState=state.slice(0);
            newState[action.payload.index]=action.payload.bool
            return newState;
        default:
            return state;
    }
}

export default carouselAnimating;