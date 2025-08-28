package example.iotstar.controller;

import example.iotstar.models.UserModel;
import example.iotstar.service.IUserService;
import example.iotstar.service.impl.UserServiceImpl;
import example.iotstar.utils.Constant;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
@WebServlet(urlPatterns = {"/login"} )
public class LoginController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    IUserService service = new UserServiceImpl();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.getRequestDispatcher("/views/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");

        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String remember = request.getParameter("remember");

        boolean isRememberMe = "on".equals(remember);

        if (username == null || username.isEmpty() || password == null || password.isEmpty()) {
            request.setAttribute("alert", "Tài khoản hoặc mật khẩu không được rỗng");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
            return;
        }

        UserModel user = service.login(username, password);
        if (user != null) {
            HttpSession session = request.getSession(true);
            session.setAttribute("account", user);

            if (isRememberMe) {
                saveRememberMe(response, username);
            }
            response.sendRedirect(request.getContextPath() + "/waiting");
        } else {
            request.setAttribute("alert", "Tài khoản hoặc mật khẩu không đúng");
            request.getRequestDispatcher("/views/login.jsp").forward(request, response);
        }
    }

    private void saveRememberMe(HttpServletResponse response, String username) {
        Cookie cookie = new Cookie(Constant.COOKIE_REMEMBER, username);
        cookie.setMaxAge(30 * 60);
        response.addCookie(cookie);
    }
}
