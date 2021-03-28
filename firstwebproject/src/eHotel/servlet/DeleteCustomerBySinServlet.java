package eHotel.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eHotel.connections.PostgreSqlConn;

/**
 * Servlet implementation class DeleteCustomerBySinServlet
 */
@WebServlet("/DeleteCustomerBySinServlet")
public class DeleteCustomerBySinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String sinNum = request.getParameter("customer_sin_number");
		System.out.println("The sin we want to delete is "+sinNum);
		PostgreSqlConn cnn = new PostgreSqlConn();
		try {
			if(cnn.deleteCustomerBySin(sinNum)==1) {
				//request.getRequestDispatcher("/show.jsp").forward(request, response);
				response.sendRedirect("/firstwebproject/GetAllCustomerServlet");
			}else {
				request.getRequestDispatcher("/error.jsp").forward(request, response);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
