package eHotel.entities;

public class employee {
	
//	employee here has employeeid and password attributes
	
	private String employee_SIN_number;
	private String employee_id;
	private String works_for_hotel_id;
	private String aname;
	private String employee_addresss;
	private int salary;
	private String employee_role;
	
	public employee() {
		
	}

	public String getEmployee_SIN_number() {
		return employee_SIN_number;
	}

	public void setEmployee_SIN_number(String employee_SIN_number) {
		this.employee_SIN_number = employee_SIN_number;
	}

	public String getEmployee_id() {
		return employee_id;
	}

	public void setEmployee_id(String employee_id) {
		this.employee_id = employee_id;
	}

	public String getWorks_for_hotel_id() {
		return works_for_hotel_id;
	}

	public void setWorks_for_hotel_id(String works_for_hotel_id) {
		this.works_for_hotel_id = works_for_hotel_id;
	}

	public String getAname() {
		return aname;
	}

	public void setAname(String aname) {
		this.aname = aname;
	}

	public String getEmployee_addresss() {
		return employee_addresss;
	}

	public void setEmployee_addresss(String employee_addresss) {
		this.employee_addresss = employee_addresss;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(int salary) {
		this.salary = salary;
	}

	public String getEmployee_role() {
		return employee_role;
	}

	public void setEmployee_role(String employee_role) {
		this.employee_role = employee_role;
	}


	

}
