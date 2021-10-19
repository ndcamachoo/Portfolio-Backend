package service

import (
	"ApiGO-Productos/model"
	ProductRepository "ApiGO-Productos/repository"
)

func Save(product model.Producto) (model.Producto, error){
	return ProductRepository.Save(product)
}

func Read(productId string) (model.Producto, error){
	return ProductRepository.Read(productId)
}

func ReadAll() ([]model.Producto,error){
	return ProductRepository.ReadAll()
}

func Update(product model.Producto, productId string) error{
	return ProductRepository.Update(product, productId)
}

func Delete(productId string) error{
	return ProductRepository.Delete(productId)
}