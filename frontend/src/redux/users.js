import * as actionTypes from './actionTypes';

const users=(state={
    users: [],
    isLoading: true,
    errMess: null
},action)=>{
    switch(action.type){
        default:
            return state;
    }
}

export default users;