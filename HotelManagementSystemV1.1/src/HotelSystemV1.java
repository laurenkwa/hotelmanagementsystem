import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * HotelSystemV1.
 *
 * @author Lauren Kwa
 * @version 2017
 */
public class HotelSystemV1 {
    
    /**
     * Represents url of database connection.
     */
    private String url;
    
    /**
     * Represents username to log in to database.
     */
    private String username;
    
    /**
     * Reprsents password to log in to database.
     */
    private String password;
    
    /**
     * Establishes connection to Mysql.
     */
    private Connection connection;
    
    /**
     * Creates statement to be executed in the sql database.
     */
    private Statement statement;
    
    /**
     * Represents constant of database name used in hotel.
     */
    public static final String DATABASE_NAME = "log";
    
    /**
     * Represents prepared statement used to update 
     *  SQL database for booking new guests 
     *  and checking out guests.
     */
    private PreparedStatement update;
    
    /**
     * Represents prepared statement used to make a 
     *  select query to the SQL database.
     */
    private PreparedStatement select;
    
    /**
     * Represents total rooms occupied in hotel.
     */
    private int occupiedRoomCount;
    
    /**
     * 
     * Constructs the object of type HotelSystemV1.
     * @param url
     * @param username
     * @param password
     */
    public HotelSystemV1(String url, String username, String password) {
        this.url = url;
        this.username = username;
        this.password = password;
        
        
        try {
            
            // establish connection to sql database 
            connection = DriverManager.getConnection(url, username, password);
            
            // create statement using connection
            statement = connection.createStatement();
            
            System.out.println("Successful log in.");
            
            // create prepared statement for updating database
            update = connection.prepareStatement("UPDATE " + DATABASE_NAME 
                    + " SET status = ?,"
                    + "guestName = ?, numberOfGuests = ? WHERE roomNumber = ?");
            
            select = connection.prepareStatement("select * from " + DATABASE_NAME + " where status = ?");
            
            
        } catch (SQLException e) {
            System.out.println("Something went wrong! Unable to log in.");
            e.printStackTrace();
        }     
    }
    
    /**
     * Books new guest into hotel.
     * @param guestName
     * @param numberOfGuests
     * @param roomNumber
     * @return
     */
    public boolean bookGuest(String guestName, int numberOfGuests, int roomNumber) {
        boolean success;
 
        try {
            
            //validate that roomNumber exists in database
            if(!this.checkRoomExists(roomNumber)) {
                throw new IllegalArgumentException("Room Number does not exist!");
            }
            
            //validate that number of guests is not more than max occupancy
            //and that the room is not already booked
            PreparedStatement validation = connection.prepareStatement("select * from " + DATABASE_NAME + " where roomNumber = ?");
            validation.setInt(1, roomNumber);
            ResultSet rset = validation.executeQuery();
            
            rset.next(); 
            int maxOccupancy = rset.getInt("maxOccupancy");

            if(numberOfGuests > maxOccupancy) {
                throw new IllegalArgumentException("Number of Guests is more than max occupancy for type of room.");
            } else if (this.checkRoomStatus(roomNumber)){
                throw new IllegalArgumentException("Room is already occupied.");
            } else {
                
                // Use the prepared statement created in constructor to set parameters
                update.setBoolean(1, true); //set status to occupied
                update.setString(2, guestName); //set guestName
                update.setInt(3, numberOfGuests); //set number of guests 
                update.setInt(4, roomNumber); //set roomNumber
                
                update.executeUpdate();
                update.clearParameters();
                
                success = true; 
            }
            
            
        } catch (SQLException e) {
            
            success = false; 
            System.out.println("Unable to book guest.");
            e.printStackTrace();
            
        }
        
        return success;     
    }
    
    /**
     * Checks out guest from a specific room number.
     * @param roomNumber
     * @return
     */
    public boolean checkoutGuest(int roomNumber) {
        
        boolean success;
        
        try {
            
            //validate that room number exists in database
            if(!this.checkRoomExists(roomNumber)) {
                throw new IllegalArgumentException("Room Number does not exist!");
            }
            
            //validate that the room is occupied
            if (this.checkRoomStatus(roomNumber)) {
                
                // Use the prepared statement created in constructor to set parameters 
                update.setBoolean(1, false); //set status to occupied
                update.setString(2, null); //change guestname to null
                update.setInt(3, 0); // change number of guests to 0
                update.setInt(4, roomNumber); //set roomNumber
                
                update.executeUpdate();
                
                success = true;
              
               
            } else {
                throw new IllegalArgumentException("Room is vacant. No guest to check out.");
            }

            
        } catch (SQLException e) {
            
            success = false;
            System.out.println("Unable to checkout guest.");
        }
        
        return success;
  
    }
    
