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
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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
                Mesa t = new Mesa(numMesa,ubicacion,nombreMesero,estado,capacidad);
                tiendas.add(t);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new ArchivosException(ruta, ex.getMessage());
        }
        return tiendas;
    }

}
