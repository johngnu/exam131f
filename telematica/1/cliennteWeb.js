
const http = require('http');

const options = {
  hostname: 'ssulapaz.org',
  path: '/',
  method: 'HEAD'
};

const req = http.request(options, (res) => {
  console.log(`Response status code: ${res.statusCode}`);
  console.log(res.headers);
  res.pipe(process.stdout);
});

req.on('error', (error) => {
  console.error(error);
});

req.end();
