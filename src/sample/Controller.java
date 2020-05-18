package sample;

import DataSource.Datasource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

import javax.sql.DataSource;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.ResourceBundle;

public class Controller implements Initializable {

    public TableView<AddVegetables> tableVegetables;
    public TableView<AddMeat> tableMeat;
    public TableView<AddDiary> tableDiary;

    public TableColumn<AddVegetables, String> colVegetables;
    public TableColumn<AddDiary, String> colDiary;
    public TableColumn<AddMeat, String> colMeat;

    public TextField textVegetables;
    public TextField textMeat;
    public TextField textDiary;
    public Button addMeat;
    public Button addDiary;
    public Button addVegetables;
    public Button removeIngredient;

    public Button recepiesBoard;
    public Button ingredientsBoard;
    public Button nutritionBoard;
    public Label labelPrincipal;
    public Pane nutritionPanel;
    public Pane recepiesPanel;
    public Pane ingredientsPanel;
    public Label warningLabel;
    public static int flagIngredientsPushed = 0;

    public TableColumn<Recepies, String> recipesRecipeColumn;
    public TableColumn<Recepies, String>  recipesVegetableColumn;
    public TableColumn<Recepies, String>  recipesDiaryColumn;
    public TableColumn<Recepies, String>  recipesMeatColumn;
    public TableColumn<Recepies, String>  recipesTimeColumn;
    public TableView <Recepies>recipesTable;

    ObservableList<AddVegetables> observableListV = FXCollections.observableArrayList();
    ObservableList<AddMeat> observableListM = FXCollections.observableArrayList();
    ObservableList<AddDiary> observableListD = FXCollections.observableArrayList();
    ObservableList<Recepies> observableListRec = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colVegetables.setCellValueFactory(new PropertyValueFactory<>("vegetables"));
        colMeat.setCellValueFactory(new PropertyValueFactory<>("meat"));
        colDiary.setCellValueFactory(new PropertyValueFactory<>("diary"));
        tableVegetables.setItems(observableListV);
        tableMeat.setItems(observableListM);
        tableDiary.setItems(observableListD);


