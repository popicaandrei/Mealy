package sample;

import DataSource.Datasource;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicReferenceArray;

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
    public static int flagRecepiesPushed = 0;

    public TableColumn<Recepies, String> recipesRecipeColumn;
    public TableColumn<Recepies, String> recipesVegetableColumn;
    public TableColumn<Recepies, String> recipesDiaryColumn;
    public TableColumn<Recepies, String> recipesMeatColumn;
    public TableColumn<Recepies, String> recipesTimeColumn;
    public TableView<Recepies> recipesTable;
    public Button showIngCalories;
    public PieChart caloriesChart;
    public PieChart quantityChart;
    public Label diaryLabel;
    public Label vegetableLabel;
    public Label meatLabel;
    public Label recipeLabel;
    public Text textDiaryNutrition;
    public Text textMeatNutrition;
    public Text textVegetablesNutrition;
    public Text textRecipeNutrition;
    public Button updateQuantity;

    ObservableList<AddVegetables> observableListV = FXCollections.observableArrayList();
    ObservableList<AddMeat> observableListM = FXCollections.observableArrayList();
    ObservableList<AddDiary> observableListD = FXCollections.observableArrayList();
    ObservableList<Recepies> observableListRec = FXCollections.observableArrayList();
    ObservableList<AddVegetables> singleProduct, allProducts;
    ObservableList<AddDiary> singleProduct1, allProducts1;
    ObservableList<AddMeat> singleProduct2, allProducts2;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        colVegetables.setCellValueFactory(new PropertyValueFactory<>("vegetables"));
        colMeat.setCellValueFactory(new PropertyValueFactory<>("meat"));
        colDiary.setCellValueFactory(new PropertyValueFactory<>("diary"));
        tableVegetables.setItems(observableListV);
        tableMeat.setItems(observableListM);
        tableDiary.setItems(observableListD);

        recipesRecipeColumn.setCellValueFactory(new PropertyValueFactory<>("recipeName"));
        recipesVegetableColumn.setCellValueFactory(new PropertyValueFactory<>("recipeVegetable"));
        recipesMeatColumn.setCellValueFactory(new PropertyValueFactory<>("recipeMeat"));
        recipesDiaryColumn.setCellValueFactory(new PropertyValueFactory<>("recipeDiary"));
        recipesTimeColumn.setCellValueFactory(new PropertyValueFactory<>("recipeTime"));
        recipesTable.setItems(observableListRec);


        textDiaryNutrition.setVisible(false);
        textMeatNutrition.setVisible(false);
        textVegetablesNutrition.setVisible(false);
        textRecipeNutrition.setVisible(false);

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
            statement.execute("insert into Vegetables (VegetableName ,Quantity, Calories) values ('" + text +
                    "'," + (int) (Math.random() * 150) + "," + (int) (Math.random() * 100) + ")");

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
            statement.close();
        } catch (SQLException e) {
            System.out.println("Something went wrong" + e.getMessage() + e.getStackTrace());
        }
        textDiary.clear();
        textMeat.clear();
        textVegetables.clear();
        warningLabel.setVisible(false);
    }


    public void actionUpdate(ActionEvent actionEvent) {
        try {
            allProducts = tableVegetables.getItems();
            singleProduct = tableVegetables.getSelectionModel().getSelectedItems();
            Statement statement = Datasource.getInstance().connection.createStatement();
            AddVegetables objectVeg = singleProduct.get(0);
            String vegetableToUpdate = objectVeg.vegetablesProperty().get();

            statement.execute("update Vegetables set Quantity=" + textVegetables.getText() + " where VegetableName= '" + vegetableToUpdate + "'");
            tableVegetables.getSelectionModel().select(null);
            textVegetables.clear();
            warningLabel.setText("updated!");
            warningLabel.setVisible(true);
            statement.close();

        } catch (RuntimeException | SQLException e) {
            e.getStackTrace();
            e.getMessage();
        }

        try {
            allProducts1 = tableDiary.getItems();
            singleProduct1 = tableDiary.getSelectionModel().getSelectedItems();
            Statement statement = Datasource.getInstance().connection.createStatement();
            AddDiary objectDiary = singleProduct1.get(0);
            String diaryToUpdate = objectDiary.diaryProperty().get();

            statement.execute("update Diary set Quantity=" + textDiary.getText() + " where DiaryName= '" + diaryToUpdate + "'");
            tableDiary.getSelectionModel().select(null);
            textDiary.clear();
            warningLabel.setText("updated!");
            warningLabel.setVisible(true);
            tableDiary.getSelectionModel().select(null);
            statement.close();
        } catch (RuntimeException | SQLException e) {
            e.getStackTrace();
            e.getMessage();
        }

        try {
            allProducts2 = tableMeat.getItems();
            singleProduct2 = tableMeat.getSelectionModel().getSelectedItems();
            Statement statement = Datasource.getInstance().connection.createStatement();
            AddMeat objectMeat = singleProduct2.get(0);
            String meatToUpdate = objectMeat.meatProperty().get();

            statement.execute("update Meat set Quantity=" + textMeat.getText() + " where MeatName= '" + meatToUpdate + "'");
            tableMeat.getSelectionModel().select(null);
            textMeat.clear();
            warningLabel.setText("updated!");
            warningLabel.setVisible(true);
            tableMeat.getSelectionModel().select(null);
            statement.close();

        } catch (RuntimeException | SQLException e) {
            e.getStackTrace();
            e.getMessage();
        }
    }


    public void actionRemove(ActionEvent actionEvent) {
        warningLabel.setVisible(false);
        try {
            allProducts = tableVegetables.getItems();
            singleProduct = tableVegetables.getSelectionModel().getSelectedItems();
            Statement statement = Datasource.getInstance().connection.createStatement();
            AddVegetables objectVeg = singleProduct.get(0);
            String vegetableToDelete = objectVeg.vegetablesProperty().get();

            ResultSet result = statement.executeQuery("select id_vegetable from Vegetables where VegetableName='" + vegetableToDelete + "'");
            int idToDelete = result.getInt("id_vegetable");
            statement.execute("delete from Recipe where Vegetable_ID=" + idToDelete + "");

            if (!vegetableToDelete.isEmpty()) {
                statement.execute("delete from Vegetables where VegetableName='" + vegetableToDelete + "'");
            }
            singleProduct.forEach(allProducts::remove);
            tableVegetables.getSelectionModel().select(null);
            result.close();
            statement.close();

        } catch (RuntimeException | SQLException e) {
            warningLabel.setText("deleted!");
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

            ResultSet result1 = statement.executeQuery("select id_diary from Diary where DiaryName='" + diaryToDelete + "'");
            int idToDelete = result1.getInt("id_diary");

            statement.execute("delete from Recipe where Diary_ID=" + idToDelete + "");
            if (!diaryToDelete.isEmpty()) {
                statement.execute("delete from Diary where DiaryName='" + diaryToDelete + "'");
            }

            singleProduct1.forEach(allProducts1::remove);
            tableDiary.getSelectionModel().select(null);
            result1.close();
            statement.close();
        } catch (RuntimeException | SQLException e) {
            warningLabel.setText("deleted!");
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

            ResultSet result2 = statement.executeQuery("select id_meat from Meat where MeatName='" + meatToDelete + "'");
            int idToDelete = result2.getInt("id_meat");
            statement.execute("delete from Recipe where Meat_ID=" + idToDelete + "");
            if (!meatToDelete.isEmpty()) {
                statement.execute("delete from Meat where MeatName='" + meatToDelete + "'");
            }
            singleProduct2.forEach(allProducts2::remove);
            tableMeat.getSelectionModel().select(null);
            result2.close();
            statement.close();

        } catch (RuntimeException | SQLException e) {
            warningLabel.setText("deleted!");
            warningLabel.setVisible(true);
            e.getStackTrace();
            e.getMessage();
        }

    }

    public void dashIngredients(ActionEvent actionEvent) {
        labelPrincipal.setText("What ingredients are you adding today?");
        ingredientsPanel.toFront();
        flagRecepiesPushed = 0;
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
                    AddVegetables veggiInitial = new AddVegetables(takeVegetables.getString("VegetableName"));
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
        recipesTable.getItems().clear();
        refresh();
        try {
            Statement statement = Datasource.getInstance().connection.createStatement();
            statement.execute("create view if not exists RecipeView1 as " +
                    "select  Recipe.RecipeName, Meat.MeatName, Vegetables.VegetableName, Diary.DiaryName, Time from Recipe" +
                    " inner join Meat on Meat_ID = id_meat" +
                    " inner join Vegetables on Vegetable_ID=id_vegetable" +
                    " inner join Diary on id_diary = Diary_ID");

            ResultSet takeRecipes = statement.executeQuery("select * from RecipeView1");

            while (takeRecipes.next()) {
                String recipeName = takeRecipes.getString("RecipeName");
                String meatName = takeRecipes.getString("MeatName");
                String diaryName = takeRecipes.getString("DiaryName");
                String vegetableName = takeRecipes.getString("VegetableName");
                String time = takeRecipes.getString("Time");

                Recepies recipe = new Recepies(recipeName, vegetableName, diaryName, meatName, time);
                recipesTable.getItems().add(recipe);
            }
            statement.close();
            takeRecipes.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void dashNutrition(ActionEvent actionEvent) {
        labelPrincipal.setText("Let's burn some calories!");
        nutritionPanel.toFront();
    }

    public void refresh() {

        try {
            Statement statement = Datasource.getInstance().connection.createStatement();
            Statement statement1 = Datasource.getInstance().connection.createStatement();
            Statement statement2 = Datasource.getInstance().connection.createStatement();
            Statement statement3 = Datasource.getInstance().connection.createStatement();
            Statement statement4 = Datasource.getInstance().connection.createStatement();

            statement.execute("create view if not exists RefreshView as select *from Catalogue where " +
                    " (Catalogue.MeatName in (select MeatName from Meat)) and " +
                    "(Catalogue.DiaryName in (select DiaryName from Diary)) and " +
                    "(Catalogue.VegetableName in (select VegetableName from Vegetables));");

            ResultSet takeRefresh = statement.executeQuery("select * from RefreshView");
            ResultSet takeMeatID = null;
            ResultSet takeVegID = null;
            ResultSet takeDiaryID = null;

            while (takeRefresh.next()) {
                String recipeName = takeRefresh.getString("RecipeName");
                String meatName = takeRefresh.getString("MeatName");
                String diaryName = takeRefresh.getString("DiaryName");
                String vegetableName = takeRefresh.getString("VegetableName");
                String time = takeRefresh.getString("Time");
                String calories = takeRefresh.getString("Calories");

                takeMeatID = null;
                takeMeatID = statement1.executeQuery("select * from Meat where MeatName ='" + meatName + "'");
                int idMeat = takeMeatID.getInt("id_meat");


                takeVegID = null;
                takeVegID = statement2.executeQuery("select * from Vegetables where VegetableName ='" + vegetableName + "'");
                int idVeg = takeVegID.getInt("id_vegetable");

                takeDiaryID = null;
                takeDiaryID = statement3.executeQuery("select * from Diary where DiaryName ='" + diaryName + "'");
                int idDiary = takeDiaryID.getInt("id_diary");


//                statement4.execute("insert into Recipe (RecipeName, Meat_ID, Vegetable_ID , Diary_ID, Time, Calories) select " +
//                        "'" + recipeName + "'," + idMeat + "," + idVeg + "," + idDiary + "," + time + "," + calories + " " +
//                        "if not exists (select * from Recipe)");

                try {
                    statement4.execute("insert into Recipe (RecipeName, Meat_ID, Vegetable_ID , Diary_ID, Time, Calories) values(" +
                            "'" + recipeName + "'," + idMeat + "," + idVeg + "," + idDiary + "," + time + "," + calories + ") ");
                } catch (SQLException e) {
                    System.out.println("dfn");
                }

            }
            takeMeatID.close();
            takeDiaryID.close();
            takeVegID.close();
            statement2.close();
            statement4.close();
            statement3.close();
            statement1.close();
            takeRefresh.close();
            statement.close();

        } catch (SQLException e) {
            e.getMessage();
            System.out.println("cdhcbwhj");
        }
    }

    public void actionShowIngCalories(ActionEvent actionEvent) {
        textDiaryNutrition.setVisible(true);
        textMeatNutrition.setVisible(true);
        textVegetablesNutrition.setVisible(true);
        textRecipeNutrition.setVisible(true);

        try {
            Statement statement = Datasource.getInstance().connection.createStatement();
            Statement statement1 = Datasource.getInstance().connection.createStatement();

            ResultSet takeCaloriesMeat = statement.executeQuery("select sum(Calories) from Meat");
            ResultSet takeQuantityMeat = statement1.executeQuery("select sum(Quantity) from Meat");
            int caloriesMeat = takeCaloriesMeat.getInt(1);
            double quantityMeat = takeQuantityMeat.getInt(1);


            ResultSet takeCaloriesDiary = statement.executeQuery("select sum(Calories) from Diary");
            ResultSet takeQuantityDiary = statement1.executeQuery("select sum(Quantity) from Diary");
            int caloriesDiary = takeCaloriesDiary.getInt(1);
            double quantityDiary = takeQuantityDiary.getInt(1);


            ResultSet takeCaloriesVegetable = statement.executeQuery("select sum(Calories) from Vegetables");
            ResultSet takeQuantityVegetable = statement1.executeQuery("select sum(Quantity) from Vegetables");
            int caloriesVeg = takeCaloriesVegetable.getInt(1);
            double quantityVeg = takeQuantityVegetable.getInt(1);

            ResultSet takeCaloriesRecipe = statement.executeQuery("select sum(Calories) from Recipe");
            int caloriesRecipes = takeCaloriesRecipe.getInt(1);


            diaryLabel.setText("C= " + caloriesDiary + ", Q=" + quantityDiary);
            meatLabel.setText("C= " + caloriesMeat + ", Q=" + quantityMeat);
            vegetableLabel.setText("C= " + caloriesVeg + ", Q=" + quantityVeg);
            recipeLabel.setText("C= " + caloriesRecipes);

            ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                    new PieChart.Data("Meat", caloriesMeat),
                    new PieChart.Data("Vegetables", caloriesVeg),
                    new PieChart.Data("Diary", caloriesDiary)
            );
            caloriesChart.setData(pieChartData);

            ObservableList<PieChart.Data> pieChartData2 = FXCollections.observableArrayList(
                    new PieChart.Data("Meat", quantityMeat),
                    new PieChart.Data("Vegetables", quantityVeg),
                    new PieChart.Data("Diary", quantityDiary)
            );
            quantityChart.setData(pieChartData2);

            takeCaloriesMeat.close();
            takeQuantityMeat.close();
            statement.close();
            statement1.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}







