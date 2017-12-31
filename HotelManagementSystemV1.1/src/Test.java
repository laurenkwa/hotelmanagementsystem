import java.sql.*;
import java.util.Scanner;


/**
 * Test.
 *
 * @author Lauren Kwa
 * @version 2017
 */
public class Test {

    /**
     * Drives the program.
     * @param args unused
     */
    public static void main(String[] args) {
        try (
                // Allocate a database 'Connection' object
                Connection conn = DriverManager.getConnection(
                      "jdbc:mysql://localhost:3306/testhotel", "root", "password");
                      // MySQL: "jdbc:mysql://hostname:port/databaseName", "username", "password"

                // Allocate a 'Statement' object in the Connection
                Statement stmt = conn.createStatement();
             ) {
            
                //create Scanner object to allow user input to book new guest
                Scanner scanner = new Scanner(System.in);
                System.out.println("Enter Room Number: ");
                int roomNumberInput = scanner.nextInt();
                scanner.nextLine();
                System.out.println("Enter Guest Name:");
                String guestNameInput = scanner.nextLine();
                System.out.println("Enter number of guests: ");
                int numberOfGuestsInput = scanner.nextInt();
                
                
                //Execute a UPDATE Query to book new guest
            
               System.out.println("Booking in new guest, " + guestNameInput 
                       + ", in room " + roomNumberInput + " with " 
                       + numberOfGuestsInput + " number of guests.");
               
               PreparedStatement update = conn.prepareStatement("UPDATE log SET status = ?,"
                       + "guestName = ?, numberOfGuests = ? WHERE roomNumber = ?");
               
               update.setBoolean(1, true); //set status to occupied
               update.setString(2, guestNameInput); //set guestName
               update.setInt(3, numberOfGuestsInput); //set number of guests 
               update.setInt(4, roomNumberInput); //set roomNumber
               
               update.executeUpdate();
               
               
               //Execute a UPDATE Query to check out a guest
               System.out.println("Checking out guest... " + guestNameInput 
                       + ", in room " + roomNumberInput + " with " 
                       + numberOfGuestsInput + " number of guests.");
               
               update = conn.prepareStatement("UPDATE log SET status = ?,"
                       + "guestName = ?, numberOfGuests = ? WHERE roomNumber = ?");
               
               update.setBoolean(1, false); //set status to occupied
               update.setString(2, ""); //set guestName
               update.setInt(3, 0); //set number of guests 
               update.setInt(4, roomNumberInput); //set roomNumber
               
               update.executeUpdate();
           
            
                // Execute a SQL SELECT query
                // Selects rooms which are occupied
                String strSelect = "select roomNumber, roomType, status, guestName, numberOfGuests from log";
                System.out.println("The SQL query is: " + strSelect); // Echo For debugging
                System.out.println();

                ResultSet rset = stmt.executeQuery(strSelect);

                // Process the ResultSet by scrolling the cursor forward via next().
                //  For each row, retrieve the contents of the cells with getXxx(columnName).
                System.out.println("The records selected are:");
                int rowCount = 0;
                while(rset.next()) {  
                   int roomNumber = rset.getInt("roomNumber");
                   String roomType = rset.getString("roomType");
                   boolean status   = rset.getBoolean("status");
                   String guestName = rset.getString("guestName");
                   int numberOfGuests = rset.getInt("numberOfGuests");
                   System.out.println(roomNumber + ", " + roomType + ", " 
                           + status + ", " + guestName + ", " + numberOfGuests);
                   ++rowCount;
                }
                System.out.println("Total number of records = " + rowCount);

             } catch(SQLException ex) {
                ex.printStackTrace();
             }
             // Close the resources - Done automatically by try-with-resources
          }
      }




