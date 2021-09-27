package com.codegym.dao;

import com.codegym.model.Deposit;
import com.codegym.model.Transfer;
import com.codegym.model.User;
import com.codegym.model.Withdraw;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransferDAO implements ITransferDAO {

    private String jdbcURL = "jdbc:mysql://localhost:3306/demo";
    private String jdbcUsername = "root";
    private String jdbcPassword = "Nhi09092018";

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

    Connection connection = getConnection();

    @Override
    public void insertTransfer(Transfer transfer) throws SQLException {
        String Insert_Transfer_SQL =
                "INSERT INTO `demo`.`transfer` (`id_sender`, `id_recipient`,`fee`, `fee_amount`,  `transaction_amount`, `transfer_amount`, `time`) \n" +
                "VALUES (?, ?, 5, ?, ?, ?,?);";
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(Insert_Transfer_SQL);
            preparedStatement.setInt(1, transfer.getIdSender());
            preparedStatement.setInt(2, transfer.getIdReceiver());
            preparedStatement.setFloat(3, transfer.getAmount_fee());
            preparedStatement.setFloat(4, transfer.getTotal_amount());
            preparedStatement.setFloat(5, transfer.getAmount());
            preparedStatement.setString(6,transfer.getDatetime());

            preparedStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            connection.rollback();
            printSQLException(e);
        }
    }
    public void insertWithdraw(Withdraw withdraw) throws SQLException {
        String Insert_Transfer_SQL =
                "INSERT INTO `demo`.`withdraw` (`customer_id`, `transaction_amount`, `time`) VALUES ( ?, ?, ?);";
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(Insert_Transfer_SQL);
            preparedStatement.setInt(1, withdraw.getId_customer());
            preparedStatement.setDouble(2, withdraw.getAmount());
            preparedStatement.setString(3,withdraw.getDatetime());

            preparedStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            connection.rollback();
            printSQLException(e);
        }
    }
    public void insertDeposit(Deposit deposit) throws SQLException {
        String Insert_Transfer_SQL =
                "INSERT INTO `demo`.`deposits` (`customer_id`, `transaction_amount`, `time`) VALUES ( ?, ?, ?);";
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(Insert_Transfer_SQL);
            preparedStatement.setInt(1, deposit.getId_customer());
            preparedStatement.setDouble(2, deposit.getAmount());
            preparedStatement.setString(3,deposit.getDatetime());

            preparedStatement.executeUpdate();
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            connection.rollback();
            printSQLException(e);
        }
    }


    @Override
    public List<Transfer> selectAllTransfer() throws SQLException {
        String Select_All_SQL = "SELECT t.id,t.id_sender, c1.name AS Name_Sender,t.id_recipient,c2.name AS Name_Receiver," +
                "t.transfer_amount,t.fee_amount,t.transaction_amount,t.fee,t.time FROM demo.transfer t JOIN demo.users c1 " +
                "ON c1.id = t.id_sender JOIN demo.users c2  ON c2.id = t.id_recipient;";
        List<Transfer> transferList = new ArrayList<>();
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(Select_All_SQL);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                int id = rs.getInt("id");
                int idSender = rs.getInt("id_sender");
                String Name_Sender= rs.getString("Name_Sender");
                int idReceiver = rs.getInt("id_recipient");
                String Name_Receiver = rs.getString("Name_Receiver");
                float amount = rs.getFloat("transfer_amount");
                float fee_amount = rs.getFloat("fee_amount");
                float total_amount = rs.getFloat("transaction_amount");
                int fee = rs.getInt("fee");
                String datetime = rs.getString("time");
                Transfer transfer = new Transfer(id,  idSender,  Name_Sender,  idReceiver,  Name_Receiver,  amount, fee, datetime,  fee_amount,  total_amount);
                transferList.add(transfer);
            }
            connection.commit();
            connection.setAutoCommit(true);
        } catch (SQLException e) {
            connection.rollback();
            printSQLException(e);
        }
        return transferList;
    }

    public int selectTotal() throws SQLException {
        int total = 0;
        String Select_Total = "SELECT SUM(transaction_amount) AS Total FROM transfer;";
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(Select_Total);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                total = rs.getInt("Total");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return total;
    }

    public int TotalRevenue() throws SQLException {
        int total = 0;
        String Select_Total = "SELECT SUM(fee_amount) AS Total FROM transfer;";
        try {
            connection.setAutoCommit(false);
            PreparedStatement preparedStatement = connection.prepareStatement(Select_Total);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                total = rs.getInt("Total");
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return total;
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


}
