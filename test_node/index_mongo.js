var app = require('express')();

var port = process.env.PORT || 7777;

var vegs = require('./vegs_mongo');

var mongojs = require('./db');

var db = mongojs.connect;

const spawn = require("child_process").spawn;
const pythonProcess = spawn('python',["Arima_Sample.py"]);

app.get('/', function (req, res) {
    db.vegs_mongo.count(function(err, result) {
        if (result <= 0) {
            db.vegs_mongo.insert(vegs.findAll(), function(err, docs) {
                // insert new data.
            });
        } 
        res.send('<h1>Hello Node.js</h1>');
    });
});

app.get('/vegsDetail', function (req, res) {
    db.vegs_mongo.findOne(function(err, docs) {
        console.log(docs);
        res.json(docs);
    });
});


app.get('/vegsDetail/:id', function (req, res) {
    var id = req.params.id;

    db.vegs.findOne({Name:id }, function(err, docs) {
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