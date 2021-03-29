package eHotel.entities;

public class customer {
	
	public customer(String customer_sin_number, String pwd, String full_name, String customer_address) {
	
		this.customer_sin_number = customer_sin_number;
		this.pwd = pwd;
		this.full_name = full_name;
		this.customer_address = customer_address;
	}
	public customer() {
		// TODO Auto-generated constructor stub
	}
	private String customer_sin_number;
	private String pwd;
	private String full_name;
	private String customer_address;
	public String getCustomer_sin_number() {
		return customer_sin_number;
	}
	public void setCustomer_sin_number(String customer_sin_number) {
		this.customer_sin_number = customer_sin_number;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	public String getFull_name() {
		return full_name;
	}
	public void setFull_name(String full_name) {
		this.full_name = full_name;
	}
	public String getCustomer_address() {
		return customer_address;
	}
	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}
	


	

}
