package com.example.modelos;

public class Producto {
    private int idProducto;
    private int idVendedor;
    private String nombre;
    private String descripcion;
    private static String imagenes; // Ruta o nombre de archivo de la imagen principal
    private int likes;
    private String categoria;
    private String marca;
    private String estado;
    private String estadoAnuncio;
    private String fechaPublicacion;
    private double precio;
    private int unidades;
    private int favoritos;

    public Producto(int idProducto, int idVendedor, String nombre, String descripcion, String imagenes, int likes,
                    String categoria, String marca, String estado, String estadoAnuncio, String fechaPublicacion,
                    double precio, int unidades, int favoritos) {
        this.idProducto = idProducto;
        this.idVendedor = idVendedor;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
        this.likes = likes;
        this.categoria = categoria;
        this.marca = marca;
        this.estado = estado;
        this.estadoAnuncio = estadoAnuncio;
        this.fechaPublicacion = fechaPublicacion;
        this.precio = precio;
        this.unidades = unidades;
        this.favoritos = favoritos;
    }

    // Constructor simplificado para consultas básicas
    public Producto(int idProducto, String nombre, String descripcion, String imagenes, double precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.imagenes = imagenes;
        this.precio = precio;
    }

    // Getters y Setters

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public int getIdVendedor() {
        return idVendedor;
    }

    public void setIdVendedor(int idVendedor) {
        this.idVendedor = idVendedor;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public static String getImagenes() {
        return imagenes;
    }

    public void setImagenes(String imagenes) {
        this.imagenes = imagenes;
    }

    public int getLikes() {
        return likes;
    }

    public void setLikes(int likes) {
        this.likes = likes;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstadoAnuncio() {
        return estadoAnuncio;
    }

    public void setEstadoAnuncio(String estadoAnuncio) {
        this.estadoAnuncio = estadoAnuncio;
    }

    public String getFechaPublicacion() {
        return fechaPublicacion;
    }

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getUnidades() {
        return unidades;
    }

    public void setUnidades(int unidades) {
        this.unidades = unidades;
    }

    public int getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(int favoritos) {
        this.favoritos = favoritos;
    }
}