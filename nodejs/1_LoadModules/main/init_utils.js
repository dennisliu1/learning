
var basicPrint = function basicPrint(string) {
    console.log(string);
};

var debugPrint = function debugPrint(string) {
    console.log('debug: ');
}

module.exports.basicPrint = basicPrint;
module.exports.debugPrint = debugPrint;