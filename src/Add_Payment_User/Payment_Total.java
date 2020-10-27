/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Add_Payment_User;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author goda4
 */
public class Payment_Total extends RecursiveTreeObject<Payment_Total> {

    StringProperty Amount;
    StringProperty Date;
    StringProperty Name_Su;
    StringProperty UserName;
    StringProperty Name_Of_Type;
    StringProperty Payment;
    StringProperty Amount_P;

    public Payment_Total(String Amount, String Date, String Name_Su, String UserName, String Name_Of_Type, String Payment, String Amount_P) {
        this.Amount = new SimpleStringProperty(Amount);
        this.Date = new SimpleStringProperty(Date);
        this.Name_Su = new SimpleStringProperty(Name_Su);
        this.UserName = new SimpleStringProperty(UserName);
        this.Name_Of_Type = new SimpleStringProperty(Name_Of_Type);
        this.Payment = new SimpleStringProperty(Payment);
        this.Amount_P = new SimpleStringProperty(Amount_P);
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

    public StringProperty getName_Su() {
        return Name_Su;
    }

    public void setName_Su(StringProperty Name_Su) {
        this.Name_Su = Name_Su;
    }

    public StringProperty getUserName() {
        return UserName;
    }

    public void setUserName(StringProperty UserName) {
        this.UserName = UserName;
    }

    public StringProperty getName_Of_Type() {
        return Name_Of_Type;
    }

    public void setName_Of_Type(StringProperty Name_Of_Type) {
        this.Name_Of_Type = Name_Of_Type;
    }

    public StringProperty getPayment() {
        return Payment;
    }

    public void setPayment(StringProperty Payment) {
        this.Payment = Payment;
    }

    public StringProperty getAmount_P() {
        return Amount_P;
    }

    public void setAmount_P(StringProperty Amount_P) {
        this.Amount_P = Amount_P;
    }

}
