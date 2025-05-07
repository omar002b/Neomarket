package com.example.controllers;

import com.example.modelos.Producto;
import com.example.neomarket.ConexionBDD;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.text.Font;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

public class MainController implements Initializable {

    @FXML
    private AnchorPane productosPane;

    private ConexionBDD conexionBDD = new ConexionBDD();

    @FXML private Circle cAccesorios;
    @FXML private Circle cRopa;
    @FXML private Circle cHogar;
    @FXML private Circle cDeporte;
    @FXML private Circle cAutomovil;
    @FXML private Circle cTecnologia;

    @FXML private Button btnAccesorios;
    @FXML private Button btnRopa;
    @FXML private Button btnHogar;
    @FXML private Button btnDeporte;
    @FXML private Button btnAutomovil;
    @FXML private Button btnTecnologia;

    @FXML private Text numItems;

    private String categoriaSeleccionada = null;
    private List<Producto> todosLosProductos;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Oculta todos los círculos al inicio
        ocultarTodosLosCirculos();

        // Carga todos los productos una vez
        todosLosProductos = conexionBDD.obtenerTodosLosProductos();
        cargarProductos(todosLosProductos);

        // Listeners para los textos de categoría
        btnAccesorios.setOnMouseClicked(e -> seleccionarCategoria("Accesorios", cAccesorios));
        btnRopa.setOnMouseClicked(e -> seleccionarCategoria("Ropa", cRopa));
        btnHogar.setOnMouseClicked(e -> seleccionarCategoria("Hogar", cHogar));
        btnDeporte.setOnMouseClicked(e -> seleccionarCategoria("Deporte", cDeporte));
        btnAutomovil.setOnMouseClicked(e -> seleccionarCategoria("Automovil", cAutomovil));
        btnTecnologia.setOnMouseClicked(e -> seleccionarCategoria("Tecnologia", cTecnologia));
    }
    private void ocultarTodosLosCirculos() {
        cAccesorios.setVisible(false);
        cRopa.setVisible(false);
        cHogar.setVisible(false);
        cDeporte.setVisible(false);
        cAutomovil.setVisible(false);
        cTecnologia.setVisible(false);
    }

    private void seleccionarCategoria(String categoria, Circle circulo) {
        // Si ya está seleccionada, resetea
        if (categoria.equals(categoriaSeleccionada)) {
            ocultarTodosLosCirculos();
            categoriaSeleccionada = null;
            cargarProductos(todosLosProductos);
        } else {
            ocultarTodosLosCirculos();
            circulo.setVisible(true);
            categoriaSeleccionada = categoria;
            filtrarProductosPorCategoria(categoria);
        }
    }

    private void filtrarProductosPorCategoria(String categoria) {
        List<Producto> filtrados = todosLosProductos.stream()
                .filter(p -> categoria.equalsIgnoreCase(p.getCategoria()))
                .toList();
        cargarProductos(filtrados);
    }



    private void cargarProductos(List<Producto> productos) {
        productosPane.getChildren().clear();

        numItems.setText(String.valueOf(productos.size()));

        int col = 0;
        int row = 0;
        int x = 14;
        int y = 35;
        int separacionX = 310;
        int separacionY = 220;
        int maxCols = 3;

        for (int i = 0; i < productos.size(); i++) {
            Producto p = productos.get(i);

            Pane card = crearCardProducto(p);

            card.setLayoutX(x + col * separacionX);
            card.setLayoutY(y + row * separacionY);

            productosPane.getChildren().add(card);

            col++;
            if (col == maxCols) {
                col = 0;
                row++;
            }
        }
    }

    private Pane crearCardProducto(Producto producto) {
        Pane pane = new Pane();
        pane.setPrefSize(218, 200);


        ImageView imgView = new ImageView();
        imgView.setFitHeight(150);
        imgView.setFitWidth(200);
        imgView.setPreserveRatio(true);

        String imgPath = producto.getImagenes() != null && !producto.getImagenes().isEmpty()
                ? "file:src/main/resources/images/" + producto.getImagenes()
                : "file:src/main/resources/images/camisetaejemplo.jpg";
        imgView.setImage(new Image(imgPath));


        javafx.scene.control.Label precioLabel = new javafx.scene.control.Label(String.format("%.2f €", producto.getPrecio()));
        precioLabel.setFont(Font.font("System Bold", 16));
        precioLabel.setTextFill(javafx.scene.paint.Color.WHITE);
        precioLabel.setStyle("-fx-background-color: rgba(0,0,0,0.6); -fx-padding: 3 8 3 8; -fx-background-radius: 8;");
        StackPane.setAlignment(precioLabel, javafx.geometry.Pos.TOP_RIGHT);

        // Botón favorito en la esquina superior izquierda de la imagen
        Button favBtn = new Button();
        favBtn.setPrefSize(28, 26);
        favBtn.setStyle("-fx-background-color: white; -fx-background-radius: 100;");
        ImageView favImg = new ImageView(new Image("file:src/main/resources/images/i_favorito.png"));
        favImg.setFitHeight(15);
        favImg.setFitWidth(19);
        favImg.setPreserveRatio(true);
        favBtn.setGraphic(favImg);
        StackPane.setAlignment(favBtn, javafx.geometry.Pos.TOP_LEFT);
        favBtn.setOpacity(0.85);


        StackPane imgStack = new StackPane(imgView, precioLabel, favBtn);
        imgStack.setPrefSize(200, 150);

        Button imgBtn = new Button();
        imgBtn.setPrefSize(216, 152);
        imgBtn.setStyle("-fx-background-color: white;");
        imgBtn.setGraphic(imgStack);
        imgBtn.setLayoutX(0);
        imgBtn.setLayoutY(0);
        imgBtn.setCursor(javafx.scene.Cursor.HAND);


        javafx.scene.control.Label nombre = new javafx.scene.control.Label(producto.getNombre());
        nombre.setFont(Font.font("System Bold", 15));
        nombre.setLayoutX(10);
        nombre.setLayoutY(170);
        nombre.setPrefWidth(180);
        nombre.setMaxWidth(180);
        nombre.setWrapText(true);

        pane.getChildren().addAll(imgBtn, nombre);

        return pane;
    }
}