/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pooespol.proyecto2parcial;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author UserPC
 */
public class SesionController implements Initializable {

    @FXML
    private TextField txtCorreo;
    @FXML
    private Button btnIngresar;
    @FXML
    private Label txtMostrar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void irInterfaz(MouseEvent event) {
        try{
            App.setRoot("interfazAdministrador");
        }catch(IOException ex){
            System.out.println("Sucedio algo");
            System.err.println(ex);
        }
        
        
    }
    
}
