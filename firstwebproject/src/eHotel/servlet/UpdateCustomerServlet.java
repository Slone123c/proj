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
 * Servlet implementation class UpdateCustomerServlet
 */
//@WebServlet("/UpdateCustomerServlet")
public class UpdateCustomerServlet extends HttpServlet {
	private static final long serialVersionUID = 21L;
      


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sin_number = request.getParameter("customer_sin_number");
		PostgreSqlConn cnn = new PostgreSqlConn();
		customer cust=null;
		try {
			cust=cnn.getCustomerBySIN(sin_number);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.setAttribute("customer", cust);
		request.getRequestDispatcher("/modify.jsp").forward(request, response);
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
