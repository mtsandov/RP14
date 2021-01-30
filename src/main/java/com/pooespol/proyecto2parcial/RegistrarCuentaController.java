/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pooespol.proyecto2parcial;

import com.pooespol.proyecto2parcial.data.UsuarioData;
import com.pooespol.proyecto2parcial.usuarios.Administrador;
import com.pooespol.proyecto2parcial.usuarios.Mesero;
import com.pooespol.proyecto2parcial.usuarios.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author UserPC
 */
public class RegistrarCuentaController implements Initializable {
    App app = new App();


    @FXML
    private ComboBox<Usuario> tipoUsuario;
    @FXML
    private TextField txtCorreo;
    @FXML
    private PasswordField txtContrasena;
    @FXML
    private Label txtMostrar;
    /**
     * Initializes the controller class.
     */
    
    private LoginController lc;
    private Parent root;
    
    
    public RegistrarCuentaController() {  
        try {
            FXMLLoader  fxmlLoader = new FXMLLoader(App.class.getResource("Login.fxml"));
            root = fxmlLoader.load();
            lc  = fxmlLoader.<LoginController>getController(); 
            System.out.println(lc);
        } catch (IOException ex) {
            System.out.println("Ocurrio algo en registrar cuenta controller");
        }      
    }    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.add(new Administrador(null,null));
        usuarios.add(new Mesero(null,null));
        tipoUsuario.getItems().addAll(usuarios);
        
        
        
        // TODO
    }    
    
    @FXML
    private void registrarUsuario(MouseEvent event) {
        String correo = txtCorreo.getText();
        String contrasena = txtContrasena.getText();
        Usuario u = tipoUsuario.getValue();
        boolean b = false;
        if((correo != null) && (contrasena !=null) && (u != null)){
            if(u instanceof Administrador){
                //UsuarioData.registrarUsuarios(new Administrador(correo,contrasena));
                lc.aggUsuarios(new Administrador(correo,contrasena));
                txtMostrar.setText("Cuenta Administrador Registrada");
                b = true;
            }else if(u instanceof Mesero){
                //UsuarioData.registrarUsuarios(new Mesero(correo,contrasena));
                lc.aggUsuarios(new Mesero(correo,contrasena));
                txtMostrar.setText("Cuenta Mesero Registrada");
                b = true;
            }      
        }if(b == false){
                txtMostrar.setText("Datos Incorrectos");
            }
    }

    @FXML
    private void irLogin(MouseEvent event) {      
            App.cambiarRoot(root);
    }

}
