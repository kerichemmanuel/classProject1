
package javalibraryfinal.ui.addMember;


import javafx.application.Application;;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class MemberLoader extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception {
        //method below launches the member_add.fxml document layout
	Parent root = FXMLLoader.load(getClass().getResource("member_add.fxml"));
	Scene scene = new Scene(root);//creating a scene
			
	primaryStage.setScene(scene);//setting the scene
        primaryStage.show();//displaying the scene
    }

    public static void main(String[] args) {
        launch(args);
    }
    
}
