/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package center.elgam3a;

import Tools.mysql;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.json.JSONArray;

/**
 *
 * @author goda4
 */
public class CenterElgam3a extends Application {

    @Override
    public void start(Stage stage) throws Exception {

        if (mysql.Con()) {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            Scene scene = new Scene(root);
            stage.getIcons().add(new Image("/image/Icon.png"));
            stage.setScene(scene);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } else {
            Tools.Tools_JavaFX.Alert_T(Alert.AlertType.ERROR, "مشكلة", "مشكلة بالاتصال بالانترنت", "لا يوجد اتصال بالانترنت");
        }

    }

    @Override
    public void stop() throws Exception {
        mysql.con.close();
        super.stop(); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
        
    }

}
