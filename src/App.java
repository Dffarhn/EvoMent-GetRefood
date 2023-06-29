import javafx.stage.Stage; 
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.fxml.FXMLLoader;
import javafx.application.Application;
public class App extends Application {
   @Override
   public void start(Stage stage) throws Exception {

      // Parent root = FXMLLoader.load(getClass().getResource("Login/Scene1.fxml"));
      // Parent root = FXMLLoader.load(getClass().getResource("HomePage\\myproduct.fxml"));
      // Parent root = FXMLLoader.load(getClass().getResource("MyReFood\\MyRefood.fxml"));
      // Parent root = FXMLLoader.load(getClass().getResource("HomePage\\searchview.fxml"));
      // Parent root = FXMLLoader.load(getClass().getResource("PageSeeProduct/seepro.fxml"));
      Parent root = FXMLLoader.load(getClass().getResource("HomePage/Homepage.fxml"));
      // Parent root = FXMLLoader.load(getClass().getResource("HomePage\\berandaseller.fxml"));
      Scene scene = new Scene(root);
      stage.setScene(scene);
      stage.show();
   }
   public static void main(String[] args) {
      launch(args);
   }
}