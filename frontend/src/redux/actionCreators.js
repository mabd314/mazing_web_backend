import * as actionTypes from './actionTypes';
import serverBase from './serverBase';
import {Storage} from 'aws-amplify'

const activeUserId=0;

export const carouselNext=(length,index)=>({
    type: actionTypes.CAROUSEL_NEXT,
    payload:{
        index,
        length
    }
})

export const carouselPrev=(length,index)=>({
    type: actionTypes.CAROUSEL_PREV,
    payload:{
        index,
        length
    }
})

export const setAnimating=(bool,index)=>({
    type: actionTypes.SET_ANIMATING,
    payload:{
        index,
        bool
    }
})

export const startCarouselAnimating=length=>({
    type:actionTypes.START_CAROUSEL_ANIMATING,
    payload: length
})

export const startCarouselIndex=length=>({
    type: actionTypes.START_CAROUSEL_INDEX,
    payload: length
})

export const sellItem=(itemId,buyerId)=>async dispatch=>{
    const response = await fetch(serverBase+'/items/'+itemId+'?'+new URLSearchParams({
        sell: true,
        buyerId
    }),
    {
        method: 'PUT',
    })
    const jsonResponse= await response.json();
    console.log(jsonResponse);
    dispatch({
        type: actionTypes.SELL_ITEM,
        payload: {
            itemId,
            buyerId
        }
    })
    dispatch(toggleConfirmedModal('buy'));

}


export const toggleConfirmModal=(t)=>({
    type : actionTypes.TOGGLE_CONFIRM_MODAL,
    payload:t

})

export const toggleConfirmedModal=(t)=>({
    type: actionTypes.TOGGLE_CONFIRMED_MODAL,
    payload: t
})

export const prepareItem=id=>({
    type: actionTypes.PREPARE_ITEM,
    payload:id
})

export const removeItem=item=>async dispatch=>{
    await Storage.remove(item.imageName);
    await fetch(serverBase+'/items/'+item.id,{
        method: 'DELETE',
    })
    dispatch({
        type:actionTypes.REMOVE_ITEM,
        payload: item.id
    });
}

export const editItem=(sent)=>async dispatch=>{
    let image=sent.image;
    let imageName=sent.imageName
    console.log(sent);
    if(sent.file!==undefined){
        await Storage.remove(imageName);
        await Storage.put(sent.file[0].name,sent.file[0])
        image= await Storage.get(sent.file[0].name); 
        imageName=sent.file[0].name; 
    }
    const id=sent.id
    const data={
        name:sent.name,
        price:sent.price,
        description: sent.description,
        image,
        imageName
    }
    const response = await fetch(serverBase+'/items/'+id,{
        headers: {
            'Content-Type': 'application/json'
        },      
        method: 'PUT',
        body: JSON.stringify(data)
    })
    const jsonResponse= await response.json();
    console.log(jsonResponse);
    const changes=jsonResponse.Attributes;
    dispatch({
        type: actionTypes.EDIT_ITEM,
        payload:{
            changes,
            id
        }
    });
}


export const toggleEditingModal=()=>({
    type: actionTypes.TOGGLE_EDITING_MODAL
})

export const addItem=(tableId,values)=>async dispatch=>{
    let image='/'
    let imageName='/'
    if(values.file!==undefined){
        imageName=values.file[0].name;
        await Storage.put(values.file[0].name,values.file[0])
        image= await Storage.get(values.file[0].name);    
    }
    const data={
        item:{...values}
    }
    console.log(image);
    data.item.image=image;
    data.item.imageName=imageName;
    data.item.available=true;
    data.item.tableId=tableId
    data.item.price=Number(data.item.price);

    const response = await fetch(serverBase+'/items',{
      headers: {
        'Content-Type': 'application/json'
      },
        method: 'POST',
        body: JSON.stringify(data)
    })
    const jsonResponse= await response.json();
    data.item.id=jsonResponse.id;
    dispatch({
        type:actionTypes.ADD_ITEM,
        payload: data.item
    })
}

export const addTables=tables=>({
    type: actionTypes.ADD_TABLES,
    payload: tables
})

export const addItems=items=>({
    type: actionTypes.ADD_ITEMS,
    payload: items
})

export const tablesFailed=errMess=>({
    type: actionTypes.TABLES_FAILED,
    payload: errMess
})

export const itemsFailed=errMess=>({
    type: actionTypes.ITEMS_FAILED,
    payload: errMess
})

