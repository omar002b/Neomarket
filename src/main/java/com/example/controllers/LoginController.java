package com.example.controllers;

import com.example.neomarket.ConexionBDD;
import com.example.modelos.Usuario;
import com.example.neomarket.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import static com.example.controllers.Controller.cambiarVista;
import static com.example.controllers.Controller.showAlert;

public class LoginController {

    @FXML
    private TextField emailField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Button loginBtn;

    @FXML
    private Button registerBtn;

    private ConexionBDD conexionBDD;

    public LoginController() {
        conexionBDD = new ConexionBDD();
    }

    @FXML
    private void handleLogin(ActionEvent event) {
        String email = emailField.getText();
        String password = passwordField.getText();

        if (email.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Todos los campos son obligatorios", Alert.AlertType.ERROR);
            return;
        }

        // Obtener usuario desde la base de datos
        Usuario usuario = conexionBDD.verificarCredenciales(email, password);

        if (usuario != null) {  // Si las credenciales son correctas
            Session.setUsuarioActual(usuario);  // Guardar usuario en la sesión
            showAlert("Éxito", "Inicio de sesión correcto", Alert.AlertType.INFORMATION);
            cambiarVista("main.fxml", "Neomarket - Inicio");
        } else {
            showAlert("Error", "Credenciales incorrectas", Alert.AlertType.ERROR);
        }
    }

    @FXML
    private void handleRegister(ActionEvent event) {
        cambiarVista("registrar.fxml", "Neomarket - Registro");
    }
}
