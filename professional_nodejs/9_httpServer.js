var http = require('http');
var server = http.createServer();
var port = 8000;

server.on('request', function(req, res) {
    // req.url;
    // req.method;
    // req.headers;
    // console.log(req);

    var msg = "";
    req.on('data', function(data) {
        console.log('data', data);
        msg = data;
    });

    // res.writeHead(statusCode);
    // res.setHeader("hey", "yo");
    // res.removeHeader('hey');
    res.write("server");
    res.end();
});
server.listen(port);
console.log('http server listening on port ', port);
// server.listen(port, host);

// server.close();