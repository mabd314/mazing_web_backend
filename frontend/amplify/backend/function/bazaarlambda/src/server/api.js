const express = require('express');
const apiRouter = express.Router();
const tablesRouter=require('./api-routers/tablesRouter');
const usersRouter=require('./api-routers/usersRouter');
const itemsRouter=require('./api-routers/itemsRouter')
const app = require('../app');


const AWS = require("aws-sdk");
const docClient= new AWS.DynamoDB.DocumentClient();
let params;

// const db = require('./fakeDB');
// apiRouter.get('/',async(req,res)=>{

//     await new Promise(async(resolve,reject)=>{
//         for(let i=0;i<db.tablesDB.length;i++){
//             params={
//                 TableName: 'Tables',
//                 Item:{
//                     ...db.tablesDB[i]
//                 }
//             }
//             try{
//                 const data=await docClient.put(params).promise();
//                 console.log(`table ${db.tablesDB[i].id} added`);
//                 resolve(data);
//             }catch(err){
//                 console.log(err);
//                 reject(err)
//             }
//         }
//     })


//     await new Promise(async(resolve,reject)=>{
//         for(let i=0;i<db.usersDB.length;i++){
//             params={
//                 TableName: 'Users',
//                 Item:{
//                     ...db.usersDB[i]
//                 }
//             }
//             try{
//                 const data=await docClient.put(params).promise();
//                 console.log(`user ${db.usersDB[i].id} added`);
//                 resolve(data);
//             }catch(err){
//                 console.log(err);
//                 reject(err)
//             }
//         }
//     })

//     await new Promise(async(resolve,reject)=>{
//         for(let i=0;i<db.itemsDB.length;i++){
//             params={
//                 TableName: 'Items',
//                 Item:{
//                     ...db.itemsDB[i]
//                 }
//             }
//             try{
//                 const data=await docClient.put(params).promise();
//                 console.log(`item ${db.itemsDB[i].id} added`);
//                 resolve(data);
//             }catch(err){
//                 console.log(err);
//                 reject(err)
//             }
//         }
//     })
// })

apiRouter.use('/tables',tablesRouter);
apiRouter.use('/users',usersRouter);
apiRouter.use('/items',itemsRouter);

module.exports = apiRouter;
