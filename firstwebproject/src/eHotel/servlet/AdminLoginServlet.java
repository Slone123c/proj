package eHotel.servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import eHotel.connections.PostgreSqlConn;


public class AdminLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 12L;
      
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String adminid = request.getParameter("adminid");
		String adminpwd = request.getParameter("adminpwd");
		PostgreSqlConn cnn = new PostgreSqlConn();
		String[] param = new String[2];
		//System.out.println(adminid);
		//System.out.println(adminpwd);
		param[0] = adminid;
		param[1] = adminpwd;	
		ResultSet rs=null;
		try {
			rs = cnn.loginAdmin(param);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rs!=null) {
			System.out.println("admin login success");
			response.sendRedirect("/firstwebproject/GetAllCustomerServlet");
		}else {
			System.out.println("admin login fail");
			response.sendRedirect("/firstwebproject/error.jsp");
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
