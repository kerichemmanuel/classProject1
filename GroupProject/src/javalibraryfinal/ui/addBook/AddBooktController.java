
package javalibraryfinal.ui.addBook;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javalibraryfinal.database.connection.DatabaseHandler;

/**
 * FXML Controller class
 *
 * @author Tapsilei
 */
public class AddBooktController implements Initializable {

    @FXML
    private JFXTextField title;
    @FXML
    private JFXTextField author;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField publisher;
    @FXML
    private JFXTextField category;
    @FXML
    private JFXButton saveButton;
    @FXML
    private JFXButton cancelButton;
    @FXML
    private AnchorPane rootParent;
    
  DatabaseHandler databasehandler;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
       databasehandler = new DatabaseHandler();
       
       CheckData();
    }    

    @FXML
    private void addBook(ActionEvent event) {
       String bookTitle = title.getText();
        String bookAuthor = author.getText();
        String bookID = id.getText();
        String bookPublisher = publisher.getText();
        String bookCategory = category.getText();
        
        if(bookTitle.isEmpty()||bookAuthor.isEmpty()||bookID.isEmpty()||bookPublisher.isEmpty()||
                bookCategory.isEmpty()){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter All Fields");
            alert.showAndWait();
            return;
        }
        String qu = "INSERT INTO `bookinfo`(`bookTitle`, `bookAuthor`, `bookID`, `bookPublisher`, `bookCategory`,`isAvail`) "
                + "VALUES ("
                + "'"+bookTitle+"',"
                + "'"+bookAuthor+"',"
                + "'"+bookID+"',"
                + "'"+bookPublisher+"',"
                + "'"+bookCategory+"',"
                + ""+"true"+""
                + ")";
        System.out.println(qu);
        if(databasehandler.execAction(qu)){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setContentText("Success");
            alert.showAndWait();
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Failed");
            alert.showAndWait();
        }
    }

    @FXML
    private void cancel(ActionEvent event) {
       Stage stage = (Stage) rootParent.getScene().getWindow();
       stage.close();
    }

    private void CheckData() {
        String qu = "SELECT `bookTitle` FROM `bookinfo`";
        ResultSet rs = databasehandler.execQuery(qu);
        
        try {
            while(rs.next()){
                String title = rs.getString("bookTitle");
                System.out.println(title);
            }
        } catch (SQLException ex) {
            Logger.getLogger(AddBooktController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
