package com.mousuf.rarestem.tableViewTest.dbTableView;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import com.mousuf.rarestem.tableViewTest.Person;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import org.bson.Document;
import io.github.cdimascio.dotenv.Dotenv;
import javafx.fxml.Initializable;



import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class DBTableViewControllerSingle implements Initializable {

    @FXML
    private TableColumn<Person, String> fn;

    @FXML
    private TableColumn<Person, String> ln;

    @FXML
    private TableColumn<Person, String> o;

    @FXML
    public TableView<Person> table;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        fn.setCellValueFactory(new PropertyValueFactory<Person, String>("fn"));
        ln.setCellValueFactory(new PropertyValueFactory<Person, String>("ln"));
        o.setCellValueFactory(new PropertyValueFactory<Person, String>("o"));

        // Connect to MongoDB
        // Load environment variables from .env file
        Dotenv dotenv = Dotenv.load();
        // Get the value of the API_KEY environment variable
        String uri;
        uri = dotenv.get("MONGODB_URI");
        // Use the API key in your application
        System.out.println("MongoDB URI: " + uri);

        MongoClient client = MongoClients.create(uri);
        MongoDatabase db = client.getDatabase("rs-db");
        System.out.println("Get Database successful.");
        MongoCollection<Document> collection = db.getCollection("person");
        System.out.println("Get collection successful.");

        // Retrieve data from MongoDB and populate the table
        List<Person> persons = new ArrayList<>();
        for (Document doc : collection.find()) {
            Person person = new Person(
                    doc.getString("fn"),
                    doc.getString("ln"),
                    doc.getString("o")
            );
            persons.add(person);
        }
        table.setItems(FXCollections.observableArrayList(persons));
    }

}