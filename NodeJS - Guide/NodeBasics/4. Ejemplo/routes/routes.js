const fs = require('fs');

const handlerRoutes = (req, res) => {

    const URL = req.url;
    const METHOD = req.method;

    console.log(` -> [${new Date().toISOString()}][${METHOD}] ${URL}`);


    if (URL === '/' && METHOD === 'POST') {
        const body = [];
        req.on('data', (chunk) => {
            body.push(chunk);
        });
        req.on('end', () => {
            const parseBody = Buffer.concat(body).toString();
            console.log(` -> [Body request] ${parseBody.split("=")[1]}`)
        });

        res.statusCode = 302;
        res.setHeader('Location', '/');
    }

    else if (URL === '/') {
        try{
            const mainPage = fs.readFileSync(`./views/index.html`);
            res.write(mainPage);
        }catch{
            res.write(JSON.stringify({
                error: 'Something went wrong trying to read the file'}
            ));
        }
    }

    else if (URL === '/users') {
        try{
            let users = fs.readFileSync('.views/users.html');
            res.write(users);
        }catch{
            res.write(JSON.stringify({
                error: 'Something went wrong trying to read the file'}
            ));
        }
    }

    else{
        res.write(JSON.stringify({
            error: 'The page you are looking for does not exist'}
        ));
    }

    res.end();

}

module.exports = handlerRoutes;