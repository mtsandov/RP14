/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pooespol.proyecto2parcial.modelo;

/**
 *
 * @author UserPC
 */
public class Ubicacion {

    private double x;
    private double y;

    public Ubicacion(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double calcularDistancia(Ubicacion u) {
        return Math.sqrt(Math.pow(u.x - x, 2) + Math.pow(u.y - y, 2));
    }

    @Override
    public String toString() {
        return "Ubicacion{" + "x=" + x + ", y=" + y + '}';
    }

}
