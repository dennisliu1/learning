var process = require('process');
var net = require('net');
// var conn = net.createConnection(port, host, function listener(conn));
var port = 8080;
var conn = net.createConnection(port);

// read
conn.on('data', function(data) {
    console.log('data', data);
});
conn.on('error', function(err) {
    console.log('error', err);
});
// conn.setEncoding(encoding);

// write
setInterval(function() {
    conn.write("hi from client!");
}, 1000);
// conn.write(msg, base);
// conn.write(msg, callback);

// close
setTimeout(function() {
    conn.end();
    process.exit();
}, 5000);
