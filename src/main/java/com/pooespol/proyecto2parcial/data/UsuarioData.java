/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pooespol.proyecto2parcial.data;

import com.pooespol.proyecto2parcial.App;
import com.pooespol.proyecto2parcial.usuarios.Administrador;
import com.pooespol.proyecto2parcial.usuarios.Mesero;
import com.pooespol.proyecto2parcial.usuarios.Usuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

/**
 *
 * @author UserPC
 */
public class UsuarioData {
    
    static String ruta = "Usuarios.txt";
    
    public static ArrayList<Usuario>leerUsuarios() throws IOException {
        ArrayList<Usuario> usuarios = new ArrayList<>();
        
        try{
            URL u = App.class.getResource(ruta);
            File file = new File(u.toURI());
            try(BufferedReader bf = new BufferedReader(new FileReader(file))){
                String linea;
                while( (linea=bf.readLine())!=null ){
                    String[] partes = linea.split(";");
                    if(partes[0].equals("Administrador")){
                        Administrador a = new Administrador(partes[1],partes[2]);
                        usuarios.add(a);

                    }else if(partes[0].equals("Mesero")){
                        Mesero m = new Mesero(partes[1],partes[2],partes[3]);
                        usuarios.add(m);
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
        return usuarios;
    
    }    


    public static void registrarUsuarios(Usuario usuario) {   
        try{
            URL u = App.class.getResource(ruta);
            File file = new File(u.toURI());
            try(BufferedWriter bf = new BufferedWriter(new FileWriter(file, true))){
                String linea = usuario.toString() + ";" + usuario.getCorreo() + ";" + usuario.getContrasena();
                bf.write(linea);
                bf.newLine();    
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
    } 
    

    
}
