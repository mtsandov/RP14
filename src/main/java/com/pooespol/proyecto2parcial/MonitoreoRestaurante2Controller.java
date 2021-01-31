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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
 
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

    private void informacionMesa(int numero, int capacidad, String mesero, String estado) {

        Stage st = new Stage();
        VBox vb = new VBox(0.5);
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

    }

}
