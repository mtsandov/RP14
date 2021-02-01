/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pooespol.proyecto2parcial;

import com.pooespol.proyecto2parcial.usuarios.Administrador;
import com.pooespol.proyecto2parcial.usuarios.Mesero;
import com.pooespol.proyecto2parcial.usuarios.Usuario;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
/**
 * FXML Controller class
 *
 * @author UserPC
 */
public class LoginController implements Initializable {
    private ArrayList<Usuario> usuarios;
    App app = new App();

    @FXML
    private TextField txtCorreo;
    @FXML
    private PasswordField txtContrasena;
    @FXML
    private Label txtMostrar;
    @FXML
    private Label irRegistro;
     public String correo = txtCorreo.getText();
    
   //public static String nombreUsuario = txtCorreo.getText();
    
    public LoginController() {
        usuarios= new ArrayList<>();
        Administrador a1 = new Administrador("alfrcall@espol.edu.ec","12345");
        Administrador a2 = new Administrador("usuario1","01234");
        Mesero m2 = new Mesero("usuario2","56789","Victor");
        usuarios.add(a1);
        usuarios.add(a2);
        usuarios.add(m2);
        
        for(Usuario u: usuarios){
            System.out.println(u.getCorreo()+ ";"+ u.getContrasena() + ";"+ u.toString());
        }
    }
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    
    @FXML
    private void irInterfaz(MouseEvent event) {
        ArrayList<Usuario> us = usuarios;
        boolean b = false;
        for(Usuario u: us){
            if((u.getCorreo().equals(txtCorreo.getText())) && (u.getContrasena().equals(txtContrasena.getText()))){
                    if(u instanceof Administrador){
                        try{
                            b = true;
                            
                            App.setRoot("interfazAdministrador");
                            break;
                        }catch(IOException ex){
                            System.out.println("Sucedio algo en el login parte administrador");
                            System.err.println(ex);
                        }                     
                    }else if(u instanceof Mesero){
                        try{
                            b = true;
                            FXMLLoader fxmlLoader= new FXMLLoader(App.class.getResource("interfazMesero.fxml"));
                            Parent root=fxmlLoader.load();
                            InterfazMeseroController it = fxmlLoader.<InterfazMeseroController>getController();
                            it.setCorreo(txtCorreo.getText());
                            App.setRoot("interfazMesero");
                            break;
                        }catch(IOException ex){
                            System.out.println("Sucedio algo");
                            System.err.println(ex);
                        }   
                    }
                }
        }
        if(b == false){
            txtMostrar.setText("Datos incorrectos");
        }
    }

    @FXML
    private void irRegistrar(MouseEvent event) {
        try {      
            App.setRoot("RegistrarCuenta");
        } catch (IOException ex) {
            System.out.println("Ocurrio Algo");
            System.out.println(ex);
        }
    }

    public void aggUsuarios(Usuario u) {
        usuarios.add(u);
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
    public String obtenerUsuarioIngresado(){
        System.out.println("Hola");
        String correo = txtCorreo.getText();
        String nombreUsuario=null;
        for(Usuario user : usuarios){
            if(user.getCorreo().equals(correo)){
               nombreUsuario=user.getCorreo();
            }
        }return nombreUsuario;
        
    }
    
    

}
