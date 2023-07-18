
const net = require('net');
//const puerto = {port:7777};
const names=process.argv[2];

//creacion de socket servidor
const server = net.createServer(function(conexion){
    console.log('cliente conectado!');
    var ip=conexion.remoteAddress;
    var puerto=conexion.remotePort;
    var fecha=new Date();
    var fecha2=fecha.getDate()+"/"+(fecha.getMonth()+1)+"/"+fecha.getFullYear();
    conexion.write("Fecha: "+fecha2+"\nIP: "+ip+"\nPuerto: "+puerto+"\n");
    conexion.end();
});

server.listen(7777, function(){
    console.log("servidor corriendo por el puerto",7777);
});

// hola vato
