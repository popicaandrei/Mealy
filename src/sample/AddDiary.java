package sample;

import javafx.beans.property.SimpleStringProperty;

public class AddDiary {

    private SimpleStringProperty diary;


    public AddDiary(String diary) {
        this.diary = new SimpleStringProperty(diary);
    }

    public String getDiary() {
        return diary.get();
    }

    public SimpleStringProperty diaryProperty() {
        return diary;
    }

    public void setDiary(String diary) {
        this.diary.set(diary);
    }
}
