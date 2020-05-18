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

//        public static void main(String[] args) {
//
//            try{
//                Connection connection = DriverManager.getConnection("jdbc:sqlite:A:\\mealy\\Mealy\\MealyDB.db");
//                Statement statement = connection.createStatement();
//
//                //statement.execute("insert into Recepies (id_ingr,RecepyName,MeatName,VegetableName,DiaryName,Time,Calories) " +
//                    //"values ('15','Spaghetti Bolognese','Chopped Beaf','Tomatoes','',15,200)");
//
//               // statement.execute("insert into Diary(DiaryName, Calories) values ('ppk',32)");
//                //statement.execute("insert into Diary(DiaryName, Calories) values ('ldsdfs7',32)");
//
//
////                statement.execute("update Recepies set RecepyName ='Spaghetti Carbonara', VegetableName='Garlic',MeatName='Beaf'" +
////                        "DiaryName='SourCream', Time= 15,Calories=320 where id_recipe=1 ");
//
//
//
//
////                statement.execute("delete from Diary where Calories=32");
//
////            statement.execute("insert into contacts (name,phone,email) values ('ppk',334344,'mail2')");
////            statement.execute("insert into contacts (name,phone,email) values ('vlad',8987,'mail3')");
//
////            statement.execute("update contacts set phone=90 where name='ppk'");
////            statement.execute("delete from contacts where name ='andre'");
//
////            statement.execute("select *from contacts");
//////            ResultSet result = statement.getResultSet();
//
////                ResultSet result =statement.executeQuery("select *from contacts");
////                while(result.next()){
////                    System.out.println(result.getString("name")+ "  "+
////                            result.getInt("phone")+" "+
////                            result.getString("email"));
////
////                }
////                result.close();
//                statement.close();
//                connection.close();
//
//            }catch (SQLException e){
//                System.out.println("Something went wrong"+e.getMessage()+e.getStackTrace());
//            }
//        }
//    }


