package eHotel.servlet;

import java.io.IOException;
import java.sql.ResultSet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import eHotel.connections.PostgreSqlConn;
import eHotel.entities.Room;
import eHotel.entities.employee;

@WebServlet("/CustomerloginServlet")
public class CustomerloginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String userSIN = req.getParameter("userSIN");
		String pwd = req.getParameter("userpwd");
		PostgreSqlConn con = new PostgreSqlConn();
		String[] param = new String[2];
//		[0]:name,[1]:pwd
		param[0] = userSIN;
		param[1] = pwd;
		ResultSet rs = null;
		
		try {
			rs = con.customerLogin(param);
			req.setAttribute("customerName", rs.getString(3));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(rs!=null) {
			System.out.println("customer login success");
			resp.sendRedirect("/firstwebproject/login_success.jsp");
		}else {
			resp.sendRedirect("/firstwebproject/login_failure.jsp");
		}
		
	}

	public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}