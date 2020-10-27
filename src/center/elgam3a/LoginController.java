package center.elgam3a;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import Tools.mysql;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXSnackbar;
import com.jfoenix.controls.JFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Screen;
import javafx.stage.Stage;

/**
 *
 * @author goda4
 */
public class LoginController implements Initializable {

    @FXML
    private JFXTextField user;
    @FXML
    private JFXPasswordField password;
    @FXML
    private VBox box;

    private JFXSnackbar snack;

    @FXML
    private HBox HBO;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        snack = new JFXSnackbar(box);
        Tools.Tools_JavaFX.Move_Stage(HBO);
    }

    @FXML
    private void Login(Event e) throws IOException {

        if (user.getText().isEmpty()) {
            snack.show("User is Empty", 1500);
            return;

        }
        if (password.getText().isEmpty()) {
            snack.show("Password is Empty", 1500);
            return;
        }

        new Thread() {
            @Override
            public void run() {
                if (mysql.Che0(user.getText().trim(), password.getText().trim())) {
                    Platform.runLater(()->{
                        try {
                            Parent root = FXMLLoader.load(getClass().getResource("/Main/Main.fxml"));
                            Scene scene = new Scene(root);
                            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
                            stage.setScene(scene);
                            stage.setMaximized(true);
                            Rectangle2D rd = Screen.getPrimary().getVisualBounds();
                            stage.setX((rd.getWidth() - stage.getWidth()) / 2);
                            stage.setY((rd.getHeight() - stage.getHeight()) / 2);
                        } catch (IOException ex) {
                            Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });

                } else {
                   Platform.runLater(()->{
                    snack.show("User & Password Faild", 1500);
                   });
                    return;
                }
            }
        }.start();

    }

    @FXML
    private void Exit() {

        Platform.exit();

    }

    @FXML
    private void Enter_key(KeyEvent evt) {
        if (evt.getCode() == KeyCode.ENTER) {
            try {
                Login(evt);
            } catch (IOException ex) {
                Logger.getLogger(LoginController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

}
