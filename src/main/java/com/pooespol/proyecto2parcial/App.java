package com.pooespol.proyecto2parcial;

import com.pooespol.proyecto2parcial.usuarios.Administrador;
import com.pooespol.proyecto2parcial.usuarios.Mesero;
import com.pooespol.proyecto2parcial.usuarios.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {
    
    private ArrayList<Usuario> usuarios = new ArrayList<>();
    private static Scene scene;

    public App() {
        Administrador a1 = new Administrador("alfrcall@espol.edu.ec","12345");
        Administrador a2 = new Administrador("usuario1","01234");
        Mesero m2 = new Mesero("usuario2","56789");
        usuarios.add(a1);
        usuarios.add(a2);
        usuarios.add(m2);
    }


    @Override
    public void start(Stage stage) throws IOException {    
        scene = new Scene(loadFXML("pruebaLogin"));
        stage.setScene(scene);
        stage.show();
        
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public static void main(String[] args) {
        
        launch();
    }

    public ArrayList<Usuario> getUsuarios() {
        return usuarios;
    }
    
    

}