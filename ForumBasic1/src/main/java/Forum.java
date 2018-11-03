import org.springframework.context.ApplicationContext;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Forum implements ForumCapable {
    private static final Logger LOGGER = Logger.getLogger(Forum.class.getName());
    private MySQLHandler sqlHandler;
    private User userLoggedIn = null;
    private Category currentCategory = null;
    private ForumStrings forumStrings;


    public Forum(String dbUrl, String userDb, String password, ApplicationContext context) throws SQLException {
        sqlHandler = new MySQLHandler(dbUrl, userDb, password);
        this.forumStrings = context.getBean("getForumString", ForumStrings.class);
    }

    @Override
    public boolean login(String username, String password) {
        boolean loginSuccesfull = false;
        User user = sqlHandler.getUserByUsername(username);
        if (user != null && user.isThisPasswordMine(password)) {
            LOGGER.log(Level.INFO, username + forumStrings.getLogginSucces());
            loginSuccesfull = true;
        } else {
            LOGGER.log(Level.WARNING, "Invalid login");
        }
        return loginSuccesfull;
    }

    @Override
    public void logout() {
        userLoggedIn = null;
    }

    @Override
    public void exitCategory() {
        currentCategory = null;
    }

    @Override
    public void regiter(String username, String password, String mail) {
        sqlHandler.insertUser(username, password, mail);
    }

    @Override
    public void insertCategory(String subject) {
        LOGGER.log(Level.INFO, "Insert subjecct for the new category! ");
        sqlHandler.insertNewCategory(subject, userLoggedIn.getId());
    }

    @Override
    public List<Category> getAllCategories() {
        return sqlHandler.getAllCategoryes();
    }

    @Override
    public boolean enterCategory(Integer category_id) {
        boolean enterSuccesfully = false;
        Category categ = sqlHandler.getCategoryById(category_id);
        if (categ == null) {
            LOGGER.log(Level.SEVERE, "Invalid Category");
        } else {
            currentCategory = categ;
            enterSuccesfully = true;
        }
        return enterSuccesfully;
    }

    @Override
    public void cleanupEntireDB() {
        sqlHandler.cleanUp();
    }

    public String whoLoggedIn() {
        return userLoggedIn != null ? userLoggedIn.getUsername() : " ";
    }

    public String wichCategory() {
        return currentCategory != null ? currentCategory.getSubject() : " ";
    }
}
