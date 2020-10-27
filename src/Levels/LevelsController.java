/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Levels;

import Tools.mysql;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author goda4
 */
public class LevelsController implements Initializable {

    public String ID_UN_S;
    public String ID_FC_S;
    public String ID_YE_S;
    public String ID_ST_S;
    public String ID_Su_S;

    @FXML
    private AnchorPane AP;

    @FXML
    private JFXButton Exit;

    @FXML
    private JFXTextField Name_UN;

    @FXML
    private JFXButton ADD_UN;

    @FXML
    private JFXButton Update_UN;

    @FXML
    private JFXButton Delete_UN;

    @FXML
    private JFXTreeTableView<Universty> T_UN;

    @FXML
    private JFXButton ADD_FA;

    @FXML
    private JFXButton Update_FA;

    @FXML
    private JFXButton Delete_FA;

    @FXML
    private JFXComboBox<String> UN_COM;

    @FXML
    private JFXTextField Faclty;

    @FXML
    private JFXTreeTableView<faculty> T_Fa;

    @FXML
    private JFXComboBox UN_COM1;

    @FXML
    private JFXComboBox<String> FA_Com;

    @FXML
    private JFXTreeTableView<years> T_Ye;

    @FXML
    private JFXTextField year;

    @FXML
    private JFXButton ADD_FA1;

    @FXML
    private JFXButton Update_FA1;

    @FXML
    private JFXButton Delete_FA1;

    @FXML
    private JFXButton ADD_S;

    @FXML
    private JFXButton Update_S;

    @FXML
    private JFXButton Delete_S;

    @FXML
    private JFXTextField Name_S;

    @FXML
    private JFXTextField Phone_S;

    @FXML
    private JFXTextField Email_S;

    @FXML
    private JFXTextField P_S;

    @FXML
    private JFXTreeTableView<Staff> T_Sta;

    @FXML
    private JFXComboBox<String> UN_COM11;

    @FXML
    private JFXComboBox<String> FA_Com1;
    @FXML
    private JFXComboBox<String> ST_COM;

    @FXML
    private JFXComboBox<String> YE_Com11;

    @FXML
    private JFXTextField Name_Su;

    @FXML
    private JFXTextField Amount_Su;

    @FXML
    private JFXTextField Count_G;

    @FXML
    private JFXButton ADD_S1;

    @FXML
    private JFXButton Update_S1;

    @FXML
    private JFXTreeTableView<Subject> T_Su;

    @FXML
    private TableColumn<Group, String> Count_GR;

    @FXML
    private TableColumn<Group, Integer> NUM;

    @FXML
    private TableColumn<Group, ComboBox> Day1;

    @FXML
    private TableColumn<Group, ComboBox> Day2;

    @FXML
    private TableColumn<Group, ComboBox> Hours;

    @FXML
    private TableView<Group> T_G;

    ComboBox<String> Day1_C;
    ComboBox<String> Day2_C;
    ComboBox<String> Hours_C;
    String ID_UNM;
    String ID_Ye_M;
    String ID_UN1_M;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            T_G.getColumns().setAll(NUM, Count_GR, Day1, Day2, Hours);
            NUM.setCellValueFactory(new PropertyValueFactory<>("ID_G"));
            Count_GR.setCellValueFactory(new PropertyValueFactory<>("Count_G"));
            Day1.setCellValueFactory(new PropertyValueFactory<>("Day1_G"));
            Day2.setCellValueFactory(new PropertyValueFactory<>("Day2_G"));
            Hours.setCellValueFactory(new PropertyValueFactory<>("Time_G"));
            T_G.setEditable(true);
            FA_Com.setDisable(true);
            ADD_UN.setDisable(false);
            Delete_UN.setDisable(true);
            Update_UN.setDisable(true);
            ADD_FA.setDisable(false);
            Delete_FA.setDisable(true);
            Update_FA.setDisable(true);
            ADD_FA1.setDisable(false);
            Delete_FA1.setDisable(true);
            Update_FA1.setDisable(true);
            ADD_S.setDisable(false);
            Delete_S.setDisable(true);
            Update_S.setDisable(true);
            ADD_S1.setDisable(false);
            Update_S1.setDisable(true);
            YE_Com11.setDisable(false);
            T_G.getItems().clear();
            Tools.Tools_JavaFX.Move_Stage(AP);
            JFXTreeTableColumn<Universty, String> ID_UN = new JFXTreeTableColumn<>("كود");
            ID_UN.setPrefWidth(75);
            ID_UN.setCellValueFactory((TreeTableColumn.CellDataFeatures<Universty, String> param) -> param.getValue().getValue().ID_UN);
            JFXTreeTableColumn<Universty, String> Name_UN_T = new JFXTreeTableColumn<>("اسم الجامعة");
            Name_UN_T.setPrefWidth(100);
            Name_UN_T.setCellValueFactory((TreeTableColumn.CellDataFeatures<Universty, String> param) -> param.getValue().getValue().Name_Un);
            JFXTreeTableColumn<Universty, String> Date_UN = new JFXTreeTableColumn<>("التاريخ");
            Date_UN.setPrefWidth(75);
            Date_UN.setCellValueFactory((TreeTableColumn.CellDataFeatures<Universty, String> param) -> param.getValue().getValue().Date_UN);
            ObservableList Li = mysql.Table_FX5("SELECT * FROM `universty`");
            final TreeItem<Universty> root = new RecursiveTreeItem<Universty>(Li, RecursiveTreeObject::getChildren);
            T_UN.getColumns().setAll(ID_UN, Name_UN_T, Date_UN);
            T_UN.setRoot(root);
            T_UN.setShowRoot(false);
            UN_COM.getItems().clear();
            UN_COM1.getItems().clear();
            UN_COM11.getItems().clear();
            ST_COM.getItems().clear();
            mysql.Combo1("SELECT `Name_UN` FROM `universty`", UN_COM);
            mysql.Combo1("SELECT `Name_UN` FROM `universty`", UN_COM1);
            mysql.Combo1("SELECT `Name_UN` FROM `universty`", UN_COM11);
            mysql.Combo1("SELECT `Name_SF` FROM `staff`", ST_COM);
            UN_COM1.valueProperty().addListener((ObservableValue observable, Object oldValue, Object newValue) -> {
                try {
                    ID_UNM = (String) mysql.PrintTable("SELECT `ID_UN` FROM `universty` WHERE `Name_UN`='" + newValue + "'").Table[0][0];
                    FA_Com.setDisable(false);
                    FA_Com.getSelectionModel().clearSelection();
                    FA_Com.getItems().clear();
                    mysql.Combo1("SELECT `Name_FA` FROM `faculty` WHERE ID_UN=" + ID_UNM, FA_Com);
                } catch (Exception e) {

                    FA_Com.setDisable(true);

                }
            });

