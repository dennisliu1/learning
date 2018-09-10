var timers = require('timers');

var i = setInterval(() => {
    console.log('read me every second!');
}, 1000);
var t = setTimeout(() => {
    console.log('stop the interval at 5 seconds');
    clearInterval(i);
}, 5000);
// clearTimeout(t);

process.nextTick(() => {
    console.log('next tick!');
});
console.log('start?');
