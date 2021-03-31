CREATE TABLE project.Parent_hotel(
    hotel_brand_name VARCHAR NOT NULL,
    physical_address VARCHAR NOT NULL,
    parent_hotel_email_address VARCHAR NOT NULL,
    phone_number NUMERIC NOT NULL,
    total_number_of_hotels NUMERIC NOT NULL,
    hotel_ID NUMERIC NOT NULL,

    CONSTRAINT hotel_ID_fkey FOREIGN KEY(hotel_ID)
        REFERENCES project.hotel(hotel_ID) 
        on UPDATE CASCADE on DELETE CASCADE
)

CREATE TABLE project.hotel(
    hotel_address VARCHAR NOT NULL,
    hotel_email_address VARCHAR NOT NULL,
    star_category INTEGER NOT NULL,
    number_of_rooms INTEGER NOT NULL,
    phone_number NUMERIC NOT NULL,
    hotel_ID NUMERIC NOT NULL,
    owned_by VARCHAR NOT NULL,
    CONSTRAINT owned_by_fkey FOREIGN KEY(owned_by)
        REFERENCES project.Parent_hotel(hotel_brand_name), 
    CONSTRAINT hotel_ID_pkey PRIMARY KEY(hotel_ID),
    CONSTRAINT hotel_address_pkey PRIMARY KEY(hotel_address_pkey), 
    CONSTRAINT star_category_check CHECK (star_category >=1 AND star_category <=5),
    CONSTRAINT number_of_rooms_check CHECK(number_of_rooms >=1)

)


CREATE TABLE project.room(
    owned_by_hotel_ID VARCHAR NOT NULL,
    room_number VARCHAR NOT NULL,
    room_type VARCHAR NOT NULL,
    price INTEGER NOT NULL,
    amenity_ID NUMERIC NOT NULL,
    room_status VARCHAR DEFAULT 'open',
    CONSTRAINT room_status_check CHECK(room_status in('open','closed','booked','renting')),
    CONSTRAINT room_type_check CHECK(room_type in('Single','Double','Triple','Quad','Studio')),
    CONSTRAINT owned_by_check FOREIGN KEY(owned_by_hotel_ID)
        REFERENCES project.hotel(hotel_ID),
    CONSTRAINT amenity_check PRIMARY KEY(amenity_ID)
        REFERENCES project.amenity(amenity_ID)
        ON UPDATE CASCADE
        ON DELETE CASCADE

)

CREATE TABLE project.amenity(
    amenity_ID VARCHAR NOT NULL,
    amenity_Name VARCHAR NOT NULL,
    CONSTRAINT amenity_id_pkey PRIMARY KEY(amenity_ID).
)



CREATE TABLE project.customer(
    customer_sin_number VARCHAR NOT NULL,
    pwd VARCHAR NOT NULL,
    full_name VARCHAR NOT NULL,
    customer_address VARCHAR NOT NULL,
    CONSTRAINT customer_sin_number_pkey PRIMARY KEY(customer_sin_number)
)

CREATE TABLE project.bookinfo(
    room_number VARCHAR NOT NULL,
    owned_by_hotel_ID VARCHAR NOT NULL,
    date_start DATE NOT NULL,
    date_end DATE NOT NULL,

    CONSTRAINT date_check CHECK(date_end >= date_start),

    CONSTRAINT owned_by_hotel_check FOREIGN KEY(owned_by_hotel_ID)
        REFERENCES project.room(owned_by_hotel_ID),

)

CREATE TABLE project.employee(
    employee_SIN_number VARCHAR NOT NULL,
    employee_id VARCHAR NOT NULL,
    works_for_hotel_id VARCHAR NOT NULL,
    aname VARCHAR NOT NULL,
    employee_addresss VARCHAR NOT NULL,
    salary INTEGER NOT NULL,
    employee_role VARCHAR NOT NULL,
    CONSTRAINT employee_role_check CHECK(employee_role in('Manager','concierge','housekeeper')),
    CONSTRAINT employee_sin_number_pkey PRIMARY KEY(employee_sin_number),
    CONSTRAINT employee_id_pkey PRIMARY KEY(employee_id),
    CONSTRAINT employee_works_for_fkey FOREIGN KEY(works_for_hotel_id)
        REFERENCES project.hotel(hotel_ID)

)

CREATE TABLE project.admin(
    admin_id VARCHAR NOT NULL PRIMARY KEY,
    admin_pwd VARCHAR NOT NULL.
)


CREATE FUNCTION check_room_status()
    RETURNS trigger AS
$BODY$
BEGIN

IF NEW.room_status in('closed','booked','renting') THEN
    RAISE EXCEPTION 'The room is not available';
END IF;

RETURN NEW;

END
$BODY$ LANGUAGE plpqsql;

CREATE TRIGGER 





