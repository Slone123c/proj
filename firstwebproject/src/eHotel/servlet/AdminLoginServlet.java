package eHotel.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eHotel.connections.PostgreSqlConn;

/**
 * Servlet implementation class AdminLoginServlet
 */
@WebServlet("/AdminLoginServlet")
public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adminid = request.getParameter("adminid");
		String adminpwd = request.getParameter("adminpwd");
		PostgreSqlConn cnn = new PostgreSqlConn();
		String[] param = new String[2];
		param[0] = adminid;
		param[1] = adminpwd;	
		int i = cnn.loginAdmin(param);
		if(i==1) {
			response.sendRedirect("/firstwebproject/GetAllCustomerServlet");
		}else {
			response.sendRedirect("/firstwebproject/error.jsp");
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
