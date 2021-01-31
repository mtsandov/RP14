/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pooespol.proyecto2parcial.modelo;

import com.pooespol.proyecto2parcial.usuarios.Mesero;

/**
 *
 * @author UserPC
 */
public class Mesa {

    private int numMesa;
    private Ubicacion ub;
    private String mesero;
    private String estado;
    private int capacidad;

    public Mesa(int numMesa, Ubicacion ub, String mesero, String estado, int capacidad) {
        this.numMesa = numMesa;
        this.ub = ub;
        this.mesero = mesero;
        this.estado = estado;
        this.capacidad = capacidad;
    }

    public int getNumMesa() {
        return numMesa;
    }

    public Ubicacion getUbicacion() {
        return ub;
    }

    public String getMesero() {
        return mesero;
    }

    public String getEstado() {
        return estado;
    }

    public void setNumMesa(int numMesa) {
        this.numMesa = numMesa;
    }

    public void setUbicacion(Ubicacion ub) {
        this.ub = ub;
    }

    public void setMesero(String mesero) {
        this.mesero = mesero;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCapacidad() {
        return capacidad;
    }

    @Override
    public String toString() {
        return estado;
    }

    
    
}
