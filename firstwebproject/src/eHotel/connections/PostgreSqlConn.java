package eHotel.connections;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;


import eHotel.entities.Room;
import eHotel.entities.customer;
import eHotel.entities.employee;


public class  PostgreSqlConn{
	
		Connection db = null;
	    PreparedStatement ps = null;
	    ResultSet rs = null;
	    Statement st = null;
	    String sql = new String();


		public void getConn() throws Exception {			
				Class.forName("org.postgresql.Driver"); 							
				db = DriverManager.getConnection("jdbc:postgresql://web0.site.uottawa.ca:15432/group_a01_g39",
						"username", "password");														
					
		}
		
		public void closeDB() {
				try {
					if(rs != null){
						rs.close();
					}
					if(ps!=null){
						ps.close();
					}
					if(st!=null){
						st.close();
					}
					if(db!=null){
						db.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}

		
		public String getpwdbyUname(String param){
			//this.getConn();

			String pwd = "";
			
	        try{
	            ps = db.prepareStatement("select employee_pass from ehotel.employee where employee_id=?");
	            
	            ps.setString(1, param);	               
	            rs = ps.executeQuery();
	
				while(rs.next()) {
					pwd = rs.getString(1);
				}
	            
	        }catch(SQLException e){
	            e.printStackTrace();
	        }finally {
	        	closeDB();
	        }
			return pwd;       
	    }
		
		
		public String[] getuserinforbycustSSN(String param){
			//this.getConn();
			String[] pwd = new String[3];
			
	        try{
	        	sql="select * from project.customer where customer_sin_number=?";
	            ps = db.prepareStatement(sql);
	            ps.setString(1, param);//let db search for SIN_number= param.	               
	            rs = ps.executeQuery();	            
				while(rs.next()) {
					pwd[0] = rs.getString(1);
					pwd[1] = rs.getString(2);
					pwd[2] = rs.getString(3);
					pwd[3] = rs.getString(4);
				}
	            
	        }catch(SQLException e){
	            e.printStackTrace();
	        }finally {
	        	closeDB();
	        }
			return pwd;       
	    }
		
		public boolean insertNewCustomer(String[] param){
			//this.getConn();
			
	        try{
	        	st = db.createStatement();
	        	sql = "insert into project.customer values('"+param[0]+"','"+param[1]+"','"+param[2]+"')";
	        	
	        	System.out.print(sql);
	            
	            st.executeUpdate(sql);
	            
	            return true;

	        }catch(SQLException e){
	            e.printStackTrace();
	            return false;
	        }finally {
	        	closeDB();
	        }	       
	    }
		
		public  ArrayList<Room> getAllAvailRooms(){
			
			//this.getConn();
			
			ArrayList<Room> Rooms = new ArrayList<Room>();
			
			try {
				ps = db.prepareStatement("select * from ehotel.room where room_status='available'" );
				rs = ps.executeQuery();
				while(rs.next()){
					String room_no = rs.getString("room_no");
					String room_status = rs.getString("room_status");
					//Room room = new Room(room_no, room_status);
					//Rooms.add(room);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
	        	closeDB();
	        }
						
			return Rooms;
			
		}
		
		public  ArrayList<Room> getbookedRooms(String custSSN){
			
		//	this.getConn();
			
			ArrayList<Room> Rooms = new ArrayList<Room>();
			
			try {
				ps = db.prepareStatement("select * from ehotel.room where customer_ssn='"+custSSN+"'");
				rs = ps.executeQuery();
				while(rs.next()){
					String room_no = rs.getString("room_no");
					String room_status = rs.getString("room_status");
					//Room room = new Room(room_no, room_status);
					//Rooms.add(room);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
	        	closeDB();
	        }
						
			return Rooms;
			
		}
		
		public String bookRoom(String custName, String roomno){
			//this.getConn();
			String custSSN="";
		
	        try{
	        	
	        	ps = db.prepareStatement("select customer_ssn from ehotel.customer where customer_name='"+custName+"'");
				rs = ps.executeQuery();
				
				while(rs.next()){
					custSSN = rs.getString("customer_ssn");
				}
				
				
	        	st = db.createStatement();
	        	sql = "update ehotel.room set customer_ssn='"+custSSN+"', room_status='booked' where room_no='"+roomno+"'";
	            st.executeUpdate(sql);
	            
	            
	            return custSSN;

	        }catch(SQLException e){
	            e.printStackTrace();
	            return "";	 
	        }finally {
	        	closeDB();
	        }
			      
	    }
		
		public int modifyData(String sql,Object[] obj) throws Exception {
			
			this.getConn();
			int result=0;
			try {
				ps = db.prepareStatement(sql);
				for(int i =0;i<obj.length;i++) {
					ps.setObject(i+1, obj[i]);
				}
				result = ps.executeUpdate();
				
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				db.close();
			}
			return result;

			}
			
		public ResultSet getData(String sql,Object[] obj) throws Exception{
			if (db==null) {
				this.getConn();
			}
			try {
						
				ps = db.prepareStatement(sql);
				for(int i =0;i<obj.length;i++) {
					ps.setObject(i+1, obj[i]);
				}
				rs=ps.executeQuery();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}finally {
				db.close();
			}
			return rs;
		}
		
//		public int getCustomerCountBySIN(String sin) {
//			sql="SELECT count(1) from project.customer where customer_SIN_number=?";
//			ResultSet rss;
//				rss =getData(sql, new Object[] {sin});
//				int count=0;
//				try {
//					while(rss.next()) {
//						count=rss.getInt(1);
//					}
//				} catch (Exception e) {
//					e.printStackTrace();
//					// TODO: handle exception
//				}
//				return count;		
//		
//		}
		public List<customer> getAllCustomer() throws Exception{
			sql= "select * from project.customer";
			rs=getData(sql, new Object[] {});
			List<customer> list = new ArrayList<customer>();
			try {
				System.out.println("Getting customer data.");
				while(rs.next()) {
					customer cr= new customer();
					cr.setCustomer_sin_number(rs.getString(1));			
					cr.setPwd(rs.getString(2));
					cr.setFull_name(rs.getString(3));
					cr.setCustomer_address(rs.getString(4));
					list.add(cr);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				System.out.println("Fail");
				e.printStackTrace();
			}finally {
				System.out.println("Success.");
				closeDB();
			}
			return list;
		}
		
		public int deleteCustomerBySin(String sin) throws Exception {
		
			sql = "delete from project.customer where customer_sin_number=?";
			return modifyData(sql, new Object[] {sin});
		}
		
		public customer getCustomerBySIN(String sin) throws Exception {
			sql = "select* from project.customer where customer_sin_number=?";
			rs=getData(sql, new Object[] {sin});
			customer cust = new customer();
			try {
				while(rs.next()) {
					cust.setCustomer_sin_number(rs.getString(1));
					cust.setPwd(rs.getString(2));
					cust.setFull_name(rs.getString(3));
					cust.setCustomer_address(rs.getString(4));
				}
				
			} catch (Exception e) {
				// TODO: handle exception
			}finally {
				closeDB();
			}
			return cust;
		}
		
		public int updateCustomerBySIN(customer cust) throws Exception {
			sql="update project.customer set full_name=?,pwd=?,customer_address=? where customer_sin_number=?";
			return this.modifyData(sql,new Object[] {cust.getFull_name(),cust.getPwd(),cust.getCustomer_address(),cust.getCustomer_sin_number()} );
		}
		
		public ResultSet loginAdmin(Object[] param) throws Exception {
			sql="select * from project.admin where admin_id=? and admin_pwd=?";
			return this.getData(sql, param);
		}
		
//		public static void main(String []args) {
//			PostgreSqlConn con = new PostgreSqlConn();
//			con.getConn();
//			String pwd = con.getpwdbyUname("8366341");
//			
//			System.out.println(pwd);
//				
//			
//			
//		}

		
	}

