/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package com.mycompany.gestiondereservas;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebView;
import javafx.stage.Stage;

/**
 *
 * @author ben_9
 */
public class GestionDeReservas extends Application {

    @Override
    public void start(Stage primaryStage) {
        WebView webView = new WebView();
        webView.getEngine().load("https://example.com");

        BorderPane root = new BorderPane(webView);
        Scene scene = new Scene(root, 800, 600);

        primaryStage.setTitle("JavaFX WebView Example");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
