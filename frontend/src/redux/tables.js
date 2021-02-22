import * as actionTypes from './actionTypes';
let changes;
let id;
const tables=(state={
    tables:[],
    isLoading:true,
    errMess: null,
    },action)=>{
    switch(action.type){
        case actionTypes.TABLES_LOADING:
            return {...state,isLoading: true}
        case actionTypes.TABLES_FAILED:
            return {...state,isLoading: false,errMess: action.payload}
        case actionTypes.ADD_TABLES:
            return {...state,tables:action.payload,isLoading: false,errMess:null}
        case actionTypes.ADD_TABLE:
            return {...state,tables:state.tables.concat(action.payload)};
        case actionTypes.UPDATE_TABLE:
            changes=action.payload.changes;
            id=action.payload.id
            return {...state, tables:
                state.tables.map(table=>{
                    if(table.id!==id)
                        return table;
                    else{
                        return {...table,...changes};
                    }
                })};
        default:
            return state;
            
    }
}

export default tables;