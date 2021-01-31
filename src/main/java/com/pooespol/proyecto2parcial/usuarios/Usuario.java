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
public class Usuario {
    
    private String correo;
    private String contrasena;
    private String tipo = "Usuario";

    public Usuario(String correo, String contrasena, String tipo) {
        this.correo = correo;
        this.contrasena = contrasena;
        this.tipo = tipo;
    }

    
    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    @Override
    public String toString() {
        return tipo;
    }

    
    
    
}
