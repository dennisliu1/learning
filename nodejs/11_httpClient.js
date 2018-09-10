
var http = require('http');

var port = 8000;
var options = {
    "port": port
};
http.get(options, function(res) {
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

var request = http.request(options, function(res) {
    res.on('data', function(data) {
        console.log("response:", data.toString());
    });
    res.on('end', function() {
        console.log('end');
    });
});
request.write("abc");
request.end();