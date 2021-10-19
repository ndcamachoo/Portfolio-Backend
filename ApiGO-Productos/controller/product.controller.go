package controller

import (
	"ApiGO-Productos/model"
	ProductService "ApiGO-Productos/service"
	"encoding/json"
	"fmt"
	"github.com/gin-gonic/gin" //go get -u github.com/gin-gonic/gin
	"io/ioutil"
	"net/http"
)

func Read(c *gin.Context){

	id := c.Param("id")

	data, err := ProductService.Read(id)

	if err != nil {
		c.JSON(http.StatusNotFound, gin.H{
			"Message": "Búsqueda en la entidad Productos fallida por el ID: " + id,
		})
	}else{
		c.JSON(http.StatusOK, data)
	}

}

func ReadAll(c *gin.Context){

	data, err := ProductService.ReadAll()

	if err != nil {
		c.JSON(http.StatusNotFound, gin.H{
			"Message": "Lectura general de la entidad Productos fallida",
		})
	}else {
		c.JSON(http.StatusOK, data)
	}

}

func Save(c *gin.Context){

	var product model.Producto
	decoder := json.NewDecoder(c.Request.Body)
	err1 := decoder.Decode(&product)

	if err1 != nil {
		panic(err1.Error())
	}

	data, err := ProductService.Save(product)

	if err != nil {
		c.JSON(http.StatusBadRequest, gin.H{
			"Message": "Almacenamiento fallido en la entidad Productos",
		})
	}else{
		c.JSON(http.StatusCreated, data)
	}

}

func Update(c *gin.Context){

	var product model.Producto
	decoder := json.NewDecoder(c.Request.Body)
	err1 := decoder.Decode(&product)

	if err1 != nil {
		panic(err1.Error())
	}

	err := ProductService.Update(product, product.ProductId)

	if err != nil {
		c.JSON(http.StatusNotFound, gin.H{
			"Message": "Actualización de campos en la entidad Productos fallida -> ID no existente: " + product.ProductId,
		})
	}else{
		c.JSON(http.StatusOK, gin.H{
			"Message": "Dato actualizado correctamente la entidad Productos -> ID: " + product.ProductId,
		})
	}

}

func Delete(c *gin.Context){

	id := c.Param("id")
	err := ProductService.Delete(id)

	if err != nil {
		c.JSON(http.StatusNotFound, gin.H{
			"Message": "Eliminación de campos en la entidad Productos fallida -> ID no existente: " + id,
		})
	}else {
		c.JSON(http.StatusNoContent, gin.H{
			"Message": "Dato eliminado correctamente en la entidad Productos -> ID: " + id,
		})
	}


}

func Volumes (c *gin.Context){

	jsonFile, _ := ioutil.ReadFile("./repository/volumes/volumen_list.json")
	var data interface{}
	err := json.Unmarshal(jsonFile, &data)

	if err != nil {
		c.JSON(http.StatusNotFound, gin.H{
			"Message": "Archivo volumes_list no existente: ",
		})
	}

	fmt.Println(data)
	c.JSON(http.StatusOK, data)
}


func Start(){

	r := gin.Default()

	authorized := r.Group("/", gin.BasicAuth(gin.Accounts{
		"user": "user",
		"admin": "admin",
	}))

	r.GET("/", ReadAll)
	r.GET("/:id", Read)
	authorized.GET("/volumes", Volumes)
	r.POST("/save", Save)
	r.PUT("/update", Update)
	r.DELETE("/delete/:id", Delete)
	r.Run(":9098")

}

