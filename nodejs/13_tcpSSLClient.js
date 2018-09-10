// openssl genrsa -out my_key.pem 1024
// openssl req -new -key my_key.pem -out my_csr.pem
// openssl x509 -req -in my_csr.pem -signkey my_key.pem -out my_cert.pem


var tls = require('tls');
var fs = require('fs');
var clientOptions = {
    'key': fs.readFileSync('./my_key.pem'),
    'cert': fs.readFileSync('./my_cert.pem'),
    'rejectUnauthorized': false,
};

var socket = tls.connect(8080, 'localhost', clientOptions, function() {
    console.log('client connected!');
    process.stdin.pipe(socket);
    process.stdin.resume();
});
socket.setEncoding('utf8');
socket.on('data', function(data) {
    console.log('data: ', data);
});
socket.on('end', function(data) {
    console.log('end: ', data);
});