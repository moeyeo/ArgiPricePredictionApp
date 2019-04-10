const functions = require('firebase-functions');
//The Firebase Admin SDK to access the Firebase Realtime Database.
const admin = require('firebase-admin');
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


