/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pooespol.proyecto2parcial;

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

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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
    }
    
}
