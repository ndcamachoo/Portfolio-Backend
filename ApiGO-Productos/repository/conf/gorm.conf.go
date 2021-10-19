package conf

import (
	"ApiGO-Productos/model"
	"fmt"
	"gorm.io/driver/sqlite" //go get -u gorm.io/driver/sqlite
	"gorm.io/gorm" //go get -u gorm.io/gorm
)

func InitDatabase() *gorm.DB{

	db, err := gorm.Open(sqlite.Open("./repository/database/productos.db"), &gorm.Config{})
	if err != nil {
		panic("failed to connect database")
	}

	fmt.Println("Base de datos conectada correctamente")

	err2 := db.AutoMigrate(&model.Producto{})
	if err2 != nil {
		panic("failed to migrate database")
	}

	fmt.Println("Base de datos migrada")

	return db

}
