package com.example.modelos;

import java.util.Date;
import java.io.Serializable;


public class Usuario implements Serializable {
    // Propiedades basadas en la estructura de la tabla
    private int idUsuario;
    private String foto;
    private String nombre;
    private String apellido;
    private String descripcion;
    private String dni;
    private String email;
    private String fechaNacimiento;
    private String contrasena;
    private String direccionEnvio;
    private String estado; // "alta" o "baja"
    private boolean verificacion;
    private boolean newsletter;
    private String tipoUsuario; // "usuario" o "administrador"
    private Date ultLogin;


    public Usuario(String nombre, String dni, String email, String contrasena, String fechaNacimiento) {
        this.nombre = nombre;
        this.dni = dni;
        this.email = email;
        this.contrasena = contrasena;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = "alta";
        this.verificacion = false;
        this.newsletter = false;
        this.tipoUsuario = "usuario";
    }


    public Usuario(int idUsuario, String foto, String nombre, String apellido, String descripcion,
                   String dni, String email, String fechaNacimiento, String contrasena, String estado,
                   boolean verificacion, String direccionEnvio, boolean newsletter, String tipoUsuario, Date ultLogin) {
        this.idUsuario = idUsuario;
        this.foto = foto;
        this.nombre = nombre;
        this.apellido = apellido;
        this.descripcion = descripcion;
        this.dni = dni;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.contrasena = contrasena;
        this.estado = estado;
        this.verificacion = verificacion;
        this.direccionEnvio = direccionEnvio;
        this.newsletter = newsletter;
        this.tipoUsuario = tipoUsuario;
        this.ultLogin = ultLogin;
    }

    public Usuario(int idUsuario, String nombre, String apellido, String descripcion, String dni,
                   String email, String fechaNacimiento, String contrasena, String direccionEnvio,
                   String estado, boolean verificacion, boolean newsletter, String tipoUsuario, Date ultLogin) {

        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.descripcion = descripcion;
        this.dni = dni;
        this.email = email;
        this.fechaNacimiento = fechaNacimiento;
        this.contrasena = contrasena;
        this.direccionEnvio = direccionEnvio;
        this.estado = estado;
        this.verificacion = verificacion;
        this.newsletter = newsletter;
        this.tipoUsuario = tipoUsuario;
        this.ultLogin = ultLogin;
    }

    // Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getDireccionEnvio() {
        return direccionEnvio;
    }

    public void setDireccionEnvio(String direccionEnvio) {
        this.direccionEnvio = direccionEnvio;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public boolean isVerificacion() {
        return verificacion;
    }

    public void setVerificacion(boolean verificacion) {
        this.verificacion = verificacion;
    }

    public boolean isNewsletter() {
        return newsletter;
    }

    public void setNewsletter(boolean newsletter) {
        this.newsletter = newsletter;
    }

    public String getTipoUsuario() {
        return tipoUsuario;
    }

    public void setTipoUsuario(String tipoUsuario) {
        this.tipoUsuario = tipoUsuario;
    }

    public Date getUltLogin() {
        return ultLogin;
    }

    public void setUltLogin(Date ultLogin) {
        this.ultLogin = ultLogin;
    }

    // Métodos adicionales

    /**
     * Verifica si el usuario tiene permisos de administrador
     *
     * @return true si el tipo de usuario es "administrador"
     */
    public boolean isAdmin() {
        return tipoUsuario != null && tipoUsuario.equals("administrador");
    }

    /**
     * Verifica si el usuario está activo
     *
     * @return true si el estado es "alta"
     */
    public boolean isActivo() {
        return estado != null && estado.equals("alta");
    }

    /**
     * Actualiza la fecha del último login
     */
    public void actualizarUltimoLogin() {
        this.ultLogin = new Date();
    }

    /**
     * Método para dar de baja al usuario
     */
    public void darDeBaja() {
        this.estado = "baja";
    }

    /**
     * Método para dar de alta al usuario
     */
    public void darDeAlta() {
        this.estado = "alta";
    }

    /**
     * Método para verificar un DNI (implementación básica)
     *
     * @return true si el DNI tiene el formato correcto
     */
    public boolean validarDni() {
        // Formato básico: 8 números y una letra
        return dni != null && dni.matches("\\d{8}[A-Za-z]");
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", nombre='" + nombre + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", estado='" + estado + '\'' +
                ", tipoUsuario='" + tipoUsuario + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Usuario usuario = (Usuario) o;
        return idUsuario == usuario.idUsuario;
    }

    @Override
    public int hashCode() {
        return idUsuario;
    }
}