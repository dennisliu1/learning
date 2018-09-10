
var b = new Buffer('this is a string', 'ascii');
var length = 15;
var c = new Buffer(length);
var d = b.slice(5, 10);

console.log(b);
console.log('b[1]: '+ b[1]);
console.log(c);
console.log(d);