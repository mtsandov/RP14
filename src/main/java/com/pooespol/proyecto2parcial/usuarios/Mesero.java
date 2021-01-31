/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pooespol.proyecto2parcial.usuarios;

/**
 *
 * @author UserPC
 */
public class Mesero extends Usuario{
    
    private String nombre;

    public Mesero(String correo, String contrasena, String nombre) {
        super(correo, contrasena,"Mesero");
        this.nombre = nombre;
    }
    
    
}
