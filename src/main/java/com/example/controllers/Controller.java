package com.example.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class Controller {

    static Stage getCurrentStage() {
        for (Window window : Stage.getWindows()) {
            if (window.isShowing() && window instanceof Stage) {
                return (Stage) window;
            }
        }
        return null;
    }


    public static void cambiarVista(String fxmlFile, String title) {
        try {
            FXMLLoader loader = new FXMLLoader(Controller.class.getResource("/com/example/vistas/" + fxmlFile));
            Scene newScene = new Scene(loader.load());

            // Obtener el Stage actual
            Stage stage = getCurrentStage();
            if (stage != null) {
                stage.setScene(newScene);
                stage.setTitle(title);
                stage.show();
            } else {
                showAlert("Error", "No se pudo obtener la ventana actual.", Alert.AlertType.ERROR);
            }
        } catch (IOException e) {
            showAlert("Error", "No se pudo cargar la vista: " + fxmlFile, Alert.AlertType.ERROR);
            e.printStackTrace();
        }
    }


    public static void showAlert(String title, String message, Alert.AlertType type) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
