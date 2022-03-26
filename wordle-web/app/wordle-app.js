const axios = require("axios").default;
const express = require("express");
const path = require("path");
require('dotenv').config({debug:true, path: './config/.env.'+process.env.NODE_ENV.trim()});
const cors = require("cors");
const PORT = process.env.PORT || 8000;
const app = express();

app.use(cors());

console.log('env: ' + process.env.NODE_ENV );
console.log('env: ' + process.env.MICROSERVICE_HOST );

//setting middleware
app.use(express.static('static')); //Serves resources from public folder

app.get('/word', (req, res) => {


    // make configurable via env
    const options = {
        method: 'GET',
        url: 'http://'+ process.env.MICROSERVICE_HOST+'/api/v1/words/random/5'
    }

    axios.request(options).then(( response) => {
        console.log(response.data);
        res.json(response.data);
    }).catch( (error) => {
        console.error(error);
    })
})

app.get('/checkword', (req, res) => {

    const word = req.query.word;

    console.log(req);

    // make configurable via env
    const options = {
        method: 'GET',
        url: 'http://'+ process.env.MICROSERVICE_HOST+'/api/v1/words/isAWord/'+word,

    }

    axios.request(options).then((response) => {
        console.log(response.data);
        res.json(response.data);
    }).catch( (error) => {
        console.error(error);
    })
})

app.listen(PORT, () => console.log('Server running on port ' + PORT))

