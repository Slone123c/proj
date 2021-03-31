package eHotel.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eHotel.connections.PostgreSqlConn;
import eHotel.entities.customer;

/**
 * Servlet implementation class DoUpdateCustomerServlet
 */
@WebServlet("/DoUpdateCustomerServlet")
public class DoUpdateCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 14L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sin = request.getParameter("customer_sin_number");
		String pwd = request.getParameter("pwd");
		String name = request.getParameter("full_name");
		String address = request.getParameter("customer_address");
		PostgreSqlConn cnn = new PostgreSqlConn();
		customer cust = new customer(sin,pwd,name,address);
		int result=0;
		try {
			result=cnn.updateCustomerBySIN(cust);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(result==1) {
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