    /**
     * Returns a list of currently occupied rooms in the hotel.
     * @return resultString
     */
    public String getOccupiedRooms() {
        
        occupiedRoomCount = 0;
        
        String resultString="";
        
        try {
            
            
            // Use the prepared statement created in constructor to set parameters 
            select.setBoolean(1, true);
            
            //execute select query 
            ResultSet rset = select.executeQuery();
            
            //loop through resultset from query and get values to add to the resultString
            while(rset.next()) {  
               int roomNumber = rset.getInt("roomNumber");
               String roomType = rset.getString("roomType");
               boolean status   = rset.getBoolean("status");
               String guestName = rset.getString("guestName");
               int numberOfGuests = rset.getInt("numberOfGuests");
               resultString += "\n\nRoom Number: " + roomNumber + "\nRoom Type: " 
                       + roomType + "\nOccupied: " + status + "\nGuest Name: " 
                       + guestName + "\nNumber of Guests: " + numberOfGuests;
               occupiedRoomCount++;
            }
            
        } catch (SQLException e) {
            
            resultString += "Unable to get occupied rooms list.";
            
        }
        
        return resultString;
        
        
    }
    
    /**
     * Returns a list of vacant rooms in hotel.
     * @return resultString
     */
    public String getVacantRooms() {
        
        String resultString="";
        
       
        
        try {
            
         // Use the prepared statement created in constructor to set parameters 
            select.setBoolean(1, false);
            ResultSet rset = select.executeQuery();
            
            
            while(rset.next()) {  
                int roomNumber = rset.getInt("roomNumber");
                String roomType = rset.getString("roomType");
                boolean status   = rset.getBoolean("status");
                String guestName = rset.getString("guestName");
                int numberOfGuests = rset.getInt("numberOfGuests");
                resultString += "\n\nRoom Number: " + roomNumber + "\nRoom Type: " 
                        + roomType + "\nOccupied: " + status + "\nGuest Name: " 
                        + guestName + "\nNumber of Guests: " + numberOfGuests;
               
            }
            
        } catch (SQLException e) {
            
            resultString += "Unable to process request.";
            
        }
        
        return resultString;
        
        
    }
    
    /**
     * Checks the status of a specific room number.
     * @param roomNumber
     * @return occupied a boolean representing if occupied 
     */
    public boolean checkRoomStatus(int roomNumber) {
        boolean occupied = false;
        
        try {
            //use prepared statement "select" to select all rooms with occupied status
            select.setBoolean(1, true);
            
            ResultSet rset = select.executeQuery();
            
            //loop through resultset and check if the roomNumber is 
            //part of occupied resultset
            while(rset.next() && occupied == false) {
                
                int room = rset.getInt("roomNumber");
                if (roomNumber == room) {
                    occupied = true;
                }
                
            }
            
        } catch (SQLException e) {
            System.out.println("Unable to check room status.");
        }
        
        return occupied;
        
    }
    
    /**
     * Returns the number of occupied rooms in hotel.
     * @return occupiedRoomCount 
     */
    public int getOccupiedRoomCount() {
        
        getOccupiedRooms();
        
        return occupiedRoomCount;
    }
    
    /**
     * Checks if the room number exists in the database. 
     * @param roomNumber
     * @return exist a boolean
     */
    private boolean checkRoomExists(int roomNumber) {
        boolean exist = false;
        
        try {
            PreparedStatement validation = connection.prepareStatement("select * from " + DATABASE_NAME);
            
            ResultSet rset = validation.executeQuery();
            
            //loop through resultset and check if the roomNumber is 
            //part of resultset
            while(rset.next() && exist == false) {
                
                int room = rset.getInt("roomNumber");
                if (roomNumber == room) {
                    exist = true;
                    return exist;
                }
                
            }  
                
            
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
        return exist;   
    }
    
    
    
}