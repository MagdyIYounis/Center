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
public class faculty extends RecursiveTreeObject<faculty>{
    
    StringProperty ID_Fa;
    StringProperty Name_Fa;
    StringProperty Date_Fa;
    StringProperty Name_Un;

    public faculty(String ID_Fa, String Name_Fa, String Date_Fa, String Name_Un) {
        this.ID_Fa = new SimpleStringProperty(ID_Fa);
        this.Name_Fa = new SimpleStringProperty(Name_Fa);
        this.Date_Fa = new SimpleStringProperty(Date_Fa);
        this.Name_Un = new SimpleStringProperty(Name_Un);
    }

    public StringProperty getID_Fa() {
        return ID_Fa;
    }

    public void setID_Fa(StringProperty ID_Fa) {
        this.ID_Fa = ID_Fa;
    }

    public StringProperty getName_Fa() {
        return Name_Fa;
    }

    public void setName_Fa(StringProperty Name_Fa) {
        this.Name_Fa = Name_Fa;
    }

    public StringProperty getDate_Fa() {
        return Date_Fa;
    }

    public void setDate_Fa(StringProperty Date_Fa) {
        this.Date_Fa = Date_Fa;
    }

    public StringProperty getName_Un() {
        return Name_Un;
    }

    public void setName_Un(StringProperty Name_Un) {
        this.Name_Un = Name_Un;
    }
    

    
    
    
    
}
