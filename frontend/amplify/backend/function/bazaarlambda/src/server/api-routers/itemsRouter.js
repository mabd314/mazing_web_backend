const express = require('express');
const itemsRouter= express.Router();
const app = require('../../app');

const AWS = require("aws-sdk");
  
const docClient= new AWS.DynamoDB.DocumentClient();

let itemsId=20;

let params;

itemsRouter.get('/',async(req,res)=>{
    params = {
        TableName : "Items"
        }    

    try{
        const items=await docClient.scan(params).promise();
        res.json(items.Items);
    }catch(err){
        console.log(err);
        res.sendStatus(400);
    }
});

itemsRouter.post('/',async(req,res)=>{
    params={
        TableName: 'Items',
        Item:{ ...req.body.item, id:++itemsId}
    }
    try{
        const item=await docClient.put(params).promise();
        console.log(item);
        item.id=itemsId;
        res.json(item);
    }catch(err){
        console.log(err)
        res.sendStatus(400);
    }
});

itemsRouter.put('/:itemId',async(req,res)=>{
    if(!req.query.sell)
        params={
            TableName: 'Items',
            Key:{
                "id":Number(req.params.itemId)
            },
            UpdateExpression:'set price=:p, #nm=:n, description=:d, image=:im, imageName=:imn',
            ExpressionAttributeNames:{
                '#nm': 'name'
            },
            ExpressionAttributeValues: {
                ":p": req.body.price,
                ":n": req.body.name,
                ":d": req.body.description,
                ":im": req.body.image,
                ":imn":req.body.imageName
            },
            ReturnValues:"UPDATED_NEW"
        }    
    else
        params={
            TableName: 'Items',
            Key:{
                "id":Number(req.params.itemId)
            },
            UpdateExpression:'set available=:av, buyerId=:id',
            ExpressionAttributeValues: {
                ":av": false,
                ":id": Number(req.query.buyerId)
            },
            ReturnValues:'UPDATED_NEW'
        }
    try{
        const item= await docClient.update(params).promise();
        console.log(item)
        res.json(item);
    }catch(err){
        console.log(err)
        res.sendStatus(400);
    }
})

itemsRouter.delete('/:itemId',async(req,res)=>{
    params = {
        TableName:'Items',
        Key:{
            id:Number(req.params.itemId)
        },
    };
    try{
        await docClient.delete(params).promise();
        res.sendStatus(204)
    }catch(err){
        console.log(err.message);
        res.status(400).send(err.message);
    }
})


module.exports = itemsRouter;