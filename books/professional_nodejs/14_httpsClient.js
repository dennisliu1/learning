
var https = require('https');
var fs = require('fs');
process.env.NODE_TLS_REJECT_UNAUTHORIZED = "0";

var port = 8000;
var options = {
    'key': fs.readFileSync('./my_key.pem'),
    'cert': fs.readFileSync('./my_cert.pem'),
    'rejectUnauthorized': false,
    // 'requestCert': true,
    "port": port
};


https.get(options, function(res) {
    res.on('data', function(data) {
        console.log("response:", data.toString());
    });
    res.on('end', function() {
        console.log('end');
    });
});

var options = {
    "port": port,
    "method": 'POST',
};

var request = https.request(options, function(res) {
    res.on('data', function(data) {
        console.log("response:", data.toString());
    });
    res.on('end', function() {
        console.log('end');
    });
});
request.write("abc");
request.end();