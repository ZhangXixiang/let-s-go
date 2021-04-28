import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {
    @Bean(name = "user")
    public User user() {
        User user = new User();
        user.setUsername("pj");
        return user;
    }
}