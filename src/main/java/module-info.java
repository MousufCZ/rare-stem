module com.mousuf.rarestem {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;


    opens com.mousuf.rarestem to javafx.fxml;
    exports com.mousuf.rarestem;
}