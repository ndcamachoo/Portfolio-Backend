package main

import (
	ProductController "ApiGO-Productos/controller"
	"fmt"
)

func main(){
	fmt.Println("Inicialización de aplicación")
	ProductController.Start()
}
