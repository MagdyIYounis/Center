/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Levels;

import javafx.scene.control.ComboBox;

/**
 *
 * @author goda4
 */
public class Group {

    String ID_G;
    String Count_G;
    ComboBox<String> Day1_G;
    ComboBox<String> Day2_G;
    ComboBox<String> Time_G;

    public Group(String ID_G, String Count_G, ComboBox<String> Day1_G, ComboBox<String> Day2_G, ComboBox<String> Time_G) {
        this.ID_G = ID_G;
        this.Count_G = Count_G;
        this.Day1_G = Day1_G;
        this.Day2_G = Day2_G;
        this.Time_G = Time_G;
    }

    public String getID_G() {
        return ID_G;
    }

    public void setID_G(String ID_G) {
        this.ID_G = ID_G;
    }

    public String getCount_G() {
        return Count_G;
    }

    public void setCount_G(String Count_G) {
        this.Count_G = Count_G;
    }

    public ComboBox<String> getDay1_G() {
        return Day1_G;
    }

    public void setDay1_G(ComboBox<String> Day1_G) {
        this.Day1_G = Day1_G;
    }
    
    public void setDay1_G_S(String Day1_G) {
        this.Day1_G.getSelectionModel().select(Day1_G);
    }
    public void setDay2_G_S(String Day1_G) {
        this.Day2_G.getSelectionModel().select(Day1_G);
    }
    public void settime_S(String Day1_G) {
        this.Time_G.getSelectionModel().select(Day1_G);
    }

    public ComboBox<String> getDay2_G() {
        return Day2_G;
    }

    public void setDay2_G(ComboBox<String> Day2_G) {
        this.Day2_G = Day2_G;
    }

    public ComboBox<String> getTime_G() {
        return Time_G;
    }

    public void setTime_G(ComboBox<String> Time_G) {
        this.Time_G = Time_G;
    }

    

    
}
