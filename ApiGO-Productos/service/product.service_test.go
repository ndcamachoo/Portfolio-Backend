package service_test

import (
	"ApiGO-Productos/model"
	ProductService "ApiGO-Productos/service"
	"gorm.io/datatypes" // go get -u gorm.io/datatypes
	"testing"
	"time"
)

func TestSave(t *testing.T) {

	pd := model.Producto{
		ProductId: "pd1",
		Name: "Product1",
		Description: "Product test description",
		Status: "enabled",
		CreationDate: time.Now(),
		UpdateDate: time.Now(),
		AccountId: "default",
		FormatProduct: datatypes.JSON([]byte(`{"msg": "message test"}`)),
		ValueUnit: 100.00,
		UnitName: "unit1",
		UnitDescription: "Unit test description",
		Stock: 1,
	}

	data, err :=  ProductService.Save(pd)

	if err != nil{
		t.Error("La prueba de persistencia de datos del producto a fallado.")
		t.Fail()
	}else {
		t.Log(data)
		t.Log("La prueba finalizo con exito")
	}

}

func TestRead(t *testing.T) {

	data, err :=  ProductService.Read("pd1")

	if err != nil{
		t.Error("Se ha presentado un error en la consulta de productos")
		t.Fail()
	}else {
		t.Log(data)
		t.Log("La prueba finalizo con exito")
	}

}

func TestReadAll(t *testing.T) {

	data, err :=  ProductService.ReadAll()

	if err != nil{
		t.Error("Se ha presentado un error en la consulta general de productos")
		t.Fail()
	}else {
		t.Log(data)
		t.Log("La prueba finalizo con exito")
	}

}

func TestUpdate(t *testing.T){

	data, _ :=  ProductService.Read("pd1")

	data.Description = "Update description"
	data.UnitDescription = "updated unit test description"
	data.Status = "disabled"

	err := ProductService.Update(data, data.ProductId)

	dataUpdated, _ :=  ProductService.Read("pd1")

	if err != nil{
		t.Error("Se ha presentado un error en la actualización de productos")
		t.Fail()
	}else {
		t.Log(dataUpdated)
		t.Log("La prueba finalizo con exito")
	}

}

func TestDelete(t *testing.T) {

	err := ProductService.Delete("pd1")

	if err != nil{
		t.Error("Se ha presentado un error en la eliminación de productos")
		t.Fail()
	}else {
		t.Log("La prueba finalizo con exito")
	}

}