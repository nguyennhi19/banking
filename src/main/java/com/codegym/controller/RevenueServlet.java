package com.codegym.controller;

import com.codegym.dao.TransferDAO;
import com.codegym.dao.UserDAO;
import com.codegym.model.Transfer;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "RevenueServlet",urlPatterns = "/revenue")
public class RevenueServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    private TransferDAO transferDAO;

    public void init() {
        userDAO = new UserDAO();
        transferDAO = new TransferDAO();

    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Transfer> transferList = null;
        try {
            transferList = transferDAO.selectAllTransfer();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        int total = 0;
        try {
            total = transferDAO.TotalRevenue();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        request.setAttribute("total",total);
        request.setAttribute("transfers",transferList);
        RequestDispatcher dis = request.getRequestDispatcher("Revenue/index.jsp");
        dis.forward(request,response);
    }
}
