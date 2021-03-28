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
	    String sql;


		public void getConn(){
			
			try {
				
				Class.forName("org.postgresql.Driver"); 
								
				db = DriverManager.getConnection("jdbc:postgresql://web0.site.uottawa.ca:15432/group_a01_g39",
						"schen359", "Aaa19720103");
															
			}catch(Exception e) {
				System.out.print("error catched");
			}
						
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
			getConn();

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
			getConn();

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
				}
	            
	        }catch(SQLException e){
	            e.printStackTrace();
	        }finally {
	        	closeDB();
	        }
			return pwd;       
	    }
		
		public boolean insertNewCustomer(String[] param){
			getConn();

			
	        try{
	        	st = db.createStatement();
	        	sql = "insert into ehotel.customer values('"+param[0]+"','"+param[1]+"','"+param[2]+"')";
	        	
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
			
			getConn();
			
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
			
			getConn();
			
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
			getConn();
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
		
		public int modifyData(String sql,Object[] obj) {
			getConn();
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
			}
			return result;

			}
			
		public ResultSet getData(String sql,Object[] obj){
			getConn();
			try {
				ps = db.prepareStatement(sql);
				for(int i =0;i<obj.length;i++) {
					ps.setObject(i+1, obj[i]);
				}
				rs=ps.executeQuery();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
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
		public List<customer> getAllCustomer(){
			sql= "select * from project.customer";
			ResultSet resultSet=getData(sql, new Object[] {});
			List<customer> list = new ArrayList<customer>();
			try {
				while(resultSet.next()) {
					customer cr= new customer();
					cr.setCustomer_sin_number(resultSet.getString(1));
					cr.setPwd(resultSet.getString(2));
					cr.setFull_name(resultSet.getString(3));
					cr.setCustomer_address(resultSet.getString(4));
					list.add(cr);
				}
				
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return list;
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

