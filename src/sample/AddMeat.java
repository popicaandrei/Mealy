package sample;

import javafx.beans.property.SimpleStringProperty;

public class AddMeat {
    private SimpleStringProperty meat;

    public AddMeat(String meat) {
        this.meat = new SimpleStringProperty(meat);
    }

    public String getMeat() {
        return meat.get();
    }

    public SimpleStringProperty meatProperty() {
        return meat;
    }

    public void setMeat(String meat) {
        this.meat.set(meat);
    }
}
