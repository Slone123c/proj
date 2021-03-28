package eHotel.entities;

public class Room {
	
	//private String room_no;
	//private String customer_ssn;
	
	
    private String owned_by_hotel_ID;
    private String room_number;
    private String room_type;
    private double price;
    private int amenity_ID;
    private String room_status;
	
	public Room() {
		
	}

	public String getOwned_by_hotel_ID() {
		return owned_by_hotel_ID;
	}

	public void setOwned_by_hotel_ID(String owned_by_hotel_ID) {
		this.owned_by_hotel_ID = owned_by_hotel_ID;
	}

	public String getRoom_number() {
		return room_number;
	}

	public void setRoom_number(String room_number) {
		this.room_number = room_number;
	}

	public String getRoom_type() {
		return room_type;
	}

	public void setRoom_type(String room_type) {
		this.room_type = room_type;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getAmenity_ID() {
		return amenity_ID;
	}

	public void setAmenity_ID(int amenity_ID) {
		this.amenity_ID = amenity_ID;
	}

	public String getRoom_status() {
		return room_status;
	}

	public void setRoom_status(String room_status) {
		this.room_status = room_status;
	}
	

	

}
