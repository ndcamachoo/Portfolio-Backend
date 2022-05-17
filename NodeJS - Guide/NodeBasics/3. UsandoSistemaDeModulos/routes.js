const fs = require('fs'); 
const { exitCode } = require('process');

// Se crea una función que manejará toda la logica de enrutamiento del servidor.
const requestHandler = (req, res) => {

    const URL = req.url; 
    console.log(` -> Petición del cliente: [${req.method}] ${URL}`);

    // En este caso, la ruta de la peticion del cliente sea '/'.
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
        try{ 
            let data = fs.readFileSync(__dirname + '/index.html'); 
            res.write(data);
        }catch{ 
            res.write(JSON.stringify({
                message: 'Error al leer el archivo'
            }));
        }

    }

    // En este caso, la ruta de la peticion del cliente sea '/enviar' mediante el protocolo HTTP: POST.
    else if (URL === '/enviar' && req.method === 'POST') {

        const body = []; 

        req.on('data', (chunk) => {
            body.push(chunk);
            console.log(` -> Recibido: ${chunk}`); 
        }) 

        req.on('end', () => {
            let parseBody = Buffer.concat(body).toString(); 
            console.log(` -> Recibido: ${parseBody}`);

            fs.writeFile(__dirname + "/message.txt", parseBody.split("=")[1], (err) => { 
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

}

// Para exportar la función requestHandler, se utiliza el modulo 'module.exports'.	
//module.exports = requestHandler; 

// Tambien se puede exportar una o multiples variables o objetos.
module.exports = {
     handler: requestHandler
     //message: 'Hola mundo',
     //etc...
}

// Tambien se puede se las siguientes maneras:
// module.exports.handler = requestHandler;
// exports.handler = requestHandler;