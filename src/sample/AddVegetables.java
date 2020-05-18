package sample;

import javafx.beans.property.SimpleStringProperty;

public class AddVegetables {
    private SimpleStringProperty vegetables;

    public AddVegetables(String veg) {
        this.vegetables = new SimpleStringProperty(veg);}

    public String getVegetables() {
        return vegetables.get();
    }

    public SimpleStringProperty vegetablesProperty() {
        return vegetables;
    }

    public void setVegetables(String vegetables) {
        this.vegetables.set(vegetables);
    }

    @Override
    public String toString() {
        return "AddVegetables{" +
                "vegetables=" + vegetables +
                '}';
    }
}
