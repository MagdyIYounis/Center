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
public class Total_Amount extends RecursiveTreeObject<Total_Amount>{
    
    StringProperty Total;
    StringProperty Date;
    StringProperty type;
    public Total_Amount(String Total, String Date, String type) {
        this.Total = new SimpleStringProperty(Total);
        this.Date = new SimpleStringProperty(Date);
        this.type = new SimpleStringProperty(type);
    }

    public StringProperty getTotal() {
        return Total;
    }

    public void setTotal(StringProperty Total) {
        this.Total = Total;
    }

    public StringProperty getDate() {
        return Date;
    }

    public void setDate(StringProperty Date) {
        this.Date = Date;
    }

    public StringProperty getType() {
        return type;
    }

    public void setType(StringProperty type) {
        this.type = type;
    }
    
    
}
