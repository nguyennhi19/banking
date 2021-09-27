package com.codegym.controller;

import com.codegym.dao.IUserDAO;
import com.codegym.dao.TransferDAO;
import com.codegym.dao.UserDAO;
import com.codegym.model.Deposit;
import com.codegym.model.Transfer;
import com.codegym.model.User;
import com.codegym.model.Withdraw;
import com.codegym.utilt.Check;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@WebServlet(name = "UserServlet", urlPatterns = "/users")
public class UserServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    private TransferDAO transferDAO;

    public void init() {
        userDAO = new UserDAO();
        transferDAO = new TransferDAO();

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        try {
            switch (action) {
                case "create":
                    insertUser(request, response);
                    break;
                case "edit":
                    updateUser(request, response);
                    break;
                case "transfer":
                    transferUse(request, response);
                    break;
                case "deposit":
                    deposit(request, response);
                    break;
                case "withdraw":
                    withdraw(request, response);
                    break;
                case "search":
                    searchByName(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }

        try {
            switch (action) {
                case "create":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteUser(request, response);
                    break;
                case "transfer":
                    showtransfer(request, response);
                    break;
                case "deposit":
                    showdeposit(request, response);
                    break;
                case "withdraw":
                    showwithdraw(request, response);
                    break;
                case "search":
                    searchByName(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        dispatcher.forward(request, response);
    }


    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.selectUser(id);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/edit.jsp");
        request.setAttribute("user", existingUser);
        dispatcher.forward(request, response);

    }

    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        int gender = Integer.parseInt(request.getParameter("gender"));
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        User newUser = new User(name, age, gender, address, phone, email);

        userDAO.insertUser(newUser);

        RequestDispatcher dispatcher = request.getRequestDispatcher("user/create.jsp");
        dispatcher.forward(request, response);

    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String name = request.getParameter("name");
        int age = Integer.parseInt(request.getParameter("age"));
        int gender = Integer.parseInt(request.getParameter("gender"));
        String address = request.getParameter("address");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        double salary = Double.parseDouble(request.getParameter("salary"));

        User book = new User(id, name, age, gender, address, phone, email, salary);

        if (salary < 100000 && salary >= 0) {
            String success = "create successfully";
            request.setAttribute("success", success);
            request.setAttribute("error", null);

            userDAO.updateUser(book);
            User userUpdated = new User(id, name, age, gender, address, phone, email, salary);
            request.setAttribute("user", userUpdated);
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/edit.jsp");
            dispatcher.forward(request, response);
        } else {
            String error = "create error";
            request.setAttribute("success", null);
            request.setAttribute("error", error);
            request.setAttribute("user", book);
            RequestDispatcher dispatcher = request.getRequestDispatcher("user/edit.jsp");
            dispatcher.forward(request, response);
        }


    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        userDAO.deleteUser(id);

        List<User> listUser = userDAO.selectAllUsers();
        request.setAttribute("listUser", listUser);
        String success = "delete successfully";
        request.setAttribute("delete", success);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user/list.jsp");
        dispatcher.forward(request, response);
    }

    private void searchByName(HttpServletRequest request, HttpServletResponse response) throws SQLException {

        String key = request.getParameter("key");
        List<User> userList = userDAO.find(key);
        request.setAttribute("list", userList);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("user/search.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void transferUse(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        int receiverId = Integer.parseInt(request.getParameter("receiverId"));
        String amount_str = request.getParameter("amount");

        float money = 100000;
        String datetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY/MM/dd hh:mm:ss"));


        User userexting = userDAO.selectUser(id);
        User receiver = userDAO.selectUser(receiverId);

        if(amount_str == ""){
            request.setAttribute("error", "hay nhap so tien muon chuyen");
            showtransfer(request,response);
        }else {
            if (!Check.isNumeric(amount_str)){
                request.setAttribute("error", "hay nhap lai so tien");
                showtransfer(request,response);
            }else {
                float amount = Float.parseFloat(amount_str);
                float fee_amount = (amount * 5 / 100);
                float total_amount = amount + fee_amount;
                if (amount > money){
                    request.setAttribute("error", "so tien vuot qua han muc trong ngay");
                    showtransfer(request,response);
                }else {
                    if (amount > userexting.getSalary()||amount+fee_amount > userexting.getSalary()){
                        request.setAttribute("error", "so du khong du");
                        showtransfer(request,response);
                    }else {
                        if (amount <= 0){
                            request.setAttribute("error", "so tien chuyen phai lon hon 0");
                            showtransfer(request,response);

                        }else{
                            boolean istransfer = userDAO.isUpdateTransfer(userexting,receiver,amount,fee_amount);
                            if(istransfer){
                                request.setAttribute("success", "Transfer  successful");
                                Transfer transfer = new Transfer(id, receiverId, amount,datetime, fee_amount,total_amount );
                                transferDAO.insertTransfer(transfer);
                                showtransfer(request, response);
                            }else 
                            request.setAttribute("error", "Transfer isn't successful");
                            showtransfer(request, response);
                        }
                    }
                }
            }
        }
    }

    private void deposit(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String amount_str = request.getParameter("amount");

        String datetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY/MM/dd hh:mm:ss"));
        User userexting = userDAO.selectUser(id);

        if(amount_str == ""){
            request.setAttribute("error", "hay nhap so tien muon nop");
            showdeposit(request,response);
        }else {
            if (!Check.isNumeric(amount_str)){
                request.setAttribute("error", "hay nhap lai so tien");
                showdeposit(request,response);
            }        else {
                float amount = Float.parseFloat(amount_str);
                if (amount <= 0){
                    request.setAttribute("error", "so tien nap phai lon hon khong");
                    showdeposit(request,response);
                }else
                userexting.setSalary(userexting.getSalary() + amount);
                boolean isb = userDAO.transferBalance(userexting);
                if(isb){
                request.setAttribute("success", "deposit  successful");
                Deposit deposit = new Deposit(id,amount,datetime);
                transferDAO.insertDeposit(deposit);
                    showdeposit(request, response);
                }else
                    request.setAttribute("error", "deposit isn't successful");
                showdeposit(request, response);
            }
        }
    }


    private void withdraw(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        String amount_str = request.getParameter("amount");
        String datetime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("YYYY/MM/dd hh:mm:ss"));
        User userexting = userDAO.selectUser(id);
        float money = 100000;
        if(amount_str == ""){
            request.setAttribute("error", "hay nhap so tien muon rut");
            showwithdraw(request, response);
        }else {
            if (!Check.isNumeric(amount_str)){
                request.setAttribute("error", "hay nhap lai so tien");
                showwithdraw(request, response);
            }else {
                float amount = Float.parseFloat(amount_str);
                if (amount > userexting.getSalary()){
                    request.setAttribute("error", "so du trong tai khoan khong du ");
                    showwithdraw(request, response);
                }else {
                    if(amount < 0){
                        request.setAttribute("error", "so tien rut phai lon hon 0");
                        showwithdraw(request, response);
                    }else {
                        if (amount > money){
                            request.setAttribute("error", "so tien rut qua han muc trong ngay");
                            showwithdraw(request, response);
                        }else
                        userexting.setSalary(userexting.getSalary() - amount);
                        boolean isb = userDAO.transferBalance(userexting);
                        if(isb){
                        request.setAttribute("success", "withdraw  successful");
                        Withdraw withdraw = new Withdraw(id,amount,datetime);
                        transferDAO.insertWithdraw(withdraw);
                        showwithdraw(request, response);
                        }else
                        request.setAttribute("error", "withdraw isn't successful");
                        showwithdraw(request, response);
                    }
                }
            }
        }


    }

    private void showtransfer(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.selectUser(id);

        request.setAttribute("user", existingUser);

        List<User> users = userDAO.selectAllUsersNonExistCurrentUser(id);
        request.setAttribute("users", users);

        RequestDispatcher dispatcher = request.getRequestDispatcher("transfer/transfer.jsp");
        dispatcher.forward(request, response);
    }

    private void showwithdraw(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.selectUser(id);

        request.setAttribute("user", existingUser);

        List<User> users = userDAO.selectAllUsersNonExistCurrentUser(id);
        request.setAttribute("users", users);

        RequestDispatcher dispatcher = request.getRequestDispatcher("transfer/withdraw.jsp");
        dispatcher.forward(request, response);
    }

    private void showdeposit(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        int id = Integer.parseInt(request.getParameter("id"));
        User existingUser = userDAO.selectUser(id);

        request.setAttribute("user", existingUser);

        List<User> users = userDAO.selectAllUsersNonExistCurrentUser(id);
        request.setAttribute("users", users);

        RequestDispatcher dispatcher = request.getRequestDispatcher("transfer/deposit.jsp");
        dispatcher.forward(request, response);
    }
}
