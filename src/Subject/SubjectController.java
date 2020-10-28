/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subject;

import IReport.IReport;
import Tools.mysql;
import center.elgam3a.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.embed.swing.SwingNode;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Rectangle2D;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javax.swing.JPanel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;
import org.controlsfx.control.MaskerPane;

/**
 * FXML Controller class
 *
 * @author goda4
 */
public class SubjectController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public int ID_SU;

    public Stage s;

    String ID_PY_T;

    JasperPrint R1 = null;

    @FXML
    private AnchorPane AP;

    @FXML
    private Label Tital;

    @FXML
    private JFXTextField ID;

    @FXML
    private JFXTextField Amount;

    @FXML
    private Label Name;

    @FXML
    private JFXDatePicker Date;

    @FXML
    private JFXComboBox Type_Of_Payment;

    @FXML
    private JFXButton ADD;

    @FXML
    private JFXButton Update;

    @FXML
    private JFXButton Delete;

    @FXML
    private JFXButton Day;

    @FXML
    private JFXButton ALL;

    @FXML
    private JFXTreeTableView<Payment> tree;

    @FXML
    private JFXTreeTableView<Count_Amount> tree2;

    @FXML
    private JFXTreeTableView<Total_Amount> tree3;

    @FXML
    private JFXComboBox Group;

    @FXML
    private Label Time;

    @FXML
    private AnchorPane Anc;

    @FXML
    private MaskerPane MP;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        Platform.runLater(() -> {
            try {

                ID.setDisable(false);
                Type_Of_Payment.setDisable(false);
                //
                Update.setDisable(true);
                Delete.setDisable(true);
                ADD.setDisable(false);
                Tools.Tools_JavaFX.Move_Stage(AP);
                Tital.setText((String) mysql.PrintTable("SELECT `Name_SU` FROM `subject` WHERE `ID_SU`=" + ID_SU).Table[0][0]);
                mysql.Combo1("SELECT `Name_OF_Type` FROM `payment_type` ", Type_Of_Payment);
                mysql.Combo1("SELECT `ID_GR` FROM `group_subject` WHERE `ID_SU`=" + ID_SU, Group);
                ID.requestFocus();
                ID.setText("");
                Amount.setText("0");
                Type_Of_Payment.getSelectionModel().select(0);
                Date.setValue(LocalDate.now());
                ID.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    if (!newValue.isEmpty()) {
                        String Name_S = (String) mysql.PrintTable("SELECT `Name` FROM `payment_student` WHERE `Student`=" + newValue).Table[0][0];
                        Name.setText(Name_S);
                    }
                });

                JFXTreeTableColumn<Payment, String> ID_PY = new JFXTreeTableColumn<>("كود التحصيل");
                ID_PY.setPrefWidth(75);
                ID_PY.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment, String> param) -> param.getValue().getValue().ID_PY);

                JFXTreeTableColumn<Payment, String> Amount_t = new JFXTreeTableColumn<>("المبلغ");
                Amount_t.setPrefWidth(100);
                Amount_t.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment, String> param) -> param.getValue().getValue().Amount);

                JFXTreeTableColumn<Payment, String> Date_t = new JFXTreeTableColumn<>("تاريخ");
                Date_t.setPrefWidth(150);
                Date_t.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment, String> param) -> param.getValue().getValue().Date);

                JFXTreeTableColumn<Payment, String> ID_ST_t = new JFXTreeTableColumn<>("كود الطالب");
                ID_ST_t.setPrefWidth(75);
                ID_ST_t.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment, String> param) -> param.getValue().getValue().ID_St);

                JFXTreeTableColumn<Payment, String> User_N_t = new JFXTreeTableColumn<>("اسم المستخدم");
                User_N_t.setPrefWidth(100);
                User_N_t.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment, String> param) -> param.getValue().getValue().Name_Us);

                JFXTreeTableColumn<Payment, String> type_of_t = new JFXTreeTableColumn<>("نوع العملية");
                type_of_t.setPrefWidth(75);
                type_of_t.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment, String> param) -> param.getValue().getValue().Type_Of);
                ObservableList Li = mysql.Table_FX2("SELECT `ID_PA`, `Amount_PA`, `Date_PA`, `ID_ST`, `Name_US`, `Name_OF_Type` FROM `payment_v` WHERE `ID_SU`=" + ID_SU);
                final TreeItem<Payment> root = new RecursiveTreeItem<Payment>(Li, RecursiveTreeObject::getChildren);
                tree.getColumns().setAll(ID_PY, Amount_t, Date_t, ID_ST_t, User_N_t, type_of_t);
                tree.setRoot(root);
                tree.setShowRoot(false);

                ID.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                    tree.setPredicate((TreeItem<Payment> t) -> {
                        Boolean flag = t.getValue().ID_St.getValue().contains(newValue);
                        return flag;
                    });
                });

                Date.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {
                    if (Date.getValue() != null) {
                        tree.setPredicate((TreeItem<Payment> t) -> {
                            Boolean flag = t.getValue().Date.getValue().contains(newValue.format(DateTimeFormatter.ISO_DATE));
                            return flag;
                        });
                    } else {
                        tree.setPredicate((TreeItem<Payment> t) -> {
                            Boolean flag = t.getValue().Date.getValue().contains("");
                            return flag;
                        });
                    }
                });

                JFXTreeTableColumn<Count_Amount, String> Amount_PY = new JFXTreeTableColumn<>("المبلغ");
                Amount_PY.setPrefWidth(150);
                Amount_PY.setCellValueFactory((TreeTableColumn.CellDataFeatures<Count_Amount, String> param) -> param.getValue().getValue().Amount);

                JFXTreeTableColumn<Count_Amount, String> Count_t = new JFXTreeTableColumn<>("العدد");
                Count_t.setPrefWidth(160);
                Count_t.setCellValueFactory((TreeTableColumn.CellDataFeatures<Count_Amount, String> param) -> param.getValue().getValue().Count);

                ObservableList Li1 = mysql.Table_FX3("SELECT IFNULL(`Total`,'اجمالي عدد الطلاب'), COUNT(`Student`) , `Name Subject` FROM `payment_student` WHERE `Name Subject` = '" + Tital.getText() + "' GROUP BY Total WITH ROLLUP");
                final TreeItem<Count_Amount> root1 = new RecursiveTreeItem<Count_Amount>(Li1, RecursiveTreeObject::getChildren);
                tree2.getColumns().setAll(Amount_PY, Count_t);
                tree2.setRoot(root1);
                tree2.setShowRoot(false);

                JFXTreeTableColumn<Total_Amount, String> T_PY = new JFXTreeTableColumn<>("المبلغ");
                T_PY.setPrefWidth(150);
                T_PY.setCellValueFactory((TreeTableColumn.CellDataFeatures<Total_Amount, String> param) -> param.getValue().getValue().Total);

                JFXTreeTableColumn<Total_Amount, String> Da_t = new JFXTreeTableColumn<>("التاريخ");
                Da_t.setPrefWidth(100);
                Da_t.setCellValueFactory((TreeTableColumn.CellDataFeatures<Total_Amount, String> param) -> param.getValue().getValue().Date);
                JFXTreeTableColumn<Total_Amount, String> TY_t = new JFXTreeTableColumn<>("النوع");
                TY_t.setPrefWidth(100);
                TY_t.setCellValueFactory((TreeTableColumn.CellDataFeatures<Total_Amount, String> param) -> param.getValue().getValue().type);

                ObservableList Li2 = mysql.Table_FX4("SELECT IF( `Name_OF_Type` IS NULL, NULL, SUM(`total`) ) AS Amount, IF( `Name_OF_Type` IS NULL, NULL, `DATE` ) AS DATE, IF( `Name_OF_Type` IS NULL, NULL, `Name_OF_Type` ) AS TYPE FROM `amount_subject` WHERE `Name_SU` = '" + Tital.getText() + "' and `total` != 0  GROUP BY `Name_OF_Type`, `DATE` WITH ROLLUP ORDER BY `Name_OF_Type` DESC");
                final TreeItem<Total_Amount> root2 = new RecursiveTreeItem<Total_Amount>(Li2, RecursiveTreeObject::getChildren);
                tree3.getColumns().setAll(T_PY, Da_t, TY_t);
                tree3.setRoot(root2);
                tree3.setShowRoot(false);
                String Tot = "0";
                try {
                    Tot = (String) mysql.PrintTable("SELECT SUM(`total`) -( SELECT SUM(`total`) FROM `amount_subject` s WHERE `Name_SU` = '" + Tital.getText() + "' AND s.`Name_OF_Type` = 'سحب' GROUP BY `Name_SU` ) as Amount, `DATE` AS DATE, `Name_SU` AS SUBJECT, `Name_OF_Type` AS TYPE FROM `amount_subject` WHERE `Name_SU` = '" + Tital.getText() + "' AND `Name_OF_Type` = 'كاش' GROUP BY `Name_SU`").Table[0][0];
                    tree3.getSelectionModel().getModelItem(tree3.getCurrentItemsCount() - 1).getValue().Total.set(Tot);
                } catch (Exception ex) {
                    Tot = "0";
                }

                Group.getItems().add("الكل");
                if (!s.isShowing()) {
                    s.show();
                }
                Rectangle2D rd = Screen.getPrimary().getVisualBounds();
                s.setX((rd.getWidth() - s.getWidth()) / 2);
                s.setY((rd.getHeight() - s.getHeight()) / 2);
                SwingNode swingNode = new SwingNode();
                Anc.getChildren().add(swingNode);
                swingNode.autosize();

                Group.valueProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
                    if (newValue != "الكل") {
                        Time.setText((String) mysql.PrintTable("SELECT concat(`Day_Of_One`,' - ',`Day_Of_Tow`,' - ',`Day_Of_Time`) FROM `group_subject` WHERE `ID_GR`=" + newValue).Table[0][0]);
                    } else {
                        Time.setText("");
                    }

                    Task<Void> task = new Task<Void>() {
                        @Override
                        protected Void call() throws Exception {
                            MP.setVisible(true);
                            HashMap HMap = new HashMap();
                            IReport R = new IReport();
                            JPanel JP = new JPanel();

                            if (newValue != "الكل") {
                                HMap.put("ID_Group", Integer.parseInt(newValue.toString()));
                                HMap.put("ID_SU", ID_SU);
                                R1 = R.R1("Student_R_S", HMap, JP);
                            } else {
                                HMap.put("ID_SU", ID_SU);
                                R1 = R.R1("Student_ALL", HMap, JP);
                            }
                            swingNode.setContent(JP);
                            return null;
                        }
                    };
                    task.setOnSucceeded((event) -> {
                        MP.setVisible(false);
                    });

                    new Thread(task).start();

                });

            } catch (SQLException ex) {
                Logger.getLogger(SubjectController.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

    }

    @FXML
    void ADD(ActionEvent event) {

        if (!ID.getText().isEmpty() && Integer.parseInt(Amount.getText()) != 0) {
            String Type_OF = (String) mysql.PrintTable("SELECT `ID_PA_TY` FROM `payment_type` WHERE `Name_OF_Type`='" + Type_Of_Payment.getSelectionModel().getSelectedItem().toString() + "'").Table[0][0];
            boolean Run = mysql.Run("INSERT INTO `payment`(`Amount_PA`, `ID_ST`, `ID_SU`, `ID_User`, `ID_PA_TY`) VALUES (" + Amount.getText() + "," + ID.getText() + "," + ID_SU + "," + User.getID_Us() + "," + Type_OF + ")");
            if (Run) {
                Tools.Tools_JavaFX.Alert_T(Alert.AlertType.INFORMATION, "تسجيل التحصيل" + Tital.getText(), "", "تم تسجيل المبلغ");
                initialize(null, null);
            }
        }

    }

    @FXML
    void Delete(ActionEvent event) {
        if (!ID_PY_T.isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("اضافة المبلغ");
            alert.setHeaderText("مسح المبلغ");
            alert.setContentText("هل انت متاكد من مسح هذا المبلغ");

            ButtonType buttonTypeOne = new ButtonType("نعم");
            ButtonType buttonTypeTwo = new ButtonType("لا");

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == buttonTypeOne) {
                boolean Run = mysql.Run("DELETE FROM `payment` WHERE `ID_PA`=" + ID_PY_T);
                if (Run) {
                    Tools.Tools_JavaFX.Alert_T(Alert.AlertType.CONFIRMATION, "حذف المبلغ", "تم حذف المبلغ", "صاحب كود " + ID);
                    ID_PY_T = "";
                    initialize(null, null);
                }
            }

        }

    }

    @FXML
    void Group(ActionEvent event) {

    }

    @FXML
    void Update(ActionEvent event) {
        if (!ID.getText().isEmpty() && Integer.parseInt(Amount.getText()) != 0 && !ID_PY_T.isEmpty()) {
            String value = Date.getValue().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            value = value + " 00:00:00";
            mysql.Run("UPDATE `payment` SET `Amount_PA`=" + Amount.getText() + ",`Date_PA`='" + value + "' WHERE `ID_PA`=" + ID_PY_T);
            initialize(null, null);
        }

    }

    @FXML
    void select(MouseEvent event) {
        try {
            if (tree.getSelectionModel().getSelectedItem().getValue().Name_Us.get().matches(User.getName_Us())) {
                ID_PY_T = tree.getSelectionModel().getSelectedItem().getValue().ID_PY.get();
                ID.setText(tree.getSelectionModel().getSelectedItem().getValue().ID_St.get());
                Amount.setText(tree.getSelectionModel().getSelectedItem().getValue().Amount.get());
                String date_V = tree.getSelectionModel().getSelectedItem().getValue().Date.get();
                String substring = date_V.substring(0, 10);
                String Y = LocalDate.parse(substring).format(DateTimeFormatter.ofPattern("yyyy"));
                String M = LocalDate.parse(substring).format(DateTimeFormatter.ofPattern("M"));
                String D = LocalDate.parse(substring).format(DateTimeFormatter.ofPattern("d"));
                Date.setValue(LocalDate.of(Integer.parseInt(Y), Integer.parseInt(M), Integer.parseInt(D)));
                Type_Of_Payment.getSelectionModel().select(tree.getSelectionModel().getSelectedItem().getValue().Type_Of.get());
                ID.setDisable(true);
                Type_Of_Payment.setDisable(true);
                Update.setDisable(false);
                Delete.setDisable(false);
                ADD.setDisable(true);

            } else {

                Tools.Tools_JavaFX.Alert_T(Alert.AlertType.ERROR, "ليس لديك صلاحية", "صلاحية حذف", "لا يسمح بحذذف اعمال شخص اخر");

            }
        } catch (NumberFormatException e) {

        }

    }

    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }

    @FXML
    void PDF(ActionEvent event) throws JRException {
        try {
            MP.setVisible(true);
            IReport R = new IReport();
            R.JR_PDF(R1, String.valueOf(ID_SU));
            MP.setVisible(false);
        } catch (Exception ex) {
            Tools.Tools_JavaFX.Alert_T(Alert.AlertType.ERROR, "مشكلة فى استيراد الكشف", "يرجو التاكد من اختيار الكشف", "");
        }
    }

}
