import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import main.java.Style.LoginPage;

public class App extends Application
{
    
    @Override
    public void start(Stage primaryStage){

    Scene scene = new Scene(new LoginPage(primaryStage).getRoot(), 800, 600);
        

    try{
        primaryStage.getIcons().add(new Image("file:src/main/java/Images/icon.png"));
    }catch(Exception e){
        System.out.println("nuk ka ikone");
    }
        
        
    primaryStage.setTitle("BookStore Program");
    primaryStage.setScene(scene);
    primaryStage.show();
    
}

    public static void main(final String[] args) {
        launch();
    }
}