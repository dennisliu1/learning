var net = require('net');
var server = net.createServer(function(socket) {
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
    });
    socket.on('on', function(data) {
        console.log('on: ', data);
    });
    socket.setTimeout(5000);
    socket.on('timeout', function() {
        console.log('timeout');
    });

    // socket.setEncoding("ascii");
    // socket.resume();
    // socket.pause();
    // socket.pipe();

    // var enable = true;
    // var initDelayMS = 10;
    // socket.setKeepAlive(enable, initDelayMS);
    // socket.setNoDelay(true);
});
server.listen(8080);
console.log('started server on port 8080');

// server.close();