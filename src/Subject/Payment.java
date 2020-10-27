/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Subject;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author goda4
 */
public class Payment extends RecursiveTreeObject<Payment>{
    StringProperty ID_PY;
    StringProperty Amount;
    StringProperty Date;
    StringProperty ID_St;
    StringProperty Name_Us;
    StringProperty Type_Of;

    public Payment(String ID_PY, String Amount, String Date, String ID_St, String Name_Us, String Type_Of) {
        this.ID_PY = new SimpleStringProperty(ID_PY);
        this.Amount = new SimpleStringProperty(Amount);
        this.Date = new SimpleStringProperty(Date);
        this.ID_St = new SimpleStringProperty(ID_St);
        this.Name_Us = new SimpleStringProperty(Name_Us);
        this.Type_Of = new SimpleStringProperty(Type_Of);
    }

    public StringProperty getID_PY() {
        return ID_PY;
    }

    public void setID_PY(StringProperty ID_PY) {
        this.ID_PY = ID_PY;
    }

    public StringProperty getAmount() {
        return Amount;
    }

    public void setAmount(StringProperty Amount) {
        this.Amount = Amount;
    }

    public StringProperty getDate() {
        return Date;
    }

    public void setDate(StringProperty Date) {
        this.Date = Date;
    }

    public StringProperty getID_St() {
        return ID_St;
    }

    public void setID_St(StringProperty ID_St) {
        this.ID_St = ID_St;
    }

    public StringProperty getName_Us() {
        return Name_Us;
    }

    public void setName_Us(StringProperty Name_Us) {
        this.Name_Us = Name_Us;
    }

    public StringProperty getType_Of() {
        return Type_Of;
    }

    public void setType_Of(StringProperty Type_Of) {
        this.Type_Of = Type_Of;
    }
    
    
    
}
