/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pooespol.proyecto2parcial;

import com.pooespol.proyecto2parcial.App;
import com.pooespol.proyecto2parcial.data.ArchivosException;
import com.pooespol.proyecto2parcial.data.MesaData;
import com.pooespol.proyecto2parcial.modelo.Mesa;
import com.pooespol.proyecto2parcial.usuarios.Mesero;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author Freddy
 */
public class Ventas {
    private SimpleStringProperty fecha;
    private SimpleStringProperty mesero;
    private SimpleStringProperty cuenta;
    private SimpleStringProperty mesa;
    private SimpleStringProperty venta;
    private SimpleStringProperty cliente;

    public Ventas(String fecha,String mesa, String mesero, String cuenta,String cliente, String venta) {
        this.fecha = new SimpleStringProperty(fecha);
        this.mesero = new SimpleStringProperty(mesero);
        this.cuenta = new SimpleStringProperty(cuenta);
        this.mesa = new SimpleStringProperty(mesa);
        this.venta = new SimpleStringProperty(venta);
        this.cliente=new SimpleStringProperty(cliente);
    }

    public String getFecha(){
        return fecha.get();
    }

    public String getMesero() {
        return mesero.get();
    }

    public String getCuenta() {
        return cuenta.get();
    }

    public String getMesa() {
        return mesa.get();
    }

    public String getVenta() {
        return venta.get();
    }

    public String getCliente() {
        return cliente.get();
    }
    
    public static List<Ventas> leerVentas() throws ArchivosException {
        System.out.println("leyendo cuentas");
        String ruta = "ventas.txt";
        List<Ventas> ventas = new ArrayList<>();
        try (InputStream input = App.class.getResource(ruta).openStream();
                BufferedReader bf = new BufferedReader(
                        new InputStreamReader(input, "UTF-8"))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                System.out.println(linea);
                String[] lista = linea.split(";");
                String fecha = lista[0];
                String nombreMesero = lista[2];
                //Mesero mesero = Mesero.buscarMeseroPorNombre(nombreMesero);
                String numCuenta = lista[3];
                String numMesa = lista[1];
                String cliente= lista[4];
                String monto = lista[5];
                //Mesa mesa = MesaData.buscarMesaPorNumero(numMesa);
                Ventas venta= new Ventas(fecha,numMesa,nombreMesero,numCuenta,cliente,monto);
                ventas.add(venta);
            }System.out.println("Fuera del for leyendp  ventas");
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new ArchivosException(ruta, ex.getMessage());
        }
        return ventas;
    }
    public static double calcularFacturado() throws ArchivosException{
     String ruta = "ventas.txt";
        List<Ventas> ventas = new ArrayList<>();
        double facturado=0;
        try (InputStream input = App.class.getResource(ruta).openStream();
                BufferedReader bf = new BufferedReader(
                        new InputStreamReader(input, "UTF-8"))) {
            String linea;
            while ((linea = bf.readLine()) != null) {
                System.out.println(linea);
                String[] lista = linea.split(";");
                for(String datos: lista){
                    double monto = Double.valueOf(lista[5]);
                    facturado+=monto;
                }
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
            ex.printStackTrace();
            throw new ArchivosException(ruta, ex.getMessage());
        }return facturado;
    }
    public static void agregarVenta(Ventas venta) throws ArchivosException{
        String ruta = "ventas.txt";
        List<Ventas> ventas = Ventas.leerVentas();

        //try(InputStream input = App.class.getResource(ruta).openStream();
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
        try{
        URL u = App.class.getResource(ruta);
        File file = new File(u.toURI());
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {

            String linea;
            for (Ventas v : ventas) {
                linea = v.getFecha()+";"+v.getMesa()+";"+v.getMesero()+";"+v.getCuenta()+";"+v.getCliente()+";"+v.getVenta();
                bw.write(linea);
                bw.newLine();
            }

            linea =  venta.getFecha()+";"+venta.getMesa()+";"+venta.getMesero()+";"+venta.getCuenta()+";"+venta.getCliente()+";"+venta.getVenta();
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
 }
