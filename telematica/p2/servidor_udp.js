const udp = require('dgram');

const servidor = udp.createSocket('udp4');

servidor.on('message', function(request, client) {
    var mensaje = request.toString();

    // mesaje
    console.log('Mensaje: ', mensaje);

    // client info
    var puerto = " Puerto: " + client.port;
    var direccion = " Direccion: " + client.address;

    // date
    var now = new Date();
    var fecha= " Fecha: " + now;
   
    var res = puerto + direccion + fecha;
    
    servidor.send(res, client.port, 'localhost', function(err) {
        if(err) {console.log(err.message); }
    });
});

servidor.bind(9999, function() {
    console.log('Servidor iniciado en: ', 9999);
});
