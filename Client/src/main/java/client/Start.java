package client;

import org.springframework.stereotype.Component;
import programm.ConnectToSQL;
import server.Server;
import config.AppCon;
import java.sql.SQLException;

@Component
public class Start {
    public static void main(String[] args) throws SQLException {
        ConnectToSQL connectToSQL = AppCon
                .annotationConfig()
                .getBean("connectToSQL", ConnectToSQL.class);
        connectToSQL.connect();
        CheckUser checkUser =  new CheckUser();
        Server.main(null);

    }
}
