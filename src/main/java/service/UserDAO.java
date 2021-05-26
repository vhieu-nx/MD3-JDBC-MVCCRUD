package service;

import model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO {
    private static String jdbcURL = "jdbc:mysql://localhost:3306/jspservlet";
    private  static String jdbcUsername = "root";
    private static String jdbcPassword = "123456";

    private static final String INSERT_USER_SQL = "insert into tblregistration values(?,?,?,?)";
    private static final String CHECK_LOGIN = "Select *from tblregistration where username = ? and password =?";
    private static final String SELECT_USER_BY_ID = "Select *from tblregistration where username = ?";
    private static final String SELECT_USER_BY_NAME = "SELECT *FROM tblregistration WHERE lasname like ? ";
    private static final String SELECT_ALL_USER = "select * from tblregistration";
    private static final String DELETE_USER_SQL = "delete from tblregistration where username = ?;";
    private static final String UPDATE_USER_SQL = "update tblregistration set password= ?, lasname =?, isAdmin = ? where username = ?;";

    protected  static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);

        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
        return connection;
    }


    @Override
    public void insert(User user) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL);
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getPassword());
            preparedStatement.setString(3, user.getLastname());
            preparedStatement.setBoolean(4, user.isRoles());
            preparedStatement.executeUpdate();

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public User selectUserByID(String id) {
        User user = null;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);
            preparedStatement.setString(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String name = rs.getString("password");
                String pass = rs.getString("lasname");
                boolean roles = rs.getBoolean("isAdmin");

                user = new User(id, name, pass,roles);
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return user;
    }

    @Override
    public List<User> selectUserByName(String inputSearch) {
        String search = "%" + inputSearch + "%";
        List<User> users = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_NAME);
            preparedStatement.setString(1, search);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String  username = rs.getString("username");
                String password = rs.getString("password");
                String lastname = rs.getString("lasname");
                boolean roles = rs.getBoolean("isAdmin");

                users.add(new User(username,password,lastname,roles));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return users;
    }

    @Override
    public List<User> selectAllUser() {
        List<User> users = new ArrayList<>();
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USER);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String  username = rs.getString("username");
                String password = rs.getString("password");
                String lastname = rs.getString("lasname");
                boolean roles = rs.getBoolean("isAdmin");
                users.add(new User(username, password, lastname, roles));
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return users;
    }

    @Override
    public  boolean delete(String id) {
        boolean rowDelete = false;
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL);
            preparedStatement.setString(1,id);
            rowDelete = preparedStatement.executeUpdate() >0;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowDelete;
    }

    @Override
    public boolean update(User user) {
        boolean rowUpdate = false;
        Connection connection = getConnection();
        PreparedStatement preparedStatement =  null;
        try {
            preparedStatement = connection.prepareStatement(UPDATE_USER_SQL);
            preparedStatement.setString(1,user.getPassword());
            preparedStatement.setString(2,user.getLastname());
            preparedStatement.setBoolean(3,user.isRoles());
            preparedStatement.setString(4,user.getUsername());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return rowUpdate;
    }

    @Override
    public  boolean checkLogin(String username, String password) {
        Connection connection = getConnection();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(CHECK_LOGIN);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            boolean result = resultSet.next();
            resultSet.close();
            preparedStatement.close();
            connection.close();
            if (result){
                return true;
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }





}
