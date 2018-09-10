var https = require('https');
var fs = require('fs');
var serverOptions = {
    'key': fs.readFileSync('./my_key.pem'),
    'cert': fs.readFileSync('./my_cert.pem'),
    'rejectUnauthorized': false,
};


var server = https.createServer(serverOptions);
var port = 8000;

server.on('request', function(req, res) {
    req.on('data', function(data) {
        console.log('data', data);
        res.write("server");
    });
    // res.end();
});
server.listen(port);
console.log('http server listening on port ', port);