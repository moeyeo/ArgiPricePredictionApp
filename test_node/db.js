var mongojs = require('mongojs');

var databaseUrl = 'predictapp_mongodb';
var collections = ['vegs', 'clubs'];

var connect = mongojs(databaseUrl, collections);

module.exports = {
    connect: connect
};