/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pooespol.proyecto2parcial.data;

/**
 *
 * @author UserPC
 */
public class ArchivosException extends Exception{
    String filename;
    public ArchivosException(String filename){
        this(filename,"No se puede procesar archivo archivo"+filename);
    }
    public ArchivosException(String filename,String msg){
        super(msg);
        this.filename=filename;
    }
}
