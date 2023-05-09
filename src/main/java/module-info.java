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

    exports com.mousuf.rarestem.projectContributionSys.addContribution;
    opens com.mousuf.rarestem.projectContributionSys.addContribution to javafx.fxml;

    exports com.mousuf.rarestem.dbCollectionChecker;
    opens com.mousuf.rarestem.dbCollectionChecker to javafx.fxml;

    exports com.mousuf.rarestem.loggingSys.signUpController;
    opens com.mousuf.rarestem.loggingSys.signUpController to javafx.fxml;

    exports com.mousuf.rarestem.loggingSys.logInController;
    opens com.mousuf.rarestem.loggingSys.logInController to javafx.fxml;

    exports com.mousuf.rarestem.loggingSys.loggedInController;
    opens com.mousuf.rarestem.loggingSys.loggedInController to javafx.fxml;

    exports com.mousuf.rarestem.loggingSys.loggingSysModel;
    opens com.mousuf.rarestem.loggingSys.loggingSysModel to javafx.fxml;

    exports com.mousuf.rarestem.projectContributionSys.userContribution;
    opens com.mousuf.rarestem.projectContributionSys.userContribution to javafx.fxml;

    exports com.mousuf.rarestem.projectContributionSys;
    opens com.mousuf.rarestem.projectContributionSys to javafx.fxml;

    exports com.mousuf.rarestem.datasetSys.datasetController;
    opens com.mousuf.rarestem.datasetSys.datasetController to javafx.fxml;

    exports com.mousuf.rarestem.datasetSys.gene;
    opens com.mousuf.rarestem.datasetSys.gene to javafx.fxml;

    exports com.mousuf.rarestem.datasetSys.col1a2;
    opens com.mousuf.rarestem.datasetSys.col1a2 to javafx.fxml;
}