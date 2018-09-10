var process = require('process');
var child_process = require('child_process');
var spawn = child_process.spawn;

// on parent process
var child = spawn('node', ['6_childProcess.js']);
child.on('exit', function(code) {
    console.log('child exit');
});

var i = setInterval(function() {
    child.stdin.write('hi');
}, 1000);

child.stdout.on('data', function(data) {
    // child response
    console.log('got from child: ', data.toString());
});

setTimeout(function() {
    console.log('stop sending to child process...');
    clearInterval(i);

    console.log('ending child process...');
    child.emit('exit');
    process.exit();
}, 5000);