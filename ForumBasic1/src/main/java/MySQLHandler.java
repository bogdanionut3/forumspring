import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MySQLHandler {
    private static final Logger LOGGER = Logger.getLogger(MySQLHandler.class.getName());
    private Connection connection;
    private String dbUrl;
    private String userDb;
    private String passDb;


    MySQLHandler(String dbUrl, String userDb, String passDb) throws SQLException {
        LOGGER.log(Level.FINER, "Establishing connection... " + dbUrl + " , " + userDb + " , " + passDb);
        connection = DriverManager.getConnection(dbUrl, userDb, passDb);
    }

    public User getUserByUsername(String providedUsername) {
        User userFromDb = null;
        PreparedStatement preparedStatement;
        String sql = "SELECT*FROM user WHERE username = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, providedUsername);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int userIdFromDb = resultSet.getInt("id");
                String userNameFromDb = resultSet.getString("username");
                String passwordFromDb = resultSet.getString("password");
                String emailFromDb = resultSet.getString("mail");
                userFromDb = new User(userIdFromDb, userNameFromDb, passwordFromDb, emailFromDb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userFromDb;
    }

    public User getUserById(Integer providedId) {
        User userFromDb = null;
        PreparedStatement preparedStatement;
        String sql = "SELECT*FROM user WHERE id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, providedId);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int userIdFromDb = resultSet.getInt("id");
                String userNameFromDb = resultSet.getString("username");
                String passwordFromDb = resultSet.getNString("password");
                String emailFromDb = resultSet.getString("mail");
                userFromDb = new User(userIdFromDb, userNameFromDb, passwordFromDb, emailFromDb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userFromDb;
    }

    public boolean insertUser(String username, String password, String email) {
        String query = "INSERT INTO user (username, password, mail) VALUES (?,?,?)";
        PreparedStatement preparedStatement;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            preparedStatement.setString(3, email);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    public List<Category> getAllCategoryes() {
        String sql = "SELECT*FROM category";
        Statement statement;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            List<Category> categories = new ArrayList<Category>();
            Integer idFromDb = null;
            Integer idUserFromDb = null;
            String subjectFromdb = null;
            while (resultSet.next()) {
                idFromDb = resultSet.getInt("category_id");
                idUserFromDb = resultSet.getInt("id");
                subjectFromdb = resultSet.getString("subject");
                categories.add(new Category(idFromDb, getUserById(idUserFromDb), subjectFromdb));
            }
            return categories;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertNewCategory(String subjectRead, Integer user_id) {
        String query = "INSERT INTO category (id, subject) VALUES(?,?)";
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, user_id);
            preparedStatement.setString(2, subjectRead);
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Category getCategoryById(Integer category_id) {
        Category categ = null;
        PreparedStatement preparedStatement = null;
        String sql = "SELECT * FROM category WHERE category_id = ?";
        try {
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, category_id);
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                Integer categoryIdFromDb = result.getInt("category_id");
                Integer userIdFromDb = result.getInt("user_id");
                String subjectFromDb = result.getString("subject");
                categ = new Category(categoryIdFromDb,getUserById(userIdFromDb),subjectFromDb);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categ;
    }
    public void cleanUp(){
        String query = "DELETE FROM category";
        PreparedStatement preparedStatement = null;
        try{
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();
        query = "DELETE FROM user";
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.execute();}
        catch(SQLException e){
            e.printStackTrace();
        }
    }
}
