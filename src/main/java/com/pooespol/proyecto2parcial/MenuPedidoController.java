/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pooespol.proyecto2parcial;

import com.pooespol.proyecto2parcial.data.PlatoData;
import com.pooespol.proyecto2parcial.modelo.Plato;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;

import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
/**
 * FXML Controller class
 *
 * @author Personal
 */
public class MenuPedidoController implements Initializable {


    @FXML
    private VBox panelInformacion;
    @FXML
    private HBox panelPrincipal;
    @FXML
    private Label tituloMenu;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            
            List<Plato> platos = PlatoData.leerPlatos();
            for(Plato p: platos){
                VBox vbox = new VBox();
                InputStream inputImg = App.class.getResource(p.getImagen()).openStream();
                ImageView imgv = new ImageView(new Image(inputImg));
                vbox.getChildren().add(imgv);
                vbox.getChildren().add(new Label(p.getNombre()));
                vbox.getChildren().add(new Label("$ " + String.valueOf(p.getPrecio())));
                vbox.setPadding(new Insets(2,3,3,4));              
                panelPrincipal.getChildren().add(vbox);
            }
            
        } catch (IOException ex) {
            System.out.println("Paso algo");
        }
    }    

    @FXML
    private void regresar(MouseEvent event) {
        try {
            App.setRoot("interfazMesero");
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
}
