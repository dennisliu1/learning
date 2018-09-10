var fs = require('fs');

var options = {};
var rs = fs.createReadStream('7_streams.js', options);
rs.on('data', function(data) {
    console.log('reading data: ', data);
});
rs.on('drain', function() {
    console.log('drain!');
});

