/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Levels;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author goda4
 */
public class Staff extends RecursiveTreeObject<Staff>{
    
    StringProperty ID_S;
    StringProperty Name_S;
    StringProperty Phone_S;
    StringProperty Email_S;
    StringProperty Date_S;
    StringProperty P_fa;
    StringProperty Last_Update_S;

    public Staff(String ID_S, String Name_S, String Phone_S, String Email_S, String Date_S, String P_fa, String Last_Update_S) {
        this.ID_S = new SimpleStringProperty(ID_S);
        this.Name_S = new SimpleStringProperty(Name_S);
        this.Phone_S = new SimpleStringProperty(Phone_S);
        this.Email_S = new SimpleStringProperty(Email_S);
        this.Date_S = new SimpleStringProperty(Date_S);
        this.P_fa = new SimpleStringProperty(P_fa);
        this.Last_Update_S = new SimpleStringProperty(Last_Update_S);
    }

    public StringProperty getID_S() {
        return ID_S;
    }

    public void setID_S(StringProperty ID_S) {
        this.ID_S = ID_S;
    }

    public StringProperty getName_S() {
        return Name_S;
    }

    public void setName_S(StringProperty Name_S) {
        this.Name_S = Name_S;
    }

    public StringProperty getPhone_S() {
        return Phone_S;
    }

    public void setPhone_S(StringProperty Phone_S) {
        this.Phone_S = Phone_S;
    }

    public StringProperty getEmail_S() {
        return Email_S;
    }

    public void setEmail_S(StringProperty Email_S) {
        this.Email_S = Email_S;
    }

    public StringProperty getDate_S() {
        return Date_S;
    }

    public void setDate_S(StringProperty Date_S) {
        this.Date_S = Date_S;
    }

    public StringProperty getP_fa() {
        return P_fa;
    }

    public void setP_fa(StringProperty P_fa) {
        this.P_fa = P_fa;
    }

    public StringProperty getLast_Update_S() {
        return Last_Update_S;
    }

    public void setLast_Update_S(StringProperty Last_Update_S) {
        this.Last_Update_S = Last_Update_S;
    }

    
    
    
}
