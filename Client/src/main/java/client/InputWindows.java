package client;

import programm.ConnectToSQL;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import static programm.ConnectToSQL.preparedStatement;

import static programm.ConnectToSQL.*;

public class InputWindows {

    private static final String GET_ALL = "SELECT * FROM users";
    public static final String INSERT_NEW = "INSERT INTO users VALUES (?,?,?,?)";
    private static int count = 1;
    private static int check = 1;
    String name;
    String surname;
    String pass;

    public InputWindows(String name, String surname, String pass) {
        this.name = name;
        this.surname = surname;
        this.pass = pass;
    }

    public void inputConnect() throws SQLException {
        preparedStatement = getConnection().prepareStatement(GET_ALL);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            if (name.equals(resultSet.getString(2))
                && surname.equals(resultSet.getString(3))
                && pass.equals(resultSet.getString(4))) {
                Client.main(null);
                check++;
            }
        }
        if (check == 1) {
            preparedStatement = ConnectToSQL.getConnection().prepareStatement(INSERT_NEW);
            ResultSet result = preparedStatement.executeQuery("SELECT id FROM users");
            while(result.next()) {
                if (result.getInt(1) == count) {
                    ++count;
                }
            }
            try {
                preparedStatement.setInt(1, count);
                preparedStatement.setString(2, name);
                preparedStatement.setString(3, surname);
                preparedStatement.setString(4, pass);
                preparedStatement.execute();
                Client.main(null);
            } catch (SQLIntegrityConstraintViolationException e) {
            e.printStackTrace();
            }
        }
        check = 1;
    }
}




