package sql

import (
	u "dao/util"
	"database/sql"
	"fmt"
	"log"
	_ "github.com/go-sql-driver/mysql"
)

func get() *sql.DB {
	config, err := u.GetConfig()

	if err != nil {
		log.Fatal(err)
		return nil
	}

	dsn := fmt.Sprintf("%s:%s@tcp(%s:%d)/%s?tls=false&autocommit=true", config.User, config.Pass, config.Server, config.Port, config.DBName)

	db, err := sql.Open("mysql", dsn)

	if err != nil {
		log.Fatal(err)
		return nil
	}

	err = db.Ping()

	if err != nil {
		log.Fatal(err)
		return nil
	}

	return db

}