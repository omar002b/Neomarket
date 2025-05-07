package com.example.neomarket;

import com.example.modelos.Usuario;

public class Session {
    private static Usuario usuarioActual;

    // Método para establecer el usuario actual cuando inicia sesión
    public static void setUsuarioActual(Usuario usuario) {
        usuarioActual = usuario;
    }

    // Método para obtener el usuario que está logueado actualmente
    public static Usuario getUsuarioActual() {
        return usuarioActual;
    }

    // Método para cerrar sesión
    public static void logout() {
        usuarioActual = null;
    }
}
