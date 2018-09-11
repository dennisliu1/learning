

// on child process
console.log('created child process');
// process.stdin.resume();
process.stdin.on('data', function(data) {
    console.log('child got: ', data.toString());
    process.stdout.write('bye');
});
