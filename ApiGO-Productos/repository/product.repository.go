package repository

import (
	"ApiGO-Productos/model"
	"ApiGO-Productos/repository/conf"
	"log"
	"time"
)

var db = conf.InitDatabase()

func Save(product model.Producto) (model.Producto, error){

	err := db.Create(&product)

	if err != nil {
		return product, err.Error
	}

	return product, nil
}

func Read(productId string) (model.Producto, error){

	var product model.Producto
	err := db.First(&product, "product_id = ?", productId)

	if err != nil {
		return product, err.Error
	}

	return product, nil
}

func ReadAll() ([]model.Producto, error){

	var products []model.Producto
	if err := db.Find(&products).Error; err != nil {
		return products, err
	}

	return products, nil
}

func Update(product model.Producto, productId string) error{

	var pd, err = Read(productId)

	if err != nil {
		return err
	}

	pd.Name = product.Name
	pd.Description = product.Description
	pd.Status = product.Status
	pd.UpdateDate = time.Now()
	pd.AccountId = product.AccountId
	pd.FormatProduct = product.FormatProduct
	pd.ValueUnit = product.ValueUnit
	pd.UnitName = product.UnitName
	pd.UnitDescription = product.UnitDescription
	pd.Stock = product.Stock

	err2 := db.Save(&pd)

	if err2 != nil {
		return err2.Error
	}

	log.Printf("El producto con el id: %s se ha actualizado", pd.ProductId)

	return nil

}

func Delete(productId string) error{
	var product model.Producto
	err := db.Delete(&product, "product_id = ?", productId)

	if err != nil {
		return err.Error
	}

	log.Printf("El producto con el id: %s se ha eliminado", productId)

	return nil
}