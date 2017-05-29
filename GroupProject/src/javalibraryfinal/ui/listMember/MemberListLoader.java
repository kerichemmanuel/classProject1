
package javalibraryfinal.ui.listMember;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MemberListLoader extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
       //method below launches the book_list.fxml document layout
	Parent root = FXMLLoader.load(getClass().getResource("MemberList.fxml"));
	Scene scene = new Scene(root);//creating a scene
			
	stage.setScene(scene);//setting the scene
        stage.show();//displaying the scene
    }

 
    public static void main(String[] args) {
        launch(args);
    }
    
}
