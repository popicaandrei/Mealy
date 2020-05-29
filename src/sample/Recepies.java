package sample;

import javafx.beans.property.SimpleStringProperty;

public class Recepies {

    private SimpleStringProperty recipeName;
    private SimpleStringProperty recipeVegetable;
    private SimpleStringProperty recipeDiary;
    private SimpleStringProperty recipeMeat;
    private SimpleStringProperty recipeTime;

    public Recepies(String recipeName, String vegetableName, String diaryName, String meatName, String time) {
        this.recipeName =new SimpleStringProperty(recipeName) ;
        this.recipeVegetable =new SimpleStringProperty(vegetableName);
        this.recipeDiary = new SimpleStringProperty(diaryName);
        this.recipeMeat = new SimpleStringProperty(meatName);
        this.recipeTime = new SimpleStringProperty(time);
    }

    public String getRecipeName() {
        return recipeName.get();
    }

    public SimpleStringProperty recipeNameProperty() {
        return recipeName;
    }

    public void setRecipeName(String recipeName) {
        this.recipeName.set(recipeName);
    }

    public String getRecipeVegetable() {
        return recipeVegetable.get();
    }

    public SimpleStringProperty recipeVegetableProperty() {
        return recipeVegetable;
    }

    public void setRecipeVegetable(String recipeVegetable) {
        this.recipeVegetable.set(recipeVegetable);
    }

    public String getRecipeDiary() {
        return recipeDiary.get();
    }

    public SimpleStringProperty recipeDiaryProperty() {
        return recipeDiary;
    }

    public void setRecipeDiary(String recipeDiary) {
        this.recipeDiary.set(recipeDiary);
    }

    public String getRecipeMeat() {
        return recipeMeat.get();
    }

    public SimpleStringProperty recipeMeatProperty() {
        return recipeMeat;
    }

    public void setRecipeMeat(String recipeMeat) {
        this.recipeMeat.set(recipeMeat);
    }

    public String getRecipeTime() {
        return recipeTime.get();
    }

    public SimpleStringProperty recipeTimeProperty() {
        return recipeTime;
    }

    public void setRecipeTime(String recipeTime) {
        this.recipeTime.set(recipeTime);
    }
}
