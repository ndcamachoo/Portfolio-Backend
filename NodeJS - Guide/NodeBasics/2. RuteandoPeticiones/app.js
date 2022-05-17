//Ruteando peticiones
// Para rutear peticiones es necesario conocer cual es la ruta de la peticion del cliente y definir una acción a realizar.

const http = require('http'); 
const fs = require('fs'); // Importamos el modulo fs de node.js para manejar archivos

const server = http.createServer((req, res) => {

    const URL = req.url; // Obtenemos la URL de la peticion del cliente.
    console.log(` -> Petición del cliente: [${req.method}] ${URL}`);
    
    // Definimos una logica para rutear las peticiones del cliente.
    // En este caso, la ruta de la peticion del cliente sera '/' y la acción a realizar sera enviar un mensaje al cliente.
    if (URL === '/') {
        res.write(JSON.stringify({
            message: 'Pagina principal'
        }));
    }
    // En este caso, la ruta de la peticion del cliente sea '/test
    else if (URL === '/test') {
        res.write(JSON.stringify({
            message: 'Pagina de prueba'
        }));
    }
    // En caso de definir una ruta que proyecte un archivo html, se debe definir una ruta
    else if(URL === '/main') {
        // Leemos el archivo index.html y lo enviamos al cliente.
        try{ // Intentamos leer el archivo index.html
            let data = fs.readFileSync(__dirname + '/index.html'); // readFileSync es un metodo de fs que lee un archivo y devuelve un buffer.
            // La palabra Sync significa que el metodo se ejecuta de manera sincrona. Bloquea el proceso hasta que el archivo se lea y luego continua.
            // Si necesitamos leer un archivo de manera asincrona, usamos el metodo readFile

            res.write(data);
        }catch{ // En caso de que no se pueda leer el archivo index.html, enviamos un mensaje de error al cliente.
            res.write(JSON.stringify({
                message: 'Error al leer el archivo'
            }));
        }

    }
    // En este caso, la ruta de la peticion del cliente sea '/enviar' se realizará una acción.
    else if (URL === '/enviar' && req.method === 'POST') {

        // Un Stream es una clase que permite leer y escribir datos, de tal manerá que utiliza la infomación por partes.
        // En este caso, se utiliza el Stream para leer el archivo que se enviará al servidor.
        // Un Buffer es una memoría temporal que un stream toma para mantener información hasta que se consuma.

        const body = []; // Definimos una variable para almacenar el contenido de la petición

        // Utilizando req.on('data') permite capturar un evento de datos cada vez que un nuevo fragmento o parte del archivo se establezca en el buffer.
        req.on('data', (chunk) => {
            // Este callback se ejecuta cada vez que un fragmento o parte del archivo se establezca en el buffer.
            body.push(chunk); // Almacenamos el fragmento en la variable body.
            console.log(` -> Recibido: ${chunk}`); // Imprimimos el fragmento en consola.

        }) 

        // Utilizando req.on('end') permite cerrar el buffer 
        req.on('end', () => {
            // Este callback se ejecuta el proceso termina
            let parseBody = Buffer.concat(body).toString(); // Concatenamos el contenido de todos los fragmentos en un solo buffer.
            console.log(` -> Recibido: ${parseBody}`); // Imprimimos el contenido en consola.

            // Para escribir un archivo en el servidor, se utiliza el modulo fs.
            // En este caso, se utiliza el metodo writeFile para escribir un archivo. Si los datos son muy grandes y el proceso se puede demorar, utilizamos writeFile para escribir un archivo de manera asincrona.
            fs.writeFile(__dirname + "/message.txt", parseBody.split("=")[1], (err) => { // Guardamos el archivo en el servidor.
                if (err){
                    res.write(JSON.stringify({
                        message: "Error al escribir el archivo"
                    }));
                }
            })
        });

        // Mensaje de respuesta al cliente.
        res.write(JSON.stringify({
            message: "Informacion recibida"
        })); 

    }

    // En el caso de que la ruta no sea ninguna de las anteriores, se enviara un mensaje de error al cliente.
    else {
        res.write(JSON.stringify({
            message: 'Error 404'
        }));
    }

    res.end(); // Finalizamos la respuesta del servidor.

})

console.log('\n =============== Servidor corriendo en el puerto 3000 =============== \n');

server.listen(3000); 