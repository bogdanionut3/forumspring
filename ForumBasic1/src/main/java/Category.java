import java.util.List;
import java.util.Objects;

public class Category {
    private Integer category_id;
    private List<Topic> topics;
    private User user;
    private String subject;

    public Category(User user, String subject) {
        this.user = user;
        this.subject = subject;
    }

    public Category(Integer category_id, User user, String subject) {
        this(user, subject);
        this.category_id = category_id;
    }

    public String getSubject() {
        return subject;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Category categ = (Category) obj;
        return Objects.equals(subject, categ.subject);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category_id, topics, user, subject);
    }

    @Override
    public String toString(){
        return "Category {"+
                "#"+category_id+
                ",#"+user+
                ",subject'" + subject+'\''+
                '}';
    }
}
