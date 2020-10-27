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
public class years extends RecursiveTreeObject<years>{
    
    StringProperty ID_ye;
    StringProperty Name_ye;
    StringProperty Date_ye;
    StringProperty Name_fa;

    public years(String ID_ye, String Name_ye, String Date_ye, String Name_fa) {
        this.ID_ye = new SimpleStringProperty(ID_ye);
        this.Name_ye = new SimpleStringProperty(Name_ye);
        this.Date_ye = new SimpleStringProperty(Date_ye);
        this.Name_fa = new SimpleStringProperty(Name_fa);
    }

    public StringProperty getID_ye() {
        return ID_ye;
    }

    public void setID_ye(StringProperty ID_ye) {
        this.ID_ye = ID_ye;
    }

    public StringProperty getName_ye() {
        return Name_ye;
    }

    public void setName_ye(StringProperty Name_ye) {
        this.Name_ye = Name_ye;
    }

    public StringProperty getDate_ye() {
        return Date_ye;
    }

    public void setDate_ye(StringProperty Date_ye) {
        this.Date_ye = Date_ye;
    }

    public StringProperty getName_fa() {
        return Name_fa;
    }

    public void setName_fa(StringProperty Name_fa) {
        this.Name_fa = Name_fa;
    }

    
    

    
    
    
    
}
