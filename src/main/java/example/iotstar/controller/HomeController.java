	package example.iotstar.controller;
	
	import example.iotstar.service.IUserService;
	import example.iotstar.service.impl.UserServiceImpl;
	
	import jakarta.servlet.ServletException;
	import jakarta.servlet.annotation.WebServlet;
	import jakarta.servlet.http.*;
	
	import java.io.IOException;
	
	@WebServlet(urlPatterns = {"/home"} )
	public class HomeController extends HttpServlet {
	    private static final long serialVersionUID = 1L;
	    IUserService service = new UserServiceImpl();
	
	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	        request.getRequestDispatcher("/views/home.jsp").forward(request, response);
	    }
	
	   
	}
