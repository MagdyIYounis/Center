/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Add_Student;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author goda4
 */
public class Student extends RecursiveTreeObject<Student>{
    StringProperty ID_ST;
    StringProperty Name_ST;
    StringProperty Phone_ST;
    StringProperty Date_ST;
    StringProperty Date_UP_ST;
    StringProperty Type;
    StringProperty Year;
    StringProperty Email;
    StringProperty Address;

    /**
     *
     * @param ID_ST
     * @param Name_ST
     * @param Phone_ST
     * @param Date_ST
     * @param Date_UP_ST
     * @param Type
     * @param Year
     */
    public Student(String ID_ST, String Name_ST, String Phone_ST, String Date_ST, String Date_UP_ST, String Type, String Year,String Email , String Address) {
        this.ID_ST = new SimpleStringProperty(ID_ST);
        this.Name_ST = new SimpleStringProperty(Name_ST);
        this.Phone_ST = new SimpleStringProperty(Phone_ST);
        this.Date_ST = new SimpleStringProperty(Date_ST);
        this.Date_UP_ST = new SimpleStringProperty(Date_UP_ST);
        this.Type = new SimpleStringProperty(Type);
        this.Year = new SimpleStringProperty(Year);
        this.Email = new SimpleStringProperty(Email);
        this.Address = new SimpleStringProperty(Address);
        
    }
    
}
