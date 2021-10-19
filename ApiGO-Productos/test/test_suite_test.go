package test_test

import (
	"ApiGO-Productos/model"
	ProductService "ApiGO-Productos/service"
	"gorm.io/datatypes" // go get -u gorm.io/datatypes
	"testing"
	"time"

	. "github.com/onsi/ginkgo" // go get github.com/onsi/ginkgo/ginkgo
	. "github.com/onsi/gomega" // go get github.com/onsi/gomega/...
)

func TestTest(t *testing.T) {
	RegisterFailHandler(Fail)
	RunSpecs(t, "Test Suite")
}

var testProduct = model.Producto{
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


var _ = Describe("Product service test", func(){

	It("Save products", func() {
		data, err := ProductService.Save(testProduct)

		Expect(err).To(BeNil())
		Expect(err).NotTo(HaveOccurred())

		Expect(data.Name).To(Equal(testProduct.Name))
		Expect(data.Status).To(Equal(testProduct.Status))
		Expect(data.UnitDescription).To(Equal(testProduct.UnitDescription))

	})

	It("Read products by ID", func() {

		data, err := ProductService.Read(testProduct.ProductId)

		Expect(err).To(BeNil())
		Expect(err).NotTo(HaveOccurred())

		Expect(data.Description).To(Equal(testProduct.Description))
		Expect(data.AccountId).To(Equal(testProduct.AccountId))
		Expect(data.ValueUnit).To(Equal(testProduct.ValueUnit))

	})

	It("Read all products", func() {

		data, err := ProductService.ReadAll()

		Expect(err).To(BeNil())
		Expect(err).NotTo(HaveOccurred())

		Expect(len(data) > 0).To(BeTrue())

		Expect(data[0].ProductId).To(Equal(testProduct.ProductId))
		Expect(data[0].AccountId).To(Equal(testProduct.AccountId))

	})

	It("Update products", func() {

		data, err :=  ProductService.Read("pd1")

		Expect(err).To(BeNil())
		Expect(err).NotTo(HaveOccurred())

		data.Description = "Update description"
		data.UnitDescription = "updated unit test description"
		data.Status = "disabled"

		err2 := ProductService.Update(data, data.ProductId)

		Expect(err2).To(BeNil())
		Expect(err2).NotTo(HaveOccurred())

		_, err3 := ProductService.Read(testProduct.ProductId)

		Expect(err3).To(BeNil())
		Expect(err3).NotTo(HaveOccurred())

		Expect(data.ProductId).To(Equal(testProduct.ProductId))

		Expect(data.Description).ToNot(Equal(testProduct.Description))
		Expect(data.UnitDescription).ToNot(Equal(testProduct.UnitDescription))
		Expect(data.Status).ToNot(Equal(testProduct.Status))

	})

	It("Delete products by ID", func() {

		err := ProductService.Delete(testProduct.ProductId)

		Expect(err).To(BeNil())
		Expect(err).NotTo(HaveOccurred())

		_, err2 := ProductService.Read(testProduct.ProductId)

		Expect(err2).ToNot(BeNil())
		Expect(err2).To(HaveOccurred())

	})

})
