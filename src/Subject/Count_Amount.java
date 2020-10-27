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
public class Count_Amount extends RecursiveTreeObject<Count_Amount>{
    
    StringProperty Amount;
    StringProperty Count;

    public StringProperty getAmount() {
        return Amount;
    }

    public void setAmount(StringProperty Amount) {
        this.Amount = Amount;
    }

    public StringProperty getCount() {
        return Count;
    }

    public void setCount(StringProperty Count) {
        this.Count = Count;
    }

    public Count_Amount(String Amount, String Count) {
        this.Amount = new SimpleStringProperty(Amount);
        this.Count = new SimpleStringProperty(Count);
    }
    
}
