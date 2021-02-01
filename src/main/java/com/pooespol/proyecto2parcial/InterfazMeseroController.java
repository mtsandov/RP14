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
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
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
    private String correo;
    //private LoginController lg;

    /**
     * Initializes the controller class.
     */
    

    
    public void setCorreo(String correo) {
        this.correo = correo;
    }  
    
    /*loader.setLocation(getClass().getResource("FXMLDocument.fxml"));
loader.load();
SegundoController document = loader.getController();
document.setEmployee(new Empleado(5,"Hola"));
Parent p = loader.getRoot();
Stage s = new Stage();
s.setScene(new Scene(p));
s.show();*/
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("Wenas");
       // String correo = LoginController.obtenerUsuarioIngresado();
      // setCorreo(LoginController.txtCorreo.getText());
        System.out.println(correo);
        System.out.println("Hola");
        try {
            List<Usuario> usuarios = UsuarioData.leerUsuarios();
            for(Usuario user : usuarios){
                if(user.getCorreo().equals(correo) && (user instanceof Mesero)){
                    Mesero mesero=(Mesero)user;
                    iniciarElementosPanelMesero(mesero.getNombre());
                    System.out.println("Aqui");
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
            Circle c1;
            for(Mesa m: mesas){
                System.out.println("iterando");
                Circle  c = new Circle(30, Color.BLUE);
                if(m.getEstado().equals("Ocupado")){
                    System.out.println("Dentro del if");
                     c = new Circle(30, Color.RED);
                     c1=c;
                     System.out.println("Se creo el rojo");
                     System.out.println(m.getMesero());
                     System.out.println(nombreMesero);
                     if(m.getMesero().equals(nombreMesero)){
                     c.setFill(Color.GREEN);
                     c1=c;
                         System.out.println("Se creo el circulo verde");
                     }
                
                }else if(m.getEstado().equals("DesOcupado")){
                    System.out.println("se creo el amarillo");
                    c = new Circle(30, Color.YELLOW);
                    c1=c;
                
                }
                System.out.println("Fuera del for");
                Label l = new Label(String.valueOf(m.getNumMesa()));
                System.out.println("Cree el label");
                StackPane st = new StackPane();
                st.getChildren().addAll(c, l);
                System.out.println("Cree el st");
                System.out.println("Antes del pane");
               panelMesasMesero.getChildren().add(st);
              System.out.println("Cree el pane");
                st.setLayoutX(m.getUbicacion().getX());
                st.setLayoutY(m.getUbicacion().getY());
 
                System.out.println("Antes del evento");
                st.setOnMouseClicked((MouseEvent)->{
                    System.out.println("En el evento");
                    System.out.println(m.getEstado());
                    if(m.getEstado().equals("DesOcupado")){
                        try {
                            System.out.println("en el desocupado");
                            AbrirCuenta(nombreMesero,String.valueOf(m.getNumMesa()));
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
      System.out.println("En la cuenta");
    List<Ventas> ventas = Ventas.leerVentas();
    String numCuenta=null;
    for(Ventas v: ventas){
        numCuenta=v.getCuenta();
    }System.out.println("se creo la cuenta");
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
      System.out.println("se creo la venta");
    bt.setOnMouseClicked((MouseEvent ev)->{
        System.out.println("Dentro del evento");
        try {
           String nombreCliente=t.getText();
          Ventas v = new Ventas(String.valueOf(fechaActual),numMesa,nombreMesero,cuenta,nombreCliente,"0.0");
            System.out.println("Dentro del evento");
            Ventas.agregarVenta(v);
            System.out.println("Venta agregada");
            Circle c = new Circle();
            
        } catch (ArchivosException ex) {
            ex.printStackTrace();
        }   
    });
        System.out.println("Crenaod ventana");
  //  hb.getChildren().addAll(l,t);
    //vb.getChildren().addAll(hb,bt);


     // System.out.println(nombreCliente);
        System.out.println("Creada la escena");
}
}
