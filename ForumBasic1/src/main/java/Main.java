import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.logging.Logger;

public class Main {
   // private static final Logger LOGGER = Logger.getLogger(Main.class.getName());

    public static void main(String[] args) {
        AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext();
        ctx.register(ForumConfig.class);
        ctx.refresh();

         try {
          Forum f = new ConsoleBased(
               "jdbc:mysql://localhost:3306/forum",
                  "root",
                  "admin", ctx
           );
          } catch (SQLException e) {
              e.printStackTrace();
          }


        ForumStrings strings = ctx.getBean("getForumString",ForumStrings.class);
        System.out.println(strings);

    }
}
