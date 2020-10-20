package config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import programm.ConnectToSQL;


@Configuration
public class ConfigBase {

    @Bean
    @Value("")
    public ConnectToSQL connectToSQL() {
        return new ConnectToSQL();
    }

}
