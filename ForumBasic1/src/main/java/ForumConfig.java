import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;


    @Configuration
    @ComponentScan(basePackages = "java")
    @PropertySource("classpath:forum.propr")
    public class ForumConfig {

        @Value("${forum.LogginSucces}")
        private String login;

        @Value("${forum.invalidLoggin}")
        private String notLoggedin;


        @Bean
        public ForumStrings getForumString() {
            ForumStrings strings = new ForumStrings(login, notLoggedin);
            return strings;
        }
    }

