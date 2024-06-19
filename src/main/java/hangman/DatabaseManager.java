package hangman;

import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.sql.*;
import java.util.ArrayList;

// Use JDBC to connect to your database and run queries

public class DatabaseManager {
    private static final String url = "jdbc:postgresql://localhost:5432/Hangman";
    private static final String username = "postgres";
    private static final String password = "Anet1383";
    private static Connection connection;

    public static Connection connect() throws SQLException {
        try {
            Class.forName("org.postgresql.Driver");
            System.out.println("vasl shod");
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        return DriverManager.getConnection(url, username, password);
    }

    public static void dataSignUp(String username, String password){
        try {
            Connection connection2 = connect();
            String query = "INSERT INTO userinfo (username, password) VALUES (?, ?)";
            PreparedStatement ps = connection2.prepareStatement(query);
            ps.setString(1, username);
            ps.setString(2, password);
            ps.executeUpdate();
            ps.close();
            connection2.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public static boolean validUserName(String username){;
        try {
            Connection connection2 = connect();
            String query = "SELECT USERNAME FROM userInfo";
            Statement ps = connection2.createStatement();
            ResultSet rs = ps.executeQuery(query);
            connection2.close();
            ArrayList<String> users = new ArrayList<>();
            while (rs.next()) {
                users.add(rs.getString("USERNAME"));
            }
            for (int i = 0; i < users.size(); i++){
                if (username.equals(users.get(i))){
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean validPassword(String username, String password){
        try {
            Connection connection2 = connect();
            String query = "SELECT PASSWORD FROM userInfo WHERE USERNAME = \'" + username + "\'";
            Statement ps = connection2.createStatement();
            ResultSet rs = ps.executeQuery(query);
            connection2.close();
            if (rs.next()){
                if (rs.getString("PASSWORD").equals(password)) {
                    return true;
                }
            }
            return false;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void gameInfo(String gameid,String username ,String word , int wrongGuess , int time , Boolean win){
        try {
            Connection connection2 = connect();
            String query = "INSERT INTO gameinfo (gameid ,username, word , wrongguesses , time , win) VALUES (?,?, ?, ?, ?, ?)";
            PreparedStatement ps = connection2.prepareStatement(query);
            ps.setString(1,gameid);
            ps.setString(2, username);
            ps.setString(3, word);
            ps.setInt(4,wrongGuess);
            ps.setInt(5,time);
            ps.setBoolean(6,win);
            ps.executeUpdate();
            ps.close();
            connection2.close();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
    public static TableView userGameInfo(String username) throws SQLException {
        Connection connection2 = connect();
        String query = "SELECT * FROM gameinfo";
        Statement ps = connection2.createStatement();
        ResultSet rs = ps.executeQuery(query);
        connection2.close();
        TableView tableView = new TableView();
        ResultSetMetaData metaData = rs.getMetaData();
        int columnCount = metaData.getColumnCount();
        for (int i = 1; i < columnCount; i++) {
            TableColumn<String, String> column = new TableColumn<>(metaData.getColumnName(i));
            column.setCellValueFactory(data -> new SimpleObjectProperty<>(data.getValue().get(columnIndex)));
            tableView.getColumns().add(column);
        }
        ObservableList<ObservableList<Object>> data = FXCollections.observableArrayList();
        while (rs.next()) {
            if (username.equals(rs.getObject(2))) {
                ObservableList<Object> row = FXCollections.observableArrayList();
                for (int i = 1; i <= columnCount; i++) {
                    if (i != 2) {
                        row.add(rs.getObject(i));
                    }
                }
                data.add(row);
            }
        }
        tableView.setItems(data);
        return tableView;
    }
}