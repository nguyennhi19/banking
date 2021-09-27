package com.codegym.dao;

import com.codegym.model.Transfer;

import java.sql.SQLException;
import java.util.List;

public interface ITransferDAO {
    public void insertTransfer(Transfer transfer) throws SQLException;
    public List<Transfer> selectAllTransfer() throws SQLException;
}
