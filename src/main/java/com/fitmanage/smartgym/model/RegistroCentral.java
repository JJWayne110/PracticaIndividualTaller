package com.fitmanage.smartgym.model;

public class RegistroCentral {
    private double ultimaTemperatura = 25.0;
    private double ultimaHumedad = 50.0;

    // TODO: Issue #2 - Método Hilo-Seguro
    public synchronized void registrarTemperatura(double temp) {
        this.ultimaTemperatura = temp;
        System.out.println("[MODELO] Datos de Temperatura guardados en memoria: " + temp + " °C");
    }

    // TODO: Issue #2 - Método Hilo-Seguro
    public synchronized void registrarHumedad(double hum) {
        this.ultimaHumedad = hum;
        System.out.println("[MODELO] Datos de Humedad guardados en memoria: " + hum + " %");
    }

    public double getUltimaTemperatura() { return ultimaTemperatura; }
    public double getUltimaHumedad() { return ultimaHumedad; }
}