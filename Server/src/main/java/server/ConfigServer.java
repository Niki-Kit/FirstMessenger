package server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;



@Configuration
public class ConfigServer {

    @Bean
    public Server server() {
        return new Server();
    }
}
