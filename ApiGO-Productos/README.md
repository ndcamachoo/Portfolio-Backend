# API REST - Productos (Golang - Gorm/Gin/Ginkgo)



## Objetivos 📃

1.  **Implementación de un API Rest en Golang**, implementando el framework gin, sobre el puerto 9098 que exponga los siguientes endpoints para gestionar la entidad productos (CRUD) sobre la base de datos:
	
	1. Creación de productos
	2. Lectura de productos
	3. Actualización de productos 
	4. Eliminación de productos
	
	**Framework: Gin, https://github.com/gin-gonic/gin
	Base de datos: SQLite, https://www.sqlite.org/index.html**

![Entidad productos](https://i.ibb.co/rp7NnTJ/unnamed.png)

2. **Implementación de pruebas unitarias** que garanticen la funcionalidad del servicio CRUD de la entidad producto mediante con **ginkgo y gomega (https://onsi.github.io/ginkgo/)**.
3. **Implementación de un endpoint (volumes)** que retorne un archivo JSON estático (volumen_list.json) y que solicite **Basic Authentication** al de retornar respuesta.

## Despliegue 📦

Para ejecutar el proyecto en un contenedor en `docker` utilizando el `Dockerfile` ejecutar los siguientes comandos en la carpeta del proyecto:

```bash
docker build -t apigo-productos . # Crea la imagen ApiGo - Productos
docker run --name api-productos -d -p 9098:9098 apigo-productos # Ejecutar el proyecto en el puerto 9098
```

De igual manera el contenedor se encuentra disponible en el repositorio de `Docker hub` para su uso. Se puede ejecutar mediante el siguiente comando.

```bash
docker run --name api-productos -d -p 9098:9098 ndcamachoo/api-productos
```

**Importante:** La ejecución del contenedor por puede ser tardía, recomiendo ver la consola del contenedor hasta que aparezcan los endpoints del API.


## Funcionalidades  🔧

- Implementación de una arquitectura empresarial en capas mediante el patrón de diseño MVC (Modelo, vista, controlador):

  ![Patron MVC](https://www.easyappcode.com/upload/post-792545902.jpg)

  ​	**El patrón MVC permite:**

  - Separar la lógica de la aplicación (el modelo) de su representación (la vista).

  - Reutilización del código.

  - Simplificar el desarrollo de cada capa.

  - Mayor velocidad de desarrollo en equipo.

  - Facilita la realización de pruebas unitarias.

    **[MVC ejemplo (genial.ly) - Digital House, Patrón MVC](https://view.genial.ly/611537bef44cc40d9cdcabe6)**
    
    **Estructura de capas basadas en el patrón MVC (Como la API no direccióna una vista, la vista en un archivo JSON)**
    
    ![Estructura de capas basadas en el patrón MVC - Digital House](https://i.ibb.co/kc3y4b1/Captura-de-pantalla-2021-10-18-200332.png)

- Implementación de un ORM (Mapeo Objeto-Relacional) que permite mapear las estructuras de una base de datos relacionales  sobre una estructura lógica de entidades para simplificar y acelerar el desarrollo de las aplicaciones. Es decir, es un mecanismo que permite interactuar con nuestra base de datos sin la necesidad de conocer SQL.

  GORM es una Librería ORM para Golang que permite MySQL, PostgreSQL, SQLite y SQL Server en sus drivers.

  **[GORM - Librería ORM para Golang](https://gorm.io/index.html)**

- Uso del framework Gin , que permite la creación de aplicaciones web mediante la organización de rutas, y mediante autorización hacerlas publicas o privadas sin afectar el rendimiento del proyecto web.

  **[Gin web framework](https://github.com/gin-gonic/gin#gin-web-framework)**

- Uso de Test Unitarios en función del enfoque de desarrollo TDD (*Test-driven Development* ) que permite la realización del software en base a los test unitarios y de integración para asegurar la calidad del software.

## Dependencias 🚨

- Gin Web Framework: 

  ```go
  go get -u github.com/gin-gonic/gin
  ```

- ORM GORM y Driver SQLite

  ```go
  go get -u gorm.io/gorm
  go get -u gorm.io/driver/sqlite
  ```

- Datatypes GORM

  ```go
  go get -u gorm.io/datatypes
  ```

- Ginkgo y Gomega

  ```go
  go get github.com/onsi/ginkgo/ginkgo
  go get github.com/onsi/gomega/...
  ```


## Documentación API 💼

### **Guardar productos**
----
 Devuelve un objeto JSON sobre el producto almacenado en la base de datos.

* **URL**

  /save

* **Method:**
  
  `POST` 

*  **URL Params**

   None

* **Data Params**

  Se envian datos mediante el Body utilizando un contenido de tipo JSON.

  ```json
  {
      "product_id": string,
      "name": string,
      "description": string,
      "status": string,
      "creation_date": datetime,
      "update_date": datetime,
      "account_id": string,
      "format_product": json,
      "value_unit": float,
      "unit_name": string,
      "unit_description": string,
      "stock": int
  }
  ```

  **Ejemplo:**

  ```json
  {
      "product_id": "pd1",
      "name": "Product1",
      "description": "Product test description",
      "status": "enabled",
      "creation_date": "2021-10-15T18:44:11.4469114-05:00",
      "update_date": "2021-10-15T18:44:11.4469114-05:00",
      "account_id": "default",
      "format_product": {
          "msg": "message test"
      },
      "value_unit": 100,
      "unit_name": "unit1",
      "unit_description": "Unit test description",
      "stock": 1
  }
  ```

* **Success Response:**
  
  Retorna el mismo objeto JSON el almacenamiento es correcto

  * **Code:** 201 Created
  
* **Error Response:**

  Si el formato es incorrecto, si se almacenan multiples datos con la misma clave primaria o falla la conexión con la base de datos.

  * **Code:** 400 Bad Request <br />
    **Content:** `{"Message": "Almacenamiento fallido en la entidad Productos"}`

### **Búsqueda de productos por ID**
----
Devuelve un objeto JSON sobre el producto almacenado que concuerde con el ID o Primary Key en la base de datos.

* **URL**

  /:id
  
* **URL Params**

   **Required:**

   `id=[string]`

* **Method:**
  
  `GET` 

* **Data Params**

  None

* **Success Response:**
  
  Retorna el objeto JSON correspondiente con el ID del producto

  * **Code:** 200 Ok
  
* **Error Response:**

  SI el ID buscado no se encuentra  registrado o falla la conexión con la base de datos.

  * **Code:** 404 Not Found <br />
    **Content:** `{"Message": "Búsqueda en la entidad Productos fallida por el ID: " + id}`

### **Listado de productos**
----
Devuelve un objeto JSON sobre todos los productos almacenados en la base de datos.

* **URL**

  /

* **Method:**
  
  `GET` 

*  **URL Params**

   None

* **Data Params**

  None

* **Success Response:**
  
  Retorna un objeto JSON en la que cada producto corresponde a un indice de un array. Si no ningun producto en la base de datos retorna un array vacio.

  * **Code:** 200 OK
  
* **Error Response:**

  Si falla la conexión con la base de datos.

  * **Code:** 404 Not Found <br />
    **Content:** `{"Message": "Lectura general de la entidad Productos fallida"}`

### **Actualización de productos**
----
Retorna un mensaje en formato JSON sobre el estado de la actualización en la base de datos.

* **URL**

  /update

* **Method:**
  
  `PUT` 

*  **URL Params**

   None

* **Data Params**

  Se envian datos mediante el Body utilizando un contenido de tipo JSON. **Importante:** El ID del producto debe ser exactamente al objeto a actualizar.

  ```json
  {
      "product_id": string (Required),
      "name": string,
      "description": string,
      "status": string,
      "account_id": string,
      "format_product": json,
      "value_unit": float,
      "unit_name": string,
      "unit_description": string,
      "stock": int
  }
  ```
  
  **Ejemplo:**

  ```json
  {
      "product_id": "pd1",
      "name": "Product1",
      "description": "Product test description updated",
      "status": "disabled",
      "account_id": "default",
      "format_product": {
          "msg": "message test updated"
      },
      "value_unit": 100,
      "unit_name": "unit1",
      "unit_description": "Unit test description updated",
      "stock": 1
  }
  ```
  
  
  
* **Success Response:**
  
  Retorna un objeto JSON sobre el estado de la actualización en la base de datos.

  * **Code:** 200 OK<br/>**Content**: `{"Message": "Dato actualizado correctamente la entidad Productos -> ID: " + product.ProductId}`
  
* **Error Response:**

  Si el ID del producto no existe y se intenta actualizar un campo no existente, el formato es incorrecto o falla la conexión con la base de datos.

  * **Code:** 404 Not Found <br />
    **Content:** `{"Message": "Actualización de campos en la entidad Productos fallida -> ID no existente: " + product.ProductId}`

### **Eliminación de productos**
----
Retorna un mensaje en formato JSON sobre el estado de la eliminación en la base de datos.

* **URL**

  /delete/:id
  
* **URL Params**

   **Required:**

   `id=[string]`

* **Method:**
  
  `DELETE` 

* **Data Params**

  None

* **Success Response:**
  
  Retorna un objeto JSON sobre el estado de la eliminación en la base de datos.

  * **Code:** 204 No Content<br/>**Content:** `{"Message": "Dato eliminado correctamente en la entidad Productos -> ID: " + id}`
  
* **Error Response:**

  Si el ID del producto no existe o falla la conexión con la base de datos.

  * **Code:** 404 Not Found <br />
    **Content:** `{"Message": "Eliminación de campos en la entidad Productos fallida -> ID no existente: " + id}`

### **Visualización de archivo JSON Estatico mediante Basic Auth**
----
Retorna un objeto JSON sobre el archivo estatico volumes_list.json si cumple con autorización basica. 

* **URL**

  /volumes
  
* **URL Params**

   None

* **Method:**
  
  `GET` 

* **Data Params**

  Requiere autorización basica para la visualización del endpoint. Los usuarios de prueba en la API son

  `Usuario: user, Contraseña: user`

  `Usuario: admin, Contraseña: admin`

* **Success Response:**
  
  Retorna el objeto JSON respresentación del archivo volumes_list.json

  * **Code:** 200 OK
  
* **Error Response:**

  Si las credenciales de la autorización basica no son correctas.

  * **Code:** 401 Unauthorized

## Capturas (Postman) 📺

### Save
![Save](https://i.ibb.co/YLnKWY6/Save.png)

### Read
![Read](https://i.ibb.co/LNZJRSW/Read.png)

### Read All
![Read All](https://i.ibb.co/5Bm9P9X/ReadAll.png)

### Update
![Update](https://i.ibb.co/THBC1KJ/Update.png)

### Delete
![Delete](https://i.ibb.co/NN823qR/Delete.png)

### Volumes (Basic Auth)
![Volumes](https://i.ibb.co/ZLJgr6V/Volumes.png)



# Autores ✒️

- [Nelson David Camacho Ovalle](https://github.com/ndcamachoo) - *Desarrollador*