            UN_COM11.valueProperty().addListener((ObservableValue<? extends Object> observable, Object oldValue, Object newValue) -> {
                try {
                    ID_UN1_M = (String) mysql.PrintTable("SELECT `ID_UN` FROM `universty` WHERE `Name_UN`='" + newValue + "'").Table[0][0];
                    FA_Com1.setDisable(false);
                    FA_Com1.getSelectionModel().clearSelection();
                    FA_Com1.getItems().clear();
                    mysql.Combo1("SELECT `Name_FA` FROM `faculty` WHERE ID_UN=" + ID_UN1_M, FA_Com1);
                } catch (Exception e) {

                    FA_Com1.setDisable(true);

                }
            });

            FA_Com1.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                if (newValue != null) {

                    ID_Ye_M = (String) mysql.PrintTable("SELECT `ID_FA` FROM `faculty` WHERE `Name_FA`='" + newValue + "' and `ID_UN`=" + ID_UN1_M).Table[0][0];
                    YE_Com11.setDisable(false);
                    YE_Com11.getSelectionModel().clearSelection();
                    YE_Com11.getItems().clear();
                    mysql.Combo1("SELECT `Name_YE` FROM `years` WHERE `ID_FA`=" + ID_Ye_M, YE_Com11);

                }
            });

            JFXTreeTableColumn<faculty, String> ID_FA_T = new JFXTreeTableColumn<>("كود");
            ID_FA_T.setPrefWidth(75);
            ID_FA_T.setCellValueFactory((TreeTableColumn.CellDataFeatures<faculty, String> param) -> param.getValue().getValue().ID_Fa);
            JFXTreeTableColumn<faculty, String> Name_FA_T = new JFXTreeTableColumn<>("اسم الكلية");
            Name_FA_T.setPrefWidth(100);
            Name_FA_T.setCellValueFactory((TreeTableColumn.CellDataFeatures<faculty, String> param) -> param.getValue().getValue().Name_Fa);
            JFXTreeTableColumn<faculty, String> Date_FA_T = new JFXTreeTableColumn<>("التاريخ");
            Date_FA_T.setPrefWidth(100);
            Date_FA_T.setCellValueFactory((TreeTableColumn.CellDataFeatures<faculty, String> param) -> param.getValue().getValue().Date_Fa);
            JFXTreeTableColumn<faculty, String> Name_UN_T_F = new JFXTreeTableColumn<>("الجامعة");
            Name_UN_T_F.setPrefWidth(100);
            Name_UN_T_F.setCellValueFactory((TreeTableColumn.CellDataFeatures<faculty, String> param) -> param.getValue().getValue().Name_Un);
            ObservableList Li1 = mysql.Table_FX6("SELECT * FROM `faculty_v`");
            final TreeItem<faculty> root1 = new RecursiveTreeItem<faculty>(Li1, RecursiveTreeObject::getChildren);
            T_Fa.getColumns().setAll(ID_FA_T, Name_FA_T, Date_FA_T, Name_UN_T_F);
            T_Fa.setRoot(root1);
            T_Fa.setShowRoot(false);

            UN_COM.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                if (newValue != null) {
                    T_Fa.setPredicate((TreeItem<faculty> t) -> {
                        Boolean flag = t.getValue().Name_Un.getValue().contains(newValue);
                        return flag;
                    });
                }
            });

            JFXTreeTableColumn<years, String> ID_ye_T = new JFXTreeTableColumn<>("كود");
            ID_ye_T.setPrefWidth(75);
            ID_ye_T.setCellValueFactory((TreeTableColumn.CellDataFeatures<years, String> param) -> param.getValue().getValue().ID_ye);
            JFXTreeTableColumn<years, String> Name_ye_T = new JFXTreeTableColumn<>("اسم الكلية");
            Name_ye_T.setPrefWidth(100);
            Name_ye_T.setCellValueFactory((TreeTableColumn.CellDataFeatures<years, String> param) -> param.getValue().getValue().Name_ye);
            JFXTreeTableColumn<years, String> Date_ye_T = new JFXTreeTableColumn<>("التاريخ");
            Date_ye_T.setPrefWidth(100);
            Date_ye_T.setCellValueFactory((TreeTableColumn.CellDataFeatures<years, String> param) -> param.getValue().getValue().Date_ye);
            JFXTreeTableColumn<years, String> Name_fa_T_F = new JFXTreeTableColumn<>("الجامعة");
            Name_fa_T_F.setPrefWidth(100);
            Name_fa_T_F.setCellValueFactory((TreeTableColumn.CellDataFeatures<years, String> param) -> param.getValue().getValue().Name_fa);
            ObservableList Li2 = mysql.Table_FX7("SELECT * FROM `years_v`");
            final TreeItem<years> root2 = new RecursiveTreeItem<years>(Li2, RecursiveTreeObject::getChildren);
            T_Ye.getColumns().setAll(ID_ye_T, Name_ye_T, Date_ye_T, Name_fa_T_F);
            T_Ye.setRoot(root2);
            T_Ye.setShowRoot(false);

            FA_Com.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                if (newValue != null) {
                    T_Ye.setPredicate((TreeItem<years> t) -> {
                        Boolean flag = t.getValue().Name_fa.getValue().contains(newValue);
                        return flag;
                    });
                }
            });

            JFXTreeTableColumn<Staff, String> ID_Sa_T = new JFXTreeTableColumn<>("كود");
            ID_Sa_T.setPrefWidth(75);
            ID_Sa_T.setCellValueFactory((TreeTableColumn.CellDataFeatures<Staff, String> param) -> param.getValue().getValue().ID_S);
            JFXTreeTableColumn<Staff, String> Name_Sa_T = new JFXTreeTableColumn<>("اسم");
            Name_Sa_T.setPrefWidth(100);
            Name_Sa_T.setCellValueFactory((TreeTableColumn.CellDataFeatures<Staff, String> param) -> param.getValue().getValue().Name_S);
            JFXTreeTableColumn<Staff, String> Phone_Sa_T = new JFXTreeTableColumn<>("رقم التليفون");
            Phone_Sa_T.setPrefWidth(100);
            Phone_Sa_T.setCellValueFactory((TreeTableColumn.CellDataFeatures<Staff, String> param) -> param.getValue().getValue().Phone_S);
            JFXTreeTableColumn<Staff, String> Email_Sa_T = new JFXTreeTableColumn<>("الاميل");
            Email_Sa_T.setPrefWidth(100);
            Email_Sa_T.setCellValueFactory((TreeTableColumn.CellDataFeatures<Staff, String> param) -> param.getValue().getValue().Email_S);
            JFXTreeTableColumn<Staff, String> Date_Sa_T = new JFXTreeTableColumn<>("التاريخ");
            Date_Sa_T.setPrefWidth(100);
            Date_Sa_T.setCellValueFactory((TreeTableColumn.CellDataFeatures<Staff, String> param) -> param.getValue().getValue().Date_S);
            JFXTreeTableColumn<Staff, String> P_Sa_F = new JFXTreeTableColumn<>("النسبة");
            P_Sa_F.setPrefWidth(100);
            P_Sa_F.setCellValueFactory((TreeTableColumn.CellDataFeatures<Staff, String> param) -> param.getValue().getValue().P_fa);
            JFXTreeTableColumn<Staff, String> Data_Update_Sa_F = new JFXTreeTableColumn<>("اخر تعديل");
            Data_Update_Sa_F.setPrefWidth(100);
            Data_Update_Sa_F.setCellValueFactory((TreeTableColumn.CellDataFeatures<Staff, String> param) -> param.getValue().getValue().Last_Update_S);
            ObservableList Li3 = mysql.Table_FX8("SELECT * FROM `staff`");
            final TreeItem<Staff> root3 = new RecursiveTreeItem<Staff>(Li3, RecursiveTreeObject::getChildren);
            T_Sta.getColumns().setAll(ID_Sa_T, Name_Sa_T, Phone_Sa_T, Email_Sa_T, Date_Sa_T, P_Sa_F, Data_Update_Sa_F);
            T_Sta.setRoot(root3);
            T_Sta.setShowRoot(false);

            Name_S.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                T_Sta.setPredicate((TreeItem<Staff> t) -> {
                    Boolean flag = t.getValue().Name_S.getValue().contains(newValue);
                    return flag;
                });
            });

            Amount_Su.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                if (!newValue.matches("^[-+]?\\d+(\\.\\d+)?$")) {
                    Platform.runLater(() -> {
                        Amount_Su.setText("0");
                    });
                }
            });

            Count_G.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                if (!newValue.matches("^[-+]?\\d+(\\.\\d+)?$")) {
                    Platform.runLater(() -> {
                        Count_G.setText(oldValue);
                    });
                } else {
                    if (!Update_S1.isDisable() && !ID_Su_S.isEmpty()) {
                        Platform.runLater(() -> {
                            if (Integer.parseInt(newValue) > Integer.parseInt(oldValue)) {

                                for (int x = T_G.getItems().size(); x < Integer.parseInt(newValue); x++) {
                                    Day1_C = new ComboBox<>();
                                    Day1_C.getItems().clear();
                                    Day1_C.getItems().add("saturday");
                                    Day1_C.getItems().add("sunday");
                                    Day1_C.getItems().add("monday");
                                    Day1_C.getSelectionModel().select(0);
                                    Day2_C = new ComboBox<>();
                                    Day2_C.getItems().clear();
                                    Day2_C.getItems().add("tuesday");
                                    Day2_C.getItems().add("wednesday");
                                    Day2_C.getItems().add("thursday");
                                    Day2_C.getSelectionModel().select(0);
                                    Hours_C = new ComboBox<>();
                                    Hours_C.getItems().clear();
                                    for (int x1 = 01; x1 <= 24; x1++) {
                                        Hours_C.getItems().add(String.valueOf(x1));
                                    }
                                    Hours_C.getSelectionModel().select(0);
                                    Group group = new Group("F" + (x + 1), "100", Day1_C, Day2_C, Hours_C);
                                    T_G.getItems().add(group);
                                }

                            } else if (Integer.parseInt(newValue) < Integer.parseInt(oldValue)) {

                                Count_G.setText(oldValue);

                            }
                        });
                    } else {
                        T_G.getItems().clear();
                        for (int x = 0; x < Integer.parseInt(newValue); x++) {
                            Day1_C = new ComboBox<>();
                            Day1_C.getItems().clear();
                            Day1_C.getItems().add("saturday");
                            Day1_C.getItems().add("sunday");
                            Day1_C.getItems().add("monday");
                            Day1_C.getSelectionModel().select(0);
                            Day2_C = new ComboBox<>();
                            Day2_C.getItems().clear();
                            Day2_C.getItems().add("tuesday");
                            Day2_C.getItems().add("wednesday");
                            Day2_C.getItems().add("thursday");
                            Day2_C.getSelectionModel().select(0);
                            Hours_C = new ComboBox<>();
                            Hours_C.getItems().clear();
                            for (int x1 = 01; x1 <= 24; x1++) {
                                Hours_C.getItems().add(String.valueOf(x1));
                            }
                            Hours_C.getSelectionModel().select(0);
                            Group group = new Group("F" + (x + 1), "100", Day1_C, Day2_C, Hours_C);
                            T_G.getItems().add(group);
                        }

                    }
                }
            });

            Count_GR.setCellFactory(TextFieldTableCell.forTableColumn());
            Count_GR.setOnEditCommit((TableColumn.CellEditEvent<Group, String> event) -> {
                int row = event.getTablePosition().getRow();
                event.getTableView().getItems().get(row).setCount_G(event.getNewValue());
                if (!Update_S1.isDisable() && event.getTableView().getItems().get(row).ID_G.charAt(0) != 'F') {

                    boolean Run = mysql.Run("UPDATE `group_subject` SET `Count_Limit_GR`='" + event.getNewValue() + "' WHERE `ID_GR`=" + event.getTableView().getItems().get(row).ID_G);

                    if (Run) {
                        Tools.Tools_JavaFX.Alert_T(Alert.AlertType.CONFIRMATION, "عدد الطلاب", "تم تعديل عدد الطلاب", "بنجاح");
                    }

                }
            });

            JFXTreeTableColumn<Subject, String> ID_Su_T = new JFXTreeTableColumn<>("كود");
            ID_Su_T.setPrefWidth(75);
            ID_Su_T.setCellValueFactory((TreeTableColumn.CellDataFeatures<Subject, String> param) -> param.getValue().getValue().ID_su);
            JFXTreeTableColumn<Subject, String> Name_Su_T = new JFXTreeTableColumn<>("اسم");
            Name_Su_T.setPrefWidth(100);
            Name_Su_T.setCellValueFactory((TreeTableColumn.CellDataFeatures<Subject, String> param) -> param.getValue().getValue().Name_su);
            JFXTreeTableColumn<Subject, String> Date_Su_T = new JFXTreeTableColumn<>("التاريخ");
            Date_Su_T.setPrefWidth(100);
            Date_Su_T.setCellValueFactory((TreeTableColumn.CellDataFeatures<Subject, String> param) -> param.getValue().getValue().Date_su);
            JFXTreeTableColumn<Subject, String> Amount_Su_T = new JFXTreeTableColumn<>("المبلغ");
            Amount_Su_T.setPrefWidth(100);
            Amount_Su_T.setCellValueFactory((TreeTableColumn.CellDataFeatures<Subject, String> param) -> param.getValue().getValue().amount_su);
            JFXTreeTableColumn<Subject, String> Count_G_Su_T = new JFXTreeTableColumn<>("عددالمجميع");
            Count_G_Su_T.setPrefWidth(100);
            Count_G_Su_T.setCellValueFactory((TreeTableColumn.CellDataFeatures<Subject, String> param) -> param.getValue().getValue().Count_G_su);
            JFXTreeTableColumn<Subject, String> Name_ST_Su_F = new JFXTreeTableColumn<>("اسم المدرس");
            Name_ST_Su_F.setPrefWidth(100);
            Name_ST_Su_F.setCellValueFactory((TreeTableColumn.CellDataFeatures<Subject, String> param) -> param.getValue().getValue().Name_ST);
            JFXTreeTableColumn<Subject, String> Name_Y = new JFXTreeTableColumn<>("الفرقة");
            Name_Y.setPrefWidth(100);
            Name_Y.setCellValueFactory((TreeTableColumn.CellDataFeatures<Subject, String> param) -> param.getValue().getValue().Years_su);
            JFXTreeTableColumn<Subject, String> Name_UN_S_T = new JFXTreeTableColumn<>("الجامعة");
            Name_UN_S_T.setPrefWidth(100);
            Name_UN_S_T.setCellValueFactory((TreeTableColumn.CellDataFeatures<Subject, String> param) -> param.getValue().getValue().UN_Name_su);
            ObservableList Li4 = mysql.Table_FX9("SELECT * FROM `subject_v`");
            final TreeItem<Subject> root4 = new RecursiveTreeItem<Subject>(Li4, RecursiveTreeObject::getChildren);
            T_Su.getColumns().setAll(ID_Su_T, Name_Su_T, Date_Su_T, Amount_Su_T, Count_G_Su_T, Name_ST_Su_F, Name_Y, Name_UN_S_T);
            T_Su.setRoot(root4);
            T_Su.setShowRoot(false);
            P_S.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                if (!newValue.matches("^[-+]?\\d+(\\.\\d+)?$")) {
                    Platform.runLater(() -> {
                        P_S.setText("0.0");
                    });
                }
            });

        } catch (SQLException ex) {
            Logger.getLogger(LevelsController.class.getName()).log(Level.SEVERE, null, ex);
        }

        Platform.runLater(() -> {
            Name_UN.requestFocus();
        });

    }

    @FXML
    void ADD_FA(ActionEvent event) {

        if (!Faclty.getText().isEmpty() && !UN_COM.getSelectionModel().isEmpty()) {
            String ID_UN1 = (String) mysql.PrintTable("SELECT `ID_UN` FROM `universty` WHERE `Name_UN`='" + UN_COM.getSelectionModel().getSelectedItem() + "'").Table[0][0];

            boolean Run = mysql.Run("INSERT INTO `faculty`(`Name_FA`,`ID_UN`) VALUES ('" + Faclty.getText() + "'," + ID_UN1 + ")");

            if (Run) {

                Tools.Tools_JavaFX.Alert_T(Alert.AlertType.CONFIRMATION, "اضافة جامعة", "تم اضافة اسم الجامعة", "بنجاح" + " " + Name_UN.getText());
                Faclty.setText("");
                initialize(null, null);

            }

        }

    }

    @FXML
    void Exit(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }

    @FXML
    void ADD_FA1(ActionEvent event) {

        if (!year.getText().isEmpty() && !UN_COM1.getSelectionModel().isEmpty() && !FA_Com.getSelectionModel().isEmpty()) {
            String ID_Ye = (String) mysql.PrintTable("SELECT `ID_FA` FROM `faculty` WHERE `Name_FA`='" + FA_Com.getSelectionModel().getSelectedItem() + "' and `ID_UN`=" + ID_UNM).Table[0][0];
            boolean Run = mysql.Run("INSERT INTO `years`( `Name_YE`, `ID_FA`) VALUES ('" + year.getText() + "'," + ID_Ye + ")");
            if (Run) {

                Tools.Tools_JavaFX.Alert_T(Alert.AlertType.CONFIRMATION, "اضافة الفرقة", "تم اضافة اسم الفرقة", "بنجاح" + " " + year.getText());
                year.setText("");
                initialize(null, null);

            }

        }

    }

    @FXML
    void ADD_S(ActionEvent event) {

        if (!Name_S.getText().isEmpty() && !Phone_S.getText().isEmpty() && !Email_S.getText().isEmpty() && !P_S.getText().isEmpty()) {

            boolean Run = mysql.Run("INSERT INTO `staff`( `Name_SF`, `Phone_SF`, `Email`, `Date_SF`, `Percentage_SF`) VALUES ('" + Name_S.getText() + "','" + Phone_S.getText() + "','" + Email_S.getText() + "',Now(),'" + P_S.getText() + "')");

            if (Run) {

                Tools.Tools_JavaFX.Alert_T(Alert.AlertType.CONFIRMATION, "اضافة المدرس", "تم اضافة اسم المدرس", "بنجاح" + " " + Name_S.getText());
                Name_S.setText("");
                Phone_S.setText("");
                Email_S.setText("");
                initialize(null, null);

            }

        }

    }

    @FXML
    void ADD_S1(ActionEvent event) {

        if (!Name_Su.getText().isEmpty() && Integer.parseInt(Amount_Su.getText()) > 0 && Integer.parseInt(Count_G.getText()) > 0 && !YE_Com11.getSelectionModel().getSelectedItem().isEmpty() && !ST_COM.getSelectionModel().getSelectedItem().isEmpty()) {
            String ID_Ye = (String) mysql.PrintTable("SELECT `ID_YE` FROM `years` WHERE `Name_YE`='" + YE_Com11.getSelectionModel().getSelectedItem() + "' and `ID_FA`=" + ID_Ye_M).Table[0][0];
            String ID_ST = (String) mysql.PrintTable("SELECT `ID_SF` FROM `staff` WHERE `Name_SF`='" + ST_COM.getSelectionModel().getSelectedItem() + "'").Table[0][0];
            boolean Run = true;
            mysql.Run("INSERT INTO `subject`(`Name_SU`, `Amount_SU`, `Count_Group_SU`, `ID_SF`, `ID_YE`) VALUES ('" + Name_Su.getText() + "','" + Amount_Su.getText() + "','" + Count_G.getText() + "','" + ID_ST + "','" + ID_Ye + "')");
            if (Run) {

                for (int x = 0; x < Integer.parseInt(Count_G.getText()); x++) {

                    if (T_G.getItems().get(x).ID_G.charAt(0) == 'F') {
                        String ID_SU = (String) mysql.PrintTable("SELECT `ID_SU` FROM `subject`, years,faculty, universty WHERE subject.`Name_SU`='" + Name_Su.getText() + "' AND subject.ID_YE = years.ID_YE AND years.ID_FA = faculty.ID_FA AND faculty.ID_UN = universty.ID_UN AND universty.Name_UN ='" + UN_COM11.getSelectionModel().getSelectedItem() + "'  AND faculty.Name_FA='" + FA_Com1.getSelectionModel().getSelectedItem() + "' AND years.Name_YE='" + YE_Com11.getSelectionModel().getSelectedItem() + "'").Table[0][0];
                        mysql.Run("INSERT INTO `group_subject`(`Count_Limit_GR`,`Date_GR`, `ID_SU`, `Day_Of_One`, `Day_Of_Tow`, `Day_Of_Time`) VALUES (" + T_G.getItems().get(x).Count_G + ",Now()," + ID_SU + ",'" + T_G.getItems().get(x).Day1_G.getSelectionModel().getSelectedItem() + "','" + T_G.getItems().get(x).Day2_G.getSelectionModel().getSelectedItem() + "','" + T_G.getItems().get(x).Time_G.getSelectionModel().getSelectedItem() + ":00:00')");
                    }

                }
                Tools.Tools_JavaFX.Alert_T(Alert.AlertType.CONFIRMATION, "اضافة المادة", "تم اضافة المادة", "بنجاح" + " " + Name_UN.getText());
                Name_Su.setText("");
                Count_G.setText("0");
                Amount_Su.setText("0");
                ID_Su_S = "";
                initialize(null, null);

            } else {
                Tools.Tools_JavaFX.Alert_T(Alert.AlertType.CONFIRMATION, "اضافة المادة", "لم يتم اضافة المادة", "تاكد من اسم المادة" + " " + Name_UN.getText());
                Name_Su.setText("");
                Count_G.setText("0");
                Amount_Su.setText("0");
                ID_Su_S = "";
                initialize(null, null);
            }
        } else {
            Tools.Tools_JavaFX.Alert_T(Alert.AlertType.CONFIRMATION, "اضافة المادة", "هناك مشكلة فى المدخلات", "تاكد من المدخلات" + " " + Name_UN.getText());
            Name_Su.setText("");
            Count_G.setText("0");
            Amount_Su.setText("0");
            ID_Su_S = "";
            initialize(null, null);
        }

    }

    @FXML
    void ADD_UN(ActionEvent event) {

        if (!Name_UN.getText().isEmpty()) {

            boolean Run = mysql.Run("INSERT INTO `universty`(`Name_UN`) VALUES ('" + Name_UN.getText() + "')");

            if (Run) {

                Tools.Tools_JavaFX.Alert_T(Alert.AlertType.CONFIRMATION, "اضافة جامعة", "تم اضافة اسم الجامعة", "بنجاح" + " " + Name_UN.getText());
                Name_UN.setText("");
                initialize(null, null);

            }

        }

    }

    @FXML
    void Delete_FA(ActionEvent event) {

        if (!Faclty.getText().isEmpty() && !ID_FC_S.isEmpty() && !UN_COM.getSelectionModel().isEmpty()) {

            boolean Run = mysql.Run("DELETE FROM `faculty` WHERE `ID_FA`=" + ID_FC_S);

            try {
                if (Run) {

                    Tools.Tools_JavaFX.Alert_T(Alert.AlertType.CONFIRMATION, "مسح الكلية", "تم مسح اسم الكلية", "بنجاح" + " " + Name_UN.getText());
                    Faclty.setText("");
                    ID_FC_S = "";
                    initialize(null, null);

                }
            } catch (Exception e) {

                Tools.Tools_JavaFX.Alert_T(Alert.AlertType.CONFIRMATION, "مسح الكلية", "لم نستطيع مسح الكلية ", "بسبب ان هناك داتا مرتبطة بالكلية" + " " + Name_UN.getText());
                Faclty.setText("");
                ID_FC_S = "";
                initialize(null, null);

            }

        }

    }

    @FXML
    void Delete_FA1(ActionEvent event) {

        if (!year.getText().isEmpty() && !ID_YE_S.isEmpty() && !UN_COM1.getSelectionModel().isEmpty() && !FA_Com.getSelectionModel().isEmpty()) {

            boolean Run = mysql.Run("DELETE FROM `years` WHERE `ID_YE`=" + ID_YE_S);

            try {
                if (Run) {

                    Tools.Tools_JavaFX.Alert_T(Alert.AlertType.CONFIRMATION, "مسح الفرقة", "تم مسح اسم الفرقة", "بنجاح" + " " + year.getText());
                    year.setText("");
                    ID_YE_S = "";
                    initialize(null, null);

                }
            } catch (Exception e) {

                Tools.Tools_JavaFX.Alert_T(Alert.AlertType.CONFIRMATION, "مسح الفرقة", "لم نستطيع مسح الفرقة ", "بسبب ان هناك داتا مرتبطة الفرقة" + " " + year.getText());
                year.setText("");
                ID_YE_S = "";
                initialize(null, null);

            }

        }

    }

    @FXML
    void Delete_S(ActionEvent event) {

        if (!Name_S.getText().isEmpty() && !Phone_S.getText().isEmpty() && !Email_S.getText().isEmpty() && !P_S.getText().isEmpty() && !ID_ST_S.isEmpty()) {

            boolean Run = mysql.Run("DELETE FROM `staff` WHERE `ID_SF`=" + ID_ST_S);
            try {
                if (Run) {

                    Tools.Tools_JavaFX.Alert_T(Alert.AlertType.CONFIRMATION, "مسح المدرس", "تم مسح اسم المدرس", "بنجاح" + " " + Name_S.getText());
                    Name_S.setText("");
                    Phone_S.setText("");
                    Email_S.setText("");
                    ID_ST_S = "";
                    initialize(null, null);

                }
            } catch (Exception e) {

                Tools.Tools_JavaFX.Alert_T(Alert.AlertType.CONFIRMATION, "مسح المدرس", "لم نستطيع مسح المدرس ", "بسبب ان هناك داتا مرتبطة بالمدرس" + " " + Name_S.getText());
                Name_S.setText("");
                Phone_S.setText("");
                Email_S.setText("");
                ID_ST_S = "";
                initialize(null, null);

            }

        }

    }

    @FXML
    void Delete_UN(ActionEvent event) {

        if (!Name_UN.getText().isEmpty() && !ID_UN_S.isEmpty()) {

            boolean Run = mysql.Run("DELETE FROM `universty` WHERE `ID_UN`=" + ID_UN_S);

            try {
                if (Run) {

                    Tools.Tools_JavaFX.Alert_T(Alert.AlertType.CONFIRMATION, "مسح جامعة", "تم مسح اسم الجامعة", "بنجاح" + " " + Name_UN.getText());
                    Name_UN.setText("");
                    ID_UN_S = "";
                    initialize(null, null);

                }
            } catch (Exception e) {

                Tools.Tools_JavaFX.Alert_T(Alert.AlertType.CONFIRMATION, "مسح جامعة", "لم نستطيع مسح الجامعة ", "بسبب ان هناك داتا مرتبطة بالجامعة" + " " + Name_UN.getText());
                Name_UN.setText("");
                ID_UN_S = "";
                initialize(null, null);

            }

        }

    }

    @FXML
    void Update_FA(ActionEvent event) {

        if (!Faclty.getText().isEmpty() && !ID_FC_S.isEmpty() && !UN_COM.getSelectionModel().isEmpty()) {
            String ID_UN1 = (String) mysql.PrintTable("SELECT `ID_UN` FROM `universty` WHERE `Name_UN`='" + UN_COM.getSelectionModel().getSelectedItem() + "'").Table[0][0];
            boolean Run = mysql.Run("UPDATE `faculty` SET `Name_FA`='" + Faclty.getText() + "',`ID_UN`=" + ID_UN1 + " WHERE `ID_FA`=" + ID_FC_S);
            if (Run) {

                Tools.Tools_JavaFX.Alert_T(Alert.AlertType.CONFIRMATION, "تعديل الكلية", "تم تعديل اسم الكلية", "بنجاح" + " " + Faclty.getText());
                Faclty.setText("");
                ID_FC_S = "";
                initialize(null, null);

            }

        }

    }

    @FXML
    void Update_FA1(ActionEvent event) {

        if (!year.getText().isEmpty() && !ID_YE_S.isEmpty() && !UN_COM1.getSelectionModel().isEmpty() && !FA_Com.getSelectionModel().isEmpty()) {
            String ID_UN1 = (String) mysql.PrintTable("SELECT `ID_FA` FROM `faculty` WHERE `Name_FA`='" + FA_Com.getSelectionModel().getSelectedItem() + "' and `ID_UN`=" + ID_UNM).Table[0][0];
            boolean Run = mysql.Run("UPDATE `years` SET `Name_YE`='" + year.getText() + "',`ID_FA`=" + ID_UN1 + " WHERE `ID_YE`=" + ID_YE_S);
            if (Run) {

                Tools.Tools_JavaFX.Alert_T(Alert.AlertType.CONFIRMATION, "تعديل الفرقة", "تم تعديل اسم الفرقة", "بنجاح" + " " + Faclty.getText());
                year.setText("");
                ID_YE_S = "";
                initialize(null, null);

            }

        }

    }

    @FXML
    void Update_S(ActionEvent event) {
        if (!Name_S.getText().isEmpty() && !Phone_S.getText().isEmpty() && !Email_S.getText().isEmpty() && !P_S.getText().isEmpty() && !ID_ST_S.isEmpty()) {

            boolean Run = mysql.Run("UPDATE `staff` SET `Name_SF`='" + Name_S.getText() + "',`Phone_SF`='" + Phone_S.getText() + "',`Email`='" + Email_S.getText() + "',`Percentage_SF`='" + P_S.getText() + "' WHERE `ID_SF`=" + ID_ST_S);

            if (Run) {

                Tools.Tools_JavaFX.Alert_T(Alert.AlertType.CONFIRMATION, "اضافة المدرس", "تم اضافة اسم المدرس", "بنجاح" + " " + Name_S.getText());
                Name_S.setText("");
                Phone_S.setText("");
                Email_S.setText("");
                ID_ST_S = "";
                initialize(null, null);

            }

        }

    }

    @FXML
    void Update_S1(ActionEvent event) {

        if (!Name_Su.getText().isEmpty() && Integer.parseInt(Amount_Su.getText()) > 0 && Integer.parseInt(Count_G.getText()) > 0 && !YE_Com11.getSelectionModel().getSelectedItem().isEmpty() && !ST_COM.getSelectionModel().getSelectedItem().isEmpty()) {
            String ID_ST = (String) mysql.PrintTable("SELECT `ID_SF` FROM `staff` WHERE `Name_SF`='" + ST_COM.getSelectionModel().getSelectedItem() + "'").Table[0][0];
            boolean Run = mysql.Run("UPDATE `subject` SET `Name_SU`='" + Name_Su.getText() + "',`Amount_SU`='" + Amount_Su.getText() + "',`Count_Group_SU`='" + Count_G.getText() + "',`ID_SF`=" + ID_ST + " WHERE `ID_SU`=" + ID_Su_S);
            if (Run) {

                for (int x = 0; x < T_G.getItems().size(); x++) {

                    if (T_G.getItems().get(x).ID_G.charAt(0) == 'F') {
                        String ID_SU = (String) mysql.PrintTable("SELECT `ID_SU` FROM `subject`, years,faculty, universty WHERE subject.`Name_SU`='" + Name_Su.getText() + "' AND subject.ID_YE = years.ID_YE AND years.ID_FA = faculty.ID_FA AND faculty.ID_UN = universty.ID_UN AND universty.Name_UN ='" + UN_COM11.getSelectionModel().getSelectedItem() + "'  AND faculty.Name_FA='" + FA_Com1.getSelectionModel().getSelectedItem() + "' AND years.Name_YE='" + YE_Com11.getSelectionModel().getSelectedItem() + "'").Table[0][0];
                        mysql.Run("INSERT INTO `group_subject`(`Count_Limit_GR`,`Date_GR`, `ID_SU`, `Day_Of_One`, `Day_Of_Tow`, `Day_Of_Time`) VALUES (" + T_G.getItems().get(x).Count_G + ",Now()," + ID_SU + ",'" + T_G.getItems().get(x).Day1_G.getSelectionModel().getSelectedItem() + "','" + T_G.getItems().get(x).Day2_G.getSelectionModel().getSelectedItem() + "','" + T_G.getItems().get(x).Time_G.getSelectionModel().getSelectedItem() + ":00:00')");
                    } else {
                        String ID_SU = (String) mysql.PrintTable("SELECT `ID_SU` FROM `subject`, years,faculty, universty WHERE subject.`Name_SU`='" + Name_Su.getText() + "' AND subject.ID_YE = years.ID_YE AND years.ID_FA = faculty.ID_FA AND faculty.ID_UN = universty.ID_UN AND universty.Name_UN ='" + UN_COM11.getSelectionModel().getSelectedItem() + "'  AND faculty.Name_FA='" + FA_Com1.getSelectionModel().getSelectedItem() + "' AND years.Name_YE='" + YE_Com11.getSelectionModel().getSelectedItem() + "'").Table[0][0];
                        mysql.Run("UPDATE `group_subject` SET `Count_Limit_GR`=" + T_G.getItems().get(x).Count_G + ",`ID_SU`=" + ID_SU + ",`Day_Of_One`='" + T_G.getItems().get(x).Day1_G.getSelectionModel().getSelectedItem() + "',`Day_Of_Tow`='" + T_G.getItems().get(x).Day2_G.getSelectionModel().getSelectedItem() + "',`Day_Of_Time`='" + T_G.getItems().get(x).Time_G.getSelectionModel().getSelectedItem() + ":00:00' WHERE `ID_GR`=" + T_G.getItems().get(x).ID_G);
                    }

                }

                Tools.Tools_JavaFX.Alert_T(Alert.AlertType.CONFIRMATION, "اضافة المادة", "تم اضافة المادة", "بنجاح" + " " + Name_UN.getText());
                Name_Su.setText("");
                Amount_Su.setText("0");
                ID_Su_S = "";
                Count_G.setText("0");
                initialize(null, null);

            }
        }

    }

    @FXML
    void Update_UN(ActionEvent event) {

        if (!Name_UN.getText().isEmpty() && !ID_UN_S.isEmpty()) {

            boolean Run = mysql.Run("UPDATE `universty` SET `Name_UN`='" + Name_UN.getText() + "' WHERE `ID_UN`=" + ID_UN_S);

            if (Run) {

                Tools.Tools_JavaFX.Alert_T(Alert.AlertType.CONFIRMATION, "تعديل جامعة", "تم تعديل اسم الجامعة", "بنجاح" + " " + Name_UN.getText());
                Name_UN.setText("");
                ID_UN_S = "";
                initialize(null, null);

            }

        }

    }

    @FXML
    void select_T1(MouseEvent event) {

        try {

            TreeItem<Universty> selectedItem = T_UN.getSelectionModel().getSelectedItem();
            ID_UN_S = selectedItem.getValue().ID_UN.get();
            Name_UN.setText(selectedItem.getValue().Name_Un.get());
            ADD_UN.setDisable(true);
            Update_UN.setDisable(false);
            Delete_UN.setDisable(false);

        } catch (Exception e) {

        }

    }

    @FXML
    void select_T2(MouseEvent event) {

        try {

            TreeItem<faculty> selectedItem = T_Fa.getSelectionModel().getSelectedItem();
            ID_FC_S = selectedItem.getValue().ID_Fa.get();
            UN_COM.getSelectionModel().select(selectedItem.getValue().Name_Un.get());
            Faclty.setText(selectedItem.getValue().Name_Fa.get());
            ADD_FA.setDisable(true);
            Update_FA.setDisable(false);
            Delete_FA.setDisable(false);

        } catch (Exception e) {

        }

    }

    @FXML
    void select_T3(MouseEvent event) {
        try {

            TreeItem<years> selectedItem = T_Ye.getSelectionModel().getSelectedItem();
            ID_YE_S = selectedItem.getValue().ID_ye.get();
            String name = (String) mysql.PrintTable("SELECT `Name_UN` FROM `universty` WHERE `ID_UN` = (SELECT faculty.ID_UN from faculty WHERE faculty.Name_FA ='" + selectedItem.getValue().Name_fa.get() + "')").Table[0][0];
            UN_COM1.getSelectionModel().select(name);
            FA_Com.getSelectionModel().select(selectedItem.getValue().Name_fa.get());
            year.setText(selectedItem.getValue().Name_ye.get());
            ADD_FA1.setDisable(true);
            Update_FA1.setDisable(false);
            Delete_FA1.setDisable(false);

        } catch (Exception e) {

        }
    }

    @FXML
    void select_T4(MouseEvent event) {
        try {

            TreeItem<Staff> selectedItem = T_Sta.getSelectionModel().getSelectedItem();
            ID_ST_S = selectedItem.getValue().ID_S.get();
            Name_S.setText(selectedItem.getValue().Name_S.get());
            Phone_S.setText(selectedItem.getValue().Phone_S.get());
            Email_S.setText(selectedItem.getValue().Email_S.get());
            P_S.setText(selectedItem.getValue().P_fa.get());
            ADD_S.setDisable(true);
            Update_S.setDisable(false);
            Delete_S.setDisable(false);

        } catch (Exception e) {

        }
    }

    @FXML
    void select_T5(MouseEvent event) {

        try {

            TreeItem<Subject> selectedItem = T_Su.getSelectionModel().getSelectedItem();
            String univ = (String) mysql.PrintTable("SELECT * FROM `all_years` WHERE `ID_SU`='" + selectedItem.getValue().ID_su.get() + "'").Table[0][0];
            String fa = (String) mysql.PrintTable("SELECT * FROM `all_years` WHERE `ID_SU`='" + selectedItem.getValue().ID_su.get() + "'").Table[0][1];
            String ye = (String) mysql.PrintTable("SELECT * FROM `all_years` WHERE `ID_SU`='" + selectedItem.getValue().ID_su.get() + "'").Table[0][2];
            UN_COM11.getSelectionModel().select(univ);
            FA_Com1.getSelectionModel().select(fa);
            YE_Com11.getSelectionModel().select(ye);
            Name_Su.setText(selectedItem.getValue().Name_su.get());
            Amount_Su.setText(selectedItem.getValue().amount_su.get());
            Count_G.setText("0");
            Count_G.setText(selectedItem.getValue().Count_G_su.get());
            ST_COM.getSelectionModel().select(selectedItem.getValue().Name_ST.get());
            ID_Su_S = selectedItem.getValue().ID_su.get();
            ADD_S1.setDisable(true);
            Update_S1.setDisable(false);
            YE_Com11.setDisable(true);
            ObservableList Table_FX10 = mysql.Table_FX10("SELECT `ID_GR`, `Count_Limit_GR`, `Day_Of_One`, `Day_Of_Tow`, HOUR(`Day_Of_Time`) FROM `group_subject` WHERE `ID_SU`=" + ID_Su_S);
            for (int x = 0; x < Integer.parseInt(selectedItem.getValue().Count_G_su.get()); x++) {

                String[] s = (String[]) Table_FX10.get(x);
                T_G.getItems().get(x).setID_G(s[0]);
                T_G.getItems().get(x).setCount_G(s[1]);
                T_G.getItems().get(x).setDay1_G_S(s[2]);
                T_G.getItems().get(x).setDay2_G_S(s[3]);
                T_G.getItems().get(x).settime_S(s[4]);

            }

        } catch (SQLException ex) {
        }

    }

    @FXML
    void select_G(MouseEvent event) {

    }

    @FXML
    void Refrech(ActionEvent event) {
        initialize(null, null);
    }

}