        ingredientsPanel.toFront();
        warningLabel.setVisible(false);
        flagIngredientsPushed = 0;
    }



    public void actionAddVegetables(ActionEvent actionEvent) {

        AddVegetables veg = new AddVegetables(textVegetables.getText());
        tableVegetables.getItems().add(veg);
        try {
            String text = textVegetables.getText();
            Statement statement = Datasource.getInstance().connection.createStatement();
//            ResultSet result = statement.executeQuery("select Nutrition from Ingredients where IngrName = '" + text + "'");
//            int calories = result.getInt("Nutrition");
//            System.out.println(calories);
            statement.execute("insert into Vegetables (VegetableName ,Quantity, Calories) values ('" + text +
                    "'," + (int) (Math.random() * 150) + "," + (int) (Math.random() * 100) + ")");
            //result.close();
            statement.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong" + e.getMessage() + e.getStackTrace());
        }
        textDiary.clear();
        textMeat.clear();
        textVegetables.clear();
        warningLabel.setVisible(false);
    }

    public void actionAddDiary(ActionEvent actionEvent) {
        AddDiary diary = new AddDiary(textDiary.getText());
        tableDiary.getItems().add(diary);
        try {
            Statement statement = Datasource.getInstance().connection.createStatement();
            statement.execute("insert into Diary (DiaryName ,Quantity, Calories) values ('" + textDiary.getText() +
                    "'," + (int) (Math.random() * 150) + "," + (int) (Math.random() * 100) + ")");
//            statement.execute("insert into Ingredients( IngrName, Category,Nutrition) values(' " + textDiary.getText() +
//                    "', 'diary', 'calories')");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong" + e.getMessage() + e.getStackTrace());
        }
        textDiary.clear();
        textMeat.clear();
        textVegetables.clear();
        warningLabel.setVisible(false);
    }

    public void actionAddMeat(ActionEvent actionEvent) {
        AddMeat meat = new AddMeat(textMeat.getText());
        tableMeat.getItems().add(meat);
        try {
            Statement statement = Datasource.getInstance().connection.createStatement();
            statement.execute("insert into Meat (MeatName ,Quantity, Calories) values ('" + textMeat.getText() +
                    "'," + (int) (Math.random() * 150) + "," + (int) (Math.random() * 100) + ")");
//            statement.execute("insert into Ingredients(IngrName, Category,Nutrition) values(' " + textMeat.getText() +
//                    "', 'meat', 'protein')");
            statement.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong" + e.getMessage() + e.getStackTrace());
        }
        textDiary.clear();
        textMeat.clear();
        textVegetables.clear();
        warningLabel.setVisible(false);
    }

    public void actionRemove(ActionEvent actionEvent) {
        ObservableList<AddVegetables> singleProduct, allProducts;
        ObservableList<AddDiary> singleProduct1, allProducts1;
        ObservableList<AddMeat> singleProduct2, allProducts2;

        try {
            allProducts = tableVegetables.getItems();
            singleProduct = tableVegetables.getSelectionModel().getSelectedItems();
            Statement statement = Datasource.getInstance().connection.createStatement();
            AddVegetables objectVeg = singleProduct.get(0);
            String vegetableToDelete = objectVeg.vegetablesProperty().get();
            if (vegetableToDelete.isEmpty() == false) {
               // statement.execute("delete from Recipes where VegetableName='" + vegetableToDelete + "'");
                statement.execute("delete from Vegetables where VegetableName='" + vegetableToDelete + "'");
            }
            singleProduct.forEach(allProducts::remove);
            statement.close();
        } catch (RuntimeException | SQLException e) {
            warningLabel.setVisible(true);
            e.getStackTrace();
            e.getMessage();
        }
        try {
            allProducts1 = tableDiary.getItems();
            singleProduct1 = tableDiary.getSelectionModel().getSelectedItems();

            Statement statement = Datasource.getInstance().connection.createStatement();
            AddDiary objectDiary = singleProduct1.get(0);
            String diaryToDelete = objectDiary.diaryProperty().get();
            if (diaryToDelete.isEmpty() == false) {
                statement.execute("delete from Diary where DiaryName='" + diaryToDelete + "'");
                //statement.execute("delete from Ingredients where IngrName='" + diaryToDelete + "'");}
            }
            singleProduct1.forEach(allProducts1::remove);
            statement.close();
        } catch (RuntimeException | SQLException e) {
            warningLabel.setVisible(true);
            e.getStackTrace();
            e.getMessage();
        }

        try {
            allProducts2 = tableMeat.getItems();
            singleProduct2 = tableMeat.getSelectionModel().getSelectedItems();
            Statement statement = Datasource.getInstance().connection.createStatement();
            AddMeat objectMeat = singleProduct2.get(0);
            String meatToDelete = objectMeat.meatProperty().get();
            if (meatToDelete.isEmpty() == false) {
                statement.execute("delete from Meat where MeatName='" + meatToDelete + "'");
                //statement.execute("delete from Ingredients where IngrName='" + meatToDelete + "'");
            }
            singleProduct2.forEach(allProducts2::remove);
            statement.close();

        } catch (RuntimeException | SQLException e) {
            warningLabel.setVisible(true);
            e.getStackTrace();
            e.getMessage();
        }

    }

    public void dashIngredients(ActionEvent actionEvent) {
        labelPrincipal.setText("What ingredients are you adding today?");
        ingredientsPanel.toFront();
        if (flagIngredientsPushed == 0) {
            try {
                Statement statement = Datasource.getInstance().connection.createStatement();
                Statement statement1 = Datasource.getInstance().connection.createStatement();
                Statement statement2 = Datasource.getInstance().connection.createStatement();
                ResultSet takeVegetables = statement.executeQuery("select *from Vegetables");
                ResultSet takeMeat = statement1.executeQuery("select * from Meat");
               ResultSet takeDiary = statement2.executeQuery("select * from Diary");

                while (takeVegetables.next()) {
                    takeVegetables.getString("VegetableName");
                    AddVegetables veggiInitial = new AddVegetables( takeVegetables.getString("VegetableName"));
                    tableVegetables.getItems().add(veggiInitial);
                }

                while (takeDiary.next()) {
                    takeDiary.getString("DiaryName");
                    AddDiary diaryInitial = new AddDiary(takeDiary.getString("DiaryName"));
                    tableDiary.getItems().add(diaryInitial);
                }

                while (takeMeat.next()) {
                    takeMeat.getString("MeatName");
                    AddMeat meatInitial = new AddMeat(takeMeat.getString("MeatName"));
                    tableMeat.getItems().add(meatInitial);
                }
                takeDiary.close();
                takeMeat.close();
                takeVegetables.close();
                statement.close();
            } catch (SQLException e) {
                e.getStackTrace();
                e.getMessage();
            }
        }
        flagIngredientsPushed = 1;
    }

    public void dashRecepies(ActionEvent actionEvent) {
        labelPrincipal.setText("Here are some recepies just good for you!");
        recepiesPanel.toFront();

//      try {
//          Statement statement = Datasource.getInstance().connection.createStatement();
//          ResultSet takeRecipes = statement.executeQuery("select Meat.MeatName, Vegetables.VegetableName, Diary.DiaryName, Time from Recepies" +
//                  " inner join Meat on Meat_ID = id_meat " +
//                  "inner join Vegetables on Vegetable_ID=id_vegetable " +
//                  "inner join Diary on id_diary = Diary_ID");
//
//
//          while(takeRecipes.next()){
//              String recipeName = takeRecipes.getString("VegetableName");
//              String meatName = takeRecipes.getString("MeatName");
//              String diaryName= takeRecipes.getString("DiaryName");
//              String vegetableName = takeRecipes.getString("VegetableName");
//              String time = takeRecipes.getString("Time");
//              System.out.println(recipeName);
////              AddVegetables veg = new AddVegetables(textVegetables.getText());
////              tableVegetables.getItems().add(veg);
////
////              Recepies reci = new Recepies(recipeName,vegetableName,diaryName,meatName,time);
////              tableVegetables.getItems().add(reci);
//
//          }
//
//      } catch (SQLException e) {
//          e.printStackTrace();
//      }

    }

    public void dashNutrition(ActionEvent actionEvent) {
        labelPrincipal.setText("Let's burn some calories!");
        nutritionPanel.toFront();
    }
}







