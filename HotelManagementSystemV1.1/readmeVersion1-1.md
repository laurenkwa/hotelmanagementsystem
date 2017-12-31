Added the following changes from previous Hotel Management Java class Version 1.0:

1. Created a new method: "private boolean checkroomExists(int roomNumber)" which will check if a certain room number is found in the DB.

  - this method is used in bookGuest() and checkoutGuest() to validate that the roomNumber parameter is a valid room number in hotel.

2. Exception Handling

  a. In the method: *bookGuest()* 
  
    - before booking a new guest, we validate whether the room number exists, the number of guests are less than or equal to the max occupancy allowed, and that the room is vacant.

b. In the method: *checkoutGuest()*

    - validate whether the room number exists and the room is occupied
    
    
