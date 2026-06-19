package com.fitmanage.smartgym;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class Main extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        // Carga la vista que acabas de maquetar
        FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("main-view.fxml"));

        // Define el tamaño de la ventana (350x350)
        Scene scene = new Scene(fxmlLoader.load(), 350, 350);

        stage.setTitle("FitManage Telemetry System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}