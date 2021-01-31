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
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
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
public class MonitoreoRestaurante2Controller implements Initializable {

    @FXML
    private Label numComensales;
    @FXML
    private Label totalFacturado;
    @FXML
    private TextField fechaInicio;
    @FXML
    private TextField fechaFin;
    @FXML
    private Button filtrar;
    @FXML
    private Pane panelMesas;
    @FXML
    private Pane panelMesaDP;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Monitoreo
        /**
        try{
            //List<Mesa> mesas = new ArrayList<Mesa>();
            //Mesa(int numMesa, Ubicacion ub, String mesero, String estado, int capacidad)
            //Victor;2;81:5;Ocupado;10
            //Nadie;4;265:-1;DesOcupado;2
            //mesas.add(new Mesa(2, new Ubicacion(81,5),"Alex","Ocupada",10));
            //mesas.add(new Mesa(4, new Ubicacion(265,-1),"Nadie","DesOcupada",2));
            List<Mesa> mesas = MesaData.cargarMesaArchivos("UbicacionMesas.txt");
            
            for(Mesa m: mesas){
                Circle  c = new Circle(30, Color.BLUE);
                if(m.getEstado().equals("Ocupado")){
                     c = new Circle(30, Color.RED);
                }else if(m.getEstado().equals("DesOcupado")){
                    c = new Circle(30, Color.YELLOW);
                }
                Label l = new Label(String.valueOf(m.getNumMesa()));
                StackPane st = new StackPane();
                st.getChildren().addAll(c, l);
                panelMesas.getChildren().add(st);
                st.setLayoutX(m.getUbicacion().getX());
                st.setLayoutY(m.getUbicacion().getY());
                
                st.setOnMouseClicked((MouseEvent ev) -> {
                    informacionMesa(m.getNumMesa(), m.getCapacidad(), m.getMesero(), m.getEstado());
                });
            }     
        }catch (ArchivosException ex) {
            System.out.println("Ocurrio algo en monitoreo restaurante 2");
        }
        */
        //Diseno Plano
        iniciarElementosPanel(panelMesas,"Monitoreo");
        iniciarElementosPanel(panelMesaDP, "DisenoPlano");
        panelMesaDP.setOnMouseClicked((MouseEvent ev) -> {
          
                //public Mesa(int numMesa, Ubicacion ub, String mesero, String estado, int capacidad) {
                informacionMesa(0, 0, null, "DesOcupado", "DisenoPlano", new Ubicacion(ev.getX(),ev.getY()));
                //Mesa mes = new Mesa(0, new Ubicacion(ev.getX(),ev.getY()), null, "DesOcupado",0);  
                //MesaData.agregarMesaArchivos(mes);
                
                /**
                 * Circle  c = new Circle(30, Color.YELLOW);
                 * Label l = new Label("0");
                 * StackPane st = new StackPane();
                 * st.getChildren().addAll(c, l);
                 * panelMesaDP.getChildren().add(st);
                 * st.setLayoutX(ev.getX());
                 * st.setLayoutY(ev.getY());
                 **/

  
        });    

        

