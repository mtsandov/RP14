/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pooespol.proyecto2parcial;

import com.pooespol.proyecto2parcial.data.ArchivosException;
import com.pooespol.proyecto2parcial.data.MesaData;
import com.pooespol.proyecto2parcial.modelo.Mesa;
import com.pooespol.proyecto2parcial.modelo.Ubicacion;
import com.pooespol.proyecto2parcial.usuarios.Mesero;
import com.pooespol.proyecto2parcial.usuarios.Usuario;
import com.pooespol.proyecto2parcial.data.UsuarioData;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;


/**
 * FXML Controller class
 *
 * @author Personal
 */
public class InterfazMeseroController implements Initializable {
    @FXML
    private Pane panelMesasMesero;
    public static String correo;
    //private LoginController lg;

    /**
     * Initializes the controller class.
     */
    

    
    public void setCorreo(String correo) {
        this.correo = correo;
    }  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Wenas");
       // String correo = LoginController.obtenerUsuarioIngresado();
      // setCorreo(LoginController.txtCorreo.getText());
   /*   String ruta = "ingresados.txt";
      //  List<Mesa> mesas = MesaData.cargarMesaArchivos("UbicacionMesas.txt");

        //try(InputStream input = App.class.getResource(ruta).openStream();
        //BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out))) {
        try{
        URL u = App.class.getResource(ruta);
        File file = new File(u.toURI());
        try (BufferedReader bf = new BufferedReader(new FileReader(file))) {
            String linea;
            while((linea=bf.readLine())!=null){
                correo=linea.split(";")[0];
            }
        }catch(Exception ex){
            System.out.println(ex);
        }
        } catch (URISyntaxException ex) {
            ex.printStackTrace();
        }*/
        try {
            List<Mesa> mesas=MesaData.cargarMesaArchivos("UbicacionMesas.txt");
            for(Mesa m:mesas){
                System.out.println(m);
            }
        } catch (ArchivosException ex) {
            ex.printStackTrace();
        }
      
      
        System.out.println(correo);
        try {
            List<Usuario> usuarios = UsuarioData.leerUsuarios();
            for(Usuario user : usuarios){
                if(user.getCorreo().equals(correo) && (user instanceof Mesero)){
                    Mesero mesero=(Mesero)user;
                    iniciarElementosPanelMesero(mesero.getNombre());
                }   
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
    }    

    private void irPedido(MouseEvent event) {
        try {
            App.setRoot("menuPedido");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    @FXML
    private void irLogin(MouseEvent event) {
        try {
            App.setRoot("Login");
        } catch (IOException ex) {
            System.out.println("Ocurrio algo");
            System.out.println(ex);
        }
    }
    
        public void iniciarElementosPanelMesero(String nombreMesero){
        try{
            List<Mesa> mesas = MesaData.cargarMesaArchivos("UbicacionMesas.txt");
            System.out.println("inciando");
            Circle c1=null;
            for(Mesa m: mesas){
                Circle  c = new Circle(30, Color.BLUE);
                if(m.getCapacidad()<=4){
                c = new Circle(25, Color.BLUE);
                }else if(m.getCapacidad()>4 && m.getCapacidad()<8){
                c = new Circle(35, Color.BLUE);
                }else if(m.getCapacidad()>=8){
                c = new Circle(40, Color.BLUE);
                }
                if(m.getEstado().equals("Ocupado")){
                     //c = new Circle(30, Color.RED);
                     c.setFill(Color.RED);
                     c1=c;
                     if(m.getMesero().equals(nombreMesero)){
                     c.setFill(Color.GREEN);
                     c1=c;
                     }
                
                }else if(m.getEstado().equals("DesOcupado")){
                   // c = new Circle(30, Color.YELLOW);
                   c.setFill(Color.YELLOW);
                    c1=c;
                
                }
                Label l = new Label(String.valueOf(m.getNumMesa()));
                StackPane st = new StackPane();
                st.getChildren().addAll(c, l);
                
               panelMesasMesero.getChildren().add(st);
              
                st.setLayoutX(m.getUbicacion().getX());
                st.setLayoutY(m.getUbicacion().getY());
 
                
                st.setOnMouseClicked((MouseEvent)->{
                    if(m.getEstado().equals("DesOcupado")){
                        try {
                            System.out.println("en el desocupado");
                            AbrirCuenta(nombreMesero,String.valueOf(m.getNumMesa()));
                            m.setEstado("Ocupado");
                            System.out.println(m);
                        } catch (ArchivosException ex) {
                            ex.printStackTrace();
                        }
                    }
                });
                
                
                }
                
                /**
                st.setOnMouseClicked((MouseEvent ev) -> {
                    //informacionMesa(m.getNumMesa(), m.getCapacidad(), m.getMesero(), m.getEstado());
                });
                **/
                
        }catch (ArchivosException ex) {
            System.out.println("Ocurrio algo en monitoreo restaurante 2");
        }
        
        
    
    }
        
  public void AbrirCuenta(final String nombreMesero,final String numMesa) throws ArchivosException{
    List<Ventas> ventas = Ventas.leerVentas();
    String numCuenta=null;
    for(Ventas v: ventas){
        numCuenta=v.getCuenta();
    }
    final String cuenta=String.valueOf(Integer.valueOf(numCuenta)+1);
    Stage st = new Stage();
    VBox vb = new VBox();
    HBox hb = new HBox();
    Label l = new Label("Ingrese nombre del cliente: ");
    TextField t = new TextField();
    Button bt = new Button("Abrir cuenta");
            hb.getChildren().addAll(l,t);
    vb.getChildren().addAll(hb,bt);
        Scene sc = new Scene(vb,800,400);
    st.setScene(sc);
    st.show();
      //nombreCliente=t.getText();
    //  System.out.println(nombreCliente);
    Date fecha = new Date();
    SimpleDateFormat fechaHoy = new SimpleDateFormat("dd-MM-YYYY");
    final String fechaActual = fechaHoy.format(new Date());
      
  //  Ventas v = new Ventas(String.valueOf(fechaActual),numMesa,nombreMesero,numCuenta,nombreCliente,"0.0");
      
    bt.setOnMouseClicked((MouseEvent ev)->{
        try {
           String nombreCliente=t.getText();
          Ventas v = new Ventas(String.valueOf(fechaActual),numMesa,nombreMesero,cuenta,nombreCliente,"0.0");
            Ventas.agregarVenta(v);
            System.out.println("Venta agregada");
            
        } catch (ArchivosException ex) {
            ex.printStackTrace();
        }   
    });
    
}
}
