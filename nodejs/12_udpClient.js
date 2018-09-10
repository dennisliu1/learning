var process = require('process');
var stdin = process.openStdin();
var dgram = require('dgram');
var client = dgram.createSocket('udp6') // udp4

var url = 'localhost';
var port = 6001;
var serverPort = 6000;

client.on('message', function(msg, rinfo) {
    var msgStr = msg.toString();
    console.log('got message', msg.toString(), rinfo);
});
client.on('listening', function() {
    console.log('udp client listening on port ', port);
});
client.bind(port);

stdin.addListener('data', function(d) {
    console.log('sending: ', d.toString());
    client.send(d, 0, d.length, serverPort, url, function(err, bytes) {
        console.log('sent', err, bytes);
    });
});