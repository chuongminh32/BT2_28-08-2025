package example.iotstar.controller;

import example.iotstar.dao.impl.*;
import example.iotstar.models.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;

@WebServlet("/login")
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDaoImpl userDAO;

    @Override
    public void init() throws ServletException {
        userDAO = new UserDaoImpl();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UserModel user = userDAO.checkLogin(username, password);

        if (user != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", user);
            request.setAttribute("message", "Đăng nhập thành công!");
            response.sendRedirect(request.getContextPath() + "/views/profile.jsp");
        }
else {
            request.setAttribute("errorMessage", "Sai username hoặc password!");
            response.sendRedirect(request.getContextPath());
        }
    }
}
