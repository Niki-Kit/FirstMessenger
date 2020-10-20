package client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import server.ConfigServer;
import config.ConfigBase;

@Configuration
@Import({ConfigBase.class, ConfigServer.class})
public class ConfigClient {

    @Bean
    public Client client() { return  new Client(); }
}
