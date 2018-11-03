import java.util.List;

public interface ForumCapable {
    public boolean login(String username, String password);
    public void logout();
    public void exitCategory();
    public void regiter(String username, String password, String mail);
    public void insertCategory(String subject);
    public List<Category> getAllCategories();
    public boolean enterCategory(Integer category_id);
    public void cleanupEntireDB();
}
