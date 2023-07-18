const http = require('http');

const options = {
  hostname: 'informatica.edu.bo',
  path: '/',
  method: 'HEAD'
};

const req = http.request(options, function(res) {
  // print headers
  console.log(res.headers);

  // cerrar conexion
  res.pipe(process.stdout);
});

req.on('error', function(error) {
  console.error(error);
});

req.end();
