import * as actionTypes from './actionTypes';

const items=(state={
    items:[],
    isLoading: true,
    errMess: null
},action)=>{
    let values
    let item;
    let id;
    let changes;
    switch(action.type){
        case actionTypes.ITEMS_LOADING:
            return {...state, isLoading: true};
        case actionTypes.ITEMS_FAILED:
            return {...state, isLoading: false, errMess: action.payload};
        case actionTypes.ADD_ITEMS:
            return {...state, items: action.payload, isLoading: false, errMess: null};
        case actionTypes.ADD_ITEM:
            return {...state,items:state.items.concat(action.payload)};
        case actionTypes.EDIT_ITEM:
            changes=action.payload.changes;
            id=action.payload.id
            return {...state, items:
                state.items.map(item=>{
                    if(item.id!==id)
                        return item;
                    else{
                        return {...item,...changes};
                    }
                })};
        case actionTypes.SELL_ITEM:
            return {...state, items:
                state.items.map(item=>{
                    if(item.id===action.payload.itemId)
                        return {...item,available: false, buyerId:action.payload.buyerId}
                    else 
                        return item;
                })};
        case actionTypes.REMOVE_ITEM:
            return {...state, items: 
                state.items.filter(item=>item.id!==action.payload)};
        default:
            return state;
    }
}

export default items;