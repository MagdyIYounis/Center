/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tools;

import Add_Payment_User.Payment_Total;
import Add_Student.Student;
import Levels.Staff;
import Levels.Subject;
import Levels.Universty;
import Levels.faculty;
import Levels.years;
import Subject.Count_Amount;
import Subject.Payment;
import Subject.Total_Amount;
import Tools.Tools.Table;
import center.elgam3a.User;
import com.jfoenix.controls.JFXComboBox;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashSet;
import java.util.Set;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Label;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.controlsfx.control.ListSelectionView;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author adel yons
 */
public class mysql {

    private static String URL;
    public static Connection con;
    public static boolean resultcon;

    private static void SetURL() {
        String dbName = "center_elgam3a2";
        String userName = "Mego";
        String password = "Mego2010";
        String hostname = "center.calpeky9vqvd.us-east-2.rds.amazonaws.com";
        String port = "3306";
        URL = "jdbc:mysql://" + hostname + ":" + port + "/" + dbName + "?user=" + userName + "&password=" + password;

    }

    /**
     *
     * @return
     */
    public static boolean Con() {

        SetURL();
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection(URL);
            resultcon = true;
            return true;
        } catch (SQLException | ClassNotFoundException ex) {
            System.out.println(ex);
            resultcon = false;
            return false;
        }

    }

    public static Connection Con1() {

        SetURL();
        try {

            Connection con1 = DriverManager.getConnection(URL);
            return con1;

        } catch (SQLException ex) {
            return null;
        }

    }

    public static boolean Con2() {

        SetURL();
        try {

            Connection con1 = DriverManager.getConnection(URL);
            con1.close();
            return true;

        } catch (SQLException ex) {
            return false;
        }

    }

    /**
     *
     * @param user
     * @param Password
     * @return
     */
    public static boolean Che(String user, String Password) {

        try {

            Statement c = con.createStatement();
            String S = "SELECT * FROM user_login WHERE PassWord='" + Password + "' and FirstName='" + user + "'";
            c.executeQuery(S);

            if (c.getResultSet().next()) {
                return true;

            }

        } catch (SQLException ex) {

            Tools.Show_M("Sorry !!!!!!!!" + ex);

        }
        return false;

    }

    /**
     *
     * @param user
     * @param Password
     * @return
     */
    public static boolean Che0(String user, String Password) {

        try {

            Statement c = con.createStatement();
            String S = "SELECT * FROM user WHERE Name_US='" + user + "' AND Password_US='" + Password + "'";
            c.executeQuery(S);

            if (c.getResultSet().next()) {
                if (c.getResultSet().getString("Name_US").matches(user) && c.getResultSet().getString("Password_US").matches(Password)) {
                    User.setID_Us(c.getResultSet().getInt("ID_User"));
                    User.setName_Us(c.getResultSet().getString("Name_US"));
                    User.setRole(c.getResultSet().getInt("Role_US"));
                    User.setUserName_US(c.getResultSet().getString("UserName_US"));
                    User.setPhoto_US(c.getResultSet().getBinaryStream("Photo_US"));
                    return true;
                }

            }

        } catch (SQLException ex) {

            Tools.Show_M("Sorry !!!!!!!!" + ex);

        }
        return false;

    }

    /**
     *
     * @param user
     * @param Password
     * @return
     */
    public static boolean Che1(String user, String Password) {

        try {

            Statement c = con.createStatement();
            String S = "SELECT * FROM US WHERE Name='" + Password + "' and PassWord='" + user + "'";
            c.executeQuery(S);

            if (c.getResultSet().next()) {
                return true;
            }

        } catch (SQLException ex) {

            Tools.Show_M("Sorry !!!!!!!!" + ex);

        }
        return false;

    }

    /**
     *
     * @param num
     * @param dbt
     * @return
     */
    public static boolean Check(int num, String dbt) {

        try {

            Statement c = con.createStatement();
            String S = "SELECT ID FROM " + dbt + " WHERE ID =" + num;
            c.executeQuery(S);

            if (c.getResultSet().next()) {
                return true;
            }

        } catch (SQLException ex) {

            Tools.Show_M("Sorry !!!!!!!!" + ex);

        }
        return false;

    }

    /**
     *
     * @param Stat
     * @return
     */
    public static boolean Check1(String Stat) {

        try {

            Statement c = con.createStatement();
            String S = Stat;
            c.executeQuery(S);

            if (c.getResultSet().next()) {
                return true;
            }

        } catch (SQLException ex) {

            Tools.Show_M("Sorry !!!!!!!!" + ex);

        }
        return false;

    }

    /**
     *
     * @param Stat
     * @return
     */
    public static boolean Run(String Stat) {

        try {

            Statement sta = con.createStatement();
            sta.execute(Stat);
            return true;

        } catch (SQLException ex) {
            Tools.Show_M(ex);
            return false;
        }

    }

    /**
     *
     * @param Stat
     * @return
     */
    public static boolean Run1(String Stat) {

        try {

            Statement sta = con.createStatement();
            sta.execute("SET @row = 0;");
            sta.execute(Stat);
            return true;

        } catch (SQLException ex) {
            Tools.Show_M("---" + ex);
            return false;
        }

    }

    /**
     *
     * @param table
     * @param column
     * @return
     */
    public static String autonumber(String table, String column) {

        String num = "";
        try {
            Statement stat = con.createStatement();
            String S = "select Max(" + column + ")+1 as auto FROM " + table;
            stat.executeQuery(S);
            while (stat.getResultSet().next()) {
                num = (String) stat.getResultSet().getString("auto");
            }

            if (num == null || "".equals(num)) {
                num = "1";
            }
            return num;
        } catch (SQLException ex) {
            Logger.getLogger(mysql.class.getName()).log(Level.SEVERE, null, ex);

        }
        return num;

    }

    /**
     *
     * @param Statement
     * @return
     */
    public static Table PrintTable(String Statement) {
        Tools t = new Tools();
        try {

            Statement S = con.createStatement();
            ResultSet x = S.executeQuery(Statement);
            ResultSetMetaData Y = x.getMetaData();
            int n = Y.getColumnCount();

            Table T = t.new Table(n);

            while (x.next()) {
                Object row[] = new Object[n];
                for (int Q = 0; Q < n; Q++) {
                    row[Q] = x.getString(Q + 1);
                }
                T.addRow(row);
            }

            return T;
        } catch (SQLException ex) {
            Tools.Show_M("----" + ex);
            return t.new Table(0);
        }
    }

    public static synchronized JSONArray GetData(String Statement) {

        try {

            Statement S = con.createStatement();
            ResultSet x = S.executeQuery(Statement);
            ResultSetMetaData rsmd = x.getMetaData();
            int column_count = rsmd.getColumnCount();
            JSONArray JA = new JSONArray();
            while (x.next()) {
                JSONObject JO = new JSONObject();

                for (int x1 = 0; x1 < column_count; x1++) {
                    JO.put(rsmd.getColumnName(x1 + 1), x.getString(x1 + 1));
                }
                JA.put(JO);
            }

            return JA;
        } catch (SQLException ex) {
            Tools.Show_M("----" + ex);
            return null;
        }
    }

    /**
     *
     * @param Statement
     * @return
     */
    public static int col(String Statement) {
        Tools t = new Tools();
        try {

            Statement S = con.createStatement();
            ResultSet x = S.executeQuery(Statement);
            ResultSetMetaData Y = x.getMetaData();
            int n = Y.getColumnCount();
            return n;
        } catch (SQLException ex) {
            return 0;
        }

    }

    /**
     *
     * @param Table
     * @param Columns
     * @param comb
     */
    public static void Combo(String Table, String Columns, JFXComboBox comb) {

        try {

            Statement Stat = con.createStatement();
            ResultSet re;
            String State = "Select " + Columns + " From " + Table;
            re = Stat.executeQuery(State);
            re.last();
            int x = re.getRow();
            re.beforeFirst();
            Object[] value = new Object[x];
            int y = 0;
            while (re.next()) {
                value[y] = re.getString(1);
                comb.getItems().add(re.getString(1));
                y++;
            }

            // comb.setModel(new DefaultComboBoxModel(value));
        } catch (SQLException ex) {

            Tools.Show_M("-----" + ex);

        }

    }

    /**
     *
     * @param stat
     * @param comb
     */
    public static void Combo1(String stat, JFXComboBox comb) {

        try {

            Statement Stat = con.createStatement();
            ResultSet re = Stat.executeQuery(stat);

            while (re.next()) {
                comb.getItems().add(re.getString(1));
            }

        } catch (SQLException ex) {

            System.out.println("---FF---" + ex);

        }

    }

    public static void ListViewSelect(String stat, ListSelectionView<Label> comb) {

        try {

            Statement Stat = con.createStatement();
            ResultSet re = Stat.executeQuery(stat);
            ObservableList<Label> users = FXCollections.observableArrayList();
            while (re.next()) {
                users.add(new Label(re.getString(1)));
            }
            comb.getSourceItems().addAll(users);

        } catch (SQLException ex) {

            Tools.Show_M("----DD---" + ex);

        }

    }

    /**
     *
     * @param Name_TableorStatement
     * @param table
     */
    public static void fillJTable(String Name_TableorStatement, JTable table) {
        try {

            Statement Stat = con.createStatement();
            ResultSet re;
            String St = Name_TableorStatement.substring(0, 7).toLowerCase();

            if ("select ".equals(St)) {
                re = Stat.executeQuery(Name_TableorStatement);
            } else {
                re = Stat.executeQuery("select * from " + Name_TableorStatement);
            }

            ResultSetMetaData D = re.getMetaData();
            int Count = D.getColumnCount();

            DefaultTableModel Model = (DefaultTableModel) table.getModel();
            Model.setRowCount(0);

            Vector V = new Vector();

            while (re.next()) {
                V = new Vector(Count);
                for (int x = 1; x <= Count; x++) {

                    V.add(re.getString(x));

                }
                Model.addRow(V);
            }
            if (table.getColumnCount() != Count) {

                Tools.Show_M("Sorry MY Namber Column Table not Equals MetaData");
            }

        } catch (SQLException ex) {

            Tools.Show_M("----------" + ex);

        }

    }

    public static Set autoC(String statment) throws SQLException {
        Set<String> users = new HashSet();

        Statement S = con.createStatement();
        ResultSet x = S.executeQuery(statment);
        while (x.next()) {
            users.add(x.getString(1));
        }

        return users;

    }

    /**
     *
     * @param statment
     * @return
     * @throws SQLException
     */
    public static ObservableList Table_FX1(String statment) throws SQLException {
        ObservableList<Student> users = FXCollections.observableArrayList();

        Statement S = con.createStatement();
        ResultSet x = S.executeQuery(statment);
        while (x.next()) {
            Student st = new Student(x.getString(1), x.getString(2), x.getString(3), x.getString(4), x.getString(5), x.getString(6), x.getString(7), x.getString(8), x.getString(9));
            users.add(st);
        }

        return users;

    }

    /**
     *
     * @param statment
     * @return
     * @throws SQLException
     */
    public static ObservableList Table_FX2(String statment) throws SQLException {
        ObservableList<Payment> users = FXCollections.observableArrayList();

        Statement S = con.createStatement();
        ResultSet x = S.executeQuery(statment);
        while (x.next()) {
            Payment emp = new Payment(x.getString(1), x.getString(2), x.getString(3), x.getString(4), x.getString(5), x.getString(6));
            users.add(emp);
        }

        return users;

    }

    public static ObservableList Table_FX3(String statment) throws SQLException {
        ObservableList<Count_Amount> users = FXCollections.observableArrayList();

        Statement S = con.createStatement();
        ResultSet x = S.executeQuery(statment);
        while (x.next()) {
            Count_Amount emp = new Count_Amount(x.getString(1), x.getString(2));
            users.add(emp);
        }

        return users;

    }

    public static ObservableList Table_FX4(String statment) throws SQLException {
        ObservableList<Total_Amount> users = FXCollections.observableArrayList();

        try {
            Statement S = con.createStatement();
            ResultSet x = S.executeQuery(statment);
            while (x.next()) {
                Total_Amount emp = new Total_Amount(x.getString(1), x.getString(2), x.getString(3));
                users.add(emp);
            }
        } catch (Exception ex) {

        }

        return users;

    }

    public static ObservableList Table_FX5(String statment) throws SQLException {
        ObservableList<Universty> users = FXCollections.observableArrayList();

        Statement S = con.createStatement();
        ResultSet x = S.executeQuery(statment);
        while (x.next()) {
            Universty emp = new Universty(x.getString(1), x.getString(2), x.getString(3));
            users.add(emp);
        }

        return users;

    }

    public static ObservableList Table_FX6(String statment) throws SQLException {
        ObservableList<faculty> users = FXCollections.observableArrayList();

        Statement S = con.createStatement();
        ResultSet x = S.executeQuery(statment);
        while (x.next()) {
            faculty emp = new faculty(x.getString(1), x.getString(2), x.getString(3), x.getString(4));
            users.add(emp);
        }

        return users;

    }

    public static ObservableList Table_FX7(String statment) throws SQLException {
        ObservableList<years> users = FXCollections.observableArrayList();

        Statement S = con.createStatement();
        ResultSet x = S.executeQuery(statment);
        while (x.next()) {
            years emp = new years(x.getString(1), x.getString(2), x.getString(3), x.getString(4));
            users.add(emp);
        }

        return users;

    }

    public static ObservableList Table_FX8(String statment) throws SQLException {
        ObservableList<Staff> users = FXCollections.observableArrayList();

        Statement S = con.createStatement();
        ResultSet x = S.executeQuery(statment);
        while (x.next()) {
            Staff emp = new Staff(x.getString(1), x.getString(2), x.getString(3), x.getString(4), x.getString(5), x.getString(6), x.getString(7));
            users.add(emp);
        }

        return users;

    }

    public static ObservableList Table_FX9(String statment) throws SQLException {
        ObservableList<Subject> users = FXCollections.observableArrayList();

        Statement S = con.createStatement();
        ResultSet x = S.executeQuery(statment);
        while (x.next()) {
            Subject emp = new Subject(x.getString(1), x.getString(2), x.getString(3), x.getString(4), x.getString(5), x.getString(6), x.getString(7), x.getString(8));
            users.add(emp);
        }

        return users;

    }

    public static ObservableList Table_FX10(String statment) throws SQLException {
        ObservableList<String[]> users = FXCollections.observableArrayList();

        Statement S = con.createStatement();
        ResultSet x = S.executeQuery(statment);
        while (x.next()) {
            String[] emp = {x.getString(1), x.getString(2), x.getString(3), x.getString(4), x.getString(5)};
            users.add(emp);
        }

        return users;

    }

    public static ObservableList Table_FX11(String statment) throws SQLException {
        ObservableList<Payment_Total> users = FXCollections.observableArrayList();

        Statement S = con.createStatement();
        ResultSet x = S.executeQuery(statment);
        while (x.next()) {
            Payment_Total emp = new Payment_Total(x.getString(1), x.getString(2), x.getString(3), x.getString(4), x.getString(5), x.getString(6), x.getString(7));
            users.add(emp);
        }

        return users;

    }

}
