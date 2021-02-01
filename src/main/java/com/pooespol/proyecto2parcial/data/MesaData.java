/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pooespol.proyecto2parcial.data;

import com.pooespol.proyecto2parcial.App;
import com.pooespol.proyecto2parcial.modelo.Mesa;
import com.pooespol.proyecto2parcial.modelo.Ubicacion;
import com.pooespol.proyecto2parcial.usuarios.Usuario;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author UserPC
 */
public class MesaData {

    public static List<Mesa> cargarMesaArchivos(String nombre_archivo)
            throws ArchivosException {

        String ruta = "UbicacionMesas.txt";
        List<Mesa> tiendas = new ArrayList<>();
        try (InputStream input = App.class.getResource(ruta).openStream();
                BufferedReader bf = new BufferedReader(
                        new InputStreamReader(input, "UTF-8"))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                System.out.println(linea);
                String[] lista = linea.split(";");
                String nombreMesero = lista[0];
                int numMesa = Integer.valueOf(lista[1]);
                String[] ub = lista[2].split(":");
                Ubicacion ubicacion = new Ubicacion(Double.valueOf(ub[0]), Double.valueOf(ub[1]));
                String estado = lista[3];
                int capacidad = Integer.valueOf(lista[4]);
                Mesa t = new Mesa(numMesa, ubicacion, nombreMesero, estado, capacidad);
                tiendas.add(t);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new ArchivosException(ruta, ex.getMessage());
        }
        return tiendas;
    }

    public static void agregarMesaArchivos(Mesa m)
            throws ArchivosException {

        String ruta = "UbicacionMesas.txt";
        List<Mesa> mesas = MesaData.cargarMesaArchivos("UbicacionMesas.txt");

        //try(InputStream input = App.class.getResource(ruta).openStream();
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
        try{
        URL u = App.class.getResource(ruta);
        File file = new File(u.toURI());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

            String linea;
            for (Mesa me : mesas) {
                linea = me.getMesero() + ";" + me.getNumMesa() + ";" + me.getUbicacion().getX() + ":"
                        + me.getUbicacion().getY() + ";" + me.getEstado() + ";" + me.getCapacidad();
                bw.write(linea);
                bw.newLine();
            }

            linea = m.getMesero() + ";" + m.getNumMesa() + ";" + m.getUbicacion().getX() + ":"
                    + m.getUbicacion().getY() + ";" + m.getEstado() + ";" + m.getCapacidad();
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
    public static Mesa buscarMesaPorNumero(String numero) throws ArchivosException{
        String ruta = "UbicacionMesas.txt";
        try (InputStream input = App.class.getResource(ruta).openStream();
                BufferedReader bf = new BufferedReader(new InputStreamReader(input, "UTF-8"))) {
            String linea;
            Mesa mesa = null;
            while ((linea = bf.readLine()) != null) {
                String[] lista = linea.split(";");
                if(lista[1].equals(numero)){
                    double X = Double.valueOf(lista[2].split(":")[0]);
                    double Y = Double.valueOf(lista[2].split(":")[1]);
                    Ubicacion ub = new Ubicacion(X,Y);
                    int capacidad = Integer.valueOf(lista[4]);
                    Mesa m = new Mesa(Integer.valueOf(lista[1]),ub,lista[0],lista[3],capacidad);
                    mesa=m;
                    break;
                }
            }
            return mesa;
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new ArchivosException(ruta, ex.getMessage());
        }
    }

}
