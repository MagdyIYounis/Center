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
public class Subject extends RecursiveTreeObject<Subject>{
    
    StringProperty ID_su;
    StringProperty Name_su;
    StringProperty Date_su;
    StringProperty amount_su;
    StringProperty Count_G_su;
    StringProperty Name_ST;
    StringProperty Years_su;
    StringProperty UN_Name_su;

    

    public Subject(String ID_su, String Name_su, String Date_su, String amount_su, String Count_G_su, String Name_ST, String Years_su , String UN_Name_su) {
        this.ID_su = new SimpleStringProperty(ID_su);
        this.Name_su = new SimpleStringProperty(Name_su);
        this.Date_su = new SimpleStringProperty(Date_su);
        this.amount_su = new SimpleStringProperty(amount_su);
        this.Count_G_su = new SimpleStringProperty(Count_G_su);
        this.Name_ST = new SimpleStringProperty(Name_ST);
        this.Years_su = new SimpleStringProperty(Years_su);
        this.UN_Name_su = new SimpleStringProperty(UN_Name_su);
    }

    public StringProperty getUN_Name_su() {
        return UN_Name_su;
    }

    public void setUN_Name_su(StringProperty UN_Name_su) {
        this.UN_Name_su = UN_Name_su;
    }

    public StringProperty getID_su() {
        return ID_su;
    }

    public void setID_su(StringProperty ID_su) {
        this.ID_su = ID_su;
    }

    public StringProperty getName_su() {
        return Name_su;
    }

    public void setName_su(StringProperty Name_su) {
        this.Name_su = Name_su;
    }

    public StringProperty getDate_su() {
        return Date_su;
    }

    public void setDate_su(StringProperty Date_su) {
        this.Date_su = Date_su;
    }

    public StringProperty getAmount_su() {
        return amount_su;
    }

    public void setAmount_su(StringProperty amount_su) {
        this.amount_su = amount_su;
    }

    public StringProperty getCount_G_su() {
        return Count_G_su;
    }

    public void setCount_G_su(StringProperty Count_G_su) {
        this.Count_G_su = Count_G_su;
    }

    public StringProperty getName_ST() {
        return Name_ST;
    }

    public void setName_ST(StringProperty Name_ST) {
        this.Name_ST = Name_ST;
    }

    public StringProperty getYears_su() {
        return Years_su;
    }

    public void setYears_su(StringProperty Years_su) {
        this.Years_su = Years_su;
    }

    

    
    

    
    
    
    
}
