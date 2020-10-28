/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Add_Student;

import IReport.IReport;
import Tools.API_SMS_MISR;
import Tools.mysql;
import center.elgam3a.User;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXRadioButton;
import com.jfoenix.controls.JFXTabPane;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.controlsfx.control.ListSelectionView;
import org.controlsfx.control.Notifications;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 * FXML Controller class
 *
 * @author goda4
 */
public class ADD_STController implements Initializable {

    /**
     * Initializes the controller class.
     */
    public String ID;
    @FXML
    private VBox VBX;

    @FXML
    private JFXTextField Name;

    @FXML
    private JFXTextField Phone;

    @FXML
    private JFXComboBox Unicersty;

    @FXML
    private JFXComboBox Faclty;

    @FXML
    private JFXComboBox Years;

    @FXML
    private JFXRadioButton Male;

    @FXML
    private ToggleGroup G1;

    @FXML
    private JFXRadioButton Female;

    @FXML
    private TableView<Student> Tree;

    @FXML
    private ListSelectionView<Label> Subject;
    @FXML
    private JFXTextField ID_Subject;

    @FXML
    private JFXTextField ID_Amount;

    @FXML
    private JFXButton Subject_bu;

    @FXML
    private JFXButton Delete;

    @FXML
    private JFXButton Update;

    @FXML
    private JFXTabPane tab;

    @FXML
    private AnchorPane Win;

    @FXML
    private JFXButton ADD;

    @FXML
    private VBox VB_Loader;

    @FXML
    private VBox VB_Loader1;

    @FXML
    private VBox VB_Loader2;

    @FXML
    private JFXTextField Nodes;

    @FXML
    private Label L1;
    @FXML
    private Label L2;
    String ID_UN;
    String ID_Ye;
    ObservableList Li;

    public void clear() {
        try {
            Platform.runLater(() -> {
                Name.requestFocus();
            });
            Unicersty.getSelectionModel().clearSelection();
            Faclty.getSelectionModel().clearSelection();
            Years.getSelectionModel().clearSelection();
            Name.clear();
            Phone.clear();
            ID = "";
            ID_Amount.setText("0");
            ID_Subject.setText("0");
            Subject.getSourceItems().clear();
            Subject.getTargetItems().clear();
            Subject_bu.setDisable(true);
            Delete.setDisable(true);
            Update.setDisable(true);
            ADD.setDisable(false);

            Li = mysql.Table_FX1("SELECT `ID_ST`, `Name_ST`, Phone_ST , `Date_ST`, `Date_Last_ST`, `Type_Of_ST`, `Universty` , Email , address FROM `students` ORDER BY `students`.`Date_Last_ST` DESC");
            FilteredList<Student> filData = new FilteredList<>(Li, p -> true);
            Name.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                filData.setPredicate(Student -> {
                    Boolean flag = Student.Name_ST.getValue().contains(newValue) || Student.ID_ST.getValue().contains(newValue);
                    return flag;
                });

            });

