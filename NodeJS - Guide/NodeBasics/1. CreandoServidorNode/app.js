// Creando un servidor con Node.js

// 1. Importar el modulo http
// Para importar un modulo se usa la palabra reservada require y se le pasa el nombre del modulo. En Node.js existen varios modulos que se pueden importar de manera global.
// Los modulos que se pueden importar son:
// fs: para manejar archivos
// http: para manejar servidores
// path: para manejar rutas
// util: para manejar funciones utiles
// https: para manejar servidores https
// entre otros...

// Los modulos tambien se pueden importar o exportar de manera local. Creando un archivo e indicando los elementos que se quieren exportar mediante la palabra reservada exports.
// e importar los elementos mediante la palabra reservada require ingresando el nombre del archivo mediante una ruta relativa o absoluta.
// De igual manera, se puede importar recursos de manera global mediante el repositorio de modulos de Node.js (npm).

const http = require('http'); // Importamos el modulo http de node.js para crear el servidor

// 2. Crear un servidor
// Para crear un servidor se usa la funcion http.createServer().
// La funcion http.createServer() recibe una funcion como parametro.
// La funcion recibida como parametro es la que se ejecutara cuando se reciba una peticion del cliente.
// La funcion recibida como parametro tiene dos parametros:
// request: es un objeto que representa la peticion del cliente.
// response: es un objeto que representa la respuesta del servidor.
// La funcion recibida como parametro tiene que retornar una promesa.

const server = http.createServer((request, response) => {
  
    // 3. Visualizar la peticion del cliente
    // Para visualizar la peticion del cliente se usa la funcion console.log().
    //console.log(request); // La petición se visualiza en la consola. Normalmente contiene gran cantidad de informacion sobre la peticion del cliente que se hizo en el navegador.

    // Una peticion del cliente contiene:
    // method: el metodo de la peticion (GET, POST, PUT, DELETE, etc) 
    // url: la url de la peticion (http://localhost:3000/index.html)
    // headers: los encabezados de la peticion (content-type, etc)
    // body: el cuerpo de la peticion (si el metodo es POST)
    // Los podemos obtener de la siguiente manera:

    console.log(` -> Petición del cliente: [${request.method}] ${request.url}`); // Visualizamos la peticion del cliente en la consola.

    // Esta función se ejecuta en un event loop.
    // El event loop es una funcion que se ejecuta en un ciclo infinito.
    // y siempre esta a la espera de que se produzca un evento.
    // Cuando se produce un evento, el event loop se encarga de ejecutar la funcion que se le pasa como parametro.
    // En este caso, la funcion recibida como parametro es la que se ejecuta cuando se recibe una peticion del cliente.

    // Se puede cancelar el event loop con la funcion process.exit().
    //process.exit(); // Esta funcion detiene el event loop.

    // 4. Configurar la respuesta al cliente
    // No hay necesidad de obsevar el objeto response ya que a partir del mismo se utilizará el objeto response para enviar datos al cliente.
    
    // Si queremos enviar una respuesta a el cliente, definimos inicialmente el tipo de contenido de la respuesta.
    // En este caso utilizamos las cabeceras de la peticion del cliente para definir el tipo de contenido de la respuesta mediante la funcion response.setHeader()
    // Para identificar cuales son las cabeceras necesarias para enviar una petición según la necesidad, podemos observar esta referencia en la siguiente url: https://developer.mozilla.org/es/docs/Web/HTTP/Headers
    response.setHeader('Content-Type', 'application/json'); // Esta funcion define el tipo de contenido de la respuesta. En este caso, la respuesta sera en formato JSON. https://developer.mozilla.org/es/docs/Web/HTTP/Basics_of_HTTP/MIME_types

    // 5. Enviar la respuesta al cliente
    // Para enviar un contenido, en este caso de tipo JSON al cliente, utilizamos la funcion response.write().
    // La funcion response.write() recibe una cadena de texto como parametro.
    // La cadena de texto que recibe como parametro es el contenido que se enviara al cliente.
    // En este caso, la cadena de texto que recibe como parametro es un JSON.

    response.write(JSON.stringify({
        message: 'Hola mundo desde un servidor con Node.js'
    })); // Enviamos el contenido al cliente.

    // Opcional. Finalizar la respuesta al cliente
    // La funcion response.end() no recibe parametros.

    // response.end(); // Finalizamos la respuesta al cliente.

})

// Mensaje de la aplicacion
console.log('\n =============== Servidor corriendo en el puerto 3000 =============== \n');

server.listen(3000); // Le decimos al servidor que escuche en el puerto de comunicación 3000. 
// Es decir, se puede acceder desde la URL http://localhost:3000/ en el navegador. localhost es el nombre de nuestra red local.
