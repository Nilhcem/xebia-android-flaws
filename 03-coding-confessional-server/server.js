var express = require('express');
var https = require('https');
var fs = require('fs');

var options = {
  key: fs.readFileSync('cert/privatekey.pem'),
  cert: fs.readFileSync('cert/certificate.pem')
};

var app = express();

app.get('/latest', function(req, res) {
  fs.readFile('data/data.json', 'utf-8', function (err, data) {
    res.type('application/json; charset=utf-8');
    if (err) {
      res.send(500);
    } else {
      res.send(data);
    }
  });
});

app.get('^*$', function(req, res) {
 res.type('text/plain');
 res.send('Nothing to see here');
});

// Create an HTTPS server
https.createServer(options, app).listen(443);
