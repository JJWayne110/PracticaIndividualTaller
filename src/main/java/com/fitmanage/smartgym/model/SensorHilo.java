package com.fitmanage.smartgym.model;

import com.fitmanage.smartgym.controller.GymController;
import java.util.Random;

// TODO: Issue #2 - Modificar la firma de la clase
public class SensorHilo extends Thread {

    private final String tipoSensor; // Recibe "TEMPERATURA" o "HUMEDAD"
    private final RegistroCentral modelo;
    private final GymController controlador;
    private final Random random = new Random();

    public SensorHilo(String tipoSensor, RegistroCentral modelo, GymController controlador) {
        this.tipoSensor = tipoSensor;
        this.modelo = modelo;
        this.controlador = controlador;
    }

    // TODO: Issue #2 - Sobrescribir el método de ciclo de vida
    @Override
    public void run() {
        // 1. Crear un ciclo infinito
        while (true) {
            try {
                // 2 y 3. Generar aleatorios según el tipo de sensor
                if (tipoSensor.equals("TEMPERATURA")) {
                    double temp = 15 + random.nextDouble() * (45 - 15);
                    modelo.registrarTemperatura(Math.round(temp * 10.0) / 10.0);
                } else if (tipoSensor.equals("HUMEDAD")) {
                    double hum = 10 + random.nextDouble() * (90 - 10);
                    modelo.registrarHumedad(Math.round(hum * 10.0) / 10.0);
                }

                // 4. Invocar el método del controlador para refrescar visualmente
                controlador.actualizarPantalla();

                // 5. Pausar la ejecución asíncrona para simular tiempo de lectura (ej. 2 segundos)
                Thread.sleep(2000);

            } catch (InterruptedException e) {
                System.out.println("El hilo " + tipoSensor + " se detuvo.");
                break;
            }
        }
    }
}