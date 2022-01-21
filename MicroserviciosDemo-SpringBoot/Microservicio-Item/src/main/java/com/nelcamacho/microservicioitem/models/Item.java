package com.nelcamacho.microservicioitem.models;
import com.nelcamacho.microserviciocommons.models.Producto; // Importamos el modelo de producto de commons

public class Item {

    /* ========================= Atributos ========================= */

    private Producto producto;
    private Integer cantidad;

    /* ========================= Getters y Setters ======================= */

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto producto) {
        this.producto = producto;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    /* ========================= Métodos ========================= */

    public Double getTotal() {
        return producto.getPrecio() * cantidad;
    }

    /* ========================= Constructores ========================= */

    public Item(Producto producto, Integer cantidad) {
        this.producto = producto;
        this.cantidad = cantidad;
    }

    public Item() {
    }

}
