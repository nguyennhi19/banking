package com.codegym.dao;

import com.codegym.model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDAO implements IUserDAO{
    private String jdbcURL = "jdbc:mysql://localhost:3306/demo";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Nhi09092018";

    private static final String INSERT_USERS_SQL = "INSERT INTO users" + "  (name, age, gender, address, phone, email, salary) VALUES " +
            " (?, ?, ?, ?, ?, ?, 0);";

    private static final String SELECT_USER_BY_ID = "select id, name, age, gender, address, phone, email, salary from users where id =?";
    private static final String SELECT_ALL_USERS = "select * from users";
    private static final String DELETE_USERS_SQL = "delete from users where id = ?;";
    private static final String UPDATE_USERS_SQL = "update users set name =?, age =?, gender =?, address =?, phone =?, email =?, salary =? where id = ?;";
    private static final String TRANSFER_USERS_SQL = "select id from users where  id != ?";
    private static final String UPDATE_SALARY = "UPDATE users SET salary = ? WHERE id = ?;";

    public UserDAO() {
    }


    protected Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    @Override
    public void insertUser(User user) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        try (Connection connection = getConnection(); PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getName());
            preparedStatement.setInt(2,user.getAge());
            preparedStatement.setInt(3,user.getGender());
            preparedStatement.setString(4,user.getAddress());
            preparedStatement.setString(5,user.getPhone());
            preparedStatement.setString(6, user.getEmail());
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    @Override
    public User selectUser(int id) {
        User user = null;
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();
             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String name = rs.getString("name");
                int age = rs.getInt("age");
                int gender = rs.getInt("gender");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                double salary = rs.getDouble("salary");
                user = new User(id,name, age, gender, address, phone, email, salary);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    public List<User> find(String keyWord) throws SQLException{
        final String FIND_DATA = "select * from users where name like ? OR email like ? OR age like ? OR gender like ? OR address like ? OR phone like ? OR salary like ?;";
        List<User> list = new ArrayList<>();
        String key = "%" + keyWord + "%";
        try (Connection connection = getConnection();
                PreparedStatement statement = connection.prepareStatement(FIND_DATA)) {
            statement.setString(1, key);
            statement.setString(2, key);
            statement.setString(3, key);
            statement.setString(4, key);
            statement.setString(5, key);
            statement.setString(6, key);
            statement.setString(7, key);

            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            int age = rs.getInt("age");
            int gender = rs.getInt("gender");
            String address = rs.getString("address");
            String phone = rs.getString("phone");
            String email = rs.getString("email");
            double salary = rs.getDouble("salary");

            User user = new User(id, name, age, gender, address, phone, email, salary);
            list.add(user);


            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    Connection connection = getConnection();
    private final String UPDATE_TRANSFER_SALARY_SENDER = "UPDATE demo.users SET salary = salary - (? + ?) WHERE id = ?;";
    private final String UPDATE_TRANSFER_SALARY_RECEIVER = "UPDATE demo.users SET salary = salary + ? WHERE id = ?;";
    public boolean  isUpdateTransfer(User customer, User customer2, float balance, float feeTransaction) throws SQLException {

        boolean rowUpdate = false;
        try  {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TRANSFER_SALARY_SENDER);
            PreparedStatement preparedStatement2 = connection.prepareStatement(UPDATE_TRANSFER_SALARY_RECEIVER);

            preparedStatement.setFloat(1, balance);
            preparedStatement.setFloat(2,feeTransaction);
            preparedStatement.setInt(3, customer.getId());
            preparedStatement.executeUpdate();

            preparedStatement2.setFloat(1, balance);
            preparedStatement2.setInt(2, customer2.getId());
            preparedStatement2.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            rowUpdate = true;
        }catch (SQLException e){
            connection.rollback();
            printSQLException(e);
        }
        return  rowUpdate;
    }
    public boolean transferBalance(User user) throws SQLException {
        boolean rowUpdate = false;
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_SALARY);
            preparedStatement.setDouble(1, user.getSalary());
            preparedStatement.setInt(2,user.getId());
            preparedStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
            rowUpdate = true;
        }catch (SQLException e) {
            connection.rollback();
            printSQLException(e);
        }
        return rowUpdate;
    }

    public List<User> selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<User> users = new ArrayList<>();
        // Step 1: Establishing a Connection
        try (Connection connection = getConnection();

             // Step 2:Create a statement using connection object
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                int gender = rs.getInt("gender");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
               double salary = rs.getDouble("salary");
                users.add(new User(id, name, age, gender, address, phone, email, salary));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    public boolean deleteUser(int id) throws SQLException {
        boolean rowDeleted;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL)) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateUser(User user) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {

            statement.setString(1, user.getName());
            statement.setInt(2,user.getAge());
            statement.setInt(3,user.getGender());
            statement.setString(4,user.getAddress());
            statement.setString(5,user.getPhone());
            statement.setString(6, user.getEmail());
            statement.setDouble(7, user.getSalary());
            statement.setInt(8, user.getId());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }



    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


    @Override
    public List<User> selectAllUsersNonExistCurrentUser(int currentUserId) {
        String query = "select * from users where id != ?;";
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = getConnection().prepareStatement(query)) {
            preparedStatement.setInt(1, currentUserId);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                int age = rs.getInt("age");
                int gender = rs.getInt("gender");
                String address = rs.getString("address");
                String phone = rs.getString("phone");
                String email = rs.getString("email");
                double salary = rs.getDouble("salary");
                users.add(new User(id, name, age, gender, address, phone, email, salary));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return users;
    }
}
