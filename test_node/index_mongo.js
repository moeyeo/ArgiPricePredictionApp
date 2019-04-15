var app = require('express')();

var port = process.env.PORT || 7777;

var vegs_mongo = require('./vegs_mongo');

var mongojs = require('./db');

var db = mongojs.connect;

const spawn = require("child_process").spawn;
const pythonProcess = spawn('python',["Arima_Sample.py"]);

app.get('/', function (req, res) {
    db.vegs_mongo.count(function(err, result) {
        if (result <= 0) {
            db.vegs_mongo.insert(vegs_mongo.findAll(), function(err, docs) {
                // insert new data.
            });
        }  
        res.send('<h1>Hello Node.js</h1>');
    });
});

app.get('/vegsDetail', function (req, res) {
    db.vegs_mongo.find(function(err, docs) {
        res.json(docs);
    });
});


app.get('/vegsDetail/:name', function (req, res) {
    var name = req.params.name;
    db.vegs_mongo.findOne({Name: name}, function(err, docs) {
        res.json(docs);
    });
});

app.post('/newuser', function (req, res) {
    var json = req.body;
    res.send('Add new ' + json.name + ' Completed!');
});

app.listen(port, function() {
    console.log('Starting node.js on port ' + port);
});