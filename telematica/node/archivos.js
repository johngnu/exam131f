var http = require('http');
var fs = require('fs');

const puerto=process.argv[2];
const nombre=process.argv[3];

const ContentTypes={
    'png':'image/png',
    'pdf':'application/pdf',
    'txt':'text/txt'
};

// Create a server
http.createServer(function (request, response) {
   // Parse the request containing file name
   // Read the requested file content from file system
   fs.readFile(nombre ,function (err, data) {
   	if (err) {
      	      console.log(err);
      	      // HTTP Status: 404 : NOT FOUND
      	      // Content Type: text/plain
      	      response.writeHead(404,'utf8', {'Content-Type': 'text/png'});
	} else {
      	      // Page found
      	      // HTTP Status: 200 : OK
      	      // Content Type: text/plain
	      var ext=nombre[nombre.length-3]+nombre[nombre.length-2]+nombre[nombre.length-1];
	      var miar=ContentTypes[ext];
	      response.writeHead(200, {'Content-Type': miar});

	      // Write the content of the file to response body
	      response.write(data);
   	}
        // Send the response body
        response.end();
   });
}).listen(puerto);
     
// Console will print the message
console.log('Server running at http://127.0.0.1:' + puerto);


