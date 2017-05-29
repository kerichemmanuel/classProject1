
package javalibraryfinal.ui.listMember;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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

public class MemberListController implements Initializable {
    
    ObservableList<Member> list;

    @FXML
    private AnchorPane rootPane;
    @FXML
    private TableView<Member> tableView;
    @FXML
    private TableColumn<Member,String> indexCol;
    @FXML
    private TableColumn<Member,String> fnameCol;
    @FXML
    private TableColumn<Member,String> lnameCol;
    @FXML
    private TableColumn<Member,String> regCol;
    @FXML
    private TableColumn<Member,String> genderCol;
    @FXML
    private TableColumn<Member,String> mobileCol;
    @FXML
    private TableColumn<Member,String> emailCol;

    public MemberListController() {
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
        fnameCol.setCellValueFactory(new PropertyValueFactory<>("fname"));
        lnameCol.setCellValueFactory(new PropertyValueFactory<>("lname"));
        regCol.setCellValueFactory(new PropertyValueFactory<>("reg"));
        genderCol.setCellValueFactory(new PropertyValueFactory<>("gender"));
        mobileCol.setCellValueFactory(new PropertyValueFactory<>("mobile"));
        emailCol.setCellValueFactory(new PropertyValueFactory<>("email"));
    }

    private void loadData() {
        //initializing the databasehandler
        DatabaseHandler handler = new DatabaseHandler();  
         String qu = "SELECT * FROM `memberinfo`";
        ResultSet rs = handler.execQuery(qu);
        
        try {
            while(rs.next()){
                String id = rs.getString("member-id");
                String fname = rs.getString("memberFname");
                String lname = rs.getString("memberLname");
                String Id = rs.getString("memberReg");
                String gender = rs.getString("memberGender");
                String mobile = rs.getString("memberMobile");
                String email = rs.getString("memberEmail");
               
                list.add(new Member(id, fname, lname, Id, gender, mobile,email));
               
              }
            } catch (SQLException ex) {
            Logger.getLogger(AddBooktController.class.getName()).log(Level.SEVERE, null, ex);
           } 
        
        tableView.getItems().setAll(list);
    }
  //class to contain objects of members
    public static class Member{
        private final SimpleStringProperty index;
        private final SimpleStringProperty fname;
        private final SimpleStringProperty lname;
        private final SimpleStringProperty reg;
        private final SimpleStringProperty gender;
        private final SimpleStringProperty mobile;
        private final SimpleStringProperty email;
        
        //constructor
        Member(String index,String fname,String lname,String reg,String gender,String mobile,String email){
            this.index = new SimpleStringProperty(index);
            this.fname = new SimpleStringProperty(fname);
            this.lname = new SimpleStringProperty(lname);
            this.reg = new SimpleStringProperty(reg);
            this.gender = new SimpleStringProperty(gender);
            this.mobile = new SimpleStringProperty(mobile);
            this.email = new SimpleStringProperty(email);
        }
        
     //Getters
        public String getIndex() {
            return index.get();
        }

        public String getFname() {
            return fname.get();
        }

        public String getLname() {
            return lname.get();
        }

        public String getReg() {
            return reg.get();
        }

        public String getGender() {
            return gender.get();
        }

        public String getMobile() {
            return mobile.get();
        }

        public String getEmail() {
            return email.get();
        }
   }
}