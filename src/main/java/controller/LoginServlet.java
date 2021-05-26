package controller;

import model.User;
import service.IUserDAO;
import service.UserDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static IUserDAO userDAO = new UserDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null){
            action ="";
        }switch (action){
            case "create":
                register(request,response);
                break;
            case "Login":
                checkLogin(request,response);
                break;
            case "search":
                searchByName(request,response);
                break;
            case "delete":
                deleteUser(request,response);
                break;
            case "update":
                showEditForm(request, response);
                break;
            default:
                home(request,response);
                break;

        }
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        User user = userDAO.selectUserByID(id);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/editUser.jsp");
        request.setAttribute("user", user);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void home(HttpServletRequest request, HttpServletResponse response) {
        List<User> products = userDAO.selectAllUser();
        request.setAttribute("listUser", products);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/show.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        userDAO.delete(id);
        List<User> products = userDAO.selectAllUser();
        request.setAttribute("listUser", products);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/show.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                insertUser(request, response);
                break;
            case "Login":
                checkLogin(request, response);
                break;
            case "search":
                searchByName(request,response);
                break;
            case "update":
                updateUser(request,response);
                break;
//            case "search":
//                searchByName(request, response);
//                break;
        }
    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response) {

        String username = request.getParameter("txtUser");
        String password = request.getParameter("txtPass");
        String lastname = request.getParameter("txtLast");
        boolean roles = Boolean.parseBoolean(request.getParameter("chkAdmin"));
        User user = new User(username,password,lastname,roles);
        userDAO.update(user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/show.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void checkLogin(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("txtUser");
        String password = request.getParameter("txtPass");
        boolean result =
                userDAO.checkLogin(username, password);

        String url = "invalid.jsp";
        if (result) {
            HttpSession session = request.getSession();
            session.setAttribute("USER", username);
            url =("view/searchUser.jsp");
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(url);
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    private void insertUser(HttpServletRequest request, HttpServletResponse response) {
        String username = request.getParameter("txtUser");
        String password = request.getParameter("txtPass");
        String lastname = request.getParameter("txtLast");
        boolean roles = Boolean.parseBoolean(request.getParameter("chkAdmin"));
        User user = new User(username, password, lastname, roles);
        userDAO.insert(user);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("index.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void register(HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/register.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    private void searchByName(HttpServletRequest request, HttpServletResponse response) {
        String search = request.getParameter("txtSearchValue");
        List<User> users = userDAO.selectUserByName(search);
        request.setAttribute("listUser", users);
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("view/show.jsp");
        try {
            requestDispatcher.forward(request, response);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
