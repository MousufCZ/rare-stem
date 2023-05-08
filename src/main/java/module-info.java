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

/*    opens com.mousuf.rarestem.loggingSys to javafx.fxml;
    exports com.mousuf.rarestem.loggingSys;*/

/*    opens com.mousuf.rarestem.projContribution to javafx.fxml;
    exports com.mousuf.rarestem.projContribution;*/

    opens com.mousuf.rarestem.tableViewTest to javafx.fxml;
    exports com.mousuf.rarestem.tableViewTest;

    opens com.mousuf.rarestem.tableViewTest.dbTableView to javafx.fxml;
    exports com.mousuf.rarestem.tableViewTest.dbTableView;

    exports com.mousuf.rarestem.projContributionSys.addContrib;
    opens com.mousuf.rarestem.projContributionSys.addContrib to javafx.fxml;

    exports com.mousuf.rarestem.dbSetup;
    opens com.mousuf.rarestem.dbSetup to javafx.fxml;

    exports com.mousuf.rarestem.loggingSys.signUp;
    opens com.mousuf.rarestem.loggingSys.signUp to javafx.fxml;

    exports com.mousuf.rarestem.loggingSys.logIn;
    opens com.mousuf.rarestem.loggingSys.logIn to javafx.fxml;

    exports com.mousuf.rarestem.loggingSys.loggedIn;
    opens com.mousuf.rarestem.loggingSys.loggedIn to javafx.fxml;

    exports com.mousuf.rarestem.loggingSys.loggingSysModel;
    opens com.mousuf.rarestem.loggingSys.loggingSysModel to javafx.fxml;

    exports com.mousuf.rarestem.tableViewTest.localTableView;
    opens com.mousuf.rarestem.tableViewTest.localTableView to javafx.fxml;

    exports com.mousuf.rarestem.projContributionSys.userContrib;
    opens com.mousuf.rarestem.projContributionSys.userContrib to javafx.fxml;

    exports com.mousuf.rarestem.projContributionSys;
    opens com.mousuf.rarestem.projContributionSys to javafx.fxml;

/*
    exports com.mousuf.rarestem.dataset;
    opens com.mousuf.rarestem.dataset to javafx.fxml;
*/

    exports com.mousuf.rarestem.dataset.dataset;
    opens com.mousuf.rarestem.dataset.dataset to javafx.fxml;

    exports com.mousuf.rarestem.dataset.gene;
    opens com.mousuf.rarestem.dataset.gene to javafx.fxml;

    exports com.mousuf.rarestem.dataset.col1a2;
    opens com.mousuf.rarestem.dataset.col1a2 to javafx.fxml;
}