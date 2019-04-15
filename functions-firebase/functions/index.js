const functions = require('firebase-functions');
//The Firebase Admin SDK to access the Firebase Realtime Database.
const admin = require('firebase-admin');
var app = require('express')();
admin.initializeApp(functions.config().firebase);
// Get a database reference to our posts
var db = admin.database();

exports.vegsDetail = functions.https.onRequest((request, response) => {
  if(request.method == 'GET'){
     var ref = db.ref("data");
     ref.once("value", function(snapshot) {
       response.contentType('application/json');
       response.send(JSON.stringify(snapshot.val()));
     });
  }
});

// exports.findById = function (id) {
//   for (var i = 0; i < vegs.result.length; i++) {
//       if (vegs.result[i].Name == id) return vegs.result[i];
//   }
// };

// app.get('/vegsDetail/:name', functions.https.onRequest ((request, response) => {
//   var name = request.params.name;
//   var ref = db.ref("result");
//   ref.("value", function(snapshot) {
//     response.contentType('application/json');
//     response.send(JSON.stringify(snapshot.val()));
//   });
// }));