export const itemsLoading=()=>({
    type: actionTypes.ITEMS_LOADING
})
export const tablesLoading=()=>({
    type: actionTypes.TABLES_LOADING
})

export const fetchTables=()=>async dispatch=>{
    dispatch(tablesLoading());
    try{
        const response= await fetch(serverBase+'/tables');
        const tables=await response.json();
        if(response.status>=400)
            throw new Error('status code > 400');
        dispatch(addTables(tables));
        dispatch(startCarouselAnimating(tables.length));
        dispatch(startCarouselIndex(tables.length));
        
    }catch(err){
        console.log(err);
        dispatch(tablesFailed(err.message));
    }
}

export const fetchItems=()=>async dispatch=>{
    dispatch(itemsLoading());
    try{
        const response= await fetch(serverBase+'/items');
        const items=await response.json();
        if(response.status>=400)
            throw new Error('status code > 400');
        dispatch(addItems(items));
    }catch(err){
        console.log(err);
        dispatch(itemsFailed(err.message));
    }
}

export const loadActiveUser=id=>async dispatch=>{
    dispatch(activeUserLoading());
    try{
        const response=await fetch(serverBase+'/users/'+id);
        const user=await response.json();
        if(response.status>=400)   
            throw new Error('status code > 400');
        dispatch({
            type: actionTypes.LOAD_ACTIVE_USER,
            payload: user
        });     
    }catch(err){
        console.log(err.message);
        dispatch(activeUserFailed(err.message));
    }
}

export const activeUserFailed=errMess=>({
    type: actionTypes.ACTIVE_USER_FAILED,
    payload: errMess
})

export const activeUserLoading=()=>({
    type: actionTypes.ACTIVE_USER_LOADING
})


export const addTable=(user)=>async dispatch=>{
    const data={
        table:{
            ownerId: user.id,
            ownerName: user.name,
            ownerImage: user.image,
        }
    }
    let response = await fetch(serverBase+'/tables',{
      headers: {
        'Content-Type': 'application/json'
      },
        method: 'POST',
        body: JSON.stringify(data)
    })
    let jsonResponse= await response.json();
    console.log(jsonResponse);
    data.table.id=jsonResponse.id;
    dispatch({
        type:actionTypes.ADD_TABLE,
        payload: data.table
    })
    response = await fetch(serverBase+'/users/'+user.id+'?'+new URLSearchParams({
        rented: true,
        tableId: data.table.id
    }),
    {
        method: 'PUT',
    })
    jsonResponse= await response.json();
    const changes=jsonResponse.Attributes;
    console.log(jsonResponse);
    dispatch({
        type: actionTypes.UPDATE_ACTIVE_USER,
        payload: changes
    })
    dispatch(loadActiveUser(user.id));
    dispatch(fetchTables());
    dispatch(toggleConfirmedModal('rent'));
}

export const editUser=(sent)=>async dispatch=>{
    let image=sent.image;
    let imageName=sent.imageName;
    console.log(sent);
    if(sent.file!==undefined){
        await Storage.remove(imageName);
        await Storage.put(sent.file[0].name,sent.file[0])
        image= await Storage.get(sent.file[0].name);  
        imageName=sent.file[0].name
    }

    const id=sent.id
    let data={...sent,image,imageName}
    let response = await fetch(serverBase+'/users/'+id,{
        headers: {
            'Content-Type': 'application/json'
        },      
        method: 'PUT',
        body: JSON.stringify(data)
    })
    let jsonResponse= await response.json();
    console.log(jsonResponse);
    let changes=jsonResponse.Attributes;
    dispatch({
        type: actionTypes.UPDATE_ACTIVE_USER,
        payload:changes
    });
    data={
        ownerImage:image,
        ownerName:sent.name
    }
    console.log(sent.tableId);
    if(sent.tableId!==0){
        response=await fetch(serverBase+'/tables/'+sent.tableId,{
            headers: {
                'Content-Type': 'application/json'
            },      
            method: 'PUT',
            body: JSON.stringify(data)
        })
        console.log(response);
        jsonResponse=await response.json();
        console.log(jsonResponse);
        changes=jsonResponse.Attributes;
        dispatch({
            type: actionTypes.UPDATE_TABLE,
            payload: {
                changes,
                id: sent.tableId
            }
        })    
    }
}

export const toggleSignModal=()=>({
    type: actionTypes.TOGGLE_SIGN_MODAL
})