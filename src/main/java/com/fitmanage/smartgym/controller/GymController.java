package com.fitmanage.smartgym.controller;

import com.fitmanage.smartgym.model.RegistroCentral;
import com.fitmanage.smartgym.model.SensorHilo;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

public class GymController {

    // Variables enlazadas automáticamente con el FXML
    @FXML private Label labelTemp;
    @FXML private Label labelHum;
    @FXML private Pane panelAlerta;

    private RegistroCentral modelo;

    @FXML
    public void initialize() {
        // Inicialización del recurso crítico compartido [cite: 142]
        this.modelo = new RegistroCentral();

        // TODO: Issue #3 - Instanciar las tareas de los sensores [cite: 144]
        SensorHilo tareaTemp = new SensorHilo("TEMPERATURA", modelo, this);
        SensorHilo tareaHum = new SensorHilo("HUMEDAD", modelo, this);

        // Configuramos como Daemon para que se apaguen solos al cerrar la app [cite: 170]
        tareaTemp.setDaemon(true);
        tareaHum.setDaemon(true);

        // TODO: Issue #3 - Arrancar su ejecución [cite: 147]
        tareaTemp.start();
        tareaHum.start();
    }

    // TODO: Issue #3 - Implementar la actualización de la UI [cite: 156]
    public void actualizarPantalla() {
        // 1. Invocación obligatoria para modificar la vista de forma segura [cite: 159]
        Platform.runLater(() -> {
            // 2. Extraer métricas actualizadas del modelo [cite: 160]
            double temp = modelo.getUltimaTemperatura();
            double hum = modelo.getUltimaHumedad();

            // 3. Modificar los textos [cite: 162]
            labelTemp.setText("Temperatura: " + temp + " °C");
            labelHum.setText("Humedad: " + hum + " %");

            // 4. Evaluar condicionalmente [cite: 163]
            if (temp > 35 || hum < 20) {
                // Cambiar el fondo a ROJO [cite: 164, 166]
                panelAlerta.setStyle("-fx-background-color: #d9534f; -fx-background-radius: 10;");
            } else {
                // Mantenerlo VERDE [cite: 167]
                panelAlerta.setStyle("-fx-background-color: #5cb85c; -fx-background-radius: 10;");
            }
        });
    }
}