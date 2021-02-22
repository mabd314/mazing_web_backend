const express = require('express');
const usersRouter = express.Router();
const app = require('../../app');

const AWS = require("aws-sdk");

const docClient= new AWS.DynamoDB.DocumentClient();
let params;
let usersId=3;

usersRouter.get('/:userId',async(req,res)=>{
    params={
        TableName:'Users',
        Key:{
            "id":Number(req.params.userId)
        }
    }
    try{
        const user=await docClient.get(params).promise();
        if(user.Count===0)
            throw(new Error('Not Found'))
        res.json(user.Item);
        console.log(user.Item);
    }catch(err){
        res.sendStatus(400);
        console.log(err.message);
    }
})

usersRouter.put('/:userId',async(req,res)=>{
    if(req.query.rented)
        params={
            TableName: 'Users',
            Key:{
                "id":Number(req.params.userId)
            },
            UpdateExpression:'set tableId=:id',
            ExpressionAttributeValues: {
                ":id": Number(req.query.tableId)
            },
            ReturnValues:"UPDATED_NEW"
        }    
    else
        params={
            TableName: 'Users',
            Key:{
                "id":Number(req.params.userId)
            },
            UpdateExpression:'set #nm=:n,email=:e,phone=:p,address.city=:c,address.area=:a,address.street=:s,address.house=:h,address.description=:ds, image=:im, imageName=:imn',
            ExpressionAttributeNames:{
                '#nm':'name'
            },
            ExpressionAttributeValues: {
                ":n":req.body.name,
                ':e':req.body.email,
                ':p':req.body.phone,
                ':c':req.body.address.city,
                ':a':req.body.address.area,
                ':s':req.body.address.street,
                ':h':req.body.address.house,
                ":ds":req.body.address.description,
                ":im": req.body.image,
                ":imn": req.body.imageName
            },
            ReturnValues:"UPDATED_NEW"
        }
    try{
        const user= await docClient.update(params).promise();
        res.json(user);
    }catch(err){
        console.log(err)
        res.sendStatus(400);
    }
})




module.exports = usersRouter;
