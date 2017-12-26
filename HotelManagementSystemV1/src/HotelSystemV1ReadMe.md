#HotelSystemV1 Readme

**Class: HotelSystemV1**

**Instance Variables used:**

1. url - String to represent url of database

2. username - String to represent username for log in

3. password - String to represent password for log in

4. connection - Connection used to connect to SQL database

5. statement - statement created by connection to SQL database

6. DATABASE_NAME - constant String used to represent database name of hotel

7. update - prepared statement used to update sql database 

8. select - prepared statement used to make select query to database

9. occupiedRoomCount - int representing total number of occupied rooms in hotel

Constructor parameters: String url, String username, String password

**Methods:** 

1. public boolean bookGuest(String guestName, int numberOfGuests, int roomNumber) 
	
	-- books a new guest into hotel; returns true if success

2. public boolean checkoutGuest(int roomNumber) 
	
	-- checks out guest from hotel; returns true if success

3. public String getOccupiedRooms()
	
	-- returns String containing list of occupied rooms

4. public String getVacantRooms() 
	
	-- returns String containing list of vacant rooms

5. public boolean checkRoomStatus(int roomNumber) 
	
	-- checks if roomNumber parameter is occupied 
	
6. public int getOccupiedRoomCount()
	
	-- returns total number of occupied rooms in hotel
	
	
**Things to work on:**
1. exception handling:
	- catch invalid roomNumber input
	
2. Should not be able to bookGuest if the room is occupied
3. Should not be able to checkOutGuest if room is vacant

