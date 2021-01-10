/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pooespol.proyecto2parcial.data;

import com.pooespol.proyecto2parcial.App;
import com.pooespol.proyecto2parcial.modelo.Plato;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author UserPC
 */
public class PlatoData {
    
    static String ruta = "platos.txt";
    
    public static ArrayList<Plato>leerPlatos() throws IOException {
        ArrayList<Plato> p = new ArrayList<>();
        
        try{
            URL u = App.class.getResource(ruta);
            File file = new File(u.toURI());
            try(BufferedReader bf = new BufferedReader(new FileReader(file))){
                String linea;
                while( (linea=bf.readLine())!=null ){
                    String[] partes = linea.split(";");
                    Plato plato = new Plato(partes[0], Double.parseDouble(partes[1]), partes[2]);
                    if (plato != null){
                        p.add(plato);
                    }
                }    
            }catch(FileNotFoundException ex) {
                System.out.println(ex.getMessage());
                throw ex;
            }catch (IOException ex) {
                System.out.println(ex.getMessage());
                throw ex;
            }       
        }catch(Exception ex){
            System.out.println(ex);
        }
        return p;
    
    }
    
    
    
    
    
    
}
