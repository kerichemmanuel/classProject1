
package javalibraryfinal.ui.listBook;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javalibraryfinal.database.connection.DatabaseHandler;
import javalibraryfinal.ui.addBook.AddBooktController;


public class BookListController implements Initializable {
    
    ObservableList<Book> list;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<Book> tableView;
    @FXML
    private TableColumn<Book,String> indexCol;
    @FXML
    private TableColumn<Book,String> titleCol;
    @FXML
    private TableColumn<Book,String> authorCol;
    @FXML
    private TableColumn<Book,String> ISNBCol;
    @FXML
    private TableColumn<Book,String> publisherCol;
    @FXML
    private TableColumn<Book,String> categoryCol;
    @FXML
    private TableColumn<Book,Boolean> availabilityCol;

    public BookListController() {
        this.list = FXCollections.observableArrayList();
    }

   
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //function to initialize the column
        initCol();
       
        loadData();
    } 

    private void initCol() {
        indexCol.setCellValueFactory(new PropertyValueFactory<>("index"));
        titleCol.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorCol.setCellValueFactory(new PropertyValueFactory<>("author"));
        ISNBCol.setCellValueFactory(new PropertyValueFactory<>("ISNB"));
        publisherCol.setCellValueFactory(new PropertyValueFactory<>("publisher"));
        categoryCol.setCellValueFactory(new PropertyValueFactory<>("category"));
        availabilityCol.setCellValueFactory(new PropertyValueFactory<>("availability"));
        
    }

    private void loadData() {
         //initializing the databasehandler
        DatabaseHandler handler = new DatabaseHandler();  
         String qu = "SELECT * FROM `bookinfo`";
        ResultSet rs = handler.execQuery(qu);
        
        try {
            while(rs.next()){
                String id = rs.getString("book-id");
                String title = rs.getString("bookTitle");
                String author = rs.getString("bookAuthor");
                String ID = rs.getString("bookID");
                String publisher = rs.getString("bookPublisher");
                String category = rs.getString("bookCategory");
                Boolean avail = rs.getBoolean("isAvail");
               list.add(new Book(id, title, author, ID, publisher, category, avail));
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddBooktController.class.getName()).log(Level.SEVERE, null, ex);
        }
        tableView.getItems().setAll(list);
    }
    
    //class to contain object of books
    public static class Book{
        private final SimpleStringProperty index;
        private final SimpleStringProperty title;
        private final SimpleStringProperty author;
        private final SimpleStringProperty ISNB;
        private final SimpleStringProperty publisher;
        private final SimpleStringProperty category;
        private final SimpleBooleanProperty availability;
        
        //constructor
        Book(String index,String title,String author,String ISNB,String publisher,String category,Boolean avail){
            this.index = new SimpleStringProperty(index);
            this.title = new SimpleStringProperty(title);
            this.author = new SimpleStringProperty(author);
            this.ISNB = new SimpleStringProperty(ISNB);
            this.publisher = new SimpleStringProperty(publisher);
            this.category = new SimpleStringProperty(category);
            this.availability = new SimpleBooleanProperty(avail);
        }
     //Getters
        public String getIndex() {
            return index.get();
        }

        public String getTitle() {
            return title.get();
        }

        public String getAuthor() {
            return author.get();
        }

        public String getISNB() {
            return ISNB.get();
        }

        public String getPublisher() {
            return publisher.get();
        }

        public String getCategory() {
            return category.get();
        }

        public Boolean getAvailability() {
            return availability.get();
        }
        
    }
    
}
