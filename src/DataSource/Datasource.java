package DataSource;

import javax.sql.DataSource;
import java.sql.*;


public class Datasource {

    public static final String DB_NAME = "MealyDB.db";
    public static final String CONNECTION_STRING = "jdbc:sqlite:A:\\mealy\\Mealy\\MealyDB.db";

    public static final String TABLE_DIARY = "Diary";
    public static final String COLUMN_DIARY_ID_INGR = "id_ingr";
    public static final String COLUMN_DIARY_NAME = "DiaryName";
    public static final String COLUMN_DIARY_CALORIES = "Calories";

    public static final String TABLE_MEAT = "Meat";
    public static final String COLUMN_MEAT_ID_INGR = "id_ingr";
    public static final String COLUMN_MEAT_NAME = "MeatName";
    public static final String COLUMN_MEAT_PROTEINS = "Proteins";

    public static final String TABLE_INGREDIENTS = "Ingredients";
    public static final String COLUMN_INGREDIENTS_ID_INGR = "id_ingr";
    public static final String COLUMN_INGREDIENTS_NAME = "IngrName";
    public static final String COLUMN_INGREDIENTS_CATEGORY = "Category";
    public static final String COLUMN_INGREDIENTS_NUTRITION = "Nutrition";

    public static final String TABLE_VEGETABLES = "Vegetables";
    public static final String COLUMN_VEGETABLES_ID_INGR = "id_ingr";
    public static final String COLUMN_VEGETABLES_NAME = "VegetableName";
    public static final String COLUMN_VEGETABLES_CARBOHYDRATES = "Carbohydrates";

    public static final String TABLE_RECEPIES = "Vegetables";
    public static final String COLUMN_RECEPIES_ID = "id_ingr";
    public static final String COLUMN_RECEPIES_NAME = "RecipeName";
    public static final String COLUMN_RECEPIES_MEAT_NAME = "MeatName";
    public static final String COLUMN_RECEPIES_VEGETABLES_NAME = "VegetableName";
    public static final String COLUMN_RECEPIES_DIARY_NAME = "DiaryName";
    public static final String COLUMN_RECEPIES_TIME = "Time";
    public static final String COLUMN_RECEPIES_CALORIES = "Calories";

    public Connection connection;
    private static Datasource instance = new Datasource();

    private Datasource() {

    }

    public static Datasource getInstance() {
        return instance;
    }

    public boolean open() {
        try {
            connection = DriverManager.getConnection(CONNECTION_STRING);
            return true;
        } catch (SQLException e) {
            System.out.println("Couldn't connect to database: " + e.getMessage());
            return false;
        }
    }

    public void close() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            System.out.println("Couldn't close connection: " + e.getMessage());
        }
    }
}



