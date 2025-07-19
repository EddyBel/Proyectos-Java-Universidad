package org.example.conferencias;

import javafx.fxml.FXML;
import javafx.scene.web.WebView;

import java.io.File;

public class HelloController {

    @FXML
    private WebView webView;

    @FXML
    public void initialize() {
        // Cargar una página web en el WebView
//        webView.getEngine().load("https://www.google.com/"); // Puedes cambiar la URL por la que necesites

        // Ruta del archivo HTML local
        File file = new File("src/main/resources/index.html");
        webView.getEngine().load(file.toURI().toString()); // Cargar el archivo local
    }
}
