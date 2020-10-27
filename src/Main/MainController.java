/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Subject.SubjectController;
import Tools.mysql;
import center.elgam3a.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXMasonryPane;
import java.io.IOException;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TitledPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.controlsfx.control.Notifications;
import org.controlsfx.control.StatusBar;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author goda4
 */
public class MainController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private ImageView Image1;
    @FXML
    private HBox HBO;
    @FXML
    private Label L1;
    @FXML
    private AnchorPane p2;
    @FXML
    private Label L2;
    @FXML
    private VBox Main;
    @FXML
    private ScrollPane SP;
    @FXML
    private VBox SP1;
    private JFXButton B;
    @FXML
    private StatusBar SB;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            String count_sub = (String) mysql.PrintTable("SELECT count(`total_amount_sub`-`total_amount_Payment`) AS sub, `DATE` FROM `total_of_sub` WHERE `total_amount_sub`-`total_amount_Payment` != 0").Table[0][0];
            
            Notifications.create().title("سنتر الجامعة")
                    .text("اهلا وسهلا \n" + User.getUserName_US())
                    .showInformation();
            if (Integer.parseInt(count_sub) > 0) {
                String sub = (String) mysql.PrintTable("SELECT `total_amount_sub`-`total_amount_Payment` AS sub, `DATE` FROM `total_of_sub` HAVING sub != 0").Table[0][1];
                String Massage = "هناك اخطاء فى " + count_sub + " ايام\n";
                Massage += sub;
                Notifications.create().title("سنتر الجامعة")
                        .text(Massage)
                        .showError();
            }

            // TODO
            Tools.Tools_JavaFX.Move_Stage(HBO);
            Image image = new Image("/image/" + User.getID_Us() + ".jpg");
            Image1.setImage(image);
            L1.setText(User.getUserName_US());
            Button_Unversty();
            Tools.Tools_JavaFX.check_DB(SB);
            
        } catch (Exception ex) {
            
            System.out.println(ex);
            
        }
        
    }
    
    @FXML
    void Mux(MouseEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        if (stage.isMaximized()) {
            if ("تكبير".equals(L2.getText())) {
                stage.setMaximized(true);
                L2.setText("تصغير");
            } else if ("تصغير".equals(L2.getText())) {
                stage.setMaximized(false);
                L2.setText("تكبير");
            }
            
        } else if (!stage.isMaximized()) {
            if ("تكبير".equals(L2.getText())) {
                stage.setMaximized(true);
                L2.setText("تصغير");
            } else if ("تصغير".equals(L2.getText())) {
                stage.setMaximized(false);
                L2.setText("تكبير");
            }
            
        }
        
    }
    
    @FXML
    void exit(MouseEvent event) {
        Platform.exit();
    }
    
    void Button_Unversty() {
        
        String Count_Row = (String) mysql.PrintTable("SELECT COUNT(ID_UN) FROM `universty`").Table[0][0];
        Main.getChildren().clear();
        for (int x = 0; x < Integer.parseInt(Count_Row); x++) {
            JSONObject JO = mysql.GetData("SELECT ID_UN, `Name_UN` FROM `universty`").getJSONObject(x);
            String Name_UN = JO.getString("Name_UN");
            String ID_UN = JO.getString("ID_UN");
            B = new JFXButton(Name_UN);
            B.setPrefSize(240, 70);
            B.setPadding(new Insets(0, 0, 0, 0));
            B.setFont(new Font("System", 25));
            B.setStyle("-fx-text-fill: white;");
            B.setBorder(new Border(new BorderStroke(Color.WHEAT, BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(0, 0, 5, 0))));
            B.setOnAction(e -> win_universty(Integer.parseInt(ID_UN)));
            Main.getChildren().add(B);
            
        }
        
    }
    
    void win_universty(int ID_UN) {
        
        Accordion Ac = new Accordion();
        String Count_Fcalty = (String) mysql.PrintTable("SELECT count(`Name_FA`) FROM `faculty` WHERE `ID_UN`=" + ID_UN).Table[0][0];
        
        for (int x = 0; x < Integer.parseInt(Count_Fcalty); x++) {
            JSONObject JO = mysql.GetData("SELECT `ID_FA`,`Name_FA` FROM `faculty` WHERE `ID_UN`=" + ID_UN).getJSONObject(x);
            String Name_Fcalty = JO.getString("Name_FA");
            String ID_Fcalty = JO.getString("ID_FA");
            
            new Thread() {
                @Override
                public void run() {
                    TitledPane TP = new TitledPane();
                    TP.setFont(new Font("System", 20));
                    TP.setText(Name_Fcalty);
                    Ac.getPanes().add(TP);
                    win_Faclty(Integer.parseInt(ID_Fcalty), TP);
                }
            }.start();
            
        }
        
        Platform.runLater(() -> {
            SP1.getChildren().clear();
            SP1.getChildren().add(Ac);
            SP.setContent(SP1);
            SP.setVisible(true);
        });
        
    }
    
    void win_Faclty(int ID_Fa, TitledPane TPane) {
        
        Accordion Ac = new Accordion();
        String Count_years = (String) mysql.PrintTable("SELECT COUNT(`Name_YE`) FROM `years` WHERE `ID_FA`=" + ID_Fa).Table[0][0];
        for (int x = 0; x < Integer.parseInt(Count_years); x++) {
            JSONObject JO = mysql.GetData("SELECT `ID_YE`,`Name_YE` FROM `years` WHERE `ID_FA`=" + ID_Fa).getJSONObject(x);
            String Name_years = JO.getString("Name_YE");
            String ID_years = JO.getString("ID_YE");
            
            new Thread() {
                @Override
                public void run() {
                    TitledPane TP = new TitledPane();
                    TP.setFont(new Font("System", 15));
                    TP.setText(Name_years);
                    Ac.getPanes().add(TP);
                    win_Year(Integer.parseInt(ID_years), TP);
                }
            }.start();
        }
        Platform.runLater(() -> {
            TPane.setContent(Ac);
        });
        
    }
    
    void win_Year(int ID_years, TitledPane TPane) {
        
        JFXMasonryPane MP = new JFXMasonryPane();
        String Count_Subject = (String) mysql.PrintTable("SELECT count(`Name_SU`) FROM `subject` WHERE `ID_YE`=" + ID_years).Table[0][0];
        for (int x = 0; x < Integer.parseInt(Count_Subject); x++) {
            JSONObject JO = mysql.GetData("SELECT ID_SU , `Name_SU` FROM `subject` WHERE `ID_YE`=" + ID_years).getJSONObject(x);
            String Name_Subject = JO.getString("Name_SU");
            String ID_Subject = JO.getString("ID_SU");
            
            new Thread() {
                @Override
                public void run() {
                    JFXButton B1 = new JFXButton(Name_Subject);
                    B1.setFont(new Font("System", 25));
                    B1.setOnMouseClicked((MouseEvent event) -> {
                        try {
                            FXMLLoader Loader = new FXMLLoader(getClass().getResource("/Subject/Subject.fxml"));
                            Parent root = Loader.load();
                            SubjectController C = Loader.<SubjectController>getController();
                            C.ID_SU = Integer.parseInt(ID_Subject);
                            Scene scene = new Scene(root);
                            Stage stage = new Stage();
                            C.s = stage;
                            stage.setScene(scene);
                            stage.setTitle(Name_Subject);
                            stage.initStyle(StageStyle.UNDECORATED);
                        } catch (IOException ex) {
                            Tools.Tools_JavaFX.Alert_T(Alert.AlertType.ERROR, "شاشة المادة", "غير متاحة", "بسبب عدم وجود طلاب مسجلين بها");
                        }
                    });
                    B1.setStyle("-fx-background-color: #222222;-fx-text-fill: #f5f6fa;");
                    Platform.runLater(() -> {
                        MP.getChildren().add(B1);
                    });
                }
            }.start();
            
        }
        Platform.runLater(() -> {
            TPane.setContent(MP);
        });
    }
    
    @FXML
    void ADD_ST(ActionEvent event) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Add_Student/ADD_ST.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.getIcons().add(new Image("/image/Icon.png"));
            stage.setTitle("اضافة طالب");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            Rectangle2D rd = Screen.getPrimary().getVisualBounds();
            stage.setX((rd.getWidth() - stage.getWidth()) / 2);
            stage.setY((rd.getHeight() - stage.getHeight()) / 2);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
    }
    
    @FXML
    void ADD_Sub(ActionEvent event) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Levels/Levels.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.getIcons().add(new Image("/image/Icon.png"));
            stage.setTitle("اضافة الاقسام");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            Rectangle2D rd = Screen.getPrimary().getVisualBounds();
            stage.setX((rd.getWidth() - stage.getWidth()) / 2);
            stage.setY((rd.getHeight() - stage.getHeight()) / 2);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
    }
    
    @FXML
    void Refreh(ActionEvent event) {
        initialize(null, null);
    }
    
    @FXML
    void Amount(ActionEvent event) {
        
        try {
            Parent root = FXMLLoader.load(getClass().getResource("/Add_Payment_User/Add_P_U.fxml"));
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setScene(scene);
            stage.getIcons().add(new Image("/image/Icon.png"));
            stage.setTitle("اضافة الايراد");
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            Rectangle2D rd = Screen.getPrimary().getVisualBounds();
            stage.setX((rd.getWidth() - stage.getWidth()) / 2);
            stage.setY((rd.getHeight() - stage.getHeight()) / 2);
        } catch (IOException ex) {
            System.out.println(ex);
        }
        
    }
    
}
