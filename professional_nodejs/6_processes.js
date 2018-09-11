
var child_process = require('child_process');
var exec = child_process.exec;

exec('ls', function(err, stdout, stderr) {
    console.log('err: ', err);
    console.log('stdout: ', stdout);
    console.log('stderr: ', stderr);
});

var options = {}
exec('ls', options, function(err, stdout, stderr) {
    console.log('err: ', err);
    console.log('stdout: ', stdout);
    console.log('stderr: ', stderr);
});

var spawn = child_process.spawn;
arguments_arr = [];
child = spawn('ls', arguments_arr);
child.stdout.on('data', function(data) {
    console.log('data: ', data);
});
