package com.example.controllers;

import com.example.neomarket.ConexionBDD;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.SQLException;
import java.time.format.DateTimeFormatter;

import static com.example.controllers.Controller.cambiarVista;


public class RegistroController {

    @FXML
    private TextField introducirNombre;

    @FXML
    private TextField introducirApellido;

    @FXML
    private TextField introducirEmail;

    @FXML
    private PasswordField introducirContra1;

    @FXML
    private PasswordField introducirContra2;

    @FXML
    private DatePicker introducirFecha;

    @FXML
    private Button btnRegistro;  // Botón "Siguiente"

    private ConexionBDD conexionBDD;

    public RegistroController() {
        conexionBDD = new ConexionBDD();
    }


    @FXML
    private void handleRegistro(ActionEvent event) {
        String nombre = introducirNombre.getText().trim();
        String apellido = introducirApellido.getText().trim();
        String email = introducirEmail.getText().trim();
        String contra1 = introducirContra1.getText();
        String contra2 = introducirContra2.getText();
        String fechaNacimiento = introducirFecha.getValue() != null ?
                introducirFecha.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) : "";

        // Validaciones básicas
        if (nombre.isEmpty() || apellido.isEmpty() || email.isEmpty() ||
                contra1.isEmpty() || contra2.isEmpty() || fechaNacimiento.isEmpty()) {

            showAlert("Error", "Todos los campos son obligatorios.", Alert.AlertType.ERROR);
        }

        if (!contra1.equals(contra2)) {
            showAlert("Error", "Las contraseñas no coinciden.", Alert.AlertType.ERROR);
        }

        boolean registrado = conexionBDD.registrarUsuario(nombre, apellido, email, contra1, fechaNacimiento);

        if (registrado) {
            showAlert("Éxito", "Usuario registrado correctamente.", Alert.AlertType.INFORMATION);
            // Aquí puedes cambiar a la siguiente vista si lo deseas
            cambiarVista("login.fxml", "Neomarket - Login");
        } else {
            showAlert("Error", "No se pudo registrar el usuario. Verifica los datos.", Alert.AlertType.ERROR);
        }

    }

    private void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
