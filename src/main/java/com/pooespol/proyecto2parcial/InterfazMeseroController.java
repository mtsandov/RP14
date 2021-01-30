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
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author Personal
 */
public class InterfazMeseroController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
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
    
    
    
}
