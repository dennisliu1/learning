var tls = require('tls');
var fs = require('fs');
var serverOptions = {
    'key': fs.readFileSync('./my_key.pem'),
    'cert': fs.readFileSync('./my_cert.pem'),
    'rejectUnauthorized': false,
};

var server = tls.createServer(serverOptions, function(socket) {
    socket.setEncoding('utf8');
    socket.on('listening', function() {
        console.log('listening on port ', socket.localPort);
    });
    socket.on('connection', function() {
        console.log('connection');
    });
    socket.on('close', function() {
        console.log('close');
    });
    socket.on('error', function() {
        console.log('error');
    });
    socket.on('data', function(data) {
        console.log('data: ', data);
        socket.write("server got it!");
    });
    socket.on('on', function(data) {
        console.log('on: ', data);
    });
    socket.on('end', function(data) {
        console.log('end: ', data);
    });
});
server.listen(8080);