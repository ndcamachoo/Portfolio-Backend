// Usando el Sistema de Modulos de Node
// En Node se puede utilizar el sistema de modulos para separar el código en archivos con el fin de que sea más fácil de mantener y mantener el código limpio.
// En este caso, se utiliza el sistema de modulos para separar la logica de las rutas de la peticiones en otro archivo y posteriormente llaamrlo desde el archivo principal.

const http = require('http'); 
const routes = require(__dirname + '/routes'); // Para importar el archivo routes.js utilizamos require() con la ruta del archivo. Si el archivo tiene la extensión .js, se puede omitir.

const server = http.createServer(routes.handler); // Se crea un servidor HTTP.

console.log('\n =============== Servidor corriendo en el puerto 3000 =============== \n');

server.listen(3000); 