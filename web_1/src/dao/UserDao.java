package dao;

import com.mysql.cj.Session;
import model.User;

import javax.naming.Context;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.sql.rowset.serial.SerialStruct;
import java.sql.*;
import java.util.ArrayList;

public class UserDao {
    private static String FIND_ALL_USERS = "SELECT user_id, users.u_login, i_name, i_surname, i_middlename, i_number, i_email, pos_name FROM users inner join informations on user_id = i_user_is inner join positions on u_pos_id = position_id;";
    private static String FIND_BY_ID = "Select * from users where id=?";
    private static String FIND_BY_LOGIN = "Select user_id, users.u_login, i_name, i_surname, i_middlename, i_number, i_email, pos_name FROM users inner join informations on user_id = i_user_is inner join positions on u_pos_id = position_id where users.u_login = ?";
    private static String UPDATE_USER = "update informations set i_name=?, i_surname=?, i_middlename=?, i_number=?, i_email=?  where i_user_is=?;";
    private static String URL = "jdbc:mysql://localhost/dental?useUnicode=true&serverTimezone=UTC";
    private static String USERNAME = "root";
    private static String PASSWORD = "4549";

    private Connection connection = null;
    private PreparedStatement statement = null;
    private ResultSet resultSet = null;

    private static UserDao instance = null;
    public UserDao getInstance(){
        if (instance == null){
            instance = new UserDao();
        }
        return instance;
    }

    public ArrayList<User> findAllUsers(){
        ArrayList<User> users = new ArrayList<User>();
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(FIND_ALL_USERS);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("user_id");
                String username = resultSet.getString("u_login");
                String name = resultSet.getString("i_name");
                String surname = resultSet.getString("i_surname");
                String middlename = resultSet.getString("i_middlename");
                String phone = resultSet.getString("i_number");
                String mail = resultSet.getString("i_email");
                String role = resultSet.getString("pos_name");
                User user = new User(id, username, name, surname, middlename, phone, mail, role);
                users.add(user);

            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users;
    }

    public User findUserByLogin(String login){
        ArrayList<User> users = new ArrayList<User>();
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(FIND_BY_LOGIN);
            statement.setString(1, login);
            resultSet = statement.executeQuery();
            while (resultSet.next()){
                int id = resultSet.getInt("user_id");
                String username = resultSet.getString("u_login");
                String name = resultSet.getString("i_name");
                String surname = resultSet.getString("i_surname");
                String middlename = resultSet.getString("i_middlename");
                String phone = resultSet.getString("i_number");
                String mail = resultSet.getString("i_email");
                String role = resultSet.getString("pos_name");
                User user = new User(id, username, name, surname, middlename, phone, mail, role);
                System.out.println(user.getMail());
                users.add(user);

            }
            resultSet.close();
            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return users.get(0);
    }
    public int updateUser(int id, User user){
        int result = -1;
        try {
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            statement = connection.prepareStatement(UPDATE_USER);
            statement.setString(1, user.getName());
            statement.setString(2, user.getSurname());
            statement.setString(3, user.getMiddlename());
            statement.setString(4, user.getPhone());
            statement.setString(5, user.getMail());
            statement.setInt(6, id);
            result = statement.executeUpdate();

            statement.close();
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }




}
