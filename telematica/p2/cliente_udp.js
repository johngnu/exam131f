
const dgram = require('dgram');
const cliente = dgram.createSocket('udp4');

// mensaje
var mensaje = "Hola mundo";

// envio del mensaje
cliente.send(mensaje, 9999, 'localhost', function(err) {
    if (err) {console.log(err.message);} 
});

// Respuesta del servidor
cliente.on('message', function(msg, client) {
    console.log('Respuesta: \t', msg.toString());    
});

