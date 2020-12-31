module com.pooespol.proyecto2parcial {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.pooespol.proyecto2parcial to javafx.fxml;
    exports com.pooespol.proyecto2parcial;
}
