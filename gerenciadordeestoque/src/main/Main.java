package main;

import java.io.IOException;
import static javafx.application.Application.launch;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author santo
 */
public class Main extends Application {
    
    private static Stage stage;
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        stage = primaryStage;        
        
        Parent root = FXMLLoader.load(getClass().getResource
                                        ("/view/TelaLog.fxml"));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("GERENCIADOR DE ESTOQUE");        
        primaryStage.setResizable(false);
        primaryStage.centerOnScreen();
        primaryStage.setMaximized(true);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static Stage getStage() {
        return stage;
    }

    
    
    
}
