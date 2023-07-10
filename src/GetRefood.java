import javafx.stage.Stage; 
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
public class GetRefood extends Application {
   @Override
   public void start(Stage stage) throws Exception {

      Parent root = FXMLLoader.load(getClass().getResource("Login/Scene1.fxml"));

      Image logoImage = new Image(getClass().getClassLoader().getResourceAsStream("Login\\Foto\\loginicon.jpg"));
       

      // Set the logo image for the stage
      stage.getIcons().add(logoImage);
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.setTitle("Get Re-Food By Evo-Ment");
      stage.show();
   }
   public static void main(String[] args) {
      launch(args);
   }
}