            Phone.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                filData.setPredicate(Student -> {
                    Boolean flag = Student.Phone_ST.getValue().contains(newValue);
                    return flag;
                });
            });
            SortedList<Student> sortedData = new SortedList<>(filData);
            sortedData.comparatorProperty().bind(Tree.comparatorProperty());
            Tree.setItems(sortedData);

        } catch (SQLException ex) {
            System.out.println(ex);
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        final KeyCombination ADD_Key = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
        final KeyCombination Update_Key = new KeyCodeCombination(KeyCode.U, KeyCombination.CONTROL_DOWN);
        final KeyCombination Delete_Key = new KeyCodeCombination(KeyCode.D, KeyCombination.CONTROL_DOWN);
        final KeyCombination Res_Key = new KeyCodeCombination(KeyCode.R, KeyCombination.CONTROL_DOWN);
        final KeyCombination Plus_Key = new KeyCodeCombination(KeyCode.PLUS, KeyCombination.CONTROL_DOWN);
        final KeyCombination Tab1_Key = new KeyCodeCombination(KeyCode.DIGIT1, KeyCombination.CONTROL_DOWN);
        final KeyCombination Tab2_Key = new KeyCodeCombination(KeyCode.DIGIT2, KeyCombination.CONTROL_DOWN);
        final KeyCombination Tab3_Key = new KeyCodeCombination(KeyCode.DIGIT3, KeyCombination.CONTROL_DOWN);
        VBX.addEventFilter(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {

            if (tab.getSelectionModel().isSelected(0)) {
                if (ADD_Key.match(event)) {
                    if (!ADD.isDisable()) {
                        ADD_Student(new ActionEvent());
                    }

                } else if (Update_Key.match(event)) {
                    if (!Update.isDisable()) {
                        Update_Student(new ActionEvent());
                    }
                } else if (Delete_Key.match(event)) {
                    if (!Delete.isDisable()) {
                        Delete_Student(new ActionEvent());
                    }
                } else if (Res_Key.match(event)) {
                    if (!Subject_bu.isDisable()) {
                        New_Student(new ActionEvent());
                    }
                }
            } else if (tab.getSelectionModel().isSelected(1)) {
                if (ADD_Key.match(event)) {
                    Save(new ActionEvent());
                }
            } else if (tab.getSelectionModel().isSelected(2)) {
                if (ADD_Key.match(event)) {
                    Save_Amount(new ActionEvent());
                }
            }

            if (Tab1_Key.match(event)) {
                tab.getSelectionModel().select(0);
                Name.requestFocus();
            } else if (Tab2_Key.match(event)) {
                tab.getSelectionModel().select(1);
                ID_Subject.requestFocus();
            } else if (Tab3_Key.match(event)) {
                tab.getSelectionModel().select(2);
                ID_Amount.requestFocus();
            }

            if (Plus_Key.match(event)) {
                String type = (String) mysql.PrintTable("SELECT COUNT(`ID_TGP`) FROM `tb_group_print` WHERE `Type`='Start'").Table[0][0];

                if (Integer.parseInt(type) == 0) {

                    boolean Run = mysql.Run("INSERT INTO `tb_group_print`(`Type`) VALUES ('Start')");

                    if (Run) {

                        Tools.Tools_JavaFX.Alert_T(AlertType.ERROR, "مجموعة الطباعة", "انشاء مجموعة", "تم انشاء مجموعة جديدة");

                    }

                } else {

                    Tools.Tools_JavaFX.Alert_T(AlertType.ERROR, "مجموعة الطباعة", "هناك مجموعة متاحة", "يرجو غلق المجموعة القديمة");

                }

            }
        });
        Subject.setTargetHeader(new Label("المواد المختاره"));
        Subject.setSourceHeader(new Label("المواد المتاحة"));
        Tools.Tools_JavaFX.Move_Stage(VBX);
        TableColumn<Student, String> ID_ST = new TableColumn<>("كود الطالب");
        ID_ST.setPrefWidth(75);
        ID_ST.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> param) -> param.getValue().ID_ST);
        TableColumn<Student, String> Name_ST = new TableColumn<>("اسم الطالب");
        Name_ST.setPrefWidth(200);
        Name_ST.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> param) -> param.getValue().Name_ST);
        TableColumn<Student, String> Phone_ST = new TableColumn<>("رقم التليفون");
        Phone_ST.setPrefWidth(150);
        Phone_ST.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> param) -> {
            String Phone_Castm = "********" + (param.getValue().Phone_ST.get()).substring(8, 11);
            return new SimpleStringProperty(Phone_Castm);
        });
        TableColumn<Student, String> Date_ST = new TableColumn<>("تاريخ التسجيل");
        Date_ST.setPrefWidth(100);
        Date_ST.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> param) -> param.getValue().Date_ST);
        TableColumn<Student, String> Date_UP_ST = new TableColumn<>("تاريخ اخر تعديل");
        Date_UP_ST.setPrefWidth(150);
        Date_UP_ST.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> param) -> param.getValue().Date_UP_ST);
        TableColumn<Student, String> Type_ST = new TableColumn<>("النوع");
        Type_ST.setPrefWidth(50);
        Type_ST.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> param) -> param.getValue().Type);
        TableColumn<Student, String> Year_ST = new TableColumn<>("الفرقة");
        Year_ST.setPrefWidth(250);
        Year_ST.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> param) -> param.getValue().Year);
        TableColumn<Student, String> Email_ST = new TableColumn<>("الاميل");
        Email_ST.setPrefWidth(250);
        Email_ST.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> param) -> param.getValue().Email);
        TableColumn<Student, String> Address_ST = new TableColumn<>("العنوان");
        Address_ST.setPrefWidth(250);
        Address_ST.setCellValueFactory((TableColumn.CellDataFeatures<Student, String> param) -> param.getValue().Address);
        Tree.getColumns().setAll(ID_ST, Name_ST, Phone_ST, Date_ST, Date_UP_ST, Type_ST, Year_ST, Email_ST, Address_ST);
        Faclty.setDisable(true);
        Years.setDisable(true);
        ADD.setDisable(false);
        Unicersty.valueProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                try {
                    if (!newValue.toString().isEmpty()) {

                        try {
                            ID_UN = (String) mysql.PrintTable("SELECT `ID_UN` FROM `universty` WHERE `Name_UN`='" + newValue + "'").Table[0][0];
                            Faclty.setDisable(false);
                            Years.setDisable(false);
                            Faclty.getSelectionModel().clearSelection();
                            Faclty.getItems().clear();
                            Years.getSelectionModel().clearSelection();
                            Years.getItems().clear();
                            mysql.Combo1("SELECT `Name_FA` FROM `faculty` WHERE ID_UN=" + ID_UN, Faclty);
                        } catch (Exception e) {
                            Tools.Tools_JavaFX.Alert_T(Alert.AlertType.ERROR, "الجامعات", "خطا فى اختيار الجامعة", "لا يوجد كليات بداخل هذه الجامعة");
                            Faclty.setDisable(true);
                            Years.setDisable(true);
                        }

                    }
                } catch (Exception e) {
                }
            }
        });
        Faclty.valueProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                try {
                    if (!newValue.toString().isEmpty()) {
                        try {
                            ID_Ye = (String) mysql.PrintTable("SELECT `ID_FA` FROM `faculty` WHERE `Name_FA`='" + newValue + "' and `ID_UN`=" + ID_UN).Table[0][0];
                            Years.setDisable(false);
                            Years.getSelectionModel().clearSelection();
                            Years.getItems().clear();
                            mysql.Combo1("SELECT `Name_YE` FROM `years` WHERE `ID_FA`=" + ID_Ye, Years);
                        } catch (Exception e) {
                            Tools.Tools_JavaFX.Alert_T(Alert.AlertType.ERROR, "الفرق", "خطا فى اختيار الفرق", "لا يوجد فرق بداخل هذه الكلية");
                            Years.setDisable(true);
                        }
                    }
                } catch (Exception e) {
                }
            }
        });
        mysql.Combo1("SELECT `Name_UN` FROM `universty`", Unicersty);
        Subject_bu.setDisable(true);
        Delete.setDisable(true);
        Update.setDisable(true);
        ID_Subject.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            L2.setText("");
            Subject.getSourceItems().clear();
            Subject.getTargetItems().clear();
            if (newValue.matches("^[-+]?\\d+(\\.\\d+)?$")) {

                ID_Subject.setOnKeyPressed((event) -> {
                    if (event.getCode() == KeyCode.ENTER) {
                        sub_V(newValue);
                    }
                });

            } else {
                ID_Subject.setText(oldValue);
            }
        });
        ID_Amount.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
            L1.setText("");
            Win.getChildren().clear();
            if (newValue.matches("^[-+]?\\d+(\\.\\d+)?$")) {
                ID_Amount.setOnKeyPressed((event) -> {

                    if (event.getCode() == KeyCode.ENTER) {
                        amount_V(newValue);
                    }

                });

            } else {
                Platform.runLater(() -> {
                    ID_Amount.setText(oldValue);
                });
            }
        });
        clear();
    }

    public void sub_V(String newValue) {
        if (!newValue.isEmpty()) {
            VB_Loader1.setVisible(true);
            Subject.getSourceItems().clear();
            Subject.getTargetItems().clear();
            try {
                String Name_S = (String) mysql.PrintTable("SELECT `Name_ST` FROM `student` WHERE `ID_ST`=" + newValue).Table[0][0];
                L2.setText(Name_S);
                mysql.ListViewSelect("SELECT subject.Name_SU from subject WHERE subject.ID_YE = (SELECT student.ID_YE from student WHERE student.ID_ST = " + newValue + ") AND subject.ID_SU not in (SELECT DISTINCT payment.ID_SU FROM payment WHERE payment.ID_ST = " + newValue + ")", Subject);
            } catch (Exception ex) {
                L2.setText("");
            }
            VB_Loader1.setVisible(false);

        } else {
            L2.setText("");
        }

    }

    public void amount_V(String newValue) {

        if (!newValue.isEmpty()) {
            VB_Loader.setVisible(true);
            try {
                String Count_Row = (String) mysql.PrintTable("SELECT count(`Name`) FROM `payment_student` WHERE `Student`=" + newValue).Table[0][0];
                Win.getChildren().clear();
                if (Integer.parseInt(Count_Row) > 0) {
                    try {
                        String Name_S = (String) mysql.PrintTable("SELECT `Name_ST` FROM `student` WHERE `ID_ST`=" + newValue).Table[0][0];
                        L1.setText(Name_S);
                        Win.setPrefHeight(Integer.parseInt(Count_Row) * 85);
                        VBox VB = new VBox();

                        for (int x = 0; x < Integer.parseInt(Count_Row); x++) {
                            JSONObject JO = mysql.GetData("SELECT`Name Subject` ,`Total`,`Date Loign`,`ID Group` FROM `payment_student` WHERE `Student`=" + newValue).getJSONObject(x);
                            String ID_SU_S = (String) mysql.PrintTable("SELECT `ID_SU` FROM `payment_student` WHERE `Student`=" + newValue).Table[x][0];
                            int coun = x;
                            new Thread() {
                                @Override
                                public void run() {
                                    String ID_Group;
                                    String subject = JO.getString("Name Subject");
                                    String Total = JO.getString("Total");
                                    String Date_Login = JO.getString("Date Loign");
                                    try {
                                        ID_Group = JO.getString("ID Group");
                                    } catch (Exception ex) {
                                        ID_Group = null;
                                    }

                                    HBox HB = new HBox();

                                    HB.setPrefHeight(50);
                                    HB.setPadding(new Insets(26, 10, 20, 20));
                                    if (coun % 2 != 0) {
                                        HB.setStyle("-fx-background-color : #95a5a6");
                                    } else {
                                        HB.setStyle("-fx-background-color : #bdc3c7");
                                    }
                                    CheckBox C = new CheckBox(subject);
                                    C.setPadding(new Insets(0, 10, 0, 0));
                                    C.setPrefHeight(35);
                                    C.setPrefWidth(130);

                                    JFXTextField TF = new JFXTextField("المبلغ");
                                    TF.setFont(new Font(15));
                                    TF.setText(Total);
                                    TF.setDisable(true);
                                    TF.setPadding(new Insets(0, 10, 0, 0));
                                    TF.setAlignment(Pos.CENTER);
                                    TF.setPrefHeight(35);
                                    TF.setPrefWidth(75);
                                    TF.setPromptText("المبلغ");
                                    TF.setLabelFloat(true);
                                    TF.setFocusColor(Color.WHITE);
                                    TF.setUnFocusColor(Color.WHITE);
                                    JFXTextField TF1 = new JFXTextField("التاريخ");
                                    TF1.setFont(new Font(15));
                                    TF1.setText(Date_Login);
                                    TF1.setDisable(true);
                                    TF1.setPadding(new Insets(0, 10, 0, 0));
                                    TF1.setAlignment(Pos.CENTER);
                                    TF1.setPrefHeight(35);
                                    TF1.setPromptText("التاريخ ");
                                    TF1.setLabelFloat(true);
                                    TF1.setFocusColor(Color.WHITE);
                                    TF1.setUnFocusColor(Color.WHITE);
                                    JFXComboBox CB = new JFXComboBox();
                                    mysql.Combo1("SELECT `ID_GR` FROM `group_subject` WHERE `ID_SU`=" + ID_SU_S, CB);
                                    CB.setDisable(true);
                                    CB.setPadding(new Insets(0, 10, 0, 0));
                                    CB.setPromptText("المجموعة");
                                    CB.setLabelFloat(true);
                                    CB.setFocusColor(Color.WHITE);
                                    CB.setUnFocusColor(Color.WHITE);
                                    Label L = new Label();
                                    CB.valueProperty().addListener((ObservableValue observable1, Object oldValue1, Object newValue1) -> {
                                        if (!newValue1.toString().isEmpty()) {
                                            try {
                                                L.setText((String) mysql.PrintTable("SELECT concat(`Day_Of_One`,' - ',`Day_Of_Tow`,' - ',`Day_Of_Time`) FROM `group_subject` WHERE `ID_GR`=" + newValue1).Table[0][0]);
                                            } catch (Exception e) {
                                            }

                                        }
                                    });
                                    CB.getSelectionModel().select(ID_Group);
                                    CB.setPrefHeight(35);
                                    HB.getChildren().addAll(C, TF, TF1, CB, L);
                                    HB.alignmentProperty().set(Pos.CENTER_LEFT);
                                    Platform.runLater(() -> {
                                        VB.getChildren().add(HB);
                                        VB.setAlignment(Pos.CENTER);
                                        VBox.setVgrow(HB, Priority.ALWAYS);
                                        VBox.setMargin(HB, new Insets(7, 10, 7, 10));
                                    });

                                    C.selectedProperty().addListener((ObservableValue<? extends Boolean> observable1, Boolean oldValue1, Boolean newValue1) -> {
                                        TF.setText("0");
                                        if (newValue1) {
                                            TF.setDisable(false);
                                            CB.setDisable(false);

                                        } else {
                                            TF.setDisable(true);
                                            CB.setDisable(true);
                                        }

                                    });
                                }
                            }.start();
                        }

                        Win.getChildren().add(VB);

                    } catch (NumberFormatException ex) {
                        L1.setText("");
                    }
                } else {
                    Win.getChildren().clear();
                    Win.setPrefHeight(50);
                }

            } catch (NumberFormatException ex) {
            }
            VB_Loader.setVisible(false);
            boolean Check1 = mysql.Check1("SELECT * FROM `nodes` WHERE `ID_ST`=" + newValue);
            if (Check1) {
                String node = (String) mysql.PrintTable("SELECT `Data` FROM `nodes` WHERE `ID_ST`=" + newValue).Table[0][0];
                Nodes.setText(node);
            } else {
                Nodes.setText("");
            }
        } else {

            L1.setText("");

        }

    }

    @FXML
    void ADD_Student(ActionEvent event) {
        VB_Loader2.setVisible(true);
        if (!(Name.getText().isEmpty()) && !(Phone.getText().isEmpty()) && !(Years.getSelectionModel().getSelectedItem().toString().isEmpty())) {

            if (Phone.getText().length() == 11) {

                String ID_ST = (String) mysql.PrintTable("SELECT `ID` FROM `id_available_v`").Table[0][0];
                String ID_YE = (String) mysql.PrintTable("SELECT `ID_YE` FROM `years` WHERE `Name_YE`='" + Years.getSelectionModel().getSelectedItem() + "' and `ID_FA`=" + ID_Ye).Table[0][0];
                String Type = "";

                if (Male.isSelected()) {
                    Type = "ولد";
                } else if (Female.isSelected()) {
                    Type = "بنت";
                }

                boolean Run = mysql.Run("INSERT INTO `student`(`ID_ST`, `Name_ST`, `Phone_ST`, `Date_ST`,`Type_Of_ST`, `ID_YE`) VALUES (" + ID_ST + ",'" + Name.getText() + "','" + Phone.getText() + "',Now(),'" + Type + "'," + ID_YE + ")");

                if (Run) {

                    Tools.Tools_JavaFX.Alert_T(Alert.AlertType.CONFIRMATION, "اضافة الطالب", "تم اضافة الطالب", "بنجاح و كود الطالب " + ID_ST);
                    String Phone_Api = Phone.getText();

                    new Thread() {
                        @Override
                        public void run() {
                            String Massage = "عام سعيد\n"
                                    + "كودك(" + ID_ST + ")\n"
                                    + "صفحة الفيس(https://cutt.us/L6z4B)\n"
                                    + "01000404123";
                            API_SMS_MISR AP = new API_SMS_MISR("7TLgq36R", "3fMMaB0OMX", "Elgam3a", 2, Phone_Api, Massage);
                            boolean call_me = AP.call_me();
                            if (call_me) {
                                Platform.runLater(() -> {
                                    Notifications.create().title("ارسال الرساله الترحيبية")
                                            .text("تم ارسال الرسالة بنجاح\n" + ID_ST)
                                            .showInformation();
                                });
                            }
                        }
                    }.start();

                    clear();
                    ID_Subject.setText(ID_ST);
                    sub_V(ID_ST);
                    tab.getSelectionModel().select(1);

                }

            } else {
                Tools.Tools_JavaFX.Alert_T(Alert.AlertType.ERROR, "اضافة طالب", "هناك خطا فى اضافة الطالب", "رقم الهاتف غير صحيح");
            }

        } else {
            Tools.Tools_JavaFX.Alert_T(Alert.AlertType.ERROR, "اضافة الطالب", "هناك خطا فى اضافة الطالب", "يرجوا ملئ كافة الحقول");
        }
        VB_Loader2.setVisible(false);

    }

    @FXML
    void Delete_Student(ActionEvent event) {

        Alert alert = new Alert(AlertType.CONFIRMATION);
        alert.setTitle("اضافة طالب");
        alert.setHeaderText("مسح الطالب");
        alert.setContentText("هل انت متاكد من مسح هذا الطالب");

        ButtonType buttonTypeOne = new ButtonType("نعم");
        ButtonType buttonTypeTwo = new ButtonType("لا");

        alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
        Optional<ButtonType> result = alert.showAndWait();

        if (result.get() == buttonTypeOne) {
            boolean Run = mysql.Run("DELETE FROM `student` WHERE `ID_ST`=" + ID);
            if (Run) {
                Tools.Tools_JavaFX.Alert_T(AlertType.CONFIRMATION, "حذف طالب", "تم حذف الطالب", "صاحب كود " + ID);
                ID = "";
                clear();
            }
        }

    }

    @FXML
    void Enter(KeyEvent event) {

    }

    @FXML
    void New_Student(ActionEvent event) {

        tab.getSelectionModel().select(1);
        String ID_I = ID;
        clear();
        ID_Subject.setText(ID_I);
        sub_V(ID_I);

    }

    @FXML
    void Update_Student(ActionEvent event) {
        VB_Loader2.setVisible(true);
        String Phone_Nu;

        if (!(Name.getText().isEmpty()) && !(Phone.getText().isEmpty()) && !(Years.getSelectionModel().getSelectedItem().toString().isEmpty())) {

            if ("***".equals(Phone.getText().substring(1, 4))) {

                Phone_Nu = (String) mysql.PrintTable("SELECT `Phone_ST` FROM `student` WHERE `ID_ST`=" + ID).Table[0][0];

            } else if (Phone.getText().length() == 11) {
                Phone_Nu = Phone.getText();
            } else {
                Tools.Tools_JavaFX.Alert_T(Alert.AlertType.ERROR, "اضافة طالب", "هناك خطا فى اضافة الطالب", "رقم الهاتف غير صحيح");
                return;
            }

            String ID_YE = (String) mysql.PrintTable("SELECT `ID_YE` FROM `years` WHERE `Name_YE`='" + Years.getSelectionModel().getSelectedItem() + "' and `ID_FA`=" + ID_Ye).Table[0][0];
            String Type = "";

            if (Male.isSelected()) {
                Type = "ولد";
            } else if (Female.isSelected()) {
                Type = "بنت";
            }

            boolean Run = mysql.Run("UPDATE `student` SET `Name_ST`='" + Name.getText() + "',`Phone_ST`='" + Phone_Nu + "',`Date_ST`=Now(),`Type_Of_ST`='" + Type + "',`ID_YE`='" + ID_YE + "' WHERE `ID_ST`=" + ID);

            if (Run) {

                Tools.Tools_JavaFX.Alert_T(Alert.AlertType.CONFIRMATION, "تعديل الطالب", "تم تعديل الطالب", "بنجاح و كود الطالب " + ID);
                String ID_S = ID;
                char if_c = Phone.getText().charAt(0);
                String Phone_Api = Phone.getText();
                new Thread() {
                    @Override
                    public void run() {
                        String Massage = "عام سعيد\n"
                                + "كودك(" + ID_S + ")\n"
                                + "صفحة الفيس(https://cutt.us/L6z4B)\n"
                                + "01000404123";
                        if (if_c != '*') {
                            API_SMS_MISR AP = new API_SMS_MISR("7TLgq36R", "3fMMaB0OMX", "Elgam3a", 2, Phone_Api, Massage);
                            boolean call_me = AP.call_me();
                            if (call_me) {

                                Platform.runLater(() -> {
                                    Notifications.create().title("ارسال الرساله الترحيبية")
                                            .text("تم ارسال الرسالة بنجاح\n" + ID_S)
                                            .showInformation();
                                });

                            }
                        }
                    }
                }.start();

                clear();
                ID_Subject.setText(ID_S);
                sub_V(ID_S);
                tab.getSelectionModel().select(1);

            }

        } else {
            Tools.Tools_JavaFX.Alert_T(Alert.AlertType.ERROR, "اضافة الطالب", "هناك خطا فى اضافة الطالب", "يرجوا ملئ كافة الحقول");
        }
        VB_Loader2.setVisible(false);
    }

    @FXML
    void exit(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        stage.hide();
    }

    @FXML
    void select(MouseEvent event) {

        try {

            Subject_bu.setDisable(false);
            Delete.setDisable(false);
            Update.setDisable(false);
            ADD.setDisable(true);
            Student selectedItem = Tree.getSelectionModel().getSelectedItem();
            ID = selectedItem.ID_ST.get();
            Name.setText(selectedItem.Name_ST.get());
            Phone.setText("*******" + (selectedItem.Phone_ST.get()).substring(8, 11));
            if ((selectedItem.Type.get()).equals("ولد")) {
                Male.setSelected(true);
            } else {
                Female.setSelected(true);
            }
            JSONObject JO = mysql.GetData("SELECT universty.Name_UN , faculty.Name_FA , years.Name_YE FROM `student`,universty,faculty,years WHERE student.ID_ST = " + ID + "   AND student.ID_YE = years.ID_YE AND years.ID_FA = faculty.ID_FA AND faculty.ID_UN = universty.ID_UN").getJSONObject(0);
            String Unvirsty_SE = JO.getString("Name_UN");
            String Facalty_SE = JO.getString("Name_FA");
            String Years_SE = JO.getString("Name_YE");

            Unicersty.getSelectionModel().select(Unvirsty_SE);
            Faclty.getSelectionModel().select(Facalty_SE);
            Years.getSelectionModel().select(Years_SE);

        } catch (Exception e) {
            System.out.println(e);
            clear();
        }

    }

    @FXML
    void ALL(ActionEvent event) {
        clear();
    }

    @FXML
    void Save(ActionEvent event) {
        VB_Loader1.setVisible(true);
        ObservableList<Label> targetItems = Subject.getTargetItems();
        int count_sub = targetItems.size();
        String ID_St = ID_Subject.getText();
        if (count_sub > 0) {

            new Thread() {
                @Override
                public void run() {
                    String Qua = "INSERT INTO `payment`( `Amount_PA`, `Date_PA`, `ID_ST`, `ID_SU`, `ID_User`, `ID_PA_TY`) VALUES ";
                    for (int x = 0; x < count_sub; x++) {
                        String ID_Sub = mysql.GetData("SELECT `ID_SU` FROM `subject` WHERE `Name_SU`='" + targetItems.get(x).getText() + "' AND `ID_YE` = (SELECT student.ID_YE from student WHERE student.ID_ST = " + ID_St + ");").getJSONObject(0).getString("ID_SU");
                        if (x == (count_sub - 1)) {
                            Qua += " (0,Now()," + ID_St + "," + ID_Sub + "," + User.getID_Us() + ",1);";
                        } else {
                            Qua += " (0,Now()," + ID_St + "," + ID_Sub + "," + User.getID_Us() + ",1),";
                        }
                    }
                    boolean Run = mysql.Run(Qua);

                    if (Run) {
                        Platform.runLater(() -> {
                            Tools.Tools_JavaFX.Alert_T(AlertType.CONFIRMATION, "رسالة تاكيد", "ـم تسجيل المواد", "بنجاحح");

                        });
                    }

                }
            }.start();

            new Thread() {
                @Override
                public void run() {
                    for (int x = 0; x < count_sub; x++) {
                        String ID_Sub = mysql.GetData("SELECT `ID_SU` FROM `subject` WHERE `Name_SU`='" + targetItems.get(x).getText() + "' AND `ID_YE` = (SELECT student.ID_YE from student WHERE student.ID_ST = " + ID_St + ");").getJSONObject(0).getString("ID_SU");
                        boolean Check1 = mysql.Check1("SELECT * FROM `group_student` WHERE `ID_ST`=" + ID_St + " AND `ID_SU`=" + ID_Sub);
                        if (Check1 == false) {
                            if (count_sub == 1) {

                                String ID_Gr = (String) mysql.PrintTable("SELECT `ID_GR` FROM `group_subject` WHERE `ID_SU` = " + ID_Sub + " ORDER BY `ID_GR` DESC").Table[0][0];
                                String Count_Ava = "0";
                                try {
                                    Count_Ava = (String) mysql.PrintTable("SELECT `Count Available` FROM `count_group` WHERE `ID_GR`=" + ID_Gr).Table[0][0];

                                } catch (Exception ex) {
                                    Count_Ava = "0";
                                }
                                if (Integer.parseInt(Count_Ava) > 0) {

                                    boolean Run1 = mysql.Run("INSERT INTO `group_student`(`ID_ST`, `ID_SU`, `ID_GR`) VALUES (" + ID_St + "," + ID_Sub + "," + ID_Gr + ")");

                                    if (!Run1) {
                                        Tools.Tools_JavaFX.Alert_T(AlertType.ERROR, "اضافة المجموعة", "هناكه مشكلة", "يرجوا التاكد من المعلومات المسجله");

                                    }

                                } else {

                                    String ID_Gr_O = (String) mysql.PrintTable("SELECT `ID_GR` FROM `count_group` WHERE `ID_SU`=" + ID_Sub + "  ORDER BY `count_group`.`Count Available` DESC").Table[0][0];
                                    boolean Run1 = mysql.Run("INSERT INTO `group_student`(`ID_ST`, `ID_SU`, `ID_GR`) VALUES (" + ID_St + "," + ID_Sub + "," + ID_Gr_O + ");");

                                    if (!Run1) {
                                        Tools.Tools_JavaFX.Alert_T(AlertType.ERROR, "اضافة المجموعة", "هناكه مشكلة", "يرجوا التاكد من المعلومات المسجله");

                                    }

                                }

                            } else {
                                String ID_Gr_O = null;
                                String Count_Ava = "5";
                                try {

                                    ID_Gr_O = (String) mysql.PrintTable("SELECT `ID_GR` FROM `count_group` WHERE `ID_SU`=" + ID_Sub + "  AND `Time` not in ( SELECT  `Day_Of_Time` FROM `group_student_v` WHERE `ID_ST`= " + ID_St + " )").Table[0][0];
                                    Count_Ava = (String) mysql.PrintTable("SELECT `Count Available` FROM `count_group` WHERE `ID_GR`=" + ID_Gr_O).Table[0][0];
                                } catch (Exception e) {
                                    ID_Gr_O = (String) mysql.PrintTable("SELECT `ID_GR` FROM `group_subject` WHERE `ID_SU`=" + ID_Sub + " GROUP BY rand()").Table[0][0];

                                }
                                if (Integer.parseInt(Count_Ava) > 0) {

                                    boolean Run1 = mysql.Run("INSERT INTO `group_student`(`ID_ST`, `ID_SU`, `ID_GR`) VALUES (" + ID_St + "," + ID_Sub + "," + ID_Gr_O + ")");

                                    if (!Run1) {
                                        Tools.Tools_JavaFX.Alert_T(AlertType.ERROR, "اضافة المجموعة", "هناكه مشكلة", "يرجوا التاكد من المعلومات المسجله");

                                    }

                                } else {

                                    String ID_Gr_O1 = (String) mysql.PrintTable("SELECT `ID_GR` FROM `count_group` WHERE `ID_SU`=" + ID_Sub + "  ORDER BY `count_group`.`Count Available` DESC").Table[0][0];
                                    boolean Run1 = mysql.Run("INSERT INTO `group_student`(`ID_ST`, `ID_SU`, `ID_GR`) VALUES (" + ID_St + "," + ID_Sub + "," + ID_Gr_O1 + ")");

                                    if (!Run1) {
                                        Tools.Tools_JavaFX.Alert_T(AlertType.ERROR, "اضافة المجموعة", "هناكه مشكلة", "يرجوا التاكد من المعلومات المسجله");

                                    }

                                }

                            }
                        }
                    }
                    Platform.runLater(() -> {
                        ID_Subject.setText("0");
                        ID_Amount.setText(ID_St);
                        amount_V(ID_St);
                        tab.getSelectionModel().select(2);
                    });
                }
            }.start();

        } else {

        }
        VB_Loader1.setVisible(false);
        L2.setText("");
    }

    @FXML
    void Save_Amount(ActionEvent event) {
        VB_Loader.setVisible(true);
        if (!ID_Amount.getText().isEmpty()) {
            int size = Win.getChildren().size();
            if (size > 0) {
                VBox get = (VBox) Win.getChildren().get(0);

                for (int x = 0; x < get.getChildren().size(); x++) {

                    HBox HB = (HBox) get.getChildren().get(x);
                    CheckBox CB = (CheckBox) HB.getChildren().get(0);
                    if (CB.isSelected()) {

                        String ID_Sub = (String) mysql.PrintTable("SELECT `ID_SU` FROM `subject` WHERE `Name_SU`='" + CB.getText() + "'").Table[0][0];
                        String Amount = ((JFXTextField) HB.getChildren().get(1)).getText();
                        String ID_G = null;
                        try {
                            ID_G = ((JFXComboBox) HB.getChildren().get(3)).getSelectionModel().getSelectedItem().toString();
                        } catch (Exception ex) {
                            ID_G = null;
                        }
                        boolean Check1 = mysql.Check1("SELECT * FROM `group_student` WHERE `ID_ST`=" + ID_Amount.getText() + " AND `ID_SU`=" + ID_Sub);
                        boolean Run;
                        if (Check1) {
                            Run = mysql.Run("UPDATE `group_student` SET `ID_GR`=" + ID_G + " WHERE `ID_ST`=" + ID_Amount.getText() + "  AND `ID_SU`=" + ID_Sub);

                        } else {
                            Run = mysql.Run("INSERT INTO `group_student`(`ID_ST`, `ID_SU`, `ID_GR`) VALUES (" + ID_Amount.getText() + "," + ID_Sub + "," + ID_G + ")");

                        }

                        if (Integer.parseInt(Amount) != 0) {
                            if (Amount.charAt(0) == '-') {
                                boolean Run1 = mysql.Run("INSERT INTO `payment`(`Amount_PA`, `ID_ST`, `ID_SU`, `ID_User`, `ID_PA_TY`) VALUES (" + Amount.substring(1, Amount.length()) + "," + ID_Amount.getText() + "," + ID_Sub + "," + User.getID_Us() + ",2)");
                            } else {
                                boolean Run1 = mysql.Run("INSERT INTO `payment`(`Amount_PA`, `ID_ST`, `ID_SU`, `ID_User`, `ID_PA_TY`) VALUES (" + Amount + "," + ID_Amount.getText() + "," + ID_Sub + "," + User.getID_Us() + ",1)");
                            }
                        }

                    }

                }
                Win.getChildren().clear();
                String id_S = ID_Amount.getText();
                ID_Amount.setText("0");
                ID_Amount.setText(id_S);
                boolean Check1 = mysql.Check1("SELECT * FROM `nodes` WHERE `ID_ST`=" + id_S);
                if (Check1) {
                    mysql.Run("UPDATE `nodes` SET `Data`='" + Nodes.getText() + "' WHERE `ID_ST`=" + id_S);
                } else {
                    mysql.Run("INSERT INTO `nodes`( `ID_ST`, `Data`) VALUES (" + id_S + ",'" + Nodes.getText() + "')");
                }

                amount_V(id_S);

            } else {

            }

            String type = (String) mysql.PrintTable("SELECT COUNT(`ID_TGP`) FROM `tb_group_print` WHERE `Type`='Start'").Table[0][0];

            if (Integer.parseInt(type) == 1) {

                String ID_G = (String) mysql.PrintTable("SELECT `ID_TGP` FROM `tb_group_print` WHERE `Type`='Start'").Table[0][0];
                mysql.Run("INSERT INTO `tb_g_p_id_st`(`ID_TGP`, `ID_ST`) VALUES (" + ID_G + "," + ID_Amount.getText() + ")");
                mysql.Run("INSERT INTO `printer_order`(`ID_ST`, `ID_TOO`) VALUES (" + ID_Amount.getText() + ",1)");
                String Type_Print = (String) mysql.PrintTable("SELECT `Type_SE` FROM `setting` WHERE `Name_SE`='Auto Print cart'").Table[0][0];

            }

            Alert alert = new Alert(AlertType.CONFIRMATION);
            alert.setTitle("طباعة الوصل");
            alert.setHeaderText("طباعة الوصل");
            alert.setContentText("هل انت متاكد من طباعة الوصل");

            ButtonType buttonTypeOne = new ButtonType("نعم");
            ButtonType buttonTypeTwo = new ButtonType("لا");

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo);
            Optional<ButtonType> result = alert.showAndWait();

            if (result.get() == buttonTypeOne) {
                String ID_G = (String) mysql.PrintTable("SELECT `Type_SE` FROM `setting` WHERE `Name_SE`='Auto Print cart'").Table[0][0];
                if ("true".equals(ID_G)) {
                    HashMap M = new HashMap();
                    M.put("ID_ST", ID_Amount.getText());
                    IReport R = new IReport();
                    R.R_V("Card_Person", M);
                }
            }

        } else {

        }

        VB_Loader.setVisible(false);
    }

}