        // TODO
       
    }

    @FXML
    private void AgregarMenu(MouseEvent event) {
    }

    @FXML
    private void EditarMenu(MouseEvent event) {
    }

    @FXML
    private void EliminarMenu(MouseEvent event) {
    }

    @FXML
    private void filtrar(MouseEvent event) {
    }

    @FXML
    private void IrLogin(MouseEvent event) {
        try {
            App.setRoot("Login");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    private void informacionMesa(int numero, int capacidad, String mesero, String estado, String pestana, Ubicacion u) {

        Stage st = new Stage();
        VBox vb = new VBox(0.5);
        if(pestana.equals("Monitoreo")){
            Label lb1 = new Label("Mesa # " + numero);
            Label lb2 = new Label("Capacidad de la mesa: " + capacidad);
            Label lb3 = new Label("Nombre del Mesero: " + mesero);
            Label lb4 = new Label("Estado: " + estado);
            vb.getChildren().add(lb1);
            vb.getChildren().add(lb2);
            vb.getChildren().add(lb3);
            vb.getChildren().add(lb4);
            vb.setAlignment(Pos.CENTER);
            Scene scene = new Scene(vb, 400, 80);
            st.setScene(scene);
            st.show();    
        }else if (pestana.equals("DisenoPlano")){
            Label lb1 = new Label("Mesa #");
            TextField t1 = new TextField();
            Label lb2 = new Label("Capacidad de la mesa");
            TextField t2 = new TextField();  
            Label lb4 = new Label("Estado");
            //TextField t4 = new TextField();
            ComboBox cb = new ComboBox();
            cb.getItems().add(new Mesa(0, null, null, "Ocupado", 0));
            cb.getItems().add(new Mesa(0, null, null, "DesOcupado", 0));
            Button bt = new Button("Cambiar");
            bt.setOnMouseClicked( (MouseEvent ex) -> {
                //modificar archivo
                Mesa me = new Mesa(Integer.valueOf(t1.getText()), u, null, cb.getValue().toString(), Integer.valueOf(t2.getText()));
                try {
                    MesaData.agregarMesaArchivos(me);
                    iniciarElementosPanel(panelMesaDP, "DisenoPlano");
                } catch (ArchivosException ex1) {
                    ex1.printStackTrace();
                }
                
                //informacionMesa(Integer.valueOf(t1.getText()), Integer.valueOf(t2.getText()), null, cb.getValue().toString(), "DisenoPlano");
                
                //cambiar
            });
            
            vb.getChildren().add(lb1);
            vb.getChildren().add(t1);
            vb.getChildren().add(lb2);
            vb.getChildren().add(t2);
            vb.getChildren().add(lb4);
            vb.getChildren().add(cb);
            vb.getChildren().add(bt);
            vb.setAlignment(Pos.CENTER);

            Scene scene = new Scene(vb, 300, 200);
            st.setScene(scene);
            st.show();
        
        }
        
        
        /**
        Label lb1 = new Label("Mesa # " + numero);
        Label lb2 = new Label("Capacidad de la mesa: " + capacidad);
        Label lb3 = new Label("Nombre del Mesero: " + mesero);
        Label lb4 = new Label("Estado: " + estado);
        vb.getChildren().add(lb1);
        vb.getChildren().add(lb2);
        vb.getChildren().add(lb3);
        vb.getChildren().add(lb4);
        vb.setAlignment(Pos.CENTER);

        Scene scene = new Scene(vb, 400, 80);
        st.setScene(scene);
        st.show();
        * */

    }
    
    public void iniciarElementosPanel(Pane p, String pestana){
        try{
            List<Mesa> mesas = MesaData.cargarMesaArchivos("UbicacionMesas.txt");
            
            for(Mesa m: mesas){
                Circle  c = new Circle(30, Color.BLUE);
                if(m.getEstado().equals("Ocupado")){
                     c = new Circle(30, Color.RED);
                }else if(m.getEstado().equals("DesOcupado")){
                    c = new Circle(30, Color.YELLOW);
                }
                Label l = new Label(String.valueOf(m.getNumMesa()));
                StackPane st = new StackPane();
                st.getChildren().addAll(c, l);
                p.getChildren().add(st);
                st.setLayoutX(m.getUbicacion().getX());
                st.setLayoutY(m.getUbicacion().getY());
                
                if(pestana.equals("Monitoreo")){
                    st.setOnMouseClicked((MouseEvent ev) -> {
                        informacionMesa(m.getNumMesa(), m.getCapacidad(), m.getMesero(), m.getEstado(),pestana, new Ubicacion(m.getUbicacion().getX(),m.getUbicacion().getY()));
                    });    
                }else if (pestana.equals("DisenoPlano")){
                    st.setOnMouseClicked((MouseEvent ev) -> {
                        informacionMesa(m.getNumMesa(), m.getCapacidad(), m.getMesero(), m.getEstado(),pestana,new Ubicacion(m.getUbicacion().getX(),m.getUbicacion().getY()));
                    });  
                    
                }
                
                /**
                st.setOnMouseClicked((MouseEvent ev) -> {
                    //informacionMesa(m.getNumMesa(), m.getCapacidad(), m.getMesero(), m.getEstado());
                });
                **/
                
            }     
        }catch (ArchivosException ex) {
            System.out.println("Ocurrio algo en monitoreo restaurante 2");
        }
    
    }

}
