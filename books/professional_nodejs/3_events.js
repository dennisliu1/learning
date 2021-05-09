
var events = require('events');

var EventEmitter = events.EventEmitter;

class Elevator extends EventEmitter {
    constructor() {
        super();
        this.once('startElevator', this.startElevator);
        this.on('pressCloseDoorButton', function() {
            console.log('close door');
        });
    };
    startElevator() {
        console.log('start the elevator');
    };
}

var e = new Elevator();

console.log('e.startElevator '+ e.listenerCount('startElevator'));
console.log('e.pressCloseDoorButton '+ e.listenerCount('pressCloseDoorButton'));
e.emit('startElevator'); // seems like this.once cleared all the listeners
e.emit('startElevator');
e.emit('pressCloseDoorButton');

var closeDoorButton = function closeDoorButton() {
    console.log('close door again!!!');
}
e.addListener('pressCloseDoorButton', closeDoorButton);
e.emit('pressCloseDoorButton');
console.log('e.startElevator '+ e.listenerCount('startElevator'));
console.log('e.pressCloseDoorButton '+ e.listenerCount('pressCloseDoorButton'));


e.removeAllListeners('startElevator');
e.removeListener('pressCloseDoorButton', closeDoorButton);
console.log('e.startElevator '+ e.listenerCount('startElevator'));
console.log('e.pressCloseDoorButton' + e.listenerCount('pressCloseDoorButton'));