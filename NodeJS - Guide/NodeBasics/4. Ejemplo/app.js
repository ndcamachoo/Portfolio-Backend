const http = require('http');
const routes = require('./routes/routes');
const PORT = 3000;

const server = http.createServer(routes);

console.log(`\n ============= Server is listening on port ${PORT} ================= \n`);
server.listen(PORT);



