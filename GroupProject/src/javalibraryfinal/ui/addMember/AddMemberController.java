
package javalibraryfinal.ui.addMember;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javalibraryfinal.database.connection.DatabaseHandler;


public class AddMemberController implements Initializable {
 
    DatabaseHandler dhandler;
    
    @FXML
    private JFXTextField fname;
    @FXML
    private JFXTextField lname;
    @FXML
    private JFXTextField id;
    @FXML
    private JFXTextField gender;
    @FXML
    private JFXTextField mobile;
    @FXML
    private JFXTextField email;
    @FXML
    private JFXButton save;
    @FXML
    private JFXButton cancel;
    @FXML
    private AnchorPane rootPane;
    @FXML
    private VBox vbox;
    
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dhandler = new DatabaseHandler();
    }    

    @FXML
    private void addMember(ActionEvent event) {
        String Mfname = fname.getText();
        String Mlname = lname.getText();
        String Mid = id.getText();
        String Mgender = gender.getText();
        String Mmobile = mobile.getText();
        String Memail = email.getText();
        
       Boolean flag = Mfname.isEmpty()||Mlname.isEmpty()||Mid.isEmpty()||Mgender.isEmpty()||Mmobile.isEmpty()||Memail.isEmpty();
           if(flag){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please Enter All Fields");
            alert.showAndWait();
            return;
        }
        String st = "INSERT INTO `memberinfo`(`memberFname`,`memberLname`,`memberReg`,`memberGender`, `memberMobile`, `memberEmail`) "
                + "VALUES ('"+Mfname+"','"+Mlname+"','"+Mid+"','"+Mgender+"','"+Mmobile+"','"+Memail+"')";
           
        System.out.println(st);
        if(dhandler.execAction(st)){
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
         Stage stage = (Stage) rootPane.getScene().getWindow();
       stage.close();
    }

   
    
}
