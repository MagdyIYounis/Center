/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Add_Payment_User;

import Tools.mysql;
import center.elgam3a.User;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.controls.JFXTreeTableColumn;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.RecursiveTreeItem;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.util.StringConverter;
import org.controlsfx.control.textfield.AutoCompletionBinding;
import org.controlsfx.control.textfield.TextFields;

/**
 * FXML Controller class
 *
 * @author goda4
 */
public class Add_P_UController implements Initializable {

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @FXML
    private AnchorPane AP;

    @FXML
    private JFXTextField Amount;

    @FXML
    private JFXDatePicker Date;

    @FXML
    private JFXTextField subject;

    @FXML
    private JFXComboBox<String> Faculty;

    @FXML
    private JFXComboBox<String> Type_OF;

    AutoCompletionBinding bindAutoCompletion;

    @FXML
    private JFXTreeTableView<Payment_Total> table;

    @FXML
    private Label Total_OF_Day;

    @FXML
    private Label Total;
    @FXML
    private Label Total_OF_Day1;

    @FXML
    private Label Total1;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            final KeyCombination ADD_Key = new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN);
            AP.addEventFilter(KeyEvent.KEY_RELEASED, (KeyEvent event) -> {

                if (ADD_Key.match(event)) {
                    Add(new ActionEvent());
                }

            });
            mysql.Combo1("SELECT `Name_OF_Type` FROM `payment_type`", Type_OF);
            Date.setConverter(new StringConverter<LocalDate>() {
                private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-M-d");

                @Override
                public String toString(LocalDate localDate) {
                    if (localDate == null) {
                        return "";
                    }
                    return dateTimeFormatter.format(localDate);
                }

                @Override
                public LocalDate fromString(String dateString) {
                    if (dateString == null || dateString.trim().isEmpty()) {
                        return null;
                    }
                    return LocalDate.parse(dateString, dateTimeFormatter);
                }
            });
            Amount.textProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                if (!newValue.matches("^[-+]?\\d+(\\.\\d+)?$")) {
                    Platform.runLater(() -> {
                        Amount.setText("0");
                    });
                }
            });
            Faculty.getItems().clear();
            mysql.Combo1("SELECT `Name_FA` FROM `faculty` ", Faculty);
            Faculty.getSelectionModel().select(0);
            Type_OF.getSelectionModel().select(0);
            String ID_Ye = (String) mysql.PrintTable("SELECT `ID_FA` FROM `faculty` WHERE `Name_FA`='" + Faculty.getSelectionModel().getSelectedItem() + "'").Table[0][0];
            Set autoC = mysql.autoC("SELECT `Name_SU` FROM `subject` , years , faculty WHERE subject.ID_YE = years.ID_YE AND years.ID_FA = faculty.ID_FA AND faculty.ID_FA=" + ID_Ye);
            subject.clear();
            Date.setValue(LocalDate.now());
            bindAutoCompletion = TextFields.bindAutoCompletion(subject, autoC);
            bindAutoCompletion.setPrefWidth(150);
            Faculty.valueProperty().addListener((ObservableValue<? extends String> observable, String oldValue, String newValue) -> {
                if (!Faculty.getItems().isEmpty()) {
                    try {
                        bindAutoCompletion.dispose();
                        String ID_fa = (String) mysql.PrintTable("SELECT `ID_FA` FROM `faculty` WHERE `Name_FA`='" + newValue + "'").Table[0][0];
                        Set auto = mysql.autoC("SELECT `Name_SU` FROM `subject` , years , faculty WHERE subject.ID_YE = years.ID_YE AND years.ID_FA = faculty.ID_FA AND faculty.ID_FA=" + ID_fa);
                        subject.clear();

                        bindAutoCompletion = TextFields.bindAutoCompletion(subject, auto);
                        String Total_Day_S = (String) mysql.PrintTable("SELECT `total_amount_sub` FROM `total_of_sub` WHERE `DATE`='" + Date.getValue().format(DateTimeFormatter.ISO_DATE) + "' AND `ID_FA`=" + ID_fa).Table[0][0];
                        String Total_S = (String) mysql.PrintTable("SELECT sum(`total_amount_sub`) FROM `total_of_sub` where `ID_FA`=" + ID_fa).Table[0][0];
                        String Total_P_D_S = (String) mysql.PrintTable("SELECT `total_amount_Payment` FROM `total_of_sub` WHERE `DATE`='" + Date.getValue().format(DateTimeFormatter.ISO_DATE) + "' AND `ID_FA`=" + ID_fa).Table[0][0];
                        String Total_P_S = (String) mysql.PrintTable("SELECT sum(`total_amount_Payment`) FROM `total_of_sub` where `ID_FA`=" + ID_fa).Table[0][0];
                        Total_OF_Day.setText(Total_Day_S);
                        Total.setText(Total_S);
                        Total_OF_Day1.setText(Total_P_D_S);
                        Total1.setText(Total_P_S);
                    } catch (SQLException ex) {

                    }
                }
            });

            JFXTreeTableColumn<Payment_Total, String> Amount_T = new JFXTreeTableColumn<>("المبلغ المحصل من الطالب");
            Amount_T.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment_Total, String> param) -> param.getValue().getValue().Amount);

            JFXTreeTableColumn<Payment_Total, String> Date_T = new JFXTreeTableColumn<>("التاريخ");
            Date_T.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment_Total, String> param) -> param.getValue().getValue().Date);

            JFXTreeTableColumn<Payment_Total, String> S_T = new JFXTreeTableColumn<>("الماده");
            S_T.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment_Total, String> param) -> param.getValue().getValue().Name_Su);

            JFXTreeTableColumn<Payment_Total, String> user_T = new JFXTreeTableColumn<>("اسم المستخدم");
            user_T.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment_Total, String> param) -> param.getValue().getValue().UserName);

            JFXTreeTableColumn<Payment_Total, String> Type_T = new JFXTreeTableColumn<>("نوع العملية");
            Type_T.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment_Total, String> param) -> param.getValue().getValue().Name_Of_Type);

            JFXTreeTableColumn<Payment_Total, String> Amount_P_T = new JFXTreeTableColumn<>("المبلغ المسجل");
            Amount_P_T.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment_Total, String> param) -> param.getValue().getValue().Payment);

            JFXTreeTableColumn<Payment_Total, String> Amo_T = new JFXTreeTableColumn<>("الباقة");
            Amo_T.setCellValueFactory((TreeTableColumn.CellDataFeatures<Payment_Total, String> param) -> param.getValue().getValue().Amount_P);
            ObservableList Li4 = mysql.Table_FX11("SELECT * FROM `payment_total` WHERE `DATE`='" + Date.getValue().format(DateTimeFormatter.ISO_DATE) + "'");
            final TreeItem<Payment_Total> root4 = new RecursiveTreeItem<Payment_Total>(Li4, RecursiveTreeObject::getChildren);
            table.getColumns().setAll(Amount_T, Date_T, S_T, user_T, Type_T, Amount_P_T, Amo_T);
            table.setRoot(root4);
            table.setShowRoot(false);

            Date.valueProperty().addListener((ObservableValue<? extends LocalDate> observable, LocalDate oldValue, LocalDate newValue) -> {

                try {
                    if (newValue == null) {
                        ObservableList Li1 = mysql.Table_FX11("SELECT * FROM `payment_total`");
                        final TreeItem<Payment_Total> root1 = new RecursiveTreeItem<Payment_Total>(Li1, RecursiveTreeObject::getChildren);
                        table.getColumns().setAll(Amount_T, Date_T, S_T, user_T, Type_T, Amount_P_T, Amo_T);
                        table.setRoot(root1);
                        table.setShowRoot(false);

                    } else {
                        ObservableList Li1 = mysql.Table_FX11("SELECT * FROM `payment_total` WHERE `DATE`='" + newValue.format(DateTimeFormatter.ISO_DATE) + "'");
                        final TreeItem<Payment_Total> root1 = new RecursiveTreeItem<Payment_Total>(Li1, RecursiveTreeObject::getChildren);
                        table.getColumns().setAll(Amount_T, Date_T, S_T, user_T, Type_T, Amount_P_T, Amo_T);
                        table.setRoot(root1);
                        table.setShowRoot(false);
                        String Total_Day_S = (String) mysql.PrintTable("SELECT `total_amount_sub` FROM `total_of_sub` WHERE `DATE`='" + Date.getValue().format(DateTimeFormatter.ISO_DATE) + "' AND `ID_FA`=" + ID_Ye).Table[0][0];
                        String Total_S = (String) mysql.PrintTable("SELECT sum(`total_amount_sub`) FROM `total_of_sub` where  `ID_FA`=" + ID_Ye).Table[0][0];
                        String Total_P_D_S = (String) mysql.PrintTable("SELECT `total_amount_Payment` FROM `total_of_sub` WHERE `DATE`='" + Date.getValue().format(DateTimeFormatter.ISO_DATE) + "' AND `ID_FA`=" + ID_Ye).Table[0][0];
                        String Total_P_S = (String) mysql.PrintTable("SELECT sum(`total_amount_Payment`) FROM `total_of_sub` where  `ID_FA`=" + ID_Ye).Table[0][0];
                        Total_OF_Day.setText(Total_Day_S);
                        Total.setText(Total_S);
                        Total_OF_Day1.setText(Total_P_D_S);
                        Total1.setText(Total_P_S);
                    }
                } catch (SQLException ex) {

                }
            });

            String Total_Day_S = (String) mysql.PrintTable("SELECT `total_amount_sub` FROM `total_of_sub` WHERE `DATE`='" + Date.getValue().format(DateTimeFormatter.ISO_DATE) + "' AND `ID_FA`=" + ID_Ye).Table[0][0];
            String Total_S = (String) mysql.PrintTable("SELECT sum(`total_amount_sub`) FROM `total_of_sub` where  `ID_FA`=" + ID_Ye).Table[0][0];
            String Total_P_D_S = (String) mysql.PrintTable("SELECT `total_amount_Payment` FROM `total_of_sub` WHERE `DATE`='" + Date.getValue().format(DateTimeFormatter.ISO_DATE) + "' AND `ID_FA`=" + ID_Ye).Table[0][0];
            String Total_P_S = (String) mysql.PrintTable("SELECT sum(`total_amount_Payment`) FROM `total_of_sub` where  `ID_FA`=" + ID_Ye).Table[0][0];
            Total_OF_Day.setText(Total_Day_S);
            Total.setText(Total_S);
            Total_OF_Day1.setText(Total_P_D_S);
            Total1.setText(Total_P_S);

        } catch (SQLException ex) {

        }

    }

    @FXML
    void Exit(ActionEvent event) {
        Tools.Tools_JavaFX.Exit(event);
    }

    @FXML
    void Add(ActionEvent event) {

        if (!Amount.getText().isEmpty() && !subject.getText().isEmpty() && Integer.parseInt(Amount.getText()) != 0) {

            String ID_Ye = (String) mysql.PrintTable("SELECT `ID_FA` FROM `faculty` WHERE `Name_FA`='" + Faculty.getSelectionModel().getSelectedItem() + "'").Table[0][0];
            boolean Check1 = mysql.Check1("SELECT `Name_SU` FROM `subject` , years , faculty WHERE subject.ID_YE = years.ID_YE AND years.ID_FA = faculty.ID_FA AND faculty.ID_FA=" + ID_Ye + "  AND `Name_SU`='" + subject.getText().trim() + "'");

            if (Check1) {
                String ID_SU = (String) mysql.PrintTable("SELECT `ID_SU` FROM `subject`,years, faculty WHERE `Name_SU`='" + subject.getText() + "'  AND years.ID_YE = subject.ID_YE AND years.ID_FA =" + ID_Ye).Table[0][0];
                String ID_Y_Of = (String) mysql.PrintTable("SELECT `ID_PA_TY` FROM `payment_type` WHERE `Name_OF_Type`='" + Type_OF.getSelectionModel().getSelectedItem() + "'").Table[0][0];
                boolean Run = mysql.Run("INSERT INTO `payment_user`(`Amount`, `ID_User`, `ID_SU`, `ID_PA_TY`) VALUES (" + Amount.getText() + "," + User.getID_Us() + "," + ID_SU + "," + ID_Y_Of + ")");

                if (Run) {
                    Tools.Tools_JavaFX.Alert_T(Alert.AlertType.CONFIRMATION, "تاكيد الايراد", "تم تسجيل الايراد", "بنجاح :)");
                    initialize(null, null);
                }

            } else {

                subject.clear();
                subject.requestFocus();

            }

        }

    }

    @FXML
    void select(MouseEvent event) {
        try {
            TreeItem<Payment_Total> selectedItem = table.getSelectionModel().getSelectedItem();
            Amount.setText(selectedItem.getValue().Amount_P.get());
            subject.setText(selectedItem.getValue().Name_Su.get());
            Type_OF.getSelectionModel().select(selectedItem.getValue().Name_Of_Type.get());
        } catch (Exception ex) {
        }
    }

}
