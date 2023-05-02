module com.mousuf.rarestem {
    requires javafx.controls;
    requires javafx.fxml;
    requires lombok;
    requires org.mongodb.driver.core;
    requires org.mongodb.bson;
    requires org.mongodb.driver.sync.client;
    requires io.github.cdimascio.dotenv.java;


    opens com.mousuf.rarestem to javafx.fxml;
    exports com.mousuf.rarestem;

    opens com.mousuf.rarestem.loggingSys to javafx.fxml;
    exports com.mousuf.rarestem.loggingSys;
}