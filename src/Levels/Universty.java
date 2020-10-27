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
public class Universty extends RecursiveTreeObject<Universty>{
    
    StringProperty ID_UN;
    StringProperty Name_Un;
    StringProperty Date_UN;

    public Universty(String ID_UN, String Name_Un, String Date_UN) {
        this.ID_UN = new SimpleStringProperty(ID_UN);
        this.Name_Un = new SimpleStringProperty(Name_Un);
        this.Date_UN = new SimpleStringProperty(Date_UN);
    }

    public StringProperty getID_UN() {
        return ID_UN;
    }

    public void setID_UN(StringProperty ID_UN) {
        this.ID_UN = ID_UN;
    }

    public StringProperty getName_Un() {
        return Name_Un;
    }

    public void setName_Un(StringProperty Name_Un) {
        this.Name_Un = Name_Un;
    }

    public StringProperty getDate_UN() {
        return Date_UN;
    }

    public void setDate_UN(StringProperty Date_UN) {
        this.Date_UN = Date_UN;
    }

    
    
    
    
}
