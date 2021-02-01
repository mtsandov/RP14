/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pooespol.proyecto2parcial.data;

import com.pooespol.proyecto2parcial.App;
import com.pooespol.proyecto2parcial.modelo.Mesa;
import com.pooespol.proyecto2parcial.modelo.Plato;
import com.pooespol.proyecto2parcial.modelo.Ubicacion;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author UserPC
 */
public class PlatoData {
    
    static String ruta = "platos.txt";
    
    public static ArrayList<Plato>leerPlatos() throws IOException, ArchivosException {
        String ruta = "platos.txt";
        ArrayList<Plato> platos = new ArrayList<>();
        try (InputStream input = App.class.getResource(ruta).openStream();
                BufferedReader bf = new BufferedReader(
                        new InputStreamReader(input, "UTF-8"))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                System.out.println(linea);
                String[] lista = linea.split(";");
                Plato p = new Plato(lista[0],Double.valueOf(lista[1]),lista[2]);
                System.out.println(p);
                platos.add(p);
            }
            return platos;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new ArchivosException(ruta, ex.getMessage());
        }
    }
    
    public static void agregarPlatosArchivo(Plato plato) throws IOException, ArchivosException{
        String ruta = "platos.txt";
        List<Plato> platos= PlatoData.leerPlatos();

        //try(InputStream input = App.class.getResource(ruta).openStream();
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
        try{
        URL u = App.class.getResource(ruta);
        File file = new File(u.toURI());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

            String linea;
            for (Plato pl : platos) {
                linea = pl.getNombre()+";"+pl.getPrecio()+";"+pl.getImagen();
                bw.write(linea);
                bw.newLine();
            }

            linea = plato.getNombre()+";"+plato.getPrecio()+";"+plato.getImagen();
            bw.write(linea);
            bw.newLine();

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new ArchivosException(ruta, ex.getMessage());
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
    
    }
    
    public static void editarPlatosArchivo(Plato plato)throws IOException, ArchivosException{
        String ruta = "platos.txt";
        List<Plato> platos = PlatoData.leerPlatos();
        try{
            URL u = App.class.getResource(ruta);
            File file = new File(u.toURI());
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
                String linea;
                for (Plato pl: platos){
                    if (pl.getNombre().equals(plato.getNombre())){
                        linea = pl.getNombre()+";"+plato.getPrecio()+";"+pl.getImagen();
                    }else{
                        linea = pl.getNombre()+";"+pl.getPrecio()+";"+pl.getImagen();
                    }
                    bw.write(linea);
                    bw.newLine();
                }
            }catch(IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new ArchivosException(ruta, ex.getMessage());
            }
        }catch(Exception ex){
            System.out.println(ex);
        
        }
    }
    
    public static void eliminarPlatosArchivo(String nombre) throws IOException, ArchivosException{
        String ruta = "platos.txt";
        List<Plato> platos = PlatoData.leerPlatos();
        try{
            URL u = App.class.getResource(ruta);
            File file = new File(u.toURI());
            try(BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
                String linea;
                for (Plato pl: platos){
                    if (!pl.getNombre().equals(nombre)){
                       linea = pl.getNombre()+";"+pl.getPrecio()+";"+pl.getImagen();
                       bw.write(linea);
                       bw.newLine();
                }
                }
            }catch(IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new ArchivosException(ruta, ex.getMessage());
            }
        }catch(Exception ex){
            System.out.println(ex);
        
        }        
    }    

    
        
}
