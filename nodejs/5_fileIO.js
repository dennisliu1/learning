var path = require('path');
var fs = require('fs');

var p1 = path.normalize('../nodejs/./1_LoadModules/../5_readme.md');
var p2 = path.join('abc/', '1/', '2/');
var p3 = path.resolve('~/', 'learning/', '../learning/nodejs/1_LoadModules/', '5_readme.md');
var p4 = path.relative('~/', '5_readme.md');
console.log('p1: '+ p1);
console.log('p2: '+ p2);
console.log('p3: '+ p3);// ???
console.log('p4: '+ p4); // ????

var p = '../nodejs/5_readme.md';
var dir = path.dirname(p);
var base = path.basename(p);
var ext = path.extname(p);
console.log('p: '+ p);
console.log('p.dir: '+ dir);
console.log('p.base: '+ base);
console.log('p.ext: '+ ext);

fs.exists(p, function(exists) {
    console.log('p exists: '+ exists);
});

var fileLength = 0;
fs.stat(p, function(err, stats) {
    console.log('err: ', err);
    console.log('stats: ', stats);
    fileLength = stats.size;
});

fs.open(p, 'r+', function(err, fd) {
    console.log('err: '+ err);
    console.log('fd: '+ fd);
    var b = new Buffer(fd);
    fs.read(fd, b, 0, fileLength, 0, function(err, bytesRead, buffer) {
        console.log('reading!');
        console.log('\terr: ', err);
        console.log('\tbytesRead: ', bytesRead);
        console.log('\tbuffer: ', buffer);
    });
    console.log('got: ', b);

    var wB = new Buffer("hello!");
    fs.write(fd, wB, 0, wB.length, 0, function(err, written) {
        console.log('writing!');
        console.log('\terr: ', err);
        console.log('\twritten: ', written);
    });

    fs.close(fd, function() {
        console.log('file closed!');
    })
});
