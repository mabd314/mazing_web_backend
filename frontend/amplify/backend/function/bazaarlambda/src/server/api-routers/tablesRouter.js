const express = require('express');
const tablesRouter = express.Router();
const app = require('../../app');

const AWS = require("aws-sdk");
  
  const docClient= new AWS.DynamoDB.DocumentClient();
  let params;
  let tablesId=3;
  
tablesRouter.get('/',async(req,res)=>{
    params = {
        TableName : "Tables"
        }    

    try{
        const tables=await docClient.scan(params).promise();
        res.json(tables.Items);
    }catch(err){
        console.log(err);
        res.sendStatus(400);
    }
})

tablesRouter.post('/',async(req,res)=>{
    params={
        TableName: 'Tables',
        Item:{ ...req.body.table, id:++tablesId}
    }
    try{
        const table=await docClient.put(params).promise();
        console.log(table);
        table.id=tablesId
        res.json(table);
    }catch(err){
        console.log(err)
        res.sendStatus(400);
    }
});

tablesRouter.put('/:tableId',async(req,res)=>{
    params={
        TableName: 'Tables',
        Key:{
            "id":Number(req.params.tableId)
        },
        UpdateExpression:'set #on=:on, #oi=:oi',
        ExpressionAttributeNames:{
            "#on":"ownerName",
            "#oi":"ownerImage"
        },
        ExpressionAttributeValues: {
            ":on":req.body.ownerName,
            ":oi":req.body.ownerImage
        },
        ReturnValues:"UPDATED_NEW"
    }
    try{
        const user= await docClient.update(params).promise();
        res.json(user);
    }catch(err){
        console.log(err)
        res.status(400).send(err.message);
    }
})




module.exports = tablesRouter;
