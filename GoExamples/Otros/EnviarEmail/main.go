package main

import (
	"bytes"         // Librería para manejar cadenas de bytes
	"crypto/tls"    // Librería para manejar conexiones seguras
	"fmt"           // Librería para imprimir en pantalla
	"html/template" // Librería para manejar plantillas HTML
	"log"           // Librería para imprimir errores
	"net/mail"      // Librería para manejar correos
	"net/smtp"      // Librería para enviar correos
)

// Para enviar el correo se crea una estructura que contiene el mensaje
type Dest struct {
	Name string
}

func main() {

	// Enviar correo desde Go

	from := mail.Address{"test from", "test@test.com"} // Dirección del remitente
	to := mail.Address{"test to", "test2@test.com"}    // Dirección del destinatario
	subject := "Go Email Test"                         // Asunto del correo

	dest := Dest{Name: to.Address} // Destinatario del correo

	// Configurar los headers del correo

	headers := make(map[string]string)
	headers["From"] = from.String()
	headers["To"] = to.String()
	headers["Subject"] = subject
	headers["Content-Type"] = `"text/html; charset=UTF-8"`

	// Configurar los encabezados del correo
	message := ""
	for k, v := range headers {
		message += fmt.Sprintf("%s: %s\r\n", k, v)
	}

	// Configurar el cuerpo del correo
	t, err := template.ParseFiles("./template/template.html") // Parsear el archivo de plantilla
	if err != nil {
		log.Panic(err)
	}

	// Crear el buffer de bytes para almacenar el cuerpo del correo
	buf := new(bytes.Buffer)
	err = t.Execute(buf, dest) // Ejecutar la plantilla

	if err != nil {
		log.Panic(err)
	}

	// Agregar el cuerpo del correo al mensaje
	message += buf.String()

	// Configurar el servidor SMTP
	servername := "smtp.gmail.com:465"
	host := "smtp.gmail.com"

	auth := smtp.PlainAuth("", from.Address, "123456789", host)

	// Configurar el protocolo TLS
	tlsconfig := &tls.Config{
		InsecureSkipVerify: true,
		ServerName:         host,
	}

	// Crear la conexión con el servidor Gmail
	conn, err := tls.Dial("tcp", servername, tlsconfig)

	if err != nil {
		log.Panic(err)
	}

	// Crear el cliente SMTP
	c, err := smtp.NewClient(conn, host)
	if err != nil {
		log.Panic(err)
	}

	// Enviar la autenticación al servidor SMTP

	if err = c.Auth(auth); err != nil {
		log.Panic(err)
	}

	// Enviar el correo
	if err = c.Mail(from.Address); err != nil {
		log.Panic(err)
	}

	// Enviar el correo al destinatario
	if err = c.Rcpt(to.Address); err != nil {
		log.Panic(err)
	}

	// Crear el buffer de bytes para enviar el correo
	w, err := c.Data()
	if err != nil {
		log.Panic(err)
	}

	// Enviar el correo
	_, err = w.Write([]byte(message))
	if err != nil {
		log.Panic(err)
	}

	// Cerrar la conexión
	err = w.Close()
	if err != nil {
		log.Panic(err)
	}

	// Cerrar el cliente SMTP
	c.Quit()

	fmt.Println("Correo enviado")

}



