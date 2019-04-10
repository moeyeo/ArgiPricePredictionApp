var mongojs = require('mongojs');

var databaseUrl = 'predictapp_mongodb';
var collections = ['vegs_mongo', 'clubs'];

var connect = mongojs(databaseUrl, collections);

module.exports = {
    connect: connect
};