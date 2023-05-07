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

/*    opens com.mousuf.rarestem.projContribution to javafx.fxml;
    exports com.mousuf.rarestem.projContribution;*/

    opens com.mousuf.rarestem.tableViewTest to javafx.fxml;
    exports com.mousuf.rarestem.tableViewTest;

    opens com.mousuf.rarestem.tableViewTest.dbTableView to javafx.fxml;
    exports com.mousuf.rarestem.tableViewTest.dbTableView;

    exports com.mousuf.rarestem.projContribution.getContrib;
    opens com.mousuf.rarestem.projContribution.getContrib to javafx.fxml;

    exports com.mousuf.rarestem.projContribution.addContrib;
    opens com.mousuf.rarestem.projContribution.addContrib to javafx.fxml;
}