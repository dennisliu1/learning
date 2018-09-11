
var dgram = require('dgram')
server = dgram.createSocket('udp6') // udp4

var url = 'localhost';
var port = 6000;

server.on('message', function(msg, rinfo) {
    var msgStr = msg.toString();
    console.log('got message', msg.toString(), rinfo);
    
    server.send(msg, 0, msg.length, rinfo.port, url, function(err, bytes) {
        console.log('sent', err, bytes);
    });
});
server.on('listening', function() {
    console.log('udp server listening on port ', port);
});
server.bind(port);

// var msg = "message";
// server.send(msg, 0, msg.length, port, url, function(err, bytes) {
//     console.log('sent', err, bytes);
// });